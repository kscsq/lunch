package ru.kscsq.lunch.service;

import ru.kscsq.lunch.model.User;
import ru.kscsq.lunch.to.UserTo;
import ru.kscsq.lunch.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(User user);

    void update(UserTo user);
}
