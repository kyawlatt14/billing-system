package net.kkl.billingsystem.common.responses;

import lombok.Builder;
import lombok.Data;
import net.kkl.billingsystem.utils.DateUtils;

import java.io.Serializable;

@Data
@Builder
public class AllResponse implements Serializable {
    public String status_message;
    private String date_time;
    public Object data;
    public static AllResponse fail(String errorMsg) {
        return AllResponse.builder()
                .status_message(errorMsg)
                .date_time(DateUtils.getNowDateToStringInSystem())
                .data("******")
                .build();
    }
    public static AllResponse success(String msg, Object data) {
        return AllResponse.builder()
                .status_message(msg)
                .date_time(DateUtils.getNowDateToStringInSystem())
                .data(data)
                .build();
    }
    public static AllResponse fail(String errorMsg, Object data) {
        return AllResponse.builder()
                .status_message(errorMsg)
                .date_time(DateUtils.getNowDateToStringInSystem())
                .data(data)
                .build();
    }
}
