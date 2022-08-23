package uz.bob.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.bob.appnewssite.entity.Post;

/**
 *@PreAuthorize(value = "hasAnyAuthority('ADD_POST','EDIT_POST','DELETE_POST')")
*@RepositoryRestResource(path = "post")
 Yuqoridagi ikkalasini data rest orqali iwlatganimda PreAuthorize repository ustiga qoyilgani un CommentService da comment ni add qiliwda simple
 user lar comment qowa olmadi cunki PreAuthorize repository interface ustiga qoyilgani un, commentni add qiliwda postId kk edi, postId ni oliwda
 preAuthorize ruxsat bermadi
 data rest iwlatdim post ni crudi un keyin crudni qolda alohida yozdim, keyin iwladi edit ham wunday,
 xulosa:
 PreAuthorize class ustiga qoyilsa simple userlarda PreAuthorize dagi huquqlar bolmasa iwlamas ekan
 vazifa:
 data rest orqali bir-biriga bogliq entity larni crud orqali va @PreAuthorize ni iwlatgan holda project ni iwlatib koriw
 */
public interface PostRepository extends JpaRepository<Post,Long> {



}
