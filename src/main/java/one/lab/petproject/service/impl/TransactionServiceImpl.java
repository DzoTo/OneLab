package one.lab.petproject.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import one.lab.petproject.model.Transactions;
import one.lab.petproject.repository.TransactionsRepository;
import one.lab.petproject.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionsRepository transactionsRepository;
    @Override
    public Transactions getTransactionById(Long id) {
        return transactionsRepository.getTransactionById(id);
    }

    @Override
    public void saveTransaction(Transactions transaction) {
        transactionsRepository.saveTransaction(transaction);
    }

    @Override
    public List<Transactions> getAllTransactions() {
        return transactionsRepository.getAllTransactions();
    }
}
