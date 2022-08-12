package uz.bob.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.bob.appnewssite.entity.enums.Permission;
import uz.bob.appnewssite.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbsEntity {

    //Column annotatsiyasi keyincalik qoyildi RoleRepository ocilganidan keyin
    @Column(unique = true,nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permission> permissionList;

}
