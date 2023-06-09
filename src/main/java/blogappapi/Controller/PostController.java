package blogappapi.Controller;

import blogappapi.Service.FileService;
import blogappapi.Service.FileServiceImpl;
import blogappapi.Service.PostService;
import blogappapi.config.AppConstants;
import blogappapi.dto.ApiResponse;
import blogappapi.dto.ImageResponse;
import blogappapi.dto.PostDto;
import blogappapi.dto.PostResponse;
import blogappapi.model.Post;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
       PostDto createPost= postService.createPost(postDto,userId,categoryId);
       return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    // grt by user
    @GetMapping("/getPostByUser/{userId}/posts")
    public ResponseEntity<List<PostDto>>getPostByUser(@PathVariable Integer userId){
        List<PostDto>posts=this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    // get by category
    @GetMapping("/getPostByCategory/{categoryId}/posts")
    public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto>posts=this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    // getAll Post
    @GetMapping("/post")
    public ResponseEntity<List<PostDto>>getAllPost(){
        List<PostDto>allPost=this.postService.getAllPost();
        return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
    }

    // Gat All Post By pagination
    @GetMapping("/postWithPagination")
    public ResponseEntity<PostResponse>getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir
    ){


        PostResponse postResponse = this.postService.getAllPost1(pageNumber, pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

    }

    // get post bY id
    @GetMapping("/getPostById/{id}")
    public ResponseEntity<PostDto>getPostById(@PathVariable Integer id){
            PostDto postDto=postService.getPostById(id);
            return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/deleteById/{id}")
    public ApiResponse deletePost(@PathVariable int id){
       this.postService.deletePost(id);
       return  new ApiResponse("Post is Sucessfully deleted","true");
    }

    // Upadate APi
    @PutMapping("/updatePost/{id}")
    public ResponseEntity<PostDto>updatePost(@PathVariable Integer id, @RequestBody PostDto postDto){
       PostDto updatedPost= postService.updatePost(postDto,id);
       return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }

    // Search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>>searchPostByDto(@PathVariable ("keywords")String keyword){
        List<PostDto> result = this.postService.searchPost(keyword);
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }

    // Post Image Upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<ImageResponse >uploadPostImage(
            @RequestParam("PostImage")MultipartFile image,
            @PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);

        String imageName = this.fileService.uploadImage(path, image);

        ImageResponse imageResponse=ImageResponse.builder().imageName(imageName).message("image upload successfully").success(true).status(HttpStatus.CREATED).build();


        postDto.setImageName(imageName);
        PostDto updatedPost = this.postService.updatePost(postDto, postId);

       return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);
    }

    // Method to serve File
//    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    @GetMapping("/images/{postId}")
    public void serveImage(@PathVariable("postId")Integer postId,HttpServletResponse response) throws IOException {

        PostDto post = postService.getPostById(postId);
        InputStream resource = fileService.getResource(path, post.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }


}
