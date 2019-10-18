package cn.itsource.gogou.service;

import cn.itsource.gogou.domain.ProductExt;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品扩展 服务类
 * </p>
 *
 * @author yun
 * @since 2019-10-17
 */
public interface IProductExtService extends IService<ProductExt> {

    ProductExt getByPid(Long pid);
}
