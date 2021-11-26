package pe.isil.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {

}
