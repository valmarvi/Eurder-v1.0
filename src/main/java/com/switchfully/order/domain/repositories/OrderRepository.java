package com.switchfully.order.domain.repositories;

import com.switchfully.order.domain.models.users.Customer;
import com.switchfully.order.domain.models.ItemGroup;
import com.switchfully.order.domain.models.Order;
import com.switchfully.order.exception.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    private final CustomerRepository customerRepository;
    private final Map<Customer, List<Order>> orderDatabase;

    public OrderRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        orderDatabase = new HashMap<>();
    }

    public void createOrder(String customerId, List<ItemGroup> itemGroupList) {
        Optional<Customer> customer = customerRepository.getCustomerById(customerId);
        validateCustomer(customer);
        Order order = new Order(itemGroupList);
        addOrderToDatabase(customer.get(), order);
    }

    private void addOrderToDatabase(Customer customer, Order order) {
        if (checkIfCustomerHasNoOrders(customer)) {
            List<Order> orderList = new ArrayList<>();
            orderList.add(order);
            orderDatabase.put(customer, orderList);
            return;
        }

        List<Order> orderList = orderDatabase.entrySet().stream()
                .filter(entry -> entry.getKey().getId().equals(customer.getId()))
                .map(Map.Entry::getValue)
                .findFirst().get();
        orderList.add(order);
    }

    private boolean checkIfCustomerHasNoOrders(Customer customer) {
        List<Order> list = orderDatabase.get(customer);
        return list.isEmpty();
    }

    private void validateCustomer(Optional<Customer> customer) {
        if (customer.isEmpty()) {
            throw new ItemNotFoundException("No Customer found with the specified ID");
        }
    }
}
