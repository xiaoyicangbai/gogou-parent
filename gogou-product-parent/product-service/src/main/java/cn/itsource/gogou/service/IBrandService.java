package cn.itsource.gogou.service;

import cn.itsource.gogou.domain.Brand;
import cn.itsource.gogou.domain.ProductType;
import cn.itsource.gogou.query.BrandQuery;
import cn.itsource.gogou.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author yun
 * @since 2019-10-12
 */
public interface IBrandService extends IService<Brand> {


    /**高级查询带分页
     * @param query
     * @return
     */
    PageList<Brand> queryPage(BrandQuery query);

}
