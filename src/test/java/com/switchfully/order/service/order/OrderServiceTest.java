package com.switchfully.order.service.order;

import com.switchfully.order.domain.models.order.Item;
import com.switchfully.order.domain.models.order.ItemGroup;
import com.switchfully.order.domain.models.order.Order;
import com.switchfully.order.domain.models.user.Address;
import com.switchfully.order.domain.models.user.Customer;
import com.switchfully.order.domain.repositories.order.ItemGroupRepository;
import com.switchfully.order.domain.repositories.order.OrderRepository;
import com.switchfully.order.domain.repositories.user.CustomerRepository;
import com.switchfully.order.service.support.dto.order.ItemDTO;
import com.switchfully.order.service.support.dto.order.ItemGroupDTO;
import com.switchfully.order.service.support.dto.order.OrderDTO;
import com.switchfully.order.service.support.dto.user.CreateCustomerDTO;
import com.switchfully.order.service.support.dto.user.CustomerDTO;
import com.switchfully.order.service.support.mapper.order.OrderMapper;
import com.switchfully.order.service.support.mapper.user.CustomerMapper;
import com.switchfully.order.service.support.wrapper.OrderDTOWrapper;
import com.switchfully.order.service.support.wrapper.OrderWrapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    OrderRepository orderRepositoryMock;
    ItemGroupRepository itemGroupRepositoryMock;
    CustomerRepository customerRepositoryMock;
    OrderMapper orderMapperMock;
    CustomerMapper customerMapperMock;
    OrderService orderService;

    Customer customer = new Customer.CustomerBuilder()
            .withFirstName("Roger")
            .withLastName("Mark")
            .withAddress(new Address("Loud Lane", "50", "1000", "Brussels"))
            .withEmail("r.marck@gmail.com")
            .withPhoneNumber("0678952214")
            .build();

    Item item = new Item("Test Item",
            "Item to Test", 50, 10);

    ItemGroup itemGroup = new ItemGroup(item.getId(), item.getName(), 5, item.getStockAmount(), item.getPrice());
    ItemGroupDTO itemGroupDTO = new ItemGroupDTO(item.getId(), item.getName(), 5, item.getPrice(), item.getPrice(), LocalDate.now());
    OrderWrapper orderWrapper = new OrderWrapper(customer.getId(), List.of(itemGroupDTO));
    List<ItemGroup> itemGroupList = List.of(itemGroup);
    List<ItemGroupDTO> itemGroupDTOList = List.of(itemGroupDTO);
    String customerId = orderWrapper.getCustomerId();

    CustomerDTO customerDTO = new CustomerDTO.CustomerDTOBuilder()
            .withId(customer.getId())
            .withFirstName(customer.getFirstName())
            .withLastName(customer.getLastName())
            .withAddress(customer.getAddress())
            .withEmail(customer.getEmail())
            .withPhoneNumber(customer.getPhoneNumber())
            .build();

    Order order = new Order(itemGroupList);

    OrderDTO orderDTO = new OrderDTO(order.getId(), itemGroupDTOList);

    @BeforeEach
    void setupOrderService() {
        orderRepositoryMock = Mockito.mock(OrderRepository.class);
        itemGroupRepositoryMock = Mockito.mock(ItemGroupRepository.class);
        customerRepositoryMock = Mockito.mock(CustomerRepository.class);
        orderMapperMock = Mockito.mock(OrderMapper.class);
        customerMapperMock = Mockito.mock(CustomerMapper.class);
        orderService = new OrderService(orderRepositoryMock, itemGroupRepositoryMock, orderMapperMock, customerRepositoryMock, customerMapperMock);
    }
    @Test
    void createOrder() {
        //Given
        Mockito.when(itemGroupRepositoryMock.createItemGroup(itemGroupDTO.getItemId(), itemGroupDTO.getAmount()))
                .thenReturn(itemGroup);

        Mockito.when(orderRepositoryMock.createOrder(orderWrapper.getCustomerId(), itemGroupList))
                .thenReturn(order);

        Mockito.when(customerRepositoryMock.getCustomerById(customerId))
                .thenReturn(Optional.ofNullable(customer));

        Mockito.when(customerMapperMock.toCustomerDTO(customer))
                .thenReturn(customerDTO);

        Mockito.when(orderMapperMock.toOrderDTO(order))
                .thenReturn(orderDTO);

        //When
        OrderDTOWrapper orderWrapperToCheck = orderService.createOrder(orderWrapper);

        //Then
        Assertions.assertThat(orderWrapper.getCustomerId()).isEqualTo(orderWrapperToCheck.getCustomerDTO().getId());
    }
}