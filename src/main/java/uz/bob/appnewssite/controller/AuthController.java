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
import uz.bob.appnewssite.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO){
        ApiResponse apiResponse=authService.registerUser(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


}
