package hr.java.web.helloworld.service;

import hr.java.web.helloworld.domain.Hardware;
import hr.java.web.helloworld.dto.HardwareDTO;
import hr.java.web.helloworld.dto.SearchArticleDTO;
import hr.java.web.helloworld.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Override
    public List<HardwareDTO> getAllArticles() {
        return articleRepository.getAllArticles().stream()
                .map(this::convertArticleToArticleDTO)
                .toList();
    }

    @Override
    public List<HardwareDTO> getArticlesByName(String articleName) {
        return articleRepository.getArticlesByName(articleName).stream()
                .map(this::convertArticleToArticleDTO)
                .toList();
    }

    @Override
    public Integer saveNewArticle(HardwareDTO article) {
        return articleRepository.getAllArticles().size();
    }

    @Override
    public List<HardwareDTO> filterByParameters(SearchArticleDTO searchArticleDTO) {
        return List.of();
    }

    @Override
    public Optional<HardwareDTO> updateArticle(HardwareDTO articleDTO, Integer id) {
        return articleRepository.getArticlesById(id).stream().map(this::convertArticleToArticleDTO).findFirst();
    }

    @Override
    public boolean articleByIdExists(Integer id) {
        return articleRepository.getArticlesById(id) != null;
    }

    @Override
    public boolean deleteArticleById(Integer id) {
        return articleRepository.getArticlesById(id) == null;
    }

    private HardwareDTO convertArticleToArticleDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getName(), hardware.getType(), hardware.getPrice(), hardware.getCode());
    }
}
