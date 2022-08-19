package uz.bob.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "fullName is null")
    private String fullName;

    @NotNull(message = "username is null")
    private String username;

    @NotNull(message = "password is null")
    private String password;

    @NotNull(message = "roleId is null")
    private Long roleId;
}
