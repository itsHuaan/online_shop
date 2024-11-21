package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.dto.PostDto;
import org.example.online_shop.models.PostModel;
import org.example.online_shop.models.ProductModel;
import org.example.online_shop.services.IPostService;
import org.example.online_shop.utils.Const;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "07. Post")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/post")
public class PostController {
    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "get all post", tags = {"07. Post"})
    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        List<PostDto> result = postService.findAll();
        return result != null && !result.isEmpty()
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "create new post", tags = {"07. Post"})
    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(@RequestBody PostModel post){
        int result = postService.save(post);
        return result == 1
                ? new ResponseEntity<>("Product created", HttpStatus.CREATED)
                : new ResponseEntity<>("Failed to create product", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "update post", tags = {"07. Post"})
    @PutMapping("/update-post/{id}")
    public ResponseEntity<?> updatePost(@RequestBody PostModel post, @PathVariable Long id) {
        if (postService.findById(id) == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        post.setPostId(id);
        return postService.save(post) == 2
                ? new ResponseEntity<>("post updated", HttpStatus.OK)
                : new ResponseEntity<>("Failed to update post", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Delete post", tags = {"07. Post"})
    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return postService.delete(id) != 0
                ? new ResponseEntity<>("post deleted", HttpStatus.OK)
                : new ResponseEntity<>("Failed to delete post", HttpStatus.BAD_REQUEST);
    }

}
