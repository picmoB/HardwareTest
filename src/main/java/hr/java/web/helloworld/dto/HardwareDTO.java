package hr.java.web.helloworld.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HardwareDTO {
    private String hardwareName;
    private String hardwareType;
    private BigDecimal hardwarePrice;
    private BigDecimal hardwareCode;
}
