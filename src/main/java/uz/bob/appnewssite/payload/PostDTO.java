package uz.bob.appnewssite.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotBlank
    private String url;


}
