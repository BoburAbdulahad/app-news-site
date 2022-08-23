package uz.bob.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.bob.appnewssite.entity.Comment;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.CommentDTO;
import uz.bob.appnewssite.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public HttpEntity<?>view(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        List<Comment>commentList=commentService.view(page,size);
        return ResponseEntity.ok(commentList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getComment(@PathVariable Integer id){
        Comment comment=commentService.getComment(id);
        return ResponseEntity.status(comment!=null?200:409).body(comment);
    }

    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> addComment(@Valid @RequestBody CommentDTO commentDTO){
        ApiResponse apiResponse=commentService.addComment(commentDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editComment(@PathVariable Long id,
            @Valid @RequestBody CommentDTO commentDTO){
        ApiResponse apiResponse=commentService.editComment(id,commentDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('DELETE_MY_COMMENT')")
    @DeleteMapping("/deleteMyComment/{id}")
    public HttpEntity<?> deleteMyComment(@PathVariable Long id){
        boolean deleteMyComment=commentService.deleteMyComment(id);
        return deleteMyComment?ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('DELETE_COMMENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id){
        boolean deleteComment=commentService.deleteComment(id);
        return deleteComment?ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }
}
