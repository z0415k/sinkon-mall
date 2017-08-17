package com.sinkon.utils;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis {
	private static JedisPool jedisPool= null;
	private Logger log = Logger.getLogger(this.getClass());
	
	static{
		//获取redis 配置
		ResourceBundle bundle=ResourceBundle.getBundle("redis"); 
        if (bundle == null) {
            throw new IllegalArgumentException("[redis.properties] is not found!");
        }
        if (jedisPool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(Integer.parseInt(bundle.getString("maxIdle")));
            config.setMaxWaitMillis(Integer.parseInt(bundle.getString("maxWait")));
            config.setTestOnBorrow(Boolean.parseBoolean(bundle.getString("testOnBorrow")));
            config.setTestOnReturn(Boolean.parseBoolean(bundle.getString("testOnReturn")));
            jedisPool = new JedisPool(config,bundle.getString("ip"),Integer.parseInt(bundle.getString("port")));
        }
	}
	
/*	
	 * 连接redis
	 
	public static void init(){
		//获取redis 配置
		ResourceBundle bundle=ResourceBundle.getBundle("redis"); 
        if (bundle == null) {
            throw new IllegalArgumentException("[redis.properties] is not found!");
        }
        if (jedisPool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(Integer.parseInt(bundle.getString("maxIdle")));
            config.setMaxWaitMillis(Integer.parseInt(bundle.getString("maxWait")));
            config.setTestOnBorrow(Boolean.parseBoolean(bundle.getString("testOnBorrow")));
            config.setTestOnReturn(Boolean.parseBoolean(bundle.getString("testOnReturn")));
            jedisPool = new JedisPool(config,bundle.getString("ip"),Integer.parseInt(bundle.getString("port")));
        }
	}*/
	
    /**
     * 从jedis连接池中获取获取jedis对象
     * @return
     */
    public Jedis getJedis() {
        return jedisPool.getResource();
    }
	
	/**
	 * 
	 * @param String key    键
	 * @param String value  值
	 */
	public void set(String key, String value) {
		Jedis jedis = getJedis();
		jedis.set(key, value);
	}

	/**
	 * 
	 * @param String key 键
	 * @return
	 */
	public String get(String key){
		Jedis jedis = getJedis();
		return jedis.get(key);
	}
	
    /**
     * 回收jedis
     * @param jedis
     */
    public void returnJedis(Jedis jedis) {
    	jedis.close();
    }
}
