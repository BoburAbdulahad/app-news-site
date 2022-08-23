package uz.bob.appnewssite.entity.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.bob.appnewssite.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
//@EntityListeners(value = AuditingEntityListener.class)
public abstract class AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false,nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(updatable = false)
    @CreatedBy
    private Long createdBy; //darsda mana wunday yozilgan edi--->  private User createdBy;
//    @ManyToOne(fetch = FetchType.LAZY)

    // keyin men ozim uni type ni User dan Long ga ozgartirdim
    @LastModifiedBy          // cunki createdBy va updatedBy ga yozmayotgan edi
    private Long updatedBy;                        //  bu ham  --->private User updatedBy;
//    @ManyToOne(fetch = FetchType.LAZY)

}
