//package blogappapi.Service;
//
//import blogappapi.dto.PostDto;
//import blogappapi.model.Post;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class temp {
//    List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);
//}
//    public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {
//        Pageable p= PageRequest.of(pageNumber,pageSize);
//        Page<Post> pagePost=this.postRepository.findAll(p);
//        List<Post> allPost = pagePost.getContent();
//        List<PostDto>postDtos=allPost.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
//        return postDtos;
//    }
//
//
//
//
//    @GetMapping("/post")
//    public ResponseEntity<List<PostDto>> getAllPost(
//            @RequestParam(value = "pageNumber", defaultValue = "10", required = false) Integer pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize) {
//        List<PostDto>allPost=this.postService.getAllPost(pageNumber,pageSize);
//        return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
//    }





//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@GetMapping("/postWithPagination")
//public ResponseEntity<List<PostDto>> getAllPost(
//@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
//@RequestParam(value = "pageSize", defaultValue = "9", required = false) Integer pageSize) {
//        List<PostDto>allPost=this.postService.getAllPost1(pageNumber,pageSize);
//        return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
//        }
