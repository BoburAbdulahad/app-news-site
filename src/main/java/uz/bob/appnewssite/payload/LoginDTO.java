package uz.bob.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "username must be not null")
    private String username;

    @NotBlank(message = "password must be not null")
    private String password;

}
