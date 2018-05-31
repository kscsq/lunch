package ru.kscsq.lunch;

import ru.kscsq.lunch.model.Meal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static ru.kscsq.lunch.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int RESTAURANT2_ID = START_SEQ + 3;
    public static final int RESTAURANT3_ID = START_SEQ + 4;

    public static final int RESTAURANT1_MEAL_ID = START_SEQ + 5;
    public static final int RESTAURANT2_MEAL_ID = START_SEQ + 8;
    public static final int RESTAURANT3_MEAL_ID = START_SEQ + 11;

    public static final Meal RESTAURANT1_MEAL1 = new Meal(RESTAURANT1_MEAL_ID, "first course 1", new BigDecimal("500.00"), getDate(), RESTAURANT1_ID);
    public static final Meal RESTAURANT1_MEAL2 = new Meal(RESTAURANT1_MEAL_ID + 1, "garnish 1", new BigDecimal("300.00"), getDate(), RESTAURANT1_ID);
    public static final Meal RESTAURANT1_MEAL3 = new Meal(RESTAURANT1_MEAL_ID + 2, "second course 1", new BigDecimal("700.00"), getDate(), RESTAURANT1_ID);
    public static final Meal RESTAURANT2_MEAL1 = new Meal(RESTAURANT2_MEAL_ID, "first course 2", new BigDecimal("500.00"), getDate(), RESTAURANT2_ID);
    public static final Meal RESTAURANT2_MEAL2 = new Meal(RESTAURANT2_MEAL_ID + 1, "garnish 2", new BigDecimal("100.00"), getDate(), RESTAURANT2_ID);
    public static final Meal RESTAURANT2_MEAL3 = new Meal(RESTAURANT2_MEAL_ID + 2, "second course 2", new BigDecimal("500.00"), getDate(), RESTAURANT2_ID);
    public static final Meal RESTAURANT3_MEAL1 = new Meal(RESTAURANT3_MEAL_ID, "first course 3", new BigDecimal("200.00"), LocalDate.of(2018,5,7), RESTAURANT3_ID);
    public static final Meal RESTAURANT3_MEAL2 = new Meal(RESTAURANT3_MEAL_ID + 1, "garnish 3", new BigDecimal("1000.00"), LocalDate.of(2018,5,7), RESTAURANT3_ID);
    public static final Meal RESTAURANT3_MEAL3 = new Meal(RESTAURANT3_MEAL_ID + 2, "second course 3", new BigDecimal("300.00"), LocalDate.of(2018,5,7), RESTAURANT3_ID);

    public static final List<Meal> RESTAURANT1_MEALS = Arrays.asList(RESTAURANT1_MEAL3, RESTAURANT1_MEAL2, RESTAURANT1_MEAL1);

    public static LocalDate getDate() {
        return LocalDate.of(2018,5,5);
    }

    public static Meal getCreated() {
        return new Meal(null, "created first course", new BigDecimal("300.00"), getDate(), RESTAURANT1_ID);
    }

    public static Meal getUpdated() {
        return new Meal(RESTAURANT1_MEAL_ID, "updated first course ", new BigDecimal("200.00"), RESTAURANT1_MEAL1.getDate(), RESTAURANT1_ID);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
