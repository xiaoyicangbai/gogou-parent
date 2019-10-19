package cn.itsource.gogou.service;

import cn.itsource.gogou.domain.Product;
import cn.itsource.gogou.domain.Specification;
import cn.itsource.gogou.query.ProductQuery;
import cn.itsource.gogou.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author yun
 * @since 2019-10-17
 */
public interface IProductService extends IService<Product> {

    PageList<Product> queryPage(ProductQuery query);

    /**
     * 查询SKU属性
     * @param productId
     * @return
     */
    List<Specification> getSkuProperties(Long productId);

    /**
     * 查询显示属性
     * @param productId
     * @return
     */
    List<Specification> getViewsProperties(Long productId);

    /**
     * 更新显示属性
     * @param productId
     * @param viewProperty
     * @return
     */
    void updateViewsProperties(String productId, List<Specification> viewProperty);
}
