package one.lab.petproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import one.lab.petproject.enums.TransactionType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Transactions {
    private long id;
    private int amount;
    private LocalDateTime date;
    private String description;
    private TransactionType transactionType;
    private Category category;
    private Users user;

}
