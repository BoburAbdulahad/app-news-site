package uz.bob.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.bob.appnewssite.entity.Comment;
import uz.bob.appnewssite.entity.User;
import uz.bob.appnewssite.exceptions.ResourceNotFoundException;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.CommentDTO;
import uz.bob.appnewssite.repository.CommentRepository;
import uz.bob.appnewssite.repository.PostRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public List<Comment> view(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> commentPage = commentRepository.findAll(pageable);
        return commentPage.toList();
    }

    public Comment getComment(Integer id) {
        return commentRepository.findById(id.longValue()).orElse(null);
    }

    public ApiResponse addComment(CommentDTO commentDTO) {
        Comment comment = new Comment(
                commentDTO.getText(),
                postRepository.findById(commentDTO.getPostId()).orElseThrow(() -> new ResourceNotFoundException("post", "id", commentDTO.getPostId()))
        );
        commentRepository.save(comment);
        return new ApiResponse("Comment added", true);
    }

    public ApiResponse editComment(Long id, CommentDTO commentDTO) {

        if (!commentRepository.existsById(id)) {
            return new ApiResponse("This type comment id not available", false);
        }
        Comment comment = commentRepository.getOne(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user.getId() == comment.getCreatedBy()) {
            comment.setText(commentDTO.getText());
            comment.setPost(postRepository.findById(commentDTO.getPostId()).orElseThrow(() -> new ResourceNotFoundException("postTable", "id", commentDTO.getPostId())));
            Comment editedComment = commentRepository.save(comment);
            return new ApiResponse("Comment edited", true,editedComment);
        }
        return new ApiResponse("This comment not available for you", false);
    }

    public boolean deleteMyComment(Long id) {
        try {
            Comment comment = commentRepository.getOne(id);
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user.getId() == comment.getCreatedBy()) {
                commentRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteComment(Long id) {
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
