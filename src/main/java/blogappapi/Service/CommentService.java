package blogappapi.Service;

import blogappapi.dto.CommentDto;
import blogappapi.model.Comment;

public interface CommentService {
    CommentDto crateComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commendId);
}
