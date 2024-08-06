package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// <Entity Class Type, id Type>
public interface ArticleRepository extends CrudRepository<Article, Long> {
    ArrayList<Article> findAll(); // 디폴트로 반환하는 Iterable -> Arraylist로 수정
}