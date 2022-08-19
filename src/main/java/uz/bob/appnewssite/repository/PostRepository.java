package uz.bob.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import uz.bob.appnewssite.entity.Post;

@PreAuthorize(value = "hasAnyAuthority('ADD_POST','EDIT_POST','DELETE_POST')")
@RepositoryRestResource(path = "post")
public interface PostRepository extends JpaRepository<Post,Long> {



}
