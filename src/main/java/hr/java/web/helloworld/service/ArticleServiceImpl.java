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
        return articleRepository.saveNewArticle(convertArticleDTOToHardware(article));
    }

    @Override
    public List<HardwareDTO> filterByParameters(SearchArticleDTO searchArticleDTO) {
        return List.of();
    }

    @Override
    public Optional<HardwareDTO> updateArticle(HardwareDTO articleDTO, Integer id) {
        Optional<Hardware> updateHardware =
            articleRepository.updateArticle(convertArticleDTOToHardware(articleDTO), id);

        if (updateHardware.isPresent()) {
            return Optional.of(convertArticleToArticleDTO(updateHardware.get()));
        }

        return Optional.empty();

        // return articleRepository.getArticlesById(id).stream().map(this::convertArticleToArticleDTO).findFirst();
    }

    @Override
    public boolean articleByIdExists(Integer id) {
        return articleRepository.getArticlesById(id) != null;
    }

    @Override
    public boolean deleteArticleById(Integer id) {
        return articleRepository.deleteArticleById(id);
    }

    private HardwareDTO convertArticleToArticleDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getName(), hardware.getType(), hardware.getPrice(), hardware.getCode());
    }

    private Hardware convertArticleDTOToHardware(HardwareDTO hardwareDTO) {
        Integer latestId =
                articleRepository.getAllArticles().stream()
                        .map(Hardware::getId).findFirst().get() - 1;

        return new Hardware(latestId + 1,
                hardwareDTO.getHardwareName(),
                hardwareDTO.getHardwareType(),
                hardwareDTO.getHardwarePrice(),
                hardwareDTO.getHardwareCode()
        );
    }
}
