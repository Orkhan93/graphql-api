package az.spring.graphql.service;

import az.spring.graphql.exception.UserNotFoundException;
import az.spring.graphql.model.User;
import az.spring.graphql.repository.UserRepository;
import az.spring.graphql.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    public User createUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setMail(userRequest.getMail());
        user.setRole(userRequest.getRole());
        return userRepository.save(user);
    }

    public User updateUser(UserRequest userRequest) {
        User existing = getUserById(userRequest.getId());
        existing.setMail(userRequest.getMail());
        existing.setRole(userRequest.getRole());
        existing.setUsername(userRequest.getUsername());
        return userRepository.save(existing);
    }

    public void deleteUser(Long id) {
        User foundUser = getUserById(id);
        userRepository.delete(foundUser);
    }

}