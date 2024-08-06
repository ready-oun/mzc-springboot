package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j // 로깅 기능을 위한 어노테이션 추가 ( Simple Logging Facade for Java )
@Controller
public class ArticleController {

    @Autowired // 스프링부트가 미리 생성해 놓은 레포지토리 객체 주입(DI, Dependency Injection)
    private final ArticleRepository articleRepository; // articleREpository 객체 선언

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles")
    public String index(Model model) {

        // 1. 모든 데이터 가져오기
        // 1-1. List<Article> articleEntityList = (List<Article> articleRepository.findAll();
        // 1-2. Iterable<Article> articleEntityList = articleRepository.findAll();
        // 1-3. ArrayList<Article> <- @Override findAll(); from ArticleRepository
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articlelist", articleEntityList); // articleEntity 등록

        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/new/{id}") // 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id); // id를 잘 받았는지 확인하는 로그 찍기
        // 1. id를 조회해 데이터 가져오기
//        Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
//        System.out.println(form.toString());

        // Article Entity 작성해야 함
        // 엔티티 생성
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString()); // DTO가 엔티티로 잘 변환되는지 확인 출력

        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());
//        System.out.println(saved.toString()); // article이 DB에 잘 저장되는지 확인 출력

        return "";
    }
}
