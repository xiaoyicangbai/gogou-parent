package cn.itsource.gogou.mapper;

import cn.itsource.gogou.domain.ProductExt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>
 * 商品扩展 Mapper 接口
 * </p>
 *
 * @author yun
 * @since 2019-10-17
 */
@Component
public interface ProductExtMapper extends BaseMapper<ProductExt> {

    ProductExt getByPid(Serializable pid);
}
