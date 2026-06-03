package landry.paysted.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import landry.paysted.dtos.UserDto;
import landry.paysted.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    
};