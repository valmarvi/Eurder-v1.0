package com.switchfully.order.service.order;

import com.switchfully.order.domain.models.order.ItemGroup;
import com.switchfully.order.domain.models.order.Order;
import com.switchfully.order.domain.models.user.Customer;
import com.switchfully.order.domain.repositories.order.ItemGroupRepository;
import com.switchfully.order.domain.repositories.order.OrderRepository;
import com.switchfully.order.domain.repositories.user.CustomerRepository;
import com.switchfully.order.service.support.dto.order.OrderDTO;
import com.switchfully.order.service.support.dto.order.OrderReportDTO;
import com.switchfully.order.service.support.dto.order.OrdersByShippingDateMapper;
import com.switchfully.order.service.support.dto.user.CustomerDTO;
import com.switchfully.order.service.support.mapper.order.OrderMapper;
import com.switchfully.order.service.support.mapper.user.CustomerMapper;
import com.switchfully.order.service.support.wrapper.OrderDTOWrapper;
import com.switchfully.order.service.support.wrapper.OrderWrapper;
import com.switchfully.order.service.user.SecurityService;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemGroupRepository itemGroupRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final OrdersByShippingDateMapper ordersByShippingDateMapper;
    private final SecurityService securityService;

    public OrderService(OrderRepository orderRepository, ItemGroupRepository itemGroupRepository,
                        OrderMapper orderMapper, CustomerRepository customerRepository, CustomerMapper customerMapper,
                        OrdersByShippingDateMapper ordersByShippingDateMapper, SecurityService securityService) {
        this.orderRepository = orderRepository;
        this.itemGroupRepository = itemGroupRepository;
        this.orderMapper = orderMapper;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.ordersByShippingDateMapper = ordersByShippingDateMapper;
        this.securityService = securityService;
    }

    public OrderDTOWrapper createOrder(OrderWrapper orderWrapper) {
        List<ItemGroup> itemGroupList = unwrapOrderWrapperAndCreateItemGroups(orderWrapper);
        Order order = orderRepository.createOrder(orderWrapper.getCustomerId(), itemGroupList);
        String customerId = orderWrapper.getCustomerId();
        Customer customer = customerRepository.getCustomerById(customerId).get();
        return new OrderDTOWrapper(customerMapper.toCustomerDTO(customer), orderMapper.toOrderDTO(order));
    }

    public OrderReportDTO getOrdersByCustomer(String customerId) {
        List<Order> ordersByCustomer = orderRepository.getOrdersByCustomer(customerId);
        List<OrderDTO> orderDTOList = orderMapper.toOrderListDTO(ordersByCustomer);
        return new OrderReportDTO(orderDTOList, getTotalPrice(orderDTOList));
    }

    public Map<CustomerDTO, List<OrderDTO>> getOrderByShippingDate(LocalDate shippingDate) {
        Map<Customer, List<Order>> orderByShippingDate = orderRepository.getOrderByShippingDate(shippingDate);
        return ordersByShippingDateMapper.toGetOrderByShippingDateDTO(orderByShippingDate);
    }

    private Double getTotalPrice(List<OrderDTO> orderDTOList) {
               return orderDTOList
                .stream()
                .map(OrderDTO::getTotalPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private List<ItemGroup> unwrapOrderWrapperAndCreateItemGroups(OrderWrapper orderWrapper) {
        return orderWrapper.getItemGroupListDTO().stream()
                .map(itemGroupDTO -> itemGroupRepository
                                .createItemGroup(itemGroupDTO.getItemId(), itemGroupDTO.getAmount()))
                .toList();
    }

    public OrderDTOWrapper reorderOrder(String orderId, String authorization) throws AuthenticationException {
        Map<Customer, Order> customerOrderEntry = orderRepository.getCustomerOrderEntryByOrderId(orderId);
        Customer customer = customerOrderEntry.keySet().stream().findFirst().get();
        securityService.authenticateUser(customer.getId(), authorization);
        Order order = customerOrderEntry.values().stream().findFirst().get();
        List<ItemGroup> itemGroupList = order.getItemGroupList();
        List<ItemGroup> itemGroupListWithUpdatedPrice = itemGroupList
                .stream()
                .map(itemGroup -> itemGroupRepository.createItemGroup(itemGroup.getItemId(), itemGroup.getAmount()))
                .toList();
        Order updatedOrder = orderRepository.createOrder(customer.getId(), itemGroupListWithUpdatedPrice);
        return new OrderDTOWrapper(customerMapper.toCustomerDTO(customer), orderMapper.toOrderDTO(updatedOrder));
    }
}
