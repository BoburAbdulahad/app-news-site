package uz.bob.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.bob.appnewssite.entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Comment extends AbsEntity {


    @Column(nullable = false, columnDefinition = "text")
    private String text;


    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    // data rest bn iwlatilganda iwlamadi POST va PUT requestlarda
//    public void setPost(Post post) {
//        post.setComment(this.getPost().getComment());
//        post.setText(this.getPost().getText());
//        post.setTitle(this.getPost().getTitle());
//        post.setUrl(this.getPost().getUrl());
////              this.post = post;
//    }


}
