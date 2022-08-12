package uz.bob.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.appnewssite.entity.Role;
import uz.bob.appnewssite.entity.User;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.RegisterDTO;
import uz.bob.appnewssite.repository.RoleRepository;
import uz.bob.appnewssite.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse registerUser(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ApiResponse("This type username already exist!",false);
        }
        User user=new User(
                registerDTO.getFullName(),
                registerDTO.getUsername(),
                null,

        );
        return null;
    }
}
