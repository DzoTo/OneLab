package one.lab.petproject.repository.impl;

import one.lab.petproject.model.Transactions;
import one.lab.petproject.repository.TransactionsRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionsRepositoryImpl implements TransactionsRepository {
    private final List<Transactions> transactionsDatabase = new ArrayList<>();

    @Override
    public Transactions getTransactionById(long id) {
        return transactionsDatabase.stream()
                .filter(transaction -> transaction.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveTransaction(Transactions transaction) {
        transactionsDatabase.add(transaction);
    }

    @Override
    public List<Transactions> getAllTransactions() {
        return transactionsDatabase;
    }
}
