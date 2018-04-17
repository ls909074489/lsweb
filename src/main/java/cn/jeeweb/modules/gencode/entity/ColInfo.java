package cn.jeeweb.modules.gencode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.jeeweb.core.common.entity.AbstractEntity;

/**
 * 成员变量属性
 * @ClassName: EntityInfo
 * @author liusheng
 * @date 2017年6月28日 下午2:43:12
 */
@Entity
@javax.persistence.Table(name = "yy_colinfo", schema = "")
@SuppressWarnings("serial")
public class ColInfo extends AbstractEntity<String> {

	public class EleType {
		public static final String TEXT = "text";//
		public static final String DATE = "date";//
		public static final String DATETIME = "datetime";//
		public static final String SELECT = "select";//
		public static final String TEXTAREA = "textarea";//
		public static final String REF = "ref";//
	}
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 32)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entityinfo_id", updatable = false, insertable = true)
	@JsonIgnore
	private EntityInfo entityinfo;
	
	
	@Column(name = "col_name", nullable = true, length = 50)
	private String colName;
	
	@Column(name = "col_db_name", nullable = true, length = 50)
	private String colDbName;
	
	@Column(name = "col_type", nullable = true, length = 20)
	private String colType;
	
	@Column(name = "col_lenth")
	private Integer colLenth;
	
	@Column(name = "col_anno", nullable = true, length = 20)
	private String colAnno;//注释
	
	@Column(name = "is_required")
	private boolean isRequired=false;//是否必填
	
	@Column(name = "is_row")
	private boolean isRow=false;//是否单独占一行
	
	@Column(name = "ele_type", nullable = true, length = 20)
	private String eleType="text";//html元素
	
	@Column(name = "enum_group", nullable = true, length = 20)
	private String enumGroup="BooleanType";//枚举类型
	
	@Column(name = "col_count")
	private Integer colCount=1;//列数
	
	@Column(name = "is_search")
	private boolean isSearch=false;//是否列表查询
	
	@Column(name = "is_list_visiable")
	private boolean isListVisiable=true;//列表是否显示
	
	@Column(name = "is_detail_visiable")
	private boolean isDetailVisiable=true;//明细是否显示
	
	@Column(name = "is_main")
	private boolean isMain=true;//是否主表
	
	@Column(name = "col_width")
	private Integer colWidth=100;//列表显示长度
	
	

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColDbName() {
		return colDbName;
	}

	public void setColDbName(String colDbName) {
		this.colDbName = colDbName;
	}

	public String getColType() {
		return colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}

	public Integer getColLenth() {
		return colLenth;
	}

	public void setColLenth(Integer colLenth) {
		this.colLenth = colLenth;
	}

	public String getColAnno() {
		return colAnno;
	}

	public void setColAnno(String colAnno) {
		this.colAnno = colAnno;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public boolean isRow() {
		return isRow;
	}

	public void setRow(boolean isRow) {
		this.isRow = isRow;
	}

	public String getEleType() {
		return eleType;
	}

	public void setEleType(String eleType) {
		this.eleType = eleType;
	}

	public String getEnumGroup() {
		return enumGroup;
	}

	public void setEnumGroup(String enumGroup) {
		this.enumGroup = enumGroup;
	}

	public Integer getColCount() {
		return colCount;
	}

	public void setColCount(Integer colCount) {
		this.colCount = colCount;
	}

	public boolean isListVisiable() {
		return isListVisiable;
	}

	public void setListVisiable(boolean isListVisiable) {
		this.isListVisiable = isListVisiable;
	}

	public boolean isDetailVisiable() {
		return isDetailVisiable;
	}

	public void setDetailVisiable(boolean isDetailVisiable) {
		this.isDetailVisiable = isDetailVisiable;
	}

	public boolean isSearch() {
		return isSearch;
	}

	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

	public Integer getColWidth() {
		return colWidth;
	}

	public void setColWidth(Integer colWidth) {
		this.colWidth = colWidth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EntityInfo getEntityinfo() {
		return entityinfo;
	}

	public void setEntityinfo(EntityInfo entityinfo) {
		this.entityinfo = entityinfo;
	}
}
