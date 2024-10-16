package one.lab.petproject;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.lab.petproject.enums.TransactionType;
import one.lab.petproject.model.Transactions;
import one.lab.petproject.model.Users;
import one.lab.petproject.service.TransactionService;
import one.lab.petproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Data
@Slf4j
public class PetProjectApplication implements CommandLineRunner {
    private final UserService userService;
    private final TransactionService transactionService;

    public static void main(String[] args) {
        SpringApplication.run(PetProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var user1 = Users.builder()
                .id(1L)
                .username("DzoTo")
                .password("qwe123")
                .email("test@gmail.com")
                .createdAt(LocalDateTime.now())
                .categories(null)
                .transactionsList(null)
                .build();
        var transaction1 = Transactions.builder()
                .id(1L)
                .amount(500)
                .date(LocalDateTime.now())
                .description("Payment for ice cream")
                .transactionType(TransactionType.EXPENSE)
                .user(null)
                .category(null)
                .build();
        userService.addUser(user1);
        log.info("User added: {}", user1);
        transactionService.saveTransaction(transaction1);
        log.info("Transaction added: {}", transaction1);

        userService.addTransactionToUser(1L, transaction1);
        var updatedUser = userService.getUserById(1L);
        log.info("The transactions of user: {}", updatedUser.getTransactionsList());
    }
}
