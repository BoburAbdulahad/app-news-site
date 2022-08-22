package uz.bob.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.bob.appnewssite.entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends AbsEntity {


    @Column(nullable = false, columnDefinition = "text")
    private String text;


    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    public void setPost(Post post) {
        post.setComment(this.post.getComment());
        this.post = post;
    }
//
//    @JoinColumn(name = "post_id")
//    @ManyToOne(optional = false)
//    private Post post;

}
