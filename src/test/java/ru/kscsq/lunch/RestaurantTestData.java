package ru.kscsq.lunch;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.kscsq.lunch.model.Restaurant;
import ru.kscsq.lunch.to.RestaurantTo;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.kscsq.lunch.model.BaseEntity.START_SEQ;
import static ru.kscsq.lunch.web.json.JsonUtil.writeIgnoreProps;

public class RestaurantTestData {

    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int RESTAURANT2_ID = START_SEQ + 3;
    public static final int RESTAURANT3_ID = START_SEQ + 4;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Restaurant 1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "Restaurant 2");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT3_ID, "Restaurant 3");

    public static final RestaurantTo RESTAURANT1_TO = new RestaurantTo(RESTAURANT1_ID, "Restaurant 1", 0);
    public static final RestaurantTo RESTAURANT2_TO = new RestaurantTo(RESTAURANT2_ID, "Restaurant 2", 0);
    public static final RestaurantTo RESTAURANT3_TO = new RestaurantTo(RESTAURANT3_ID, "Restaurant 3", 0);

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "votes", "meals");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "votes", "meals").isEqualTo(expected);
    }

    public static void assertMatchTo(Iterable<RestaurantTo> actual, RestaurantTo... expected) {
        assertMatchTo(actual, Arrays.asList(expected));
    }

    public static void assertMatchTo(Iterable<RestaurantTo> actual, Iterable<RestaurantTo> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "votes", "meals").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Restaurant... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "registered"));
    }

    public static ResultMatcher contentJson(Restaurant expected) {
        return content().json(writeIgnoreProps(expected, "registered"));
    }

    public static ResultMatcher contentJson(RestaurantTo... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "registered"));
    }

    public static ResultMatcher contentJson(RestaurantTo expected) {
        return content().json(writeIgnoreProps(expected, "registered"));
    }
}
