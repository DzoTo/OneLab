package one.lab.petproject.repository.impl;

import lombok.RequiredArgsConstructor;
import one.lab.petproject.enums.TransactionType;
import one.lab.petproject.model.Transactions;
import one.lab.petproject.repository.TransactionsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionsRepositoryImpl implements TransactionsRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Transactions getTransactionById(long id) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRow(rs), id);
    }

    @Override
    public void saveTransaction(Transactions transaction) {
        String sql = "INSERT INTO transactions (amount, date, description, transaction_type, user_id, category_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, transaction.getAmount(), transaction.getDate(),
                transaction.getDescription(), transaction.getTransactionType().toString(),
                transaction.getUser().getId(), transaction.getCategory() != null ? transaction.getCategory().getId() : null);
    }

    @Override
    public List<Transactions> getAllTransactions() {
        String sql = "SELECT * FROM transactions";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs));
    }

    private Transactions mapRow(ResultSet rs) throws SQLException {
        return Transactions.builder()
                .id(rs.getLong("id"))
                .amount(rs.getDouble("amount"))
                .date(rs.getTimestamp("date").toLocalDateTime())
                .description(rs.getString("description"))
                .transactionType(TransactionType.valueOf(rs.getString("transaction_type")))
                .user(null) // You'll need to load the associated user here if needed
                .category(null) // Same for category if needed
                .build();
    }
}
