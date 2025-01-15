package kz.tenko.machine.coffee.service;


import kz.tenko.machine.coffee.dto.OrderDTO;
import kz.tenko.machine.coffee.exception.WorkingHoursException;
import kz.tenko.machine.coffee.model.Order;
import kz.tenko.machine.coffee.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private OperationalService operationalService;

    public Long findPopularRecipe() {

        return orderRepository.findMostPopularRecipe();

    }

    public void addOrder(OrderDTO orderDTO) {

        if (!operationalService.isWorkingHours()) {

            throw new WorkingHoursException("Not working hours");

        }

        Order order = new Order();
        order.setRecipeId(orderDTO.getRecipeId());
        order.setDate(LocalDateTime.now());
        orderRepository.save(order);

    }
}
