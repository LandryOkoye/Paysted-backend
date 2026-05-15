package landry.paysted.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import landry.paysted.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
