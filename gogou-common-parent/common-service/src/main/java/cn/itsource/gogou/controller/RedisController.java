package cn.itsource.gogou.controller;

import cn.itsource.gogou.util.JedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    /**
     * 从缓存种获取数据
     * @param key
     * @return
     */
    @GetMapping("/redis")
    public String getData(@RequestParam("key") String key){
        System.out.println("++++"+key);
        return JedisUtils.INSTANCE.getJedis().get(key);
    }

    /**
     * 缓存数据
     * @param key
     * @param value
     */
    @PostMapping("/redis")
    public void setData(@RequestParam("key") String key,@RequestParam("value") String value){
        JedisUtils.INSTANCE.getJedis().set(key,value);
    }

}
