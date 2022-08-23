package uz.bob.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.bob.appnewssite.entity.Comment;
import uz.bob.appnewssite.entity.Post;
import uz.bob.appnewssite.exceptions.ResourceNotFoundException;
import uz.bob.appnewssite.payload.ApiResponse;
import uz.bob.appnewssite.payload.PostDTO;
import uz.bob.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;


    public List<Post> view(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Post> postPage = postRepository.findAll(pageable);
        return postPage.toList();
    }


    public Post getComment(Integer id) {
        return postRepository.findById(id.longValue()).orElseThrow(()->new ResourceNotFoundException("postTable","id",id));
    }


    public ApiResponse addPost(PostDTO postDTO) {
        Post post=new Post(
                postDTO.getTitle(),
                postDTO.getText(),
                postDTO.getUrl()
        );
        postRepository.save(post);
        return new ApiResponse("Post saved",true);
    }


    public ApiResponse editPost(Long id, PostDTO postDTO) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent()) {
            return new ApiResponse("Post not found",false);
        }
        Post post = optionalPost.get();
        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        post.setUrl(postDTO.getUrl());
        Post editedPost = postRepository.save(post);
        return new ApiResponse("Post successfully edited",true,editedPost);
    }


    public boolean deletePost(Long id) {
        try {
            postRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
