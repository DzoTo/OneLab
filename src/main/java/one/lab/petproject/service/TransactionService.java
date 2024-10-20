package one.lab.petproject.service;

import one.lab.petproject.model.Transactions;

import java.util.List;

public interface TransactionService {
    Transactions getTransactionById(Long id);
    void saveTransaction(Transactions transaction);
    List<Transactions> getAllTransactions();
}
