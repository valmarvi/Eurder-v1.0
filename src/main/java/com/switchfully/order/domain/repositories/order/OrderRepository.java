package com.switchfully.order.domain.repositories.order;

import com.switchfully.order.domain.models.user.Customer;
import com.switchfully.order.domain.models.order.ItemGroup;
import com.switchfully.order.domain.models.order.Order;
import com.switchfully.order.domain.repositories.user.CustomerRepository;
import com.switchfully.order.service.support.wrapper.OrderWrapper;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    private final CustomerRepository customerRepository;
    private final Map<Customer, List<Order>> orderDatabase;

    public OrderRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        orderDatabase = new HashMap<>();
    }

    public Order createOrder(String customerId, List<ItemGroup> itemGroupList) {
        Optional<Customer> customer = customerRepository.getCustomerById(customerId);
        validateCustomer(customer);
        Order order = new Order(itemGroupList);
        addOrderToDatabase(customer.get(), order);
        return order;
    }

    public List<Order> getOrdersByCustomer(String customerId) {
        return orderDatabase
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getId().equals(customerId))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No Orders found for the provided customer"));
    }

    public Map<Customer, List<Order>> getOrderByShippingDate(LocalDate shippingDate) {
        return orderDatabase.entrySet()
                .stream()
                .map(entry -> {
                    Customer customer = entry.getKey();
                    List<Order> orders = entry.getValue();
                    List<Order> filteredOrders = orders.stream()
                            .filter(order -> order.getItemGroupList().stream()
                                    .anyMatch(itemGroup -> itemGroup.getShippingDate().equals(shippingDate)))
                            .toList();
                    return Map.entry(customer, filteredOrders);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    private void addOrderToDatabase(Customer customer, Order order) {
        if (checkIfCustomerHasNoOrders(customer)) {
            List<Order> orderList = new ArrayList<>();
            orderList.add(order);
            orderDatabase.put(customer, orderList);
            return;
        }

        List<Order> orderList = orderDatabase
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getId().equals(customer.getId()))
                .map(Map.Entry::getValue)
                .findFirst().get();

        orderList.add(order);
    }

    private boolean checkIfCustomerHasNoOrders(Customer customer) {
        List<Order> list = orderDatabase.get(customer);
        return list == null;
    }

    private void validateCustomer(Optional<Customer> customer) {
        if (customer.isEmpty()) {
            throw new NotFoundException("No Customer found with the specified ID");
        }
    }

    public Map<Customer, Order> getCustomerOrderEntryByOrderId(String orderId) {
        return orderDatabase
                .entrySet()
                .stream()
                .map(entry -> {
                    Customer customer = entry.getKey();
                    List<Order> orders = entry.getValue();
                    Order filteredOrders = orders
                            .stream()
                            .filter(order -> order.getId().equals(orderId))
                            .findFirst()
                            .orElseThrow(() -> new NotFoundException("No Order found with the Requested Id"));
                    return Map.entry(customer, filteredOrders);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
