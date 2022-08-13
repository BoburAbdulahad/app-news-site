package uz.bob.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.bob.appnewssite.entity.Role;
import uz.bob.appnewssite.entity.User;
import uz.bob.appnewssite.exceptions.ResourceNotFoundException;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.RegisterDTO;
import uz.bob.appnewssite.repository.RoleRepository;
import uz.bob.appnewssite.repository.UserRepository;
import uz.bob.appnewssite.utils.AppConstants;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public ApiResponse registerUser(RegisterDTO registerDTO) {
        if(!registerDTO.getPassword().equals(registerDTO.getPrePassword()))
            return new ApiResponse("Password and pre password not equal",false);

        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ApiResponse("This type username already exist!",false);
        }

        User user=new User(
                registerDTO.getFullName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
               roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("role","name",AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("Registration successfully available!",true);
    }


}
