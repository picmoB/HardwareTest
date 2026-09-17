package hr.java.web.helloworld.service;

import hr.java.web.helloworld.domain.Hardware;
import hr.java.web.helloworld.dto.HardwareDTO;
import hr.java.web.helloworld.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private HardwareDTO convertArticleToArticleDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getName(), hardware.getType(), hardware.getPrice(), hardware.getCode());
    }
}
