package com.example.ambproject.service;

import com.example.ambproject.model.Post;
import com.example.ambproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // 작성일 기준 내림차순 정렬 추가
    public List<Post> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post updatePost(Long id, Post postDetails, String password) {
        Post post = verifyPost(id, password);

        post.setTitle(postDetails.getTitle());
        post.setAuthor(postDetails.getAuthor());
        post.setContent(postDetails.getContent());

        return postRepository.save(post);
    }

    public void deletePost(Long id, String password) {
        Post post = verifyPost(id, password);
        postRepository.deleteById(post.getId());
    }
    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post) {
        savePost(post);
        return "redirect:/posts";  // 게시물 목록 페이지로 리디렉션
    }

    private Post verifyPost(Long id, String password) {
        Post post = getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return post;
    }
}