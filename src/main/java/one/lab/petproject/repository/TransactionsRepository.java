package one.lab.petproject.repository;

import one.lab.petproject.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    Transactions getById(long id);
    List<Transactions> findAllByUserIdAndDateBetween(long userId, LocalDateTime from, LocalDateTime  to);
//    void saveTransaction(Transactions transaction);
//    List<Transactions> getAllTransactions();
}
