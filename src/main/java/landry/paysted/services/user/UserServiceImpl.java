package landry.paysted.services.user;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotBlank;
import landry.paysted.dtos.CreateUserRequest;
import landry.paysted.dtos.UserDto;
import landry.paysted.exceptions.ResourceNotFoundException;
import landry.paysted.model.User;
import landry.paysted.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {


    private final ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    UserServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

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
        };

        User user = userRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("user with name " + name + "does not exist"));
        return modelMapper.map(user, UserDto.class);
        
    }

    @Override
    public UserDto getUserByEmail(@NotBlank String email) {
        if(email == null){
            throw new IllegalStateException("field email cannot be empty");
        }
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("User with email does not exists"));
        return modelMapper.map(user, UserDto.class);
    }

    

}
