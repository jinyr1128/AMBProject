package com.example.ambproject.controller;

import com.example.ambproject.model.Post;
import com.example.ambproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping
    public String listPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());  // 이 부분을 추가해야 합니다.
        return "index";
    }

    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "create";  // create.html로 이동
    }

    @PostMapping("/create")
    public String createPost(Post post) {
        postService.savePost(post);
        return "redirect:/posts";
    }
    @GetMapping("/{id}/delete")
    public String deletePostForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);
        return "delete";  // delete.html 템플릿으로 이동
    }
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id, @RequestParam String password) {
        postService.deletePost(id, password);
        return "redirect:/posts";
    }
    @GetMapping("/{id}/update")
    public String updatePostForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);
        return "update";  // update.html 템플릿으로 이동
    }

    @PostMapping("/{id}/update")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post, @RequestParam String password) {
        postService.updatePost(id, post, password);
        return "redirect:/posts";
    }



    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);
        return "view";  // view.html로 이동
    }


    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}
