package com.study.demo.writeAndFlush;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ResponseSample {
    private String code;
    private String data;
    private long timestamp;

}
