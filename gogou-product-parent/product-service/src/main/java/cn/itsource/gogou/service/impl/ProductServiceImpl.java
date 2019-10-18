package cn.itsource.gogou.service.impl;

import cn.itsource.gogou.domain.Product;
import cn.itsource.gogou.domain.ProductExt;
import cn.itsource.gogou.mapper.ProductExtMapper;
import cn.itsource.gogou.mapper.ProductMapper;
import cn.itsource.gogou.query.ProductQuery;
import cn.itsource.gogou.service.IProductService;
import cn.itsource.gogou.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

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

    @Override
    public PageList<Product> queryPage(ProductQuery query) {
        IPage iPage = baseMapper.queryPage(new Page(query.getPageNum(), query.getPageSize()), query);
        return new PageList<>(iPage.getTotal(),iPage.getRecords());
    }

    @Override
    public boolean save(Product product) {
        product.setCreateTime(System.currentTimeMillis());
        baseMapper.insert(product);
        ProductExt ext = product.getExt();
        ext.setProductId(product.getId());
        productExtMapper.insert(ext);
        return true;
    }

    @Override
    public boolean updateById(Product product) {
        product.setUpdateTime(System.currentTimeMillis());
        baseMapper.updateById(product);
        ProductExt ext=product.getExt();
        productExtMapper.updateById(product.getExt());
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        ProductExt ext = productExtMapper.getByPid(id);
        System.out.println(ext);
        productExtMapper.deleteById(ext);
        return true;
    }
}
