package uz.bob.appnewssite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.bob.appnewssite.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Post extends AbsEntity {

    @Column(nullable = false,columnDefinition = "text")
    private String title;

    @Column(nullable = false,columnDefinition = "text")
    private String text;

    @Column(nullable = false,columnDefinition = "text")
    private String url;

    // mappedBy orqali boglanish data rest un billa iwlatgan edim
//    @JsonIgnore
//    @OneToMany(mappedBy = "post")
//    private List<Comment> comment;
}
