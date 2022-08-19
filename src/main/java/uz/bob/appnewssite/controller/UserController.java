package uz.bob.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.bob.appnewssite.entity.Role;
import uz.bob.appnewssite.entity.User;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.RegisterDTO;
import uz.bob.appnewssite.payload.UserDTO;
import uz.bob.appnewssite.service.AuthService;
import uz.bob.appnewssite.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAuthority('VIEW_USER')")
    @GetMapping
    public HttpEntity<?> view(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size){
        List<User> userList = userService.view(page, size);
        return new HttpEntity<>(userList);
//        return ResponseEntity.ok(roleList);
//        return ResponseEntity.status(200).body(roleList);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_USER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable Long id){
        User user=userService.getUser(id);
        return ResponseEntity.status(user!=null? HttpStatus.OK:HttpStatus.NOT_FOUND).body(user);
    }

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping
    public HttpEntity<?> addUser(@Valid @RequestBody UserDTO userDTO) {
        ApiResponse apiResponse = userService.adduser(userDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable Long id,
            @Valid @RequestBody UserDTO userDTO) {
        ApiResponse apiResponse = userService.editUser(id,userDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        boolean delete = userService.delete(id);
        return delete?ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }




}
