package uz.bob.appnewssite.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.bob.appnewssite.repository.CommentRepository;

import java.sql.Timestamp;

@Projection(types = {CommentRepository.class})
public interface CustomComment {

    String getText();
    /**
     * data rest orqali projection ocildi, lekin String getText() ni spring kuzatmayapti;
     */



}
