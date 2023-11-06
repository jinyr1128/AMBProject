package com.example.ambproject.model;

import javax.persistence.*;
import java.time.LocalDateTime;

// 게시글 엔터티 클래스
@Entity
public class Post {

    // 게시글의 고유 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 게시글의 제목
    private String title;

    // 게시글의 작성자
    private String author;

    // 게시글 수정/삭제를 위한 비밀번호
    private String password;

    // 게시글의 본문. 대용량 텍스트 데이터를 위해 @Lob 어노테이션 사용
    @Lob
    private String content;

    // 게시글의 작성 시간. 기본값으로 현재 시간 할당
    private LocalDateTime createdAt = LocalDateTime.now();

    // 게시글 ID getter 메서드
    public Long getId() {
        return id;
    }

    // 게시글이 처음 생성될 때 호출되는 메서드. createdAt 필드의 값을 현재 시간으로 설정
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 게시글 ID setter 메서드
    public void setId(Long id) {
        this.id = id;
    }

    // 게시글 제목 getter 메서드
    public String getTitle() {
        return title;
    }

    // 게시글 제목 setter 메서드
    public void setTitle(String title) {
        this.title = title;
    }

    // 게시글 작성자 getter 메서드
    public String getAuthor() {
        return author;
    }

    // 게시글 작성자 setter 메서드
    public void setAuthor(String author) {
        this.author = author;
    }

    // 게시글 비밀번호 getter 메서드
    public String getPassword() {
        return password;
    }

    // 게시글 비밀번호 setter 메서드
    public void setPassword(String password) {
        this.password = password;
    }

    // 게시글 본문 getter 메서드
    public String getContent() {
        return content;
    }

    // 게시글 본문 setter 메서드
    public void setContent(String content) {
        this.content = content;
    }

    // 게시글 작성 시간 getter 메서드
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // 게시글 작성 시간 setter 메서드
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
