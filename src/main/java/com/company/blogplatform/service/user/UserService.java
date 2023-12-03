package com.company.blogplatform.service.user;

import com.company.blogplatform.exception.UserNotFoundException;
import com.company.blogplatform.model.users.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Page<User> getAllUsers(Integer pageNumber, Integer pageSize, String sort);

    User getUserById(Long id) throws UserNotFoundException;

    User addUser(User user);

    User updateUser(User user, Long userId) throws UserNotFoundException;

    void deleteUser(Long id);
}
