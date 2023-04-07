package blogappapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDto {

    private int id;
    @NotEmpty
    @NotBlank
    @Size(min = 4,message = "User must be min of 4 character")
    private String name;
    @Email(message = "Enter Valid email Address")
    @NotEmpty
   // @Pattern(regexp = "@")
    private String email;
    @NotNull
//    @NotEmpty
//    @Size(min = 3,max = 10)
   // @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$\"",message = "must use *a1")
    private String password;
    @NotNull
    @NotEmpty
    private String about;
}
