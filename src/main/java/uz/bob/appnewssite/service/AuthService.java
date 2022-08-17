package uz.bob.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.bob.appnewssite.entity.Role;
import uz.bob.appnewssite.entity.User;
import uz.bob.appnewssite.exceptions.ResourceNotFoundException;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.LoginDTO;
import uz.bob.appnewssite.payload.RegisterDTO;
import uz.bob.appnewssite.repository.RoleRepository;
import uz.bob.appnewssite.repository.UserRepository;
import uz.bob.appnewssite.security.JwtProvider;
import uz.bob.appnewssite.utils.AppConstants;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

//    @Autowired
//    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//        this.jwtProvider = jwtProvider;
//    }




    public ApiResponse registerUser(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getPrePassword()))
            return new ApiResponse("Password and pre password not equal", false);

        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ApiResponse("This type username already exist!", false);
        }

        User user = new User(
                registerDTO.getFullName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("role", "name", AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("Registration successfully available!", true);
    }


    public ApiResponse loginUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()
        ));
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();
        Set<Role> roles= Collections.singleton(role);

        String token = jwtProvider.generateToken(user.getUsername(),  role);
        return new ApiResponse("Get a token:",true,token);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//        if (optionalUser.isPresent())
//            return optionalUser.get();
//        throw new UsernameNotFoundException("Username not found");
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

    }
}
