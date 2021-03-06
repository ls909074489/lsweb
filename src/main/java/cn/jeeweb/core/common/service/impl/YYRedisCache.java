package cn.jeeweb.core.common.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.IYYRedisCache;
import cn.jeeweb.core.utils.SerializeUtil;

@Transactional
@Service("yyRedisCache")
public class YYRedisCache implements IYYRedisCache {

	private static Logger logger = LoggerFactory.getLogger(YYRedisCache.class);
	
	private RedisTemplate<String, Object> redisTemplate;
	
	private String name;

	/*private static final long liveTime = 864000*/;

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		return this.redisTemplate;
	}

	/**
	 * 获取所有的key
	 * 
	 * @param pattern
	 *            前缀
	 * @return
	 */
	public Set<String> getKeys(String pattern) {
		return redisTemplate.keys(pattern + "*");
	}

	@Override
	public ValueWrapper get(Object key) {
		final String keyf = (String) key;
		Object object = null;
		object = redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {

				byte[] key = keyf.getBytes();
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				return SerializeUtil.toObject(value);
			}
		});
		return (object != null ? new SimpleValueWrapper(object) : null);
	}

	@Override
	public void put(Object key, Object value) {
		final String keyf = (String) key;
		final Object valuef = value;
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyf.getBytes();
				byte[] valueb = SerializeUtil.toByteArray(valuef);
				connection.set(keyb, valueb);
				return 1L;
			}
		});
	}

	/**
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public void put(Object key, Object value, final long liveTime) {
		final String keyf = (String) key;
		final Object valuef = value;
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyf.getBytes();
				byte[] valueb = SerializeUtil.toByteArray(valuef);
				connection.set(keyb, valueb);
				if (liveTime > 0) {
					connection.expire(keyb, liveTime);
				}
				return 1L;
			}
		});
	}

	@Override
	public void evict(Object key) {
		final String keyf = (String) key;
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.del(keyf.getBytes());
			}
		});
	}

	@Override
	public void clear() {
		redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		return null;
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		return null;
	}

	/**
	 * 序号
	 * 
	 * @param key
	 * @param num
	 * @return
	 */
	public Long buildNum(String key, int num) {
		return redisTemplate.opsForValue().increment(key, num);
		// return count;
	}

	/*
	 **
	 * 缓存基本的对象，Integer、String、实体类等
	 *
	 * @param key 缓存的键值
	 * 
	 * @param value 缓存的值
	 * 
	 * @return 缓存的对象
	 */
	public void setCacheObject(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public void setCacheString(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 获得缓存的基本对象。
	 *
	 * @param key
	 *            缓存键值
	 * @return
	 * @return 缓存键值对应的数据
	 */
	public Object getCacheObject(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 移除缓存
	 * 
	 * @param key
	 * @return
	 */
	public boolean remove(String key) {
		try {
			redisTemplate.delete(key);
			return true;
		} catch (Throwable t) {
			logger.error("获取缓存失败key[" + key + ", error[" + t + "]");
		}
		return false;
	}


}