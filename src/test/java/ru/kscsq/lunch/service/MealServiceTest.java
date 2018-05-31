package ru.kscsq.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kscsq.lunch.model.Meal;
import ru.kscsq.lunch.util.exception.NotFoundException;

import static ru.kscsq.lunch.MealTestData.*;

public class MealServiceTest extends AbstractServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(RESTAURANT1_MEAL_ID, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID, getDate()), RESTAURANT1_MEAL3, RESTAURANT1_MEAL2);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(RESTAURANT1_MEAL_ID, 1);
    }

    @Test
    public void testSave() throws Exception {
        Meal created = getCreated();
        service.save(created, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID, getDate()), RESTAURANT1_MEAL3, RESTAURANT1_MEAL2, RESTAURANT1_MEAL1, created);
    }

    @Test
    public void testGet() throws Exception {
        System.out.println(RESTAURANT3_MEAL3);
        Meal actual = service.get(RESTAURANT3_MEAL_ID+2, RESTAURANT3_ID);
        System.out.println(actual);
        assertMatch(actual, RESTAURANT3_MEAL3);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(RESTAURANT3_MEAL_ID + 2, RESTAURANT2_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        assertMatch(service.get(updated.getId(), RESTAURANT1_ID), updated);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.update(RESTAURANT1_MEAL1, RESTAURANT1_MEAL_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        assertMatch(service.getAll(RESTAURANT1_ID, getDate()), RESTAURANT1_MEALS);
    }
}
