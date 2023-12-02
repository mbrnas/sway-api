package com.company.blogplatform.service.user;

import com.company.blogplatform.exception.UserNotFoundException;
import com.company.blogplatform.model.users.User;
import com.company.blogplatform.repository.users.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getAllUsers(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return userRepository.findAll(pageable);
    }

    public User getUserById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user id");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public User addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Invalid user data");
        }
        return userRepository.save(user);
    }

    public User updateUser(User user, Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User by id " + userId + " was not found"));
        if (existingUser == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user data");
        } else {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        }
    }


    public void deleteUser(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user id");
        }
        userRepository.deleteById(id);
    }
}