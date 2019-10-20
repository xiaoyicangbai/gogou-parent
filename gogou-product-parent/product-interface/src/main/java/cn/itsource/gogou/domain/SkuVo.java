package cn.itsource.gogou.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SkuVo {

    private List<Specification> skuProperties;

    private List<Map<String,String>> skus;
}
