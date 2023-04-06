package az.spring.graphql.controller;

import az.spring.graphql.model.User;
import az.spring.graphql.request.UserRequest;
import az.spring.graphql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @QueryMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public User getUserById(@Argument Long id) {
        return userService.getUserById(id);
    }

    @MutationMapping
    public User createUser(@Argument UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @MutationMapping
    public User updateUser(@Argument UserRequest userRequest) {
        return userService.updateUser(userRequest);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        userService.deleteUser(id);
        return true;
    }

}