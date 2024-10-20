package one.lab.petproject.repository;

import one.lab.petproject.model.Users;

import java.util.List;


public interface UserRepository {
    Users getUserById(long id);

    void saveUser(Users user);

    List<Users> getAllUsers();
}
