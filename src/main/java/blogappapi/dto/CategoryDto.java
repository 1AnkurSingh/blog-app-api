package blogappapi.dto;

import lombok.*;

@Data
@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer categoryId;
    private String categoryTitle;
    private String categoryDescription;
}
