package kz.tenko.machine.coffee.repository;

import kz.tenko.machine.coffee.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(value = "SELECT recipe_id FROM orders GROUP BY recipe_id ORDER BY COUNT(recipe_id) DESK LIMIT 1", nativeQuery = true)
    Long findMostPopularRecipe();

}
