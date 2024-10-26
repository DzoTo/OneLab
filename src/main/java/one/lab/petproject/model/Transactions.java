package one.lab.petproject.model;

import jakarta.persistence.*;
import lombok.*;
import one.lab.petproject.enums.TransactionType;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double amount;
    private LocalDateTime date;
    private String description;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name ="user_id")
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
