package one.lab.petproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class Users {
    private long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;

    private List<Transactions> transactionsList;
    private List<Category> categories;

    public void addTransaction(Transactions transaction) {
        if(transactionsList == null) {
            transactionsList = new ArrayList<>();
        }
        transactionsList.add(transaction);
    }
}
