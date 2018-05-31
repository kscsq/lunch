package ru.kscsq.lunch.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;
import ru.kscsq.lunch.MealTestData;
import ru.kscsq.lunch.model.Restaurant;
import ru.kscsq.lunch.to.RestaurantTo;
import ru.kscsq.lunch.util.exception.NotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static ru.kscsq.lunch.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void testSave() throws Exception {
        Restaurant newRestaurant = new Restaurant("New");
        Restaurant created = service.save(newRestaurant);
        newRestaurant.setId(created.getId());
        assertMatch(service.getAll(), newRestaurant, RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateNameSave() throws Exception {
        service.save(new Restaurant(null, "Restaurant 3"));
    }

    @Test
    public void testGet() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetAllVotesByDate() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2018-05-06");

        List<RestaurantTo> all = service.getAllWithVotesByDate(date);
        RESTAURANT2_TO.setVote(1);
        assertMatchTo(all, RESTAURANT1_TO, RESTAURANT2_TO, RESTAURANT3_TO);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Restaurant> all = service.getAll();
        System.out.println("actual " + all.toString());
        Assert.notNull(all, "restaurants must not be null");
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT2);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(service.get(RESTAURANT2_ID), updated);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), RESTAURANT2, RESTAURANT3);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGetAllWithMealsByDate() throws Exception {
        List<Restaurant> all = service.getAllWithMealsByDate(MealTestData.getDate());
        Assert.notNull(all, "restaurants must not be null");
        System.out.println("actual " + all.toString());
    }
}
