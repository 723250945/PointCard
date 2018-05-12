package cn.jbit.redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
public class RedisDataSource{
        private ShardedJedisPool shardedJedisPool;

    /**
     * spring注入shardedJedisPool
     * @param shardedJedisPool
     */
    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
                this.shardedJedisPool = shardedJedisPool;
    }

     /**
     * 获取redis客户端ShardedJedis对象
     * @return
     */
     public ShardedJedis getRedisClient(){
                try {
                  return shardedJedisPool.getResource();
               } catch (Exception e) {
                   System.err.println("get resource error");
               }
              return null;
            }
        }
