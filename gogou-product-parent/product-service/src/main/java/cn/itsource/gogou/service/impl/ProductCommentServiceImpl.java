package cn.itsource.gogou.service.impl;

import cn.itsource.gogou.domain.ProductComment;
import cn.itsource.gogou.mapper.ProductCommentMapper;
import cn.itsource.gogou.service.IProductCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价 服务实现类
 * </p>
 *
 * @author yun
 * @since 2019-10-17
 */
@Service
public class ProductCommentServiceImpl extends ServiceImpl<ProductCommentMapper, ProductComment> implements IProductCommentService {

}
