package cn.jbit.redis;

import cn.jbit.util.FastJSONUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
@Service("jedis")
public class Jedis {
    private ShardedJedis client;

    public void setClient(ShardedJedis client) {
        this.client = client;
    }

    public ShardedJedis getClient() {
        if(this.client==null){
            try {
                ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
                System.out.println(app);
                this.client = app.getBean("data",RedisDataSource.class).getRedisClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.client;
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        String result = null;
        if (client == null) {
            client=this.getClient();
        }
        try {
            result = client.set(key, FastJSONUtil.serialize(value));//使用FastJSON序列化更轻便快捷
            System.out.println("添加"+key+"值成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取单个值
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        if (client == null) {
            client=this.getClient();
        }
        try {
            result = client.get(key);
            System.out.println("获取"+key+"值成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断key值是否存在
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        Boolean result = false;
        if (client == null) {
            client=this.getClient();
        }
        try {
            result = client.exists(key);
            System.out.println("key:"+key+"存在！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
