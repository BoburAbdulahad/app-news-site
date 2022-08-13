package uz.bob.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.bob.appnewssite.entity.enums.Permission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    @NotBlank // agar probel kelib qolsa ham hisobga olmaydi
    private String name;

    private String description;

    @NotEmpty
    private List<Permission> permissionList;

}
