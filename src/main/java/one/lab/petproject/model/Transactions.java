package one.lab.petproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import one.lab.petproject.enums.TransactionType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Transactions {
    private long id;
    private double amount;
    private LocalDateTime date;
    private String description;
    private TransactionType transactionType;
    private Category category;
    private Users user;

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", transactionType=" + transactionType +
                '}';
    }
}
