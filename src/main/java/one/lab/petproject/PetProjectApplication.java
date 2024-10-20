package one.lab.petproject;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.lab.petproject.enums.TransactionType;
import one.lab.petproject.model.Transactions;
import one.lab.petproject.model.Users;
import one.lab.petproject.service.TransactionService;
import one.lab.petproject.service.UserService;
import org.aspectj.lang.annotation.Pointcut;
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
        userService.showAllUsers();
        var user1 = Users.builder()
                .id(2L)
                .username("Ask")
                .password("qwe123")
                .email("test2@gmail.com")
                .createdAt(LocalDateTime.now())
                .categories(null)
                .transactionsList(null)
                .build();
        userService.addUser(user1);


        var transaction1 = Transactions.builder()
                .id(2L)
                .amount(3500)
                .date(LocalDateTime.now())
                .description("Payment for I'm")
                .transactionType(TransactionType.EXPENSE)
                .user(null)
                .category(null)
                .build();

        userService.showAllUsers();
        userService.addTransactionToUser(user1, transaction1);


        showTransactionsOfUser(user1);
    }

    public void showTransactionsOfUser(Users user){
        if (user.getTransactionsList() != null) {
            user.getTransactionsList().forEach(transaction ->
                    log.info("Transaction: {}", transaction)
            );
        } else {
            log.info("No transactions found for user: {}", user.getUsername());
        }
    }
}
