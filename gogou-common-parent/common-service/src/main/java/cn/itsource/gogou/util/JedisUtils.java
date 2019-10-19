package cn.itsource.gogou.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 * @author yun
 */
public enum JedisUtils {
    INSTANCE;
    private static JedisPool pool;
    static {
        //创建配置文件对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(10);
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        //最大等待时间
        config.setMaxWaitMillis(2*1000);
        //获取连接时测试连接是否畅通
        config.setTestOnBorrow(true);

        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        config.setSoftMinEvictableIdleTimeMillis(800000);

        //创建连接池对象
        pool = new JedisPool(config,"127.0.0.1",6379, 6000, "itsource");
    }

    /**
     * @return
     */
    //获取连接对象
    public Jedis getJedis(){
        return pool.getResource();
    }
    //关闭资源
    public void close(Jedis jedis){
        if (jedis != null) {
            jedis.close();
        }
    }

}
