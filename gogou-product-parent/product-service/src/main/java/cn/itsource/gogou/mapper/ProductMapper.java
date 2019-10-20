package cn.itsource.gogou.mapper;

import cn.itsource.gogou.domain.Product;
import cn.itsource.gogou.domain.Specification;
import cn.itsource.gogou.query.ProductQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author yun
 * @since 2019-10-17
 */
public interface ProductMapper extends BaseMapper<Product> {

    IPage queryPage(Page page, @Param("query")ProductQuery query);

    /**
     * 查询显示属性
     * @param productId
     * @return
     */
    Product getViewsProperties(Long productId);
    /**
     * 更新显示属性
     * @param productId
     * @param viewsPropertyJson
     * @return
     */
    void updateViewsProperties(@Param("productId") Long productId, @Param("viewsPropertyJson") String viewsPropertyJson);
    /**
     * 更新SKU属性
     * @param productId
     * @param skuPropertyJson
     * @return
     */
    void updateSkuProperties(@Param("productId") Long productId,@Param("skuPropertyJson") String skuPropertyJson);
}
