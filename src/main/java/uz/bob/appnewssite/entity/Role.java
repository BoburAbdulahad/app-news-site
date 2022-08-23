package uz.bob.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.bob.appnewssite.entity.enums.Permission;
import uz.bob.appnewssite.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role extends AbsEntity {

    //Column annotatsiyasi keyincalik qoyildi RoleRepository ocilganidan keyin
    @Column(unique = true,nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permission> permissionList;

    @Column(columnDefinition = "text",length = 600)
    private String description;


}
