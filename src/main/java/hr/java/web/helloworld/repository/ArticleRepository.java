package hr.java.web.helloworld.repository;

import hr.java.web.helloworld.domain.Hardware;

import java.util.List;

public interface ArticleRepository {
    List<Hardware> getAllArticles();
    List<Hardware> getArticlesByName(String articleName);
    List<Hardware> getArticlesById(Integer brandId);
    /*
    void saveNewArticle(Hardware article);
    List<Hardware> filterByParameters(SearchArticle searchArticle);
     */
}
