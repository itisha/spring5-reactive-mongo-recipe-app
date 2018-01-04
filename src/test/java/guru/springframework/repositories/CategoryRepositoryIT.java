package guru.springframework.repositories;

import guru.springframework.domain.Category;
import guru.springframework.repositories.reactive.CategoryReactiveRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Test
    public void test() {
        String description = "descr_1";
        Category category = new Category();
        category.setDescription(description);

        categoryReactiveRepository.save(category).block();

        Category read = categoryReactiveRepository.findAll().blockFirst();

        Assert.assertEquals(description, read.getDescription());
    }
}
