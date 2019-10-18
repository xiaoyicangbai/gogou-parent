package cn.itsource.gogou.service;

import cn.itsource.gogou.domain.Product;
import cn.itsource.gogou.query.ProductQuery;
import cn.itsource.gogou.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
