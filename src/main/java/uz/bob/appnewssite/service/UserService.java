package uz.bob.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.bob.appnewssite.entity.Role;
import uz.bob.appnewssite.entity.User;
import uz.bob.appnewssite.exceptions.ResourceNotFoundException;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.UserDTO;
import uz.bob.appnewssite.repository.RoleRepository;
import uz.bob.appnewssite.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse adduser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return new ApiResponse("Username already exist",false);
        }
        User user=new User(
                userDTO.getFullName(),
                userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()),
                roleRepository.findById(userDTO.getRoleId()).orElseThrow(() ->
                        new ResourceNotFoundException("roleTable","roleName",userDTO.getRoleId())),
                true
        );
        userRepository.save(user);
        return new ApiResponse("User added by admin",true);
    }


    public List<User> view(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent();
    }


    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
//        return userRepository.findById(id).orElse(null);
    }


    public ApiResponse editUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("User not found",false);
        if (roleRepository.existsByNameAndIdNot(userDTO.getUsername(), id)) {
            return new ApiResponse("User with username and with id already exist",false);
        }
        User user = optionalUser.get();
        user.setFullName(userDTO.getFullName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(roleRepository.findById(userDTO.getRoleId()).orElseThrow(() ->
                new ResourceNotFoundException("roleTable","roleName",userDTO.getRoleId())));

        User editedUser = userRepository.save(user);
        return new ApiResponse("User successfully edited",true,editedUser);
    }


    public boolean delete(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
