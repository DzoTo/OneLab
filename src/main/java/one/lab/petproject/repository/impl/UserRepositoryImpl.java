package one.lab.petproject.repository.impl;

import lombok.RequiredArgsConstructor;
import one.lab.petproject.model.Users;
import one.lab.petproject.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {


    private final JdbcTemplate jdbcTemplate;

    @Override
    public Users getUserById(long id) {
        String sql = "select * from users where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRow(rs), id);
    }

    @Override
    public void saveUser(Users user) {
        String sql = "INSERT INTO users (username, password, email, created_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getCreatedAt());
    }

    @Override
    public List<Users> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> Users.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build());
    }

    private Users mapRow(ResultSet rs) throws SQLException {
        return Users.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();
    }

}
