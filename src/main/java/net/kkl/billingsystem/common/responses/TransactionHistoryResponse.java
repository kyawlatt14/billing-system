package net.kkl.billingsystem.common.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionHistoryResponse {
    private String api_caller;
    private Integer id;
    private long amount;
    private String reference_no;
    private String phone_number;

}
