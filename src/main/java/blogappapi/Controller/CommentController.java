package blogappapi.Controller;

import blogappapi.Service.CommentService;
import blogappapi.dto.ApiResponse;
import blogappapi.dto.CommentDto;
import blogappapi.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto createdComment = this.commentService.crateComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(createdComment, HttpStatus.OK);

    }
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse>deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully !!","true"),HttpStatus.OK);


    }
}
