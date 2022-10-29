package Project.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Project.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
