package uz.bob.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.appnewssite.entity.Role;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.RoleDTO;
import uz.bob.appnewssite.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public ApiResponse addRole(RoleDTO roleDTO) {
        if (roleRepository.existsByName(roleDTO.getName())) {
            return new ApiResponse("Role with name "+roleDTO.getName()+" already exist",false);
        }

        Role role=new Role(
                roleDTO.getName(),
                roleDTO.getPermissionList(),
                roleDTO.getDescription()
        );
        roleRepository.save(role);
        return new ApiResponse("Role has been created",true);
    }



}
