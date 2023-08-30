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
public class BillDTO {
    @NotBlank(message = "Bill-Name must not be null...")
    private String name;
    private String description;
}