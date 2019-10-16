package cn.itsource.gogou.service.impl;

import cn.itsource.gogou.client.RedisClient;
import cn.itsource.gogou.client.StaticPageClient;
import cn.itsource.gogou.domain.ProductType;
import cn.itsource.gogou.mapper.ProductTypeMapper;
import cn.itsource.gogou.service.IProductTypeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private StaticPageClient staticPageClient;
    /**
     * 生成静态home页面
     * @return
     */
    @Override
    public void genHomePage() {
        String templatePath = "E:\\IdeaProjects\\gogou-parent\\gogou-product-parent\\product-service\\src\\main\\resources\\template\\product.type.vm";
        String targetPath = "E:\\IdeaProjects\\gogou-parent\\gogou-product-parent\\product-service\\src\\main\\resources\\template\\product.type.vm.html";
        List<ProductType> productTypes = loadTypeTreeLoop2();
        //先生成product.type.vm.html
        staticPageClient.genStaticPage(templatePath,targetPath,productTypes);

        //生成home.html
        templatePath="E:\\IdeaProjects\\gogou-parent\\gogou-product-parent\\product-service\\src\\main\\resources\\template\\home.vm";
        Map<String,Object> model = new HashMap<>();
        model.put("staticRoot", "E:\\IdeaProjects\\gogou-parent\\gogou-product-parent\\product-service\\src\\main\\resources\\");
        targetPath="E:\\IdeaProjects\\gogou-web-parent\\ecommerce\\home.html";
        staticPageClient.genStaticPage(templatePath,targetPath,model);
    }

    /**
     * 加载类型树
     * @return
     */
    @Override
    public List<ProductType> loadTypeTree() {
        //先从redis缓存种查询数据
        System.out.println("查询reids```");
        String productTypes = redisClient.getData("productTypes");
        System.out.println("========");
        List<ProductType> productTypes1=null;
        if (productTypes!=null){
            productTypes1 = JSON.parseArray(productTypes, ProductType.class);
        }else {
            //如果缓存种没有，则在查询数据库
            System.out.println("查询数据库");
            productTypes1 = loadTypeTreeLoop();
            //再将数据库中的数据查询出来存入缓存中
            String s = JSON.toJSONString(productTypes1);
            redisClient.setData("productTypes",s);
        }
        return productTypes1;
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
    /**
     * 循环实现2
     * @param
     * @return
     */
    public List<ProductType> loadTypeTreeLoop2(){
        //将所有类型查出来保存到集合中
        List<ProductType> productTypes =baseMapper.selectList(null);
        //定义list集合存放一级类型
        List<ProductType> firstProductType =new ArrayList<>();
        //存放所有类型
        Map<Long,ProductType> productTypeMap = new HashMap<>();
        //将查询出的所有类型保存到map集合中
        for (ProductType type : productTypes) {
            productTypeMap.put(type.getId(),type);
        }
        for (ProductType type : productTypes) {
            //如果pid为0，则说明是以一级，保存到一级类型的集合
            if(type.getPid()==0){
                firstProductType.add(type);
            }else {
                //如果不是一级类型，则从map集合中去除父类型，并存入父类型的children集合中
                ProductType productType = productTypeMap.get(type.getPid());
                productType.getChildren().add(type);
            }
        }
        return firstProductType;
    }

    @Override
    public boolean save(ProductType entity) {
         super.save(entity);
         synchronizedOption();
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        synchronizedOption();
        return true;
    }

    @Override
    public boolean updateById(ProductType entity) {
        super.updateById(entity);
        synchronizedOption();
        return true;
    }

    /**
     * 同步数据库数据到redis
     */
    private void synchronizedOption(){
        List<ProductType> productTypes = loadTypeTreeLoop2();
        //再将数据库中的数据查询出来存入缓存中
        String s = JSON.toJSONString(productTypes);
        redisClient.setData("productTypes",s);
        genHomePage();
    }

}
