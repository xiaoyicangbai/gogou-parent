package cn.itsource.gogou.client.impl;

import cn.itsource.gogou.client.RedisClient;

/**
 * 拖底数据
 */
public class RedisClientImpl implements RedisClient {
    @Override
    public String getData(String key) {
        return "{\"message\",\"服务器异常\"}";
    }

    @Override
    public void setData(String key, String value) {}
}
