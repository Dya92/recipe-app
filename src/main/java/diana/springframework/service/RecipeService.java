package diana.springframework.service;

import diana.springframework.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long l);
}
