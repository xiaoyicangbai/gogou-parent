package cn.itsource.gogou.service.impl;

import cn.itsource.gogou.domain.ProductExt;
import cn.itsource.gogou.mapper.ProductExtMapper;
import cn.itsource.gogou.service.IProductExtService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 商品扩展 服务实现类
 * </p>
 *
 * @author yun
 * @since 2019-10-17
 */
@Service
public class ProductExtServiceImpl extends ServiceImpl<ProductExtMapper, ProductExt> implements IProductExtService {

    @Autowired
    private ProductExtMapper productExtMapper;

    @Override
    public ProductExt getByPid(Long pid) {
        return productExtMapper.getByPid(pid);
    }
}
