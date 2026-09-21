package hr.java.web.helloworld.controller;

import hr.java.web.helloworld.dto.HardwareDTO;
import hr.java.web.helloworld.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web-shop")
@AllArgsConstructor
public class ArticleController {

    private ArticleService articleService;

    @GetMapping
    public List<HardwareDTO> getAllArticles() {
        return articleService.getAllArticles().stream().toList();
    }

    @GetMapping("/{articleName}")
    public List<HardwareDTO> filterArticlesByName(@PathVariable String articleName) {
        return articleService.getArticlesByName(articleName).stream().toList();
    }

    @PostMapping("/new")
    public ResponseEntity<Void> addArticle(@RequestBody HardwareDTO hardwareDTO) {
        articleService.saveNewArticle(hardwareDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/article/{articleId}")
    public ResponseEntity<HardwareDTO> updateArticle(@PathVariable Integer articleId, @RequestBody HardwareDTO hardwareDTO) {
        if (articleService.articleByIdExists(articleId)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/article/{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable Integer articleId) {
        if (articleService.articleByIdExists(articleId)) {
            boolean result = articleService.deleteArticleById(articleId);
            if (result) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else  {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
