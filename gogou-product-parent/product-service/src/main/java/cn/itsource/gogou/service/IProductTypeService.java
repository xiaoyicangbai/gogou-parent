package cn.itsource.gogou.service;

import cn.itsource.gogou.domain.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author yun
 * @since 2019-10-12
 */
public interface IProductTypeService extends IService<ProductType> {
    List<ProductType> loadTypeTree();
}
