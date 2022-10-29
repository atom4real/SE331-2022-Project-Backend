package Project.comment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import Project.comment.entity.Comment;
import Project.comment.repository.CommentRepository;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

}
