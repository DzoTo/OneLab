package one.lab.petproject.service.impl;

import one.lab.petproject.dto.IncomeOutcome;
import one.lab.petproject.enums.Status;
import one.lab.petproject.enums.TransactionType;
import one.lab.petproject.model.Transactions;
import one.lab.petproject.model.Users;
import one.lab.petproject.repository.TransactionsRepository;
import one.lab.petproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionsRepository transactionsRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private Users user;
    private Transactions transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = Users.builder()
                .id(1L)
                .username("Ask")
                .password("qwe123")
                .email("test2@gmail.com")
                .createdAt(LocalDateTime.now())
                .status(Status.ACTIVATED)
                .build();


        transaction = Transactions.builder()
                .id(1L)
                .amount(3500)
                .date(LocalDateTime.now())
                .description("Payment for I'm")
                .transactionType(TransactionType.EXPENSE)
                .user(user)
                .build();

    }

    @Test
    void getUserById() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        Optional<Users> foundUserOptional  = userServiceImpl.getUserById(user.getId());
        Users foundUser = foundUserOptional.orElse(null); // or handle it in another way based on your requirements

        assertEquals(user, foundUser);
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void addUser() {
        userServiceImpl.addUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void addTransactionToUser() {
        userServiceImpl.addTransactionToUser(user, transaction);

        verify(transactionsRepository, times(1)).save(transaction);
        assertEquals(transaction, user.getTransactionsList().get(0));
    }

    @Test
    void updateUser(){
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userServiceImpl.updateUser(user.getId(), "newUsername", "newPassword");

        assertEquals("newUsername", user.getUsername());
        assertEquals("newPassword", user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deactivateUser() {

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userServiceImpl.deactivateUser(user.getId());

        assertEquals(Status.DEACTIVATED, user.getStatus());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getTotalIncomeAndExpense() {
        Transactions incomeTransaction = Transactions.builder()
                .amount(5000)
                .transactionType(TransactionType.INCOME)
                .user(user)
                .date(LocalDateTime.now())
                .build();

        when(transactionsRepository.findAllByUserIdAndDateBetween(
                user.getId(),
                LocalDateTime.MIN,
                LocalDateTime.MAX
        )).thenReturn(List.of(transaction, incomeTransaction));

        IncomeOutcome incomeOutcome = userServiceImpl.getTotalIncomeAndExpense(
                user.getId(),
                LocalDateTime.MIN,
                LocalDateTime.MAX
        );

        assertEquals(5000, incomeOutcome.getIncome());
        assertEquals(3500, incomeOutcome.getOutcome());
    }
}