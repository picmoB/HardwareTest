package hr.java.web.helloworld.service;

import hr.java.web.helloworld.dto.HardwareDTO;

import java.util.List;

public interface ArticleService {
    List<HardwareDTO> getAllArticles();
    List<HardwareDTO> getArticlesByName(String articleName);
}
