package ru.kscsq.lunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.kscsq.lunch.model.Meal;
import ru.kscsq.lunch.repository.MealRepository;
import ru.kscsq.lunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.kscsq.lunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(mealRepository.get(id, restaurantId), id);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(mealRepository.delete(id, restaurantId), id);
    }

    @Override
    public List<Meal> getAll(int restaurantId, LocalDate date) {
        return mealRepository.getAll(restaurantId, date);
    }

    @Override
    public Meal update(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        return checkNotFoundWithId(mealRepository.save(meal, restaurantId), meal.getId());
    }

    @Override
    public Meal save(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        return mealRepository.save(meal, restaurantId);
    }
}
