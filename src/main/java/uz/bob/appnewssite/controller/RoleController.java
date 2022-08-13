package uz.bob.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.RoleDTO;
import uz.bob.appnewssite.service.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDTO roleDTO){
        ApiResponse apiResponse=roleService.addRole(roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);

    }
}
