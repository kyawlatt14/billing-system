package net.kkl.billingsystem.common.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayDTOResponse {
    private Integer transaction_id;
    private long amount;
    private String transaction_date;
    private String phone_number;
}
