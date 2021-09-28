package com.webapp.resetPassword.repository;

import com.webapp.resetPassword.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("Testy");
        user.setEmail("test.changePassword@gmail.com");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode("123456Abc"));

        User savedUser = repo.save(user);

        User existedUser = entityManager.find(User.class, savedUser.getUserId());

        assertThat(user.getEmail()).isEqualTo(existedUser.getEmail());

    }

    @Test
    public void testFindByEmail() {
        String email = "reset.password@gmail.com";
        User user = repo.findByEmail(email);

        assertThat(user.getEmail()).isEqualTo(email);
    }
}
