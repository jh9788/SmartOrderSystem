package com.SOS.SmartOrderSystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveRequest {
    private String menu;
    private Integer quantity;
    private String time;
}
