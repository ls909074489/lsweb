package cn.jeeweb.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import cn.jeeweb.core.common.entity.AbstractEntity;
import net.sf.json.JSONObject;

/**   
* 
* @author xuechen 
* @date 2016年2月22日 上午11:00:00 
*/
public class EntityUtil {
	
	public static Map<String, String> tableNameMap;
	
	private static DataSource dataSource;
	
	
	public static <T> List<T> convertToEntities(String[] paramArr,Class<T> clazz) {
		List<T> returnList = new ArrayList<T>();
		if(paramArr==null||paramArr.length==0)
			return returnList;
		for(String data:paramArr){
			JSONObject jsonObject = new JSONObject();
			String[] properties = data.split("&");
			for(String property : properties){
				String[] nameAndValue =property.split("=");
				if(nameAndValue.length==2){
					try {
						nameAndValue[0] = URLDecoder.decode(nameAndValue[0], "UTF-8");
						nameAndValue[1] = URLDecoder.decode(nameAndValue[1], "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					jsonObject.put(nameAndValue[0], nameAndValue[1]);
				}
			}
			@SuppressWarnings("unchecked")
			T obj = (T)JSONObject.toBean(jsonObject,clazz);
			returnList.add(obj);
		}
		return returnList;
	}
	
	
	/**
	 * 将String List转换为字符串, eg. List(a, b, c) -> “a, b, c”
	 * @param strList
	 * @return str
	 */
	public static String convertStringListToString(List<String> strList) {
		StringBuilder str = new StringBuilder();
		if(strList == null || strList.isEmpty()) {
			return str.toString();
		}
		for(int i = 0; i < strList.size(); i++) {
			if(i == 0) {
				str.append(strList.get(i));
				continue;
			}
			str.append(",");
			str.append(strList.get(i));
		}
		return str.toString();
	}
	
	/**
	 * 将String 数组转换为字符串, eg. List(a, b, c) -> “a, b, c”
	 * @param strList
	 * @return str
	 */
	public static String convertStringsToString(String[] strings) {
		StringBuilder str = new StringBuilder();
		if(strings == null || strings.length <= 0) {
			return str.toString();
		}
		for(int i = 0; i < strings.length; i++) {
			if(i == 0) {
				str.append(strings[i]);
				continue;
			}
			str.append(",");
			str.append(strings[i]);
		}
		return str.toString();
	}
	
	
	/**
	 * 得到当前superList内entity的UUID String用 ',' 号分隔
	 * @param entityList
	 * @return
	 */
	public static <T extends AbstractEntity> String convertEntitysToUUIDString(List<T> entityList) {
		StringBuilder str = new StringBuilder();
		if(entityList == null || entityList.isEmpty()) {
			return str.toString();
		}
		for(int i = 0; i < entityList.size(); i++) {
			if(i == 0) {
				str.append(entityList.get(i).getId());
				continue;
			}
			str.append(",");
			str.append(entityList.get(i).getId());
		}
		return str.toString();
	}
	
	/**
	 * 得到当前superList内entity的UUID, eg.  -> “'a', 'b', 'c'”
	 * @param strList
	 * @return str
	 */
	public static <T extends AbstractEntity> String convertEntitysToUUIDToINSqlString(List<T> entityList) {
		StringBuilder str = new StringBuilder();
		if(entityList == null || entityList.isEmpty()) {
			return str.toString();
		}
		for(int i = 0; i < entityList.size(); i++) {
			if(i == 0) {
				str.append("'" + entityList.get(i).getId() + "'");
				continue;
			}
			str.append(",");
			str.append("'" + entityList.get(i).getId() + "'");
		}
		return str.toString();
	}
	
	/**
	 * 将String List转换为字符串, eg. List(a, b, c) -> “'a', 'b', 'c'”
	 * @param strList
	 * @return str
	 */
	public static String convertStringListToINSqlString(List<String> strList) {
		StringBuilder str = new StringBuilder();
		if(strList == null || strList.isEmpty()) {
			return str.toString();
		}
		for(int i = 0; i < strList.size(); i++) {
			if(i == 0) {
				str.append("'" + strList.get(i) + "'");
				continue;
			}
			str.append(",");
			str.append("'" + strList.get(i) + "'");
		}
		return str.toString();
	}
	
	
	/**
	 * 得到当前superList内entity的UUID List<string>
	 * @param entityList
	 * @return
	 */
	public static <T extends AbstractEntity> List<Object> convertEntitysToUUIDList(List<T> entityList) {
		List<Object> resultList = new ArrayList<Object>();
		if(entityList == null || entityList.isEmpty()) {
			return resultList;
		}
		for(T t : entityList) {
			resultList.add(t.getId());
		}
		return resultList;
	}
	
	public static <T> String getTableName(Class<T> cls) {
		buildTableNameMap();
		if(!tableNameMap.containsKey(cls.getName())) {
			return StringUtils.EMPTY;
		}
		return tableNameMap.get(cls.getName());
	}
	
	private static Map<String, String> buildTableNameMap() {
		if(tableNameMap != null) { 
			return tableNameMap;
		}
		tableNameMap = new HashMap<String, String>();
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext(); 
    	EntityManagerFactory entityManagerFactory = (EntityManagerFactory) wac.getBean("entityManagerFactory");
    	EntityManager entityManger = entityManagerFactory.createEntityManager();
    	Session session = (Session) entityManger.getDelegate();
    	SessionFactory  sessionFactory = session.getSessionFactory();
    	buildDataSource(sessionFactory);
    	Map<String, ClassMetadata> metaMap = sessionFactory.getAllClassMetadata();
    	for(String key : metaMap.keySet()) {
    		AbstractEntityPersister  aep = (AbstractEntityPersister) metaMap.get(key);
    		tableNameMap.put(key, aep.getTableName());
    	}
    	return tableNameMap;
	}
	
	public static DataSource buildDataSource(SessionFactory  sessionFactory) {
		if(dataSource != null) {
			return dataSource;
		}
		dataSource = SessionFactoryUtils.getDataSource(sessionFactory);
		return dataSource;
	}
	
	public static DataSource getDataSource() {
		if(dataSource != null) {
			return dataSource;
		}
		buildTableNameMap();
		return dataSource;
	}
	
	/**
	 * 去除Json特殊字符
	 * @param message
	 * @return
	 */
	public static String replaceEscapeSymbol(String message) {
		if(StringUtils.isBlank(message)) {
			return message;
		}
		return StringEscapeUtils.escapeEcmaScript(message); 
	}
	
	/**
	 * 转义SQL参数，拼接SQL时使用，防止SQL攻击
	 * in 查询内含有 '的不需要使用
	 * @param paramter
	 * @return
	 */
	public static String replaceSqlParamter(String paramter) {
		if(StringUtils.isBlank(paramter)) {
			return paramter;
		}
		return org.apache.commons.lang.StringEscapeUtils.escapeSql(paramter);
	}
	
	/**
	 * 转义SQL参数，拼接SQL时使用，防止SQL攻击
	 * in 查询内含有 '的不需要使用
	 * @param Map<String, Object> paramterMap
	 * @return
	 */
	public static Map<String, Object> replaceSqlParamterMap(Map<String, Object> paramterMap) {
		Map<String, Object> paramterMapResult = new HashMap<String, Object>();
		if(paramterMap == null || paramterMap.isEmpty()) {
			return paramterMap;
		}
		for(Map.Entry<String, Object> entry : paramterMap.entrySet()) {
			Object obj = entry.getValue();
			if(!(obj instanceof String)&&!(obj instanceof String[])){
				if(null!=obj){
					paramterMapResult.put(entry.getKey(), obj);
				}
				continue;
			}
			paramterMapResult.put(entry.getKey(), replaceSqlParamter(String.valueOf(obj)));
			if(obj instanceof String[]) {
				String[] values = (String[]) obj;
				if(values == null || values.length == 0) {
					continue;
				}
				for(int i = 0; i < values.length; i++) {
					values[i] = replaceSqlParamter(values[i]);
				}
				paramterMapResult.put(entry.getKey(), values);
			}
			
		}
		return paramterMapResult;
	}
	
	/**
	 * 转义SQL参数，拼接SQL时使用，防止SQL攻击
	 * in 查询内含有 '的不需要使用
	 * @param Map<String, String[]> paramterMap
	 * @return
	 */
	public static Map<String, String[]> replaceSqlArrayParamterMap(Map<String, String[]> paramterMap) {
		Map<String, String[]> paramterMapResult = new HashMap<String, String[]>();
		if(paramterMap == null || paramterMap.isEmpty()) {
			return paramterMap;
		}
		for(Map.Entry<String, String[]> entry : paramterMap.entrySet()) {
			String[] values = entry.getValue();
			if(values == null || values.length == 0) {
				continue;
			}
			for(int i = 0; i < values.length; i++) {
				values[i] = replaceSqlParamter(values[i]);
			}
			paramterMapResult.put(entry.getKey(), values);
		}
		return paramterMapResult;
	}
	
	/**
	 * 转义SQL参数，拼接SQL时使用，防止SQL攻击
	 * in 查询内含有 '的不需要使用
	 * @param Map<String, String> paramterMap
	 * @return
	 */
	public static Map<String, String> replaceSqlParamterStrMap(Map<String, String> paramterMap) {
		Map<String, String> paramterMapResult = new HashMap<String, String>();
		if(paramterMap == null || paramterMap.isEmpty()) {
			return paramterMap;
		}
		for(Map.Entry<String, String> entry : paramterMap.entrySet()) {
			paramterMapResult.put(entry.getKey(), replaceSqlParamter(entry.getValue()));
		}
		return paramterMapResult;
	}
	
}
