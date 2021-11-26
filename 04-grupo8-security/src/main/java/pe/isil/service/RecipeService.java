package pe.isil.service;


import org.springframework.stereotype.Service;
import pe.isil.model.Recipe;
import pe.isil.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService implements  BaseService<Recipe,String > {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public Optional<Recipe> findById(String id) {
        return recipeRepository.findById(id);
    }

    @Override
    public Optional<List<Recipe>> findAll() {
        return Optional.of(recipeRepository.findAll());
    }

    @Override
    public Recipe saveOrUpdate(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public boolean deleteById(String id) {
        return findById(id).map(
                recipe -> {
                    recipeRepository.delete(recipe);
                    return true;
                }
        ).orElse(false);
    }
}
