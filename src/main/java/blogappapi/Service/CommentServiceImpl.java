package blogappapi.Service;

import blogappapi.dao.CommentRepository;
import blogappapi.dao.PostRepository;
import blogappapi.dto.CommentDto;
import blogappapi.exception.ResourceNotFountException;
import blogappapi.model.Comment;
import blogappapi.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.modelmbean.ModelMBean;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDto crateComment(CommentDto commentDto, Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFountException("Post","PostId",postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commendId) {
        Comment com = this.commentRepository.findById(commendId).orElseThrow(() -> new ResourceNotFountException("Comment", "CommentId", commendId));
        this.commentRepository.delete(com);

    }
}
