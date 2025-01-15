package kz.tenko.machine.coffee.controller;

import kz.tenko.machine.coffee.dto.OrderDTO;
import kz.tenko.machine.coffee.dto.RecipeDTO;
import kz.tenko.machine.coffee.model.Recipe;
import kz.tenko.machine.coffee.service.OrderService;
import kz.tenko.machine.coffee.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CoffeeMachineController {

    private OrderService orderService;
    private RecipeService recipeService;

    @GetMapping("statistics/popular")
    public ResponseEntity<Long> popularRecipe() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findPopularRecipe());
    }

    @PostMapping("/recipe")
    public ResponseEntity<Recipe> addRecipe(@RequestBody RecipeDTO recipeDTO) {
        var savedRecipe = recipeService.addRecipe(recipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
    }

    @PostMapping("/order")
    public ResponseEntity<Object> addOrder(@RequestBody OrderDTO orderDTO) {
        orderService.addOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
