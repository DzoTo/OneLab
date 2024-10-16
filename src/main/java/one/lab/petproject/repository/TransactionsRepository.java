package one.lab.petproject.repository;

import one.lab.petproject.model.Transactions;

import java.util.List;

public interface TransactionsRepository {
    Transactions getTransactionById(long id);
    void saveTransaction(Transactions transaction);
    List<Transactions> getAllTransactions();
}
