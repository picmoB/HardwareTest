package hr.java.web.helloworld.dto;

import com.fasterxml.jackson.core.io.doubleparser.JavaBigDecimalParser;
import hr.java.web.helloworld.domain.Category;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class SearchArticleDTO extends HardwareDTO {

    private BigDecimal lowerPrice;
    private BigDecimal upperPrice;

    public SearchArticleDTO(String articleName,
                            BigDecimal code,
                            BigDecimal lowerPrice,
                            BigDecimal upperPrice,
                            String typeName) {
        super.setHardwareName(articleName);
        super.setHardwareCode(code);
        this.lowerPrice = lowerPrice;
        this.upperPrice = upperPrice;
        super.setHardwareType(typeName);
    }
}
