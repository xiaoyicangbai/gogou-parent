package cn.itsource.gogou.controller;

import cn.itsource.gogou.ProductApplication;
import cn.itsource.gogou.mapper.BrandMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class BrandControllerTest {
    @Autowired
    private BrandMapper brandMapper;
    @Test
    public void save() {

    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
        System.out.println(brandMapper.selectById(1L));
    }

    @Test
    public void list() {
        brandMapper.selectList(null).forEach(e-> System.out.println(e));
    }

    @Test
    public void json() {
    }
}