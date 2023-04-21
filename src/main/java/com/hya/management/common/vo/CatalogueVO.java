package com.hya.management.common.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CatalogueVO {

    private Long id;

    private String rawMaterialName;

    private Float value;

    private String unit;

    private Long provId;


}
