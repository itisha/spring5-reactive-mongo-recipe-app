package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeRepositoryIT {

    @Autowired
    private RecipeReactiveRepository recipeReactiveRepository;

    @Test
    public void test() {
        String description = "Description_1";
        Recipe recipe = new Recipe();
        recipe.setDescription(description);

        Assert.assertEquals(0L, (long) recipeReactiveRepository.count().block());

        recipeReactiveRepository.save(recipe).block();

        Recipe read = recipeReactiveRepository.findAll().toStream().findFirst().get();

        Assert.assertEquals(1L, (long) recipeReactiveRepository.count().block());
        Assert.assertEquals(description, read.getDescription());
    }
}
