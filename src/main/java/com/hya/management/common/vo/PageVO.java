package com.hya.management.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO {
    private List rows;
    private Long total;
    private Long current;
    private Long size;

}
