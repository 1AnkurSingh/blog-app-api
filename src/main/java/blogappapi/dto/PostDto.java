package blogappapi.dto;

import blogappapi.model.Category;
import blogappapi.model.Comment;
import blogappapi.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private int postId;
    private String title;


    private String content;

    private String imageName;

    private Date addedDate;


    private CategoryDto category;


    private UserDto user;

    private List<CommentDto> comments = new ArrayList<>();
}