package cn.jeeweb.modules.caipiao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import cn.jeeweb.core.common.entity.AbstractEntity;

@Entity
@Table(name = "yy_cai_piao")
@DynamicUpdate(false)
@DynamicInsert(false)
public class CaipiaoEntity extends AbstractEntity<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**字段主键*/
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="id",nullable=false,length=32,scale=0)
	private String id;
	@Column(length=50)
	private String qiHao;
	@Column(length=50)
	private Long endTime;
	@Column()
	private Long openTime;
	@Column(length=50)
	private String haoMa;
	
	@Column()
	private Integer wan;
	@Column()
	private Integer qian;
	@Column()
	private Integer bai;
	@Column()
	private Integer shi;
	@Column()
	private Integer ge;
	
	@Column()
	private Integer wanScope;
	@Column()
	private Integer qianScope;
	@Column()
	private Integer baiScope;
	@Column()
	private Integer shiScope;
	@Column()
	private Integer geScope;
	
	
	
	public CaipiaoEntity() {
	}
	
	public CaipiaoEntity(String qiHao, Long endTime, Long openTime, String haoMa) {
		this.qiHao = qiHao;
		this.endTime = endTime;
		this.openTime = openTime;
		this.haoMa = haoMa;
	}
	public String getQiHao() {
		return qiHao;
	}
	public void setQiHao(String qiHao) {
		this.qiHao = qiHao;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Long getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Long openTime) {
		this.openTime = openTime;
	}
	public String getHaoMa() {
		return haoMa;
	}
	public void setHaoMa(String haoMa) {
		this.haoMa = haoMa;
	}
	public Integer getWan() {
		return wan;
	}
	public void setWan(Integer wan) {
		this.wan = wan;
	}
	public Integer getQian() {
		return qian;
	}
	public void setQian(Integer qian) {
		this.qian = qian;
	}
	public Integer getBai() {
		return bai;
	}
	public void setBai(Integer bai) {
		this.bai = bai;
	}
	public Integer getShi() {
		return shi;
	}
	public void setShi(Integer shi) {
		this.shi = shi;
	}
	public Integer getGe() {
		return ge;
	}
	public void setGe(Integer ge) {
		this.ge = ge;
	}
	public Integer getWanScope() {
		return wanScope;
	}

	public void setWanScope(Integer wanScope) {
		this.wanScope = wanScope;
	}

	public Integer getQianScope() {
		return qianScope;
	}

	public void setQianScope(Integer qianScope) {
		this.qianScope = qianScope;
	}

	public Integer getBaiScope() {
		return baiScope;
	}

	public void setBaiScope(Integer baiScope) {
		this.baiScope = baiScope;
	}

	public Integer getShiScope() {
		return shiScope;
	}

	public void setShiScope(Integer shiScope) {
		this.shiScope = shiScope;
	}

	public Integer getGeScope() {
		return geScope;
	}

	public void setGeScope(Integer geScope) {
		this.geScope = geScope;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CaipiaoBean [qiHao=" + qiHao + ", haoMa=" + haoMa + "]";
	}
public static void main(String[] args) {
	System.out.println(Math.pow(3,0));
}	
}
