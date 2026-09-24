package hr.java.web.helloworld.repository;

import hr.java.web.helloworld.domain.Hardware;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MockArticleRepository implements ArticleRepository {

    private static List<Hardware> hardwareList;

    static {
        hardwareList = new ArrayList<>();

        Hardware hardware01 = new Hardware(1, "Nvidia", "GPU", new BigDecimal(900), new BigDecimal(123456789));
        Hardware hardware02 = new Hardware(2, "Intel", "CPU", new BigDecimal(500), new BigDecimal(987654321));
        Hardware hardware03 = new Hardware(3, "ASUS", "MBO", new BigDecimal(350), new BigDecimal(24681012));
        Hardware hardware04 = new Hardware(4, "SanDisk", "Storage", new BigDecimal(200), new BigDecimal(1357911));

        hardwareList.add(hardware01);
        hardwareList.add(hardware02);
        hardwareList.add(hardware03);
        hardwareList.add(hardware04);
    }

    @Override
    public List<Hardware> getAllArticles() {
        return hardwareList;
    }

    @Override
    public List<Hardware> getArticlesByName(String articleName) {
        return hardwareList.stream()
                .filter(a -> a.getName().toLowerCase().contains(articleName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Hardware> getArticlesById(Integer brandId) {
        return hardwareList.stream()
                .filter(a -> a.getId().equals(brandId))
                .collect(Collectors.toList());
        // return hardwareList.contains(brandId) ? hardwareList : null;
    }

    @Override
    public int saveNewArticle(Hardware hardware) {
        if (hardwareList.contains(hardware)) {
            return hardwareList.indexOf(hardware);
        } else  {
            hardwareList.add(hardware);
        }
        return hardwareList.size();
    }

    @Override
    public Optional<Hardware> updateArticle(Hardware hardware, Integer brandId) {
        Optional<Hardware> optionalHardware = hardwareList.stream().filter(a -> a.getId().equals(brandId)).findFirst();
        if (optionalHardware.isPresent()) {
            Hardware updateHardware = optionalHardware.get();
            updateHardware.setName(hardware.getName());
            updateHardware.setType(hardware.getType());
            updateHardware.setPrice(hardware.getPrice());
            updateHardware.setCode(hardware.getCode());

            return Optional.of(updateHardware);
        }

        return Optional.empty();
    }

    @Override
    public boolean articleByIdExists(Integer id) {
        return hardwareList.contains(id);
    }

    @Override
    public boolean deleteArticleById(Integer id) {
        // return hardwareList.remove(id);

        return hardwareList.removeIf(a -> a.getId().equals(id));
    }
}
