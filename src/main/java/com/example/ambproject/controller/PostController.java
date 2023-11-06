package com.example.ambproject.controller;

import com.example.ambproject.model.Post;
import com.example.ambproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 게시글과 관련된 요청을 처리하는 컨트롤러
@Controller
@RequestMapping("/posts")
public class PostController {

    // 게시글 서비스를 주입 (의존성 주입)
    @Autowired
    private PostService postService;

    // 모든 게시글을 조회하는 메서드
    @GetMapping
    public String listPosts(Model model) {
        List<Post> posts = postService.getAllPosts();  // 모든 게시글 가져오기
        model.addAttribute("posts", posts);  // 모델에 게시글 목록 추가
        model.addAttribute("post", new Post());  // 새 게시글 객체 추가
        return "index";  // index.html로 뷰 이동
    }

    // 게시글 작성 폼을 보여주는 메서드
    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());  // 새 게시글 객체 추가
        return "create";  // create.html로 뷰 이동
    }

    // 게시글을 저장하는 메서드
    @PostMapping("/create")
    public String createPost(Post post) {
        postService.savePost(post);  // 게시글 저장
        return "redirect:/posts";  // 게시글 목록 페이지로 리디렉션
    }

    // 게시글 삭제 폼을 보여주는 메서드
    @GetMapping("/{id}/delete")
    public String deletePostForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);  // 모델에 선택된 게시글 추가
        return "delete";  // delete.html로 뷰 이동
    }

    // 게시글을 삭제하는 메서드
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id, @RequestParam String password) {
        postService.deletePost(id, password);  // 게시글 삭제
        return "redirect:/posts";  // 게시글 목록 페이지로 리디렉션
    }

    // 게시글 수정 폼을 보여주는 메서드
    @GetMapping("/{id}/update")
    public String updatePostForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);  // 모델에 선택된 게시글 추가
        return "update";  // update.html로 뷰 이동
    }

    // 게시글을 수정하는 메서드
    @PostMapping("/{id}/update")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post, @RequestParam String password) {
        postService.updatePost(id, post, password);  // 게시글 수정
        return "redirect:/posts";  // 게시글 목록 페이지로 리디렉션
    }

    // 특정 게시글을 조회하는 메서드
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);  // 모델에 선택된 게시글 추가
        return "view";  // view.html로 뷰 이동
    }

    // 예외 처리 메서드. RuntimeException이 발생하면 실행
    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());  // 모델에 에러 메시지 추가
        return "error";  // error.html로 뷰 이동
    }
}
