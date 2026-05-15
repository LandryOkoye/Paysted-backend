package landry.paysted.services.user;

import landry.paysted.dtos.CreateUserRequest;
import landry.paysted.dtos.UserDto;

public interface UserService {

    UserDto createUser(CreateUserRequest request);

    UserDto getUserByName(String name);
}
