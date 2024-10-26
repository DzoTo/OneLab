package one.lab.petproject.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.lab.petproject.dto.IncomeOutcome;
import one.lab.petproject.enums.Status;
import one.lab.petproject.enums.TransactionType;
import one.lab.petproject.model.Transactions;
import one.lab.petproject.model.Users;
import one.lab.petproject.repository.TransactionsRepository;
import one.lab.petproject.repository.UserRepository;
import one.lab.petproject.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TransactionsRepository transactionsRepository;
    @Override
    public Optional<Users> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void addUser(Users user) {
        userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addTransactionToUser(Users user, Transactions transaction) {
        if(user != null) {
            transaction.setUser(user);

            transactionsRepository.save(transaction);

            user.addTransaction(transaction);
        }
    }

    @Override
    @Transactional
    public void updateUser(Long id, String newUsername, String newPassword) {
        var user1 = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("User not found with id: " + id));
        user1.setUsername(newUsername);
        user1.setPassword(newPassword);
        userRepository.save(user1);
        log.info("Updated user details: {}", user1);
    }

    @Override
    @Transactional
    public void deactivateUser(Long id) {
        var user = userRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("User not found with id: " + id));
        user.setStatus(Status.DEACTIVATED);
        userRepository.save(user);
        log.info("User deactivated: {}", user);
    }

    public void showAllUsers(){
        List<Users> allUsers = getAllUsers();
        allUsers.forEach(user -> log.info("User: {}", user));
    }

    @Transactional(readOnly = true)
    public IncomeOutcome getTotalIncomeAndExpense(long userId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Transactions> transactions = transactionsRepository.findAllByUserIdAndDateBetween(userId, startDate, endDate);

        double totalIncome = transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.INCOME)
                .mapToDouble(Transactions::getAmount).sum();

        double totalExpense = transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.EXPENSE)
                .mapToDouble(Transactions::getAmount).sum();

        return IncomeOutcome.builder()
                .income(totalIncome)
                .outcome(totalExpense)
                .build();
    }
}
