package uz.bob.appnewssite.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.bob.appnewssite.entity.Role;
import uz.bob.appnewssite.entity.User;
import uz.bob.appnewssite.entity.enums.Permission;
//import uz.bob.appnewssite.entity.enums.Permission.*; mana shu orqali hammasini caqirib oliw mumkin
import uz.bob.appnewssite.repository.RoleRepository;
import uz.bob.appnewssite.repository.UserRepository;
import uz.bob.appnewssite.utils.AppConstants;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialMode;


    @Override
    public void run(String... args) {
        if (initialMode.equals("ALWAYS")) {
            Permission[] permissions = Permission.values();
            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(permissions),
                    "Owner System"
            ));

            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(Permission.ADD_COMMENT, Permission.EDIT_COMMENT, Permission.DELETE_MY_COMMENT),
                    "Simple user"
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));

            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));
        }


    }
}
