package com.song.afterjob.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="posts")
@DynamicInsert
public class PostsDvo {
    @Id//pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본키 생성을 db에 위임(autoincrment썼기 때)
    private Long postNo;
    private String title;

    @ColumnDefault("0") //0
    private Long categoryNo;

    private String cont;
    private String writer;

    @ColumnDefault("sysdate") //sysdate
    private Date regTs;

    @ColumnDefault("0") //default 0
    private Long likeCnt;

    @ColumnDefault("0") //default 0
    private Long commentCnt;

    @ColumnDefault("0") //default 0
    private Long viewCnt;

    @Builder
    public PostsDvo(String title, String cont, String writer, Long categoryNo){
        this.title = title;
        this.cont = cont;
        this.writer = writer;
        this.categoryNo = categoryNo;
    }

}
