package uz.bob.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.bob.appnewssite.entity.Comment;
import uz.bob.appnewssite.projection.CustomComment;

@RepositoryRestResource(path = "comment",excerptProjection = CustomComment.class)
public interface CommentRepository extends JpaRepository<Comment,Long> {


}
