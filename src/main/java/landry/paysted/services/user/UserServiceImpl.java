package landry.paysted.services.user;

import org.springframework.beans.factory.annotation.Autowired;

import landry.paysted.dtos.CreateUserRequest;
import landry.paysted.dtos.UserDto;
import landry.paysted.model.User;
import landry.paysted.repository.UserRepository;

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
        return null;
    }

}
