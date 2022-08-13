package uz.bob.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.RegisterDTO;
import uz.bob.appnewssite.payload.UserDTO;
import uz.bob.appnewssite.service.AuthService;
import uz.bob.appnewssite.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        ApiResponse apiResponse = userService.adduser(userDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
