package ru.kscsq.lunch.repository;

import ru.kscsq.lunch.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {
    // null if updated meal do not belong to restaurantId
    Meal save(Meal meal, int restaurantId);

    // false if meal do not belong to restaurantId
    boolean delete(int id, int restaurantId);

    // null if meal do not belong to restaurantId
    Meal get(int id, int restaurantId);

    // ORDERED dateTime
    List<Meal> getAll(int restaurantId, LocalDate date);
}
