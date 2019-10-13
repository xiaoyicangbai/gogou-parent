package cn.itsource.gogou.service.impl;

import cn.itsource.gogou.domain.ProductType;
import cn.itsource.gogou.mapper.ProductTypeMapper;
import cn.itsource.gogou.service.IProductTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author yun
 * @since 2019-10-12
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Override
    public List<ProductType> loadTypeTree() {

        return loadTypeTreeLoop();
    }

    public List<ProductType> loadTypeTreeRecursive(Long parentId){
        List<ProductType> productTypes =
                baseMapper.selectList(new QueryWrapper<ProductType>().eq("pid", parentId));
        if(productTypes==null){
            return null;
        }
        for (ProductType productType : productTypes) {
            List<ProductType> children = loadTypeTreeRecursive(productType.getPid());
            if (children!=null){
                productType.setChildren(children);
            }
        }
        return productTypes;
    }

    /**
     * 循环实现1
     * @param
     * @return
     */
    public List<ProductType> loadTypeTreeLoop(){
        List<ProductType> productTypes =
                baseMapper.selectList(null);
        //定义一级菜单集合
        List<ProductType> firstType = new ArrayList<>();
        //循环取出所有，判断是否一级
        for (ProductType productType : productTypes) {
            if(productType.getPid()==0){
                firstType.add(productType);
            }else {
                //如果不是一级，获取他的父级,
                for(ProductType parent : productTypes){
                    if(parent.getId().equals(productType.getPid())){
                        System.out.println(parent);
                        parent.getChildren().add(productType);
                    }
                }
            }
        }
        return firstType;
    }


}
