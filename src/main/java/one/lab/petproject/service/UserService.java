package one.lab.petproject.service;

import one.lab.petproject.model.Transactions;
import one.lab.petproject.model.Users;

import java.util.List;

public interface UserService {
    Users getUserById(long id);
    void addUser(Users user);
    List<Users> getAllUsers();
    void showAllUsers();
    void addTransactionToUser(Users user, Transactions transaction);
}
