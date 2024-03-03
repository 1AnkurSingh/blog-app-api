package blogappapi.dto;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreationResponse {
    private int id;
    private String name;
    private String email;
    private String imageName;
    private String password;
    private String status;
    private String about;



}
