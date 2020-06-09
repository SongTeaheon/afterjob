package com.song.afterjob.repository;

import com.song.afterjob.domain.PostsDvo;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

//Jpa 조건절을 위한 specification 세팅
public final class PostsSpec {
    private PostsSpec(){} //인스턴스가 필요없으므로 private 생성자를 만든다.

    /*
     return new Specification<PostsDvo>() {
         @Override
         public predicate toPredicate(Root<PostsDvo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
             return criteriaBuilder.equal(root.get("categoryNo"), categoryNo);
         }
     }
    이걸 아래 람다식으로 바꾼 것!
    */
    public static Specification<PostsDvo> categoryIs(Long categoryNo) {
        return (Specification<PostsDvo>) ((root, criteriaQuery, criteriaBuilder)->
                criteriaBuilder.equal(root.get("categoryNo"), categoryNo));
    }
}
