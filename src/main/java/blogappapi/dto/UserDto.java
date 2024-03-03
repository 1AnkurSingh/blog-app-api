package blogappapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private int id;
    @NotEmpty
    @NotBlank
    @Size(min = 4,message = "User must be min of 4 character")
    private String name;
    @Email(message = "Enter Valid email Address")
    @NotEmpty
    private String email;
    private String imageName;
    @NotNull
    private String password;
    @NotNull
    @NotEmpty
    private String about;

    private MultipartFile imageFile;


}
