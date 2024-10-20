package one.lab.petproject.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.lab.petproject.model.Transactions;
import one.lab.petproject.model.Users;
import one.lab.petproject.repository.TransactionsRepository;
import one.lab.petproject.repository.UserRepository;
import one.lab.petproject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TransactionsRepository transactionsRepository;
    @Override
    public Users getUserById(long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void addUser(Users user) {
        userRepository.saveUser(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void addTransactionToUser(Users user, Transactions transaction) {
        if(user != null) {
            transaction.setUser(user);

            transactionsRepository.saveTransaction(transaction);

            user.addTransaction(transaction);
        }
    }

    public void showAllUsers(){
        List<Users> allUsers = getAllUsers();
        allUsers.forEach(user -> log.info("User: {}", user));
    }

}
