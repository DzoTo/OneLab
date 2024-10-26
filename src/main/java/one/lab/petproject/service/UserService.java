package one.lab.petproject.service;

import one.lab.petproject.model.Transactions;
import one.lab.petproject.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<Users> getUserById(long id);
    void addUser(Users user);
    List<Users> getAllUsers();
    void showAllUsers();
    void addTransactionToUser(Users user, Transactions transaction);

    void updateUser(Long id, String newUsername, String newPassword);
    void deactivateUser(Long id);
}
