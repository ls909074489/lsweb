package cn.jeeweb.core.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.jeeweb.core.base.BaseEntity;
import cn.jeeweb.core.common.dao.ICommonDao;
import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.core.common.service.IYYRedisCache;
import cn.jeeweb.core.utils.RedisKey;
import cn.jeeweb.core.utils.SerializeUtil;

@Transactional
@Service("redisUtilsService")
public class RedisUtilsService {
	
	@Autowired
	private IYYRedisCache yYRedisCache;
	@Autowired
	ICommonDao commonDao;
	
	private static Long LIVE_TIME = 3600L;
	
	private RedisTemplate<String, Object> redisTemplate;
	
	private HashOperations<String, String, Object> hashOps;
	
	
	/**
	 * 将String值放入缓存中
	 * @param redisKey
	 * @param value
	 * @param liveTime 如liveTime < 0，则永久保存该字段（慎用）
	 */
	public void putString(String redisKey, String value, long liveTime) {
//		redisTemplate.opsForValue().set(redisKey, value, liveTime);
		yYRedisCache.put(redisKey, value, liveTime);
	}
	
	/**
	 * 将String值放入缓存中 默认保存一个小时
	 * @param redisKey
	 * @param value
	 */
	public void putString(String redisKey, String value) {
		putString(redisKey, value, LIVE_TIME);
	}
	
	public String getString(String redisKey) {
		 return (String) redisTemplate.opsForValue().get(redisKey);
	}
	
	/**
	 * 将EntityList存入到缓存中
	 * @param redisKey
	 * @param list
	 */
	public <T extends BaseEntity> void addEntityList(String redisKey, List<T> list) {
		if(list == null || list.isEmpty()) {
			return;
		}
		yYRedisCache.put(redisKey, list, LIVE_TIME);
	}
	
	/**
	 * 将EntityList存入到缓存中
	 * @param redisKey
	 * @param list
	 */
	public <T extends BaseEntity> void addEntityList(String redisKey, List<T> list, long liveTime) {
		if(list == null || list.isEmpty()) {
			return;
		}
		yYRedisCache.put(redisKey, list, liveTime);
	}
	
	/**
	 * 得到缓存中的List数据
	 * @param redisKey
	 * @param cls
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> List<T> getEntityList(String redisKey, Class<T> cls) {
		List<T> list = null;
		ValueWrapper obj = yYRedisCache.get(redisKey);
		if(obj != null) {
			list = (List<T>) obj.get();
		}
		return list;
	}
	
	/**
	 * 增加VOList到缓存 
	 * @param redisKey
	 * @param list
	 * @param liveTime 缓存时间，当liveTime < 0时则永久存在（慎用）单位为秒
	 */
	public <T extends BaseEntity> void addVOList(String redisKey, List<T> list, long liveTime) {
		if(list == null || list.isEmpty()) {
			return;
		}
		try{
			yYRedisCache.put(redisKey, list, liveTime);
		} catch(Exception e) {
			try{
				delete(redisKey);
			} catch(Exception e1) {
				
			}
		}
	}
	
