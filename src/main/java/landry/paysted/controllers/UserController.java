package landry.paysted.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import landry.paysted.dtos.ApiResponse;
import landry.paysted.dtos.CreateUserRequest;
import landry.paysted.dtos.UserDto;
import landry.paysted.services.user.UserService;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService; 


    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request){
        try {
            if(request == null){
                return ResponseEntity.status(404).body(new ApiResponse("request body cannot be empty", null));
            };
            UserDto userDto = userService.createUser(request);
            return ResponseEntity.ok(new ApiResponse("user created successfully", userDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("internal server error. message: "+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable String username){
        try {
            UserDto user = userService.getUserByName(username);
            return ResponseEntity.ok(new ApiResponse("success", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ApiResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED));
        }
    };


}
