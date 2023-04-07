package blogappapi.Service;

import blogappapi.dto.PostDto;
import blogappapi.dto.PostResponse;
import blogappapi.model.Post;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, int id);

    //delete
    void deletePost(int id);

    //get All post
    List<PostDto>getAllPost();

    // Get All PostBy pagination
    PostResponse getAllPost1(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get post by id
    PostDto getPostById(Integer id);

    // get all Category
    List<PostDto>getPostByCategory(int categoryId);

    // get All post by user
    List<PostDto>getPostByUser(int userId);

    // Search post
    List<PostDto>searchPost(String Keyword);


}
