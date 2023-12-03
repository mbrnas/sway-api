package com.company.blogplatform;
import com.company.blogplatform.model.role.Role;
import com.company.blogplatform.model.users.User;
import com.company.blogplatform.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class BlogPlatformApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogPlatformApplication.class, args);
    }

    public void run(String... args) {
        User adminAccount = userRepository.findByRole(Role.ADMIN);
        if (null == adminAccount) {
            User user = new User();
            user.setUsername("admin");
            user.setFirstName("Matija");
            user.setLastName("Brnas");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }
}
