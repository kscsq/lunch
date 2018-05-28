package ru.kscsq.lunch.service;

import ru.kscsq.lunch.model.Vote;
import ru.kscsq.lunch.util.exception.NotFoundException;

import java.util.Date;

public interface VoteService {

    Vote get(Date date, int userId) throws NotFoundException;

    Vote vote(int restaurantId, int userId);
}
