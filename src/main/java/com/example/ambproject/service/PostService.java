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

// 서비스 계층 클래스. 게시글과 관련된 주요 기능들을 제공
@Service
public class PostService {

    // 게시글 리포지토리 객체. DB와의 연동을 위해 사용
    @Autowired
    private PostRepository postRepository;

    // 게시글을 저장하는 메서드
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // 모든 게시글을 작성일 기준 내림차순으로 조회하는 메서드
    public List<Post> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    // 주어진 ID에 해당하는 게시글을 조회하는 메서드
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // 주어진 ID와 비밀번호에 해당하는 게시글의 내용을 업데이트하는 메서드
    public Post updatePost(Long id, Post postDetails, String password) {
        Post post = verifyPost(id, password);  // 해당 ID와 비밀번호에 맞는 게시글을 검증

        post.setTitle(postDetails.getTitle());  // 게시글 제목 업데이트
        post.setAuthor(postDetails.getAuthor());  // 게시글 작성자명 업데이트
        post.setContent(postDetails.getContent());  // 게시글 본문 업데이트

        return postRepository.save(post);  // 업데이트된 게시글 저장
    }

    // 주어진 ID와 비밀번호에 해당하는 게시글을 삭제하는 메서드
    public void deletePost(Long id, String password) {
        Post post = verifyPost(id, password);  // 해당 ID와 비밀번호에 맞는 게시글을 검증
        postRepository.deleteById(post.getId());  // 게시글 삭제
    }

    // 게시글을 생성하는 메서드
    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post) {
        savePost(post);  // 게시글 저장
        return "redirect:/posts";  // 게시물 목록 페이지로 리디렉션
    }

    // 주어진 ID와 비밀번호에 해당하는 게시글이 존재하는지 검증하는 메서드
    private Post verifyPost(Long id, String password) {
        Post post = getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));  // 해당 ID에 맞는 게시글 조회

        // 게시글의 비밀번호와 주어진 비밀번호가 일치하지 않으면 예외 발생
        if (!post.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return post;  // 검증된 게시글 반환
    }
}
