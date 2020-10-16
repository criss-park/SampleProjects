package com.simplify.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends CommonDateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msrl")
    private User user;

    // Join 테이블이 Json 결과에 표시되지 않도록 처리.
    protected Board getBoard(){
        return board;
    }

    // 생성자
    public Post(String title, String author, String content, Board board, User user) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.board = board;
        this.user = user;
    }

    // 수정 시 데이터 처리
    public Post setUpdate(String author, String title, String content){
        this.author = author;
        this.title = title;
        this.content = content;
        return this;
    }
}
