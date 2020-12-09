package services;

import models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    Optional<User> getUser(User user);
    void deleteUser(User user);
    void deleteUsers();
    void putUser(User user);
    void updateUser(User oldUser, User newUser);
}
