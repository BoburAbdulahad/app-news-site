package uz.bob.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.UserDTO;
import uz.bob.appnewssite.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ApiResponse adduser(UserDTO userDTO) {

        return null;
    }
}
