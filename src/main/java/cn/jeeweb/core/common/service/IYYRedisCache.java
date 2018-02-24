package cn.jeeweb.core.common.service;

import org.springframework.cache.Cache;

public interface IYYRedisCache extends Cache  {

	public void put(Object key, Object value, final long liveTime);

}