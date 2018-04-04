package cn.jeeweb.modules.caipiao.entity;

public class CaiPiaoScoreBean {
	
	private String day;
	private Integer num;
	
	private Integer wan;
	private Integer qian;
	private Integer bai;
	private Integer shi;
	private Integer ge;
	
	
	public CaiPiaoScoreBean() {
	}
	
	public CaiPiaoScoreBean(String day, Integer num, Integer wan, Integer qian, Integer bai, Integer shi, Integer ge) {
		super();
		this.day = day;
		this.num = num;
		this.wan = wan;
		this.qian = qian;
		this.bai = bai;
		this.shi = shi;
		this.ge = ge;
	}

	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
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
	
	
}
