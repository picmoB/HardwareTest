package hr.java.web.helloworld.repository;

import hr.java.web.helloworld.domain.Hardware;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    List<Hardware> getAllArticles();
    List<Hardware> getArticlesByName(String articleName);
    List<Hardware> getArticlesById(Integer brandId);
    int saveNewArticle(Hardware hardware);
    /*
    void saveNewArticle(Hardware article);
    List<Hardware> filterByParameters(SearchArticle searchArticle);
     */
    Optional<Hardware> updateArticle(Hardware hardware, Integer brandId);
    boolean articleByIdExists(Integer id);
    boolean deleteArticleById(Integer id);
}
