package cn.jeeweb.core.utils;

/**
 * 所有操作Redis的key必须从此获取
 */
public final class RedisKey {

	public static final String ENTITY_PREFIX = "Entity";

	public static final String STRING_PREFIX = "String";

	public static final String HASH_PREFIX = "Hash";

	public static final String SET_PREFIX = "Set";

	public static final String LIST_PREFIX = "List";

	public static final String SORTED_SET_PREFIX = "SortedSet";

	public static final String VO_PREFIX = "VO";

	public static final String QUEUE_PREFIX = "Queue";

	public static final String LOCK_PREFIX = "Lock";

	public static final String DELIMITER = ":";

	
	
	public static final String ALL_FUNCS="all_funcs";//登录去读的所有菜单
	
	public static class RedisKeyConstant {
		
		//用户权限菜单
		public static final String USER_ROLE_FUNS = LIST_PREFIX + DELIMITER + "UserRoleFuns" + DELIMITER;
		//用户的角色
		public static final String USER_ROLES = LIST_PREFIX + DELIMITER + "UserRoles" + DELIMITER;
		//角色的菜单
		public static final String ROLE_FUNCSID = LIST_PREFIX + DELIMITER + "RoleFuncs" + DELIMITER;
		//菜单
		public static final String ALL_FUNCS = LIST_PREFIX + DELIMITER + "AllFuncs" + DELIMITER;
		
		//公司下的  网点ids
		public final static String ORG_SUB_IDS = RedisKey.STRING_PREFIX + ":org:subids:";
		
		//所有的网点、专卖店、经销商
		public final static String LIST_ALL_OUTLETS_AGENCYS = RedisKey.LIST_PREFIX + ":outletsagencys:all";

		//ROS
		public final static String LIST_ROS_ALL = RedisKey.LIST_PREFIX + ":ros:all";
		//ROS受理
		public final static String LIST_ROS_SHOULI_HUANJI = RedisKey.LIST_PREFIX + ":ros:sl:huanji";
		//ROS受理退机
		public final static String LIST_ROS_SHOULI_TUIJI = RedisKey.LIST_PREFIX + ":ros:sl:tuiji";
		//ROS返机
		public final static String LIST_ROS_FANJI_HUANJI = RedisKey.LIST_PREFIX + ":ros:fj:huanji";
		//物料价格 - 有效期内的List
		public final static String LIST_MATERIAL_PRICE_LIST = RedisKey.LIST_PREFIX + RedisKey.DELIMITER + "material:price:";
		
		public final static String STRING_SLSERVICEBILL_SN = RedisKey.STRING_PREFIX + ":slservicebill:sn:";
		
		public final static String STRING_SLSERVICEBILL_MOBILE = RedisKey.STRING_PREFIX + ":slservicebill:sn:mobile:";
		
		public final static String STRING_POSTGOOd = RedisKey.STRING_PREFIX + ":postgood:";
		
		public final static String LIST_OUTLETS_CONF = RedisKey.LIST_PREFIX + RedisKey.DELIMITER + "outletsConf";
		
		public final static String ENTITY_OUTLETS = RedisKey.ENTITY_PREFIX + RedisKey.DELIMITER + "outletsByOrg";
		
		public final static String MAP_MATERIAL_PRICE = RedisKey.HASH_PREFIX + RedisKey.DELIMITER + "material:price:";
		
		public final static String LIST_ORG_MATERIAL_PRICE_KEY = RedisKey.LIST_PREFIX + RedisKey.DELIMITER + "org:material:price:key:";
		
		public final static String LIST_USER_CURRENT_ROLE_KEY = RedisKey.LIST_PREFIX + RedisKey.DELIMITER + "user:currentRole:";
		
		public final static String STRING_SLDELEGATEBILL_MOBILE = RedisKey.STRING_PREFIX + RedisKey.DELIMITER + "sldelegatebill:mobile:";
		
				
		
		/**
		 * 生成网点的工单号key， key规则： String:billcode:单据类型:网点代码
		 */
		public static String getBillcodeRedisKey(String billtype, String orgCode){
			return RedisKey.STRING_PREFIX + ":billcode:"+billtype+":"+orgCode;
		}
		
		public static String getOutletsConfRedisKey(String uuid) {
			return LIST_OUTLETS_CONF + RedisKey.DELIMITER + uuid;
		}
		
		public static String getOutletsByOrgIdRedisKey(String orgId) {
			return ENTITY_OUTLETS + RedisKey.DELIMITER + orgId;
		}
		
		public static String getMaterialPriceListKey(String materialId) {
			return LIST_MATERIAL_PRICE_LIST + materialId;
		}
		
		public static String getMaterialPriceMapKey(String materialId) {
			return MAP_MATERIAL_PRICE + materialId;
		}
		
		public static String getOrgMaterialPriceMapKey(String orgId) {
			return LIST_ORG_MATERIAL_PRICE_KEY + orgId;
		}
		
		public static String getUserListByCurrentRoleCode(String code) {
			return LIST_USER_CURRENT_ROLE_KEY + code;
		}
		
	}
	
	public class ShiroActionConstant {
		
		public static final String USER_SHIRO_ACTION = LIST_PREFIX + DELIMITER + "UserShiroAction" + DELIMITER;
		
	}
	
	
	

}