	/**
	 * 增加VOList到缓存
	 * @param redisKey
	 * @param list
	 */
	public <T extends BaseEntity> void addVOList(String redisKey, List<T> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		try {
			yYRedisCache.put(redisKey, list, LIVE_TIME);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				delete(redisKey);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 增加VOList到缓存
	 * @param redisKey
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> List<T> getVOList(String redisKey, Class<T> cls) {
		List<T> list = null;
		try {
			ValueWrapper obj = yYRedisCache.get(redisKey);
			if(obj != null) {
				list = (List<T>) obj.get();
			}
		} catch (Exception e1) {
			return list;
		}
		return list;
	}
	
    /**
     * 插入缓存值
     * @param entity
     */
	public <T extends AbstractEntity<String>> void putEntity(T entity) {
		if(entity == null) {
			return;
		}
		String redisKey = getEntityRedisKey(entity.getClass(), entity.getId());
		yYRedisCache.put(redisKey, entity, LIVE_TIME);
	}
	
	/**
	 * 插入缓存值
	 * @param entity
	 * @param liveTime 缓存有效时间
	 */
	public <T extends AbstractEntity<String>> void putEntity(T entity, long liveTime) {
		if(entity == null) {
			return;
		}
		String redisKey = getEntityRedisKey(entity.getClass(), entity.getId());
		yYRedisCache.put(redisKey, entity, liveTime);
	}
	
	public <T extends BaseEntity> void putVOBeanEntity(T entity, String redisKey) {
		putVOBeanEntity(entity, redisKey, LIVE_TIME);
	}
	
	public <T extends BaseEntity> void putVOBeanEntity(T entity, String redisKey, long liveTime) {
		yYRedisCache.put(redisKey, entity, liveTime);
	}
	
	/**
     * 得到缓存中的对象
     * @param cls
     * @param uuid
     * @return
     */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> T getEntityInCache(Class<T> cls, String uuid) {
		if(StringUtils.isBlank(uuid)) {
			return null;
		}
		String redisKey = getEntityRedisKey(cls, uuid);
		T t = null;
		ValueWrapper obj = yYRedisCache.get(redisKey);
		if(obj != null) {
			t =  (T) obj.get();
		}
		return t;
	}
	
	/**
	 * 得到缓存中的Bean 利用redisKey
	 * @param cls
	 * @param redisKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> T getVOInCacheByKey(Class<T> cls, String redisKey) {
		if(StringUtils.isBlank(redisKey)) {
			return null;
		}
		T t = null;
		ValueWrapper obj = yYRedisCache.get(redisKey);
		if(obj != null) {
			t =  (T) obj.get();
		}
		return t;
	}
	
	/**
	 * 将map存入redis， 默认1个小时过期
	 * @param map
	 * @param redisKey
	 */
	public void putMap(Map<String, String> map, String redisKey) {
		try {
			hashOps.putAll(redisKey, map);
			setExpire(redisKey, LIVE_TIME);
		} catch (Exception e) {
		}
	}
	
	/**
	 * 将map存入redis.
	 * @param map
	 * @param redisKey
	 * @param liveTime 过期时间， 小于0，为永久不过期 (慎用小于0)
	 */
	public void putMap(Map<String, String> map, String redisKey, long liveTime) {
		try {
			hashOps.putAll(redisKey, map);
			if(liveTime > 0) {
				setExpire(redisKey, liveTime);
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 从redis中拿出map
	 * @param redisKey
	 * @return Map<String, String>
	 */
	public Map<String, Object> getMap(String redisKey) {
		Map<String, Object> map = null;
		try{
			map = hashOps.entries(redisKey);
		} catch(Exception e) {
			map = null;
		}
		if(map == null || map.isEmpty()) {
			return null;
		}
		return map;
	}
	
	/**
	 * 得到redis中的Map对应的Key值
	 * @param redisKey
	 * @param mapKey
	 * @return String
	 */
	public String getMapValueByKey(String redisKey, String mapKey) {
		Object object = hashOps.get(redisKey, mapKey);
		String value = StringUtils.EMPTY;
		if(object == null) {
			return value;
		}
		value = String.valueOf(object);
		return value;
	}
	
	/**
	 * redis对应的Map是否含有对应的Key
	 * @param redisKey
	 * @param mapKey
	 * @return Boolean
	 */
	public Boolean containKey(String redisKey, String mapKey) {
		return hashOps.hasKey(redisKey, mapKey);
	}
	
	/**
	 * 删除对应redisKey的Map中，对应的mapKey值
	 * @param redisKey
	 */
	public void removeMap(String redisKey, String mapKey) {
		if(StringUtils.isBlank(mapKey)) {
			delete(redisKey);
		} else {
			hashOps.delete(redisKey, mapKey);
		}
	}
	
	/**
	 * 删除redisKeys对应的Value
	 * @param redisKeys
	 */
	public void delete(String redisKey) {
		byte[] keyb = redisKey.getBytes();
		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		connection.del(keyb);
	}
	
	/**
	 * 设置一个key的过期时间
	 * @param key
	 * @param liveTime
	 */
	public void setExpire(final String redisKey, final long liveTime) {
		if(liveTime < 0) {
			return;
		}
		byte[] keyb = redisKey.getBytes();
		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		connection.expire(keyb, liveTime);
	}
	
	public void closeConnection() {
		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		connection.close();
	}
	
	
	/**
	 * 根据对象删除List中的值
	 * @param redisKey
	 * @param obj
	 * @return
	 */
	public Long removArrayListByObject(final String redisKey, final Object obj) {
		final byte[] rawKey = rawKey(redisKey);
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.lRem(rawKey, 1, rawValue(obj));
			}
		}, true);
	}
	
	/**
	 * 删除List中第index条数据
	 * @param redisKey
	 * @param index
	 * @return
	 */
	public Long removArrayListByIndex(final String redisKey, final long index) {
		final byte[] rawKey = rawKey(redisKey);
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				byte[] b = connection.lIndex(rawKey(redisKey), index);
				return connection.lRem(rawKey, 1, b);
			}
		}, true);
	}
	
	/**
	 * 返回List 中的第index数据
	 * @param redisKey
	 * @param index
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> T getArrayListObj(String redisKey, final long index, Class<T> cls) {
		byte[] b = getArrayListObjByte(redisKey, index);
		return (T) SerializeUtil.toObject(b);
	}
	
	public byte[] getArrayListObjByte(String redisKey, final long index) {
		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		byte[] b = connection.lIndex(rawKey(redisKey), index);
		connection.close();
		return b;
	}
	
	/**
	 * 得到List长度
	 * @param redisKey
	 * @return
	 */
	public Long getArrayListSize(String redisKey) {
		final byte[] rawKey = rawKey(redisKey);
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.lLen(rawKey);
			}
		}, true);
	}
	
	/**
	 * 得到List
	 * @param redisKey
	 * @return
	 */
	public <T extends Object> List<T> getArrayList(String redisKey, Class<T> cls) {
		return getArrayListByIndex(redisKey, cls, 0, -1);
	}
	
	/**
	 * 得到List, end为-1时得到 start ~ list.size的数据
	 * @param redisKey
	 * @param cls
	 * @param start
	 * @param end
	 * @return
	 */
	public <T extends Object> List<T> getArrayListByIndex(String redisKey, Class<T> cls, final long start, final long end) {
		if (StringUtils.isBlank(redisKey)) {
            return null;
		}
		final byte[] rawKey = redisKey.getBytes();
		return redisTemplate.execute(new RedisCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			public List<T> doInRedis(RedisConnection connection) {
				List<byte[]> list = connection.lRange(rawKey, start, end);
				if(list == null || list.isEmpty()) {
					return null;
				}
				List<T> objList = new ArrayList<T>();
				for(byte[] byt : list) {
					T obj = (T) SerializeUtil.toObject(byt);
					objList.add(obj);
				}
				return objList;
			}
		}, true);
	}
    
	
	 /**
     * 类似链表方式存储List，向链表右侧添加数值，即向List末尾添加数值
     * @param key
     * @param obj
     * @return
     */
	public <T extends Object> Long rightPushObjArrayList(String redisKey, T obj) {
		final byte[] rawKey = rawKey(redisKey);
		final byte[] rawValue = rawValue(obj);
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.rPush(rawKey, rawValue);
			}
		}, true);
	}
	
	 /**
     * 类似链表方式存储List，向链表右侧添加数值，即向List末尾添加数值
     * @param key
     * @param list
     * @return
     */
	public <T extends Object> Long rightPushAllArrayList(String redisKey, List<T> objList) {
		if (objList == null || objList.isEmpty() || StringUtils.isBlank(redisKey)) {
            return 0l;
		}
		final byte[] rawKey = redisKey.getBytes();
		final byte[][] rawValues = rawValues(objList);
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.rPush(rawKey, rawValues);
			}
		}, true);
	}
	
    /**
     * 类似链表方式存储List，向链表左侧添加数值，即向List顶部添加数值
     * @param key
     * @param list
     * @return
     */
	public Long leftPushAllArrayList(String redisKey, List<Object> objList) {
		if (objList == null || objList.isEmpty() || StringUtils.isBlank(redisKey)) {
            return 0l;
		}
		final byte[] rawKey = redisKey.getBytes();
		final byte[][] rawValues = rawValues(objList);
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.lPush(rawKey, rawValues);
			}
		}, true);
	}
	
	 /**
     * 类似链表方式存储List，向链表左侧添加数值，即向List顶部添加数值
     * @param key
     * @param obj
     * @return
     */
	public <T extends Object> Long leftPushObjArrayList(String redisKey, T obj) {
		final byte[] rawKey = rawKey(redisKey);
		final byte[] rawValue = rawValue(obj);
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.lPush(rawKey, rawValue);
			}
		}, true);
	}
	
	private <T extends Object> byte[][] rawValues(List<T> objList) {
		final byte[][] rawValues = new byte[objList.size()][];
		int i = 0;
		for (Object value : objList) {
			rawValues[i++] = rawValue(value);
		}
		return rawValues;
	}
	
	private byte[] rawValue(Object value) {
		return SerializeUtil.toByteArray(value);
	}
    
	private byte[] rawKey(String key) {
		return key.getBytes();
	}
	
    /**
     * 得到Entity的redisKey
     * @param class1
     * @param entity
     * @return
     */
    public String getEntityRedisKey(Class<? extends BaseEntity> class1, String uuid) {
    	String redisKey = RedisKey.ENTITY_PREFIX + RedisKey.DELIMITER + class1.getSimpleName()
		+ RedisKey.DELIMITER + uuid;
    	return redisKey;
    }
    
    public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	public HashOperations<String, String, Object> getHashOps() {
		return hashOps;
	}

	public void setHashOps(HashOperations<String, String, Object> hashOps) {
		this.hashOps = hashOps;
	}
	
	
	/**
	 * 获取list的最右边值并移除
	 */
	public Object rightPopByList(String key){
		final byte[] listkey = rawKey(key);
		return redisTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] bytes = connection.rPop(listkey);
				if(bytes!=null && bytes.length>0) {
					return SerializeUtil.toObject(bytes);
				}
				return null;
			}
		});
	}
	
	/**
     * 得到缓存中的对象若缓存中没有则去数据库中拿，并放入缓存中
     * @param cls
     * @param uuid
     * @return
     */
	public <T extends AbstractEntity<String>> T getEntity(Class<T> cls, String uuid) {
		if(StringUtils.isBlank(uuid)) {
			return null;
		}
		T t = getEntityInCache(cls, uuid);
		if(t == null) {
			t = selectAndSaveValue(cls, uuid);
		} 
		return t;
	}
	
    private <T extends AbstractEntity<String>> T selectAndSaveValue(Class<T> cls, String uuid) {
    	T t = null;
//    	String tableName = EntityUtil.getTableName(cls);
//		String sql = "SELECT * FROM " + tableName + " WHERE status = 1 AND uuid = '" + uuid + "'";
		try {
			t =	commonDao.get(uuid, cls);
			if(t != null) {
				putEntity(t, LIVE_TIME);
			}
		} catch (Exception e) {
			throw new ServiceException("数据库表 " + cls.getName() + ", 与对应实体，存在不对应字段");
		}
		return t;
    }
	
}
