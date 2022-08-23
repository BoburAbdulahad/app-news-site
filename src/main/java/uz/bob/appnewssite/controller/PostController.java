package uz.bob.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.bob.appnewssite.entity.Comment;
import uz.bob.appnewssite.entity.Post;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.CommentDTO;
import uz.bob.appnewssite.payload.PostDTO;
import uz.bob.appnewssite.service.CommentService;
import uz.bob.appnewssite.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public HttpEntity<?>view(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        List<Post>commentList=postService.view(page,size);
        return ResponseEntity.ok(commentList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getComment(@PathVariable Integer id){
        Post comment=postService.getComment(id);
        return ResponseEntity.status(comment!=null?200:409).body(comment);
    }

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> addComment(@Valid @RequestBody PostDTO postDTO){
        ApiResponse apiResponse=postService.addPost(postDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @PutMapping("/{id}")
    public HttpEntity<?> editComment(@PathVariable Long id,
            @Valid @RequestBody PostDTO postDTO){
        ApiResponse apiResponse=postService.editPost(id,postDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }



    @PreAuthorize("hasAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id){
        boolean deleteComment=postService.deletePost(id);
        return deleteComment?ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }
}
