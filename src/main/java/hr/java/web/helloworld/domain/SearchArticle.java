package hr.java.web.helloworld.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class SearchArticle extends Hardware {

    private BigDecimal lowerPrice;
    private BigDecimal upperPrice;

    /*
    public SearchArticle(String articleName,
                         BigDecimal code,
                         BigDecimal lowerPrice,
                         BigDecimal upperPrice,
                         String typeName)
    {
        this(articleName, code, lowerPrice, upperPrice, typeName);
        super.setId(id);
    }
     */

    public SearchArticle(String articleName,
                         BigDecimal code,
                         BigDecimal lowerPrice,
                         BigDecimal upperPrice,
                         String typeName)
    {
        super.setName(articleName);
        super.setCode(code);
        this.lowerPrice = lowerPrice;
        this.upperPrice = upperPrice;
        super.setType(typeName);
    }

}
