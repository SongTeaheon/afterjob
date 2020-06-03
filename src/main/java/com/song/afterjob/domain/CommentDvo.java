package com.song.afterjob.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="COMMENTS")
@DynamicInsert
public class CommentDvo {
    @Id//pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본키 생성을 db에 위임(autoincrment썼기 때)
    private Long commentNo;

    private String cont;
    private Long postNo;
    private Long upperComment;
    private String writer;

    @ColumnDefault("sysdate") //sysdate
    private Date regTs;

    @ColumnDefault("0")
    private Long likeCnt;

    @ColumnDefault("0")
    private Long layerNo;

}
