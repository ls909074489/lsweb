package cn.jeeweb.modules.caipiao.entity;

public class CaiPiaoResultBean {
	private Double money;
	private Integer sameCount;
	private Integer times;
	
	public CaiPiaoResultBean() {
	}
	public CaiPiaoResultBean(Double money, Integer sameCount, Integer times) {
		this.money = money;
		this.sameCount = sameCount;
		this.times = times;
	}
	
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getSameCount() {
		return sameCount;
	}
	public void setSameCount(Integer sameCount) {
		this.sameCount = sameCount;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
}
