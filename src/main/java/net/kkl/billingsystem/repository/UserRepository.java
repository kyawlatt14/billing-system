package net.kkl.billingsystem.repository;

import net.kkl.billingsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    User findByUsername(String username);
    Optional<User> findByUsernameIgnoreCaseLike(String username);
}
