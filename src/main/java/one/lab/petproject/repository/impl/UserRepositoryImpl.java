package one.lab.petproject.repository.impl;

import one.lab.petproject.model.Users;
import one.lab.petproject.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<Users> users = new ArrayList<>();
//Users.builder()
//        .id(1L)
//                    .username("user1")
//                    .password("password1")
//                    .email("test1@gmail.com")
//                    .createdAt(LocalDateTime.now())
//            .build(),
//            Users.builder()
//                    .id(2L)
//                    .username("user2")
//                    .password("password2")
//                    .email("test2@gmail.com")
//                    .createdAt(LocalDateTime.now())
//            .build(),
//            Users.builder()
//                    .id(3L)
//                    .username("user3")
//                    .password("password3")
//                    .email("test3@gmail.com")
//                    .createdAt(LocalDateTime.now())
//            .build()
    @Override
    public Users getUserById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveUser(Users user) {
        users.add(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return users;
    }
}
