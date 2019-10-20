package cn.itsource.gogou.service.impl;

import cn.itsource.gogou.domain.Product;
import cn.itsource.gogou.domain.ProductExt;
import cn.itsource.gogou.domain.Sku;
import cn.itsource.gogou.domain.Specification;
import cn.itsource.gogou.mapper.ProductExtMapper;
import cn.itsource.gogou.mapper.ProductMapper;
import cn.itsource.gogou.mapper.SkuMapper;
import cn.itsource.gogou.mapper.SpecificationMapper;
import cn.itsource.gogou.query.ProductQuery;
import cn.itsource.gogou.service.IProductService;
import cn.itsource.gogou.util.PageList;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author yun
 * @since 2019-10-17
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private ProductExtMapper productExtMapper;

    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SkuMapper skuMapper;

    /**
     * 分页查询
     * @param query
     * @return
     */
    @Override
    public PageList<Product> queryPage(ProductQuery query) {
        IPage iPage = baseMapper.queryPage(new Page(query.getPageNum(), query.getPageSize()), query);
        return new PageList<>(iPage.getTotal(),iPage.getRecords());
    }
    /**
     * 查询SKU属性
     * @param productId
     * @return
     */
    @Override
    public List<Specification> getSkuProperties(Long productId) {

        System.out.println("进入sku属性查询·····");//保存SKU属性
        List<Specification> specifications =null;
        //通过id查询商品表
        Product product = baseMapper.selectById(productId);
        //获取商品信息种的skuProperties属性
        String skuProperties = product.getSkuProperties();
        //判断显示属性是否为空
        System.out.println(skuProperties+"==="+product.getProductTypeId());
        if (StringUtils.isEmpty(skuProperties)){
            //如果为空则通过商品类型ID查询该类商品包含的显示属性
            specifications = specificationMapper.selectList(new QueryWrapper<Specification>()
                    .eq("product_type_id", product.getProductTypeId()).eq("isSku", 1));
            System.out.println("===========");
            System.out.println(specifications);
        }else {
            //如果不为空则将商品skuProperties属性值转成数组返回
            specifications = JSON.parseArray(skuProperties, Specification.class);
        }
        return specifications;
    }



    /**
     * 查询显示属性
     * @param productId
     * @return
     */
    @Override
    public List<Specification> getViewsProperties(Long productId) {
        //保存显示属性
        List<Specification> specifications =null;
        //通过id查询商品表
        Product product = baseMapper.selectById(productId);
        //获取商品信息种的viewsProperties属性
        String viewProperties = product.getViewProperties();
        //判断显示属性是否为空
        if (StringUtils.isEmpty(viewProperties)){
            //如果为空则通过商品类型ID查询该类商品包含的显示属性
            specifications = specificationMapper.selectList(new QueryWrapper<Specification>()
                    .eq("product_type_id", product.getProductTypeId()).eq("isSku", 0L));
        }else {
            //如果不为空则将商品viewsProperties属性值转成数组返回
            specifications = JSON.parseArray(viewProperties, Specification.class);
        }
        return specifications;
    }
    /**
     * 更新显示属性
     * @param productId
     * @param viewProperty
     * @return
     */
    @Override
    public void updateViewsProperties(Long productId, List<Specification> viewProperty) {
        //将数组转成json字符串
        String viewsPropertyJson = JSON.toJSONString(viewProperty);
        baseMapper.updateViewsProperties(productId,viewsPropertyJson);
    }
    /**
     * 更新SKU属性
     * @param productId
     * @param skuProperties
     * @return
     */
    @Override
    public void updateSkuProperties(Long productId, List<Specification> skuProperties, List<Map<String, String>> skus) {
        //将数组转成json字符串
        String skuPropertyJson = JSON.toJSONString(skuProperties);
        System.out.println("进入pruductSKU维护方法++++"+skuPropertyJson);
        baseMapper.updateSkuProperties(productId,skuPropertyJson);
        //保存sku
        Sku sku=null;
        for (Map<String, String> skuMap : skus) {
            sku=new Sku();
            sku.setCreateTime(System.currentTimeMillis());
            sku.setProductId(productId);
            //设置skuName
            StringBuilder sb= new StringBuilder();
            for (Map.Entry<String, String> entry : skuMap.entrySet()) {
                if(!"price".equals(entry.getKey())&&!"strock".equals(entry.getKey())){
                    sb.append(entry.getValue());
                }
                sku.setSkuName(sb.toString());
            }
            sku.setPrice(Integer.parseInt(skuMap.get("price")));
            sku.setAvailableStock(Integer.parseInt(skuMap.get("stock")));
            sku.setIndexs(skuMap.get("indexs"));
            skuMapper.insert(sku);
        }
    }

    /**
     * 商品保存
     * @param product
     * @return
     */
    @Override
    public boolean save(Product product) {
        product.setCreateTime(System.currentTimeMillis());
        baseMapper.insert(product);
        ProductExt ext = product.getExt();
        ext.setProductId(product.getId());
        productExtMapper.insert(ext);
        return true;
    }

    /**
     * 更新商品
     * @param product
     * @return
     */
    @Override
    public boolean updateById(Product product) {
        product.setUpdateTime(System.currentTimeMillis());
        baseMapper.updateById(product);
        ProductExt ext=product.getExt();
        productExtMapper.updateById(product.getExt());
        return true;
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        ProductExt ext = productExtMapper.getByPid(id);
        System.out.println(ext);
        productExtMapper.deleteById(ext);
        return true;
    }
}
