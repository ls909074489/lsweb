package cn.jeeweb.modules.caipiao.entity;

import java.util.List;

public class EachartBean {
	private List<List<CaiPiaoScoreBean>> result;
	private List<String> days;
	public List<List<CaiPiaoScoreBean>> getResult() {
		return result;
	}
	public void setResult(List<List<CaiPiaoScoreBean>> result) {
		this.result = result;
	}
	public List<String> getDays() {
		return days;
	}
	public void setDays(List<String> days) {
		this.days = days;
	}
	
	
}
