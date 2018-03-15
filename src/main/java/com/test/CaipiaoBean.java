package com.test;

import java.util.ArrayList;
import java.util.List;

public class CaipiaoBean {
	private String qiHao;
	private Long endTime;
	private Long openTime;
	private String haoMa;
	
	public CaipiaoBean() {
	}
	public CaipiaoBean(String qiHao, Long endTime, Long openTime, String haoMa) {
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
	
	@Override
	public String toString() {
		return "CaipiaoBean [qiHao=" + qiHao + ", haoMa=" + haoMa + "]";
	}
	public static void main(String[] args) {
		List<CaipiaoBean> list=new ArrayList<CaipiaoBean>();
		list.add(new CaipiaoBean("201801050683", 1515122580000l, 1515122586128l, "5,0,1,2,7"));
		list.add(new CaipiaoBean("201801050682", 1515122520000l, 1515122525257l, "7,8,2,5,0"));
		list.add(new CaipiaoBean("201801050681", 1515122460000l, 1515122466003l, "2,8,5,3,8"));
		list.add(new CaipiaoBean("201801050680", 1515122400000l, 1515122405291l, "4,4,3,7,6"));
		list.add(new CaipiaoBean("201801050679", 1515122340000l, 1515122345232l, "5,1,1,8,4"));
		list.add(new CaipiaoBean("201801050678", 1515122280000l, 1515122285441l, "2,4,9,8,6"));
		list.add(new CaipiaoBean("201801050677", 1515122220000l, 1515122225233l, "7,8,9,5,7"));
		list.add(new CaipiaoBean("201801050676", 1515122160000l, 1515122165247l, "1,5,5,4,2"));
		list.add(new CaipiaoBean("201801050675", 1515122100000l, 1515122105475l, "8,0,0,0,6"));
		list.add(new CaipiaoBean("201801050674", 1515122040000l, 1515122045276l, "8,1,7,8,4"));
		list.add(new CaipiaoBean("201801050673", 1515121980000l, 1515121985269l, "3,9,3,6,4"));
		list.add(new CaipiaoBean("201801050672", 1515121920000l, 1515121925560l, "8,5,4,9,1"));
		list.add(new CaipiaoBean("201801050671", 1515121860000l, 1515121865346l, "1,4,2,8,2"));
		list.add(new CaipiaoBean("201801050670", 1515121800000l, 1515121805246l, "4,6,8,3,0"));
		list.add(new CaipiaoBean("201801050669", 1515121740000l, 1515121745412l, "2,6,0,7,6"));
		list.add(new CaipiaoBean("201801050668", 1515121680000l, 1515121685255l, "7,8,7,9,2"));
		list.add(new CaipiaoBean("201801050667", 1515121620000l, 1515121625238l, "3,7,8,6,3"));
		list.add(new CaipiaoBean("201801050666", 1515121560000l, 1515121565467l, "9,0,1,5,6"));
		list.add(new CaipiaoBean("201801050665", 1515121500000l, 1515121505231l, "3,2,3,2,3"));
		list.add(new CaipiaoBean("201801050664", 1515121440000l, 1515121445243l, "4,7,0,9,9"));
		list.add(new CaipiaoBean("201801050663", 1515121380000l, 1515121385460l, "3,7,5,5,0"));
		list.add(new CaipiaoBean("201801050662", 1515121320000l, 1515121325286l, "8,4,5,2,4"));
		list.add(new CaipiaoBean("201801050661", 1515121260000l, 1515121265940l, "0,5,9,2,8"));
		list.add(new CaipiaoBean("201801050660", 1515121200000l, 1515121205453l, "5,2,9,1,1"));
		list.add(new CaipiaoBean("201801050659", 1515121140000l, 1515121145224l, "6,4,3,5,7"));
		list.add(new CaipiaoBean("201801050658", 1515121080000l, 1515121085307l, "9,2,5,5,4"));
		list.add(new CaipiaoBean("201801050657", 1515121020000l, 1515121025433l, "8,1,5,8,6"));
		list.add(new CaipiaoBean("201801050656", 1515120960000l, 1515120965273l, "6,2,1,1,3"));
		list.add(new CaipiaoBean("201801050655", 1515120900000l, 1515120905281l, "2,7,4,0,6"));
		list.add(new CaipiaoBean("201801050654", 1515120840000l, 1515120845392l, "0,7,2,5,7"));

		int shuzhi=0;//位数
		int sameCount=3;
		int actSame=1;
		
		System.out.println("万---"+"千---"+"百---"+"十---"+"个---");
		for(int k=0;k<5;k++){
			shuzhi=k;
			actSame=1;
			for(int i=0;i<sameCount;i++){
				boolean isSame=checkIsSame(Integer.parseInt(list.get(i).getHaoMa().split(",")[shuzhi]),Integer.parseInt(list.get(i+1).getHaoMa().split(",")[shuzhi]));
				if(!isSame){
					break;
				}else{
					actSame++;
				}
			}
			System.out.print(actSame+"---");
		}

		
		
	}
	
	
	private static  boolean checkIsSame(int nowNum, int lastNum) {
		if(nowNum>4&&lastNum>4){
			return true;
		}
		if(nowNum<=4&&lastNum<=4){
			return true;
		}
		return false;
	}
}
