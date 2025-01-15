package kz.tenko.machine.coffee.service;

import kz.tenko.machine.coffee.dto.RecipeDTO;
import kz.tenko.machine.coffee.model.Recipe;
import kz.tenko.machine.coffee.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeService {

    private RecipeRepository recipeRepository;

    public Recipe addRecipe(RecipeDTO recipeDTO) {

        var recipe = new Recipe();
        recipe.setName(recipeDTO.getName());
        return recipeRepository.save(recipe);

    }
}
