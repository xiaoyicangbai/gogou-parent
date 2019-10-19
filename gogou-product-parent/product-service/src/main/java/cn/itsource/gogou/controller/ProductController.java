package cn.itsource.gogou.controller;

import cn.itsource.gogou.domain.Specification;
import cn.itsource.gogou.query.ProductQuery;
import cn.itsource.gogou.service.IProductService;
import cn.itsource.gogou.domain.Product;
import cn.itsource.gogou.util.AjaxResult;
import cn.itsource.gogou.util.PageList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    public IProductService productService;

    /**
    * 保存和修改公用的
    * @param product  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Product product){
        try {
            if(product.getId()!=null){
                productService.updateById(product);
            }else{
                productService.save(product);
            }
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            productService.removeById(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.getAjaxResult().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product get(@PathVariable("id") Long id){
        return productService.getById(id);
    }

    /**
    * 查看所有
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Product> list(){

        return productService.list(null);
    }

    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Product> json(@RequestBody ProductQuery query){
        return productService.queryPage(query);
    }

    /**
     * 查询显示属性
     * @param productId
     * @return
     */
    @GetMapping("/getViewsProperties/{productId}")
    public List<Specification> getViewsProperties(@PathVariable("productId") Long productId){
       return productService.getViewsProperties(productId);
    }

    /**
     * 更新显示属性
     * @param productId
     * @param viewProperty
     * @return
     */
    @PostMapping("/updateViewsProperties")
    public AjaxResult updateViewsProperties(
            @RequestParam("productId")String productId,
            @RequestBody List<Specification> viewProperty){
        try {
            productService.updateViewsProperties(productId,viewProperty);
            return AjaxResult.getAjaxResult().setMessage("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("操作失败:"+e.getMessage());
        }
    }
    /**
     * 查询SKU属性
     * @param productId
     * @return
     */
    @GetMapping("/getSkuProperties/{productId}")
    public List<Specification> getSkuProperties(@PathVariable("productId") Long productId){
        return productService.getSkuProperties(productId);
    }
}
