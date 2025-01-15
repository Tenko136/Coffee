package kz.tenko.machine.coffee.repository;

import kz.tenko.machine.coffee.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {


}
