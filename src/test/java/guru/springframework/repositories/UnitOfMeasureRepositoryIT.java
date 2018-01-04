package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by jt on 6/17/17.
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void findByDescription() {
        String description = "Teaspoon";
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(description);

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        Flux<UnitOfMeasure> uomFlux = unitOfMeasureReactiveRepository.findByDescription(description);
        assertEquals(description, uomFlux.blockFirst().getDescription());

        Flux<UnitOfMeasure> uomFlux2 = unitOfMeasureReactiveRepository.findByDescription(description + "xxx");
        assertNull(uomFlux2.blockFirst());
    }

}