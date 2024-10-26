package one.lab.petproject.model;

import jakarta.persistence.*;
import lombok.*;
import one.lab.petproject.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Transactions> transactionsList;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Category> categories;

    public void addTransaction(Transactions transaction) {
        if(transactionsList == null) {
            transactionsList = new ArrayList<>();
        }
        transactionsList.add(transaction);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", transactionsList=" + transactionsList +
                ", categories=" + categories +
                '}';
    }
}
