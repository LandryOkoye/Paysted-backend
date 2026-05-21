package landry.paysted.services.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotBlank;
import landry.paysted.dtos.CreateUserRequest;
import landry.paysted.dtos.UserDto;
import landry.paysted.model.User;
import landry.paysted.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(CreateUserRequest request) {
    
        if(request.name() == null || request.email() == null){
            throw new IllegalArgumentException("name and email cannot be empty");
        }
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());

        userRepository.save(user);
        System.out.println("User created: " + user.getName() + " with email: " + user.getEmail());

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());

    
        return userDto;
    }

    @Override
    public UserDto getUserByName(String name) {
        assert name != null : "field name cannot ben empty"; // This works just during development, it does not work in production.
        if(name == null){
            throw new IllegalArgumentException("field name cannot ben empty");
        }
        return userRepository.findByName(name).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    @Override
    public UserDto getUserByEmail(@NotBlank String email) {
        if(email == null){
            throw new IllegalStateException("field email cannot be empty");
        }
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("User with email does not exists"));
    }

    

}
