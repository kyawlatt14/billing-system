package net.kkl.billingsystem.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO {
    @NotBlank(message = "ApiCaller must not be null...")
    private String apiCaller;
    @NotBlank(message = "Reference-No must not be null...")
    private String referenceNo;
    @NotBlank(message = "Phone-Number must not be null...")
    private String phoneNumber;
    private long amount;
    private Integer billId;
}
