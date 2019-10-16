package cn.itsource.gogou.client;

import cn.itsource.gogou.client.impl.RedisClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "GOGOU-COMMON",fallback = RedisClientImpl.class)
public interface RedisClient {
    /**
     * 从缓存种获取数据
     * @param key
     * @return
     */
    @GetMapping("/redis")
    public String getData(@RequestParam("key") String key);

    /**
     * 缓存数据
     * @param key
     * @param value
     */
    @PostMapping("/redis")
    public void setData(@RequestParam("key") String key,@RequestParam("value") String value);
}
