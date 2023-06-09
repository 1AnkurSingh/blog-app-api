package blogappapi.Service;

import blogappapi.dao.CategoryRepository;
import blogappapi.dao.PostRepository;
import blogappapi.dao.UserRepository;
import blogappapi.dto.PostDto;
import blogappapi.dto.PostResponse;
import blogappapi.exception.ResourceNotFountException;
import blogappapi.model.Category;
import blogappapi.model.Post;
import blogappapi.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository  categoryRepository;

    @Value("${project.image}")
    private String path;


    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
            User user =this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFountException("User","usedId",userId));
            Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFountException("Category","categoryId",categoryId));
            Post post= this.modelMapper.map(postDto,Post.class);
            post.setImageName("default.png");
            post.setAddedDate(new Date());
            post.setUser(user);
            post.setCategory(category);

            Post newPost=this.postRepository.save(post);

            return this.modelMapper.map(newPost,PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto postDto, int id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFountException("Post","PostId",id));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());
        Post updatedPost=this.postRepository.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(int id) {
        Post post=postRepository.findById(id).orElseThrow(() -> new ResourceNotFountException("Post","PostId",id));


        //Delete Post image
        String fullPath = path + post.getImageName();

       try {
           Path path1= Paths.get(fullPath);


           Files.delete(path1);
       }catch (NoSuchFileException ex){

       } catch (IOException e) {
           e.printStackTrace();
       }


        postRepository.deleteById(post.getPostId());
    }
//  Get All Post
    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts=this.postRepository.findAll();
        List<PostDto>postDtos=posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
    // Get All post with Pagination
        public PostResponse getAllPost1(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort=null;

        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();

        }else {
            sort=Sort.by(sortBy).descending();
        }

        Pageable p= PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePost=this.postRepository.findAll(p);
        List<Post> allPost = pagePost.getContent();
        List<PostDto>postDtos=allPost.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalPages());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    // Get Post By id
    @Override
    public PostDto getPostById(Integer id) {
        Post post=this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFountException("Post","PostId",id));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(int categoryId) {
        Category cat=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFountException("Category","CategoryId",categoryId));
        List<Post> posts=this.postRepository.findByCategory(cat);
        List<PostDto> postDtos=posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(int userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFountException("User","userId",userId));
        List<Post>posts=this.postRepository.findByUser(user);
        List<PostDto> postDtos=posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;

    }

    @Override
    public List<PostDto> searchPost(String Keyword) {
        List<Post> posts = this.postRepository.findByTitleContaining(Keyword);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
