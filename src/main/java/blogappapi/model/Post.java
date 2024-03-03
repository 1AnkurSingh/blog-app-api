package blogappapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int postId;

    @Column(name="post_title",length = 100,nullable = false)
    private  String title;


    @Column(nullable = false,length = 10000)
    private String content;

    private String imageName;

    private Date addedDate;

    @ManyToOne
 //   @JoinColumn(name = "categoryName")
    private Category category;

    @ManyToOne
    private User user;
    @OneToMany(mappedBy ="post",cascade = CascadeType.ALL)
    private List<Comment>comments=new ArrayList<>();


}
