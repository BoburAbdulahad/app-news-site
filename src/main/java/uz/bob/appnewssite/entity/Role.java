package uz.bob.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.bob.appnewssite.entity.enums.Permission;
import uz.bob.appnewssite.entity.template.AbsEntity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbsEntity {

    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permission> permissionList;

}
