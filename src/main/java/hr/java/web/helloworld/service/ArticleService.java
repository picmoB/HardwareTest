package hr.java.web.helloworld.service;

import hr.java.web.helloworld.dto.HardwareDTO;
import hr.java.web.helloworld.dto.SearchArticleDTO;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<HardwareDTO> getAllArticles();
    List<HardwareDTO> getArticlesByName(String articleName);
    Integer saveNewArticle(HardwareDTO article);
    List<HardwareDTO> filterByParameters(SearchArticleDTO searchArticleDTO);
    Optional<HardwareDTO> updateArticle(HardwareDTO articleDTO, Integer id);
    boolean articleByIdExists(Integer id);
    boolean deleteArticleById(Integer id);
}
