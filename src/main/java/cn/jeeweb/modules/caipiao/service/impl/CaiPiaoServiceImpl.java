package cn.jeeweb.modules.caipiao.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.caipiao.entity.CaiPiaoResultBean;
import cn.jeeweb.modules.caipiao.entity.CaiPiaoScoreBean;
import cn.jeeweb.modules.caipiao.entity.CaipiaoEntity;
import cn.jeeweb.modules.caipiao.entity.EachartBean;
import cn.jeeweb.modules.caipiao.service.ICaipiaoService;

@Service
@Transactional
public class CaiPiaoServiceImpl extends CommonServiceImpl<CaipiaoEntity> implements  ICaipiaoService {

	@Override
	public List<List<CaiPiaoResultBean>> getStatis(String day){
		List<CaipiaoEntity> list=listByHql("from CaipiaoEntity where SUBSTRING(qiHao,1,8)=? order by qiHao", day);

		Integer sameCount=10;
		Integer times=3;//连续次数
		Double totalMoney=500d;
		Integer base=10;
		Integer mutil=3;//倍数
		DecimalFormat df = new DecimalFormat("#.##");
		
//		totalMoney=simulateBuy(list, 0, sameCount, times, totalMoney, base, mutil);
//		totalMoney=simulateBuy(list, 1, sameCount, times, totalMoney, base, mutil);
//		totalMoney=simulateBuy(list, 2, sameCount, times, totalMoney, base, mutil);
//		totalMoney=simulateBuy(list, 3, sameCount, times, totalMoney, base, mutil);
//		totalMoney=simulateBuy(list, 4, sameCount, times, totalMoney, base, mutil);
		
		List<List<CaiPiaoResultBean>> resultList=new ArrayList<List<CaiPiaoResultBean>>();
		/*List<CaiPiaoResultBean> rowList=new ArrayList<CaiPiaoResultBean>();
		for(int i=2;i<11;i++){
			sameCount=i;
			rowList=new ArrayList<CaiPiaoResultBean>();
			for(int j=1;j<=11;j++){
				times=j;
				Double calMoney=calMoney(list, sameCount, totalMoney, base, mutil, times,0)
//						+calMoney(list, sameCount, totalMoney, base, mutil, times,1)
//						+calMoney(list, sameCount, totalMoney, base, mutil, times,2)
//						+calMoney(list, sameCount, totalMoney, base, mutil, times,3)
//						+calMoney(list, sameCount, totalMoney, base, mutil, times,4)
						;
				calMoney=Double.parseDouble(df.format(calMoney));
				if(calMoney==-1){
					rowList.add(new CaiPiaoResultBean(-1d, sameCount, times));
				}else{
					rowList.add(new CaiPiaoResultBean(Double.parseDouble(df.format((calMoney-totalMoney*1))), sameCount, times));
				}
				if((calMoney-totalMoney*1)>0){
					System.out.println(i+":"+j+"==="+(calMoney-totalMoney*5));	
				}
			}
			resultList.add(rowList);
		}*/
		return resultList;
	}

	@Override
	public EachartBean numStatis(String sDay,String eDay){
		EachartBean resutlBean=new EachartBean();
		List<List<CaiPiaoScoreBean>> result=new ArrayList<List<CaiPiaoScoreBean>>();
		
		List<CaipiaoEntity> list=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			list=listByHql("from CaipiaoEntity where endTime>? and endTime<? order by qiHao", sdf.parse(sDay+" 00:00:00").getTime(), sdf.parse(eDay+" 23:59:59").getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,List<CaipiaoEntity>> daysMap=new HashMap<String,List<CaipiaoEntity>>();
		List<CaipiaoEntity> dayList=null;
		List<String> days=new ArrayList<String>();
		String day="";
		for(CaipiaoEntity c:list){
			day=c.getQiHao().substring(0, 8);
			dayList=daysMap.get(day);
			if(dayList==null){
				dayList=new ArrayList<CaipiaoEntity>();
				days.add(day);
			}
			dayList.add(c);
			daysMap.put(day, dayList);
		}
		
		Integer sameCount=13;
		Integer times=3;//连续次数
		Double totalMoney=500d;
		Integer base=10;
		Integer mutil=3;//倍数
		
		for(String d:days){
			result.add(calcSameCount(d,daysMap.get(d)));
			totalMoney=simulateBuy(daysMap.get(d), 0, sameCount, times, totalMoney, base, mutil);
			totalMoney=simulateBuy(daysMap.get(d), 1, sameCount, times, totalMoney, base, mutil);
			totalMoney=simulateBuy(daysMap.get(d), 2, sameCount, times, totalMoney, base, mutil);
			totalMoney=simulateBuy(daysMap.get(d), 3, sameCount, times, totalMoney, base, mutil);
			totalMoney=simulateBuy(daysMap.get(d), 4, sameCount, times, totalMoney, base, mutil);
		}
		resutlBean.setDays(days);
		resutlBean.setResult(result);
		
//		for(List<CaiPiaoScoreBean> r:result){
//			for(int i=0;i<r.size();i++){
//				if(i==0){
//					System.out.print(r.get(i).getDay()+":");
//				}
//				CaiPiaoScoreBean b=r.get(i);
//				System.out.print(b.getNum()+":"+b.getWan()+"  ");
//			}
//			System.out.println();
//		}
		return resutlBean;
	}
	
	private List<CaiPiaoScoreBean> calcSameCount(String day,List<CaipiaoEntity> list){
		Map<Integer,Integer> mapWan=new HashMap<Integer,Integer>();
		Map<Integer,Integer> mapQian=new HashMap<Integer,Integer>();
		Map<Integer,Integer> mapBai=new HashMap<Integer,Integer>();
		Map<Integer,Integer> mapShi=new HashMap<Integer,Integer>();
		Map<Integer,Integer> mapGe=new HashMap<Integer,Integer>();
		
		for(int i=1;i<=16;i++){
			mapWan.put(i, 0);
			mapQian.put(i, 0);
			mapBai.put(i, 0);
			mapShi.put(i, 0);
			mapGe.put(i, 0);
		}
		List<CaiPiaoScoreBean> scoreList=new ArrayList<CaiPiaoScoreBean>();
		boolean isSameWan=false;
		boolean isSameQian=false;
		boolean isSameBai=false;
		boolean isSameShi=false;
		boolean isSameGe=false;
		Integer sameCountWan=1;
		Integer sameCountQian=1;
		Integer sameCountBai=1;
		Integer sameCountShi=1;
		Integer sameCountGe=1;
		
		Integer lastScoreWan=list.get(0).getWanScope();
		Integer lastScoreQian=list.get(0).getQianScope();
		Integer lastScoreBai=list.get(0).getBaiScope();
		Integer lastScoreShi=list.get(0).getShiScope();
		Integer lastScoreGe=list.get(0).getGeScope();
		
		for(int i=1;i<list.size();i++){
			CaipiaoEntity c=list.get(i);
			if(c.getWanScope()==lastScoreWan){
				isSameWan=true;
				sameCountWan++;
			}else{
				isSameWan=false;
			}
			if(c.getQianScope()==lastScoreQian){
				isSameQian=true;
				sameCountQian++;
			}else{
				isSameQian=false;
			}
			if(c.getBaiScope()==lastScoreBai){
				isSameBai=true;
				sameCountBai++;
			}else{
				isSameBai=false;
			}
			if(c.getShiScope()==lastScoreShi){
				isSameShi=true;
				sameCountShi++;
			}else{
				isSameShi=false;
			}
			if(c.getGeScope()==lastScoreGe){
				isSameGe=true;
				sameCountGe++;
			}else{
				isSameGe=false;
			}
			
			lastScoreWan=c.getWanScope();
			if(!isSameWan){
				Integer count=mapWan.get(sameCountWan);
				if(count!=null){
					mapWan.put(sameCountWan, (count+1));
				}else{
					mapWan.put(sameCountWan, 1);
				}
				sameCountWan=1;
			}
			lastScoreQian=c.getQianScope();
			if(!isSameQian){
				Integer count=mapQian.get(sameCountQian);
				if(count!=null){
					mapQian.put(sameCountQian, (count+1));
				}else{
					mapQian.put(sameCountQian, 1);
				}
				sameCountQian=1;
			}
			lastScoreBai=c.getBaiScope();
			if(!isSameBai){
				Integer count=mapBai.get(sameCountBai);
				if(count!=null){
					mapBai.put(sameCountBai, (count+1));
				}else{
					mapBai.put(sameCountBai, 1);
				}
				sameCountBai=1;
			}
			lastScoreShi=c.getShiScope();
			if(!isSameShi){
				Integer count=mapShi.get(sameCountShi);
				if(count!=null){
					mapShi.put(sameCountShi, (count+1));
				}else{
					mapShi.put(sameCountShi, 1);
				}
				sameCountShi=1;
			}
			lastScoreGe=c.getGeScope();
			if(!isSameGe){
				Integer count=mapGe.get(sameCountGe);
				if(count!=null){
					mapGe.put(sameCountGe, (count+1));
				}else{
					mapGe.put(sameCountGe, 1);
				}
				sameCountGe=1;
			}
		}
		for(Integer k:mapWan.keySet()){
			scoreList.add(new CaiPiaoScoreBean(day, k, mapWan.get(k), mapQian.get(k), mapBai.get(k), mapShi.get(k), mapGe.get(k)));
		}
		return scoreList;
	}
	
	private static Double calMoney(List<CaipiaoEntity> list,Integer sameCount,Double totalMoney,Integer base,Integer mutil,Integer times,int index){
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		boolean isSame=false;
		int diffCount=1;
		Integer buyTime=1;
		boolean hasBuy=false;
		DecimalFormat df = new DecimalFormat("#.##");
		for(int i=1;i<list.size();i++){
			CaipiaoEntity entity=list.get(i);
			String haoMa=entity.getHaoMa();
			String wan=haoMa.split(",")[index];
			
			CaipiaoEntity lastEntity=list.get(i-1);
			String lastHaoMa=lastEntity.getHaoMa();
			String lastWan=lastHaoMa.split(",")[index];
			
			if(changeScore(Integer.parseInt(wan))==changeScore(Integer.parseInt(lastWan))){
				isSame=true;
			}else{
				isSame=false;
			}
			
			int buyMoney=10;
			if(diffCount>sameCount){
				if(buyTime<=times){
					for(int j=1;j<buyTime;j++){
						buyMoney=buyMoney*mutil;
					}
					System.out.println("buy:"+buyTime+" time "+entity.getQiHao()+":"+entity.getHaoMa()+"   totalMoney:"+Double.parseDouble(df.format(totalMoney-buyMoney))+"="+totalMoney+"-"+buyMoney);
					hasBuy=true;
					totalMoney=Double.parseDouble(df.format(totalMoney-buyMoney));
					buyTime++;
					if(totalMoney<0){
						return -1d;
					}
				}
			}
			
			if(!isSame){
				Integer c=map.get(diffCount);
				if(c==null){
					c=0;
				}
				c++;
				map.put(diffCount, c);
				diffCount=1;
				buyTime=1;
				if(hasBuy){
//					System.out.println("totalMoney="+totalMoney+"+"+(buyMoney*1.96)+"="+(df.format(totalMoney+buyMoney*1.96)));
					totalMoney=Double.parseDouble(df.format((totalMoney+Double.parseDouble(df.format(buyMoney*1.96)))));
				}
			}else{
				diffCount++;
			}
			hasBuy=false;
		}
		for(Integer key:map.keySet()){
//			System.out.println(key+"=========="+map.get(key));
		}
		return totalMoney;
	}
	
	private static Integer changeScore(Integer num){
		if(num>=5){
			return 1;
		}else{
			return 0;
		}
	}
	
	public void calLast(List<CaipiaoEntity> list){
		int shuzhi=0;//浣嶆暟
		int sameCount=3;
		int actSame=1;
		
		System.out.println("晚---"+"千---"+"百---"+"十---"+"个---");
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
	
	
	public Double simulateBuy(List<CaipiaoEntity> list,int index,Integer sameCount,Integer times,Double totalMoney,Integer base,Integer mutil){
		System.out.println("第"+index+"位==="+sameCount+"======="+times);
		DecimalFormat df = new DecimalFormat("#.##");
		
		Integer nowSameCount=0;
		Integer nowBuyCount=0;//购买次数
		Double buyMoney=0d;
		boolean hasBuy=false;
		for(int i=1;i<list.size();i++){
			String nowNum=list.get(i).getHaoMa().split(",")[index];
			String lastNum=list.get(i-1).getHaoMa().split(",")[index];
			if(checkIsSame(Integer.parseInt(nowNum), Integer.parseInt(lastNum))){
				nowSameCount++;
			}else{
				nowSameCount=0;
				if(hasBuy){
					System.out.println(list.get(i).getQiHao()+"--"+list.get(i).getHaoMa()+"==money:"+(totalMoney+buyMoney*1.96)+"="+totalMoney+"+"+buyMoney*1.96);
					totalMoney=totalMoney+buyMoney*1.96;
					totalMoney=Double.valueOf(df.format(totalMoney));
					hasBuy=false;
					nowBuyCount=0;
				}
			}
			if(nowSameCount>=sameCount){
				if(nowBuyCount>=times){//大于一定次数就不买了
					hasBuy=false;
				}else{
					buyMoney=base*Math.pow(mutil,nowBuyCount);
					totalMoney=totalMoney-buyMoney;
					nowBuyCount++;
					hasBuy=true;
					totalMoney=Double.valueOf(df.format(totalMoney));
					System.out.println(list.get(i).getQiHao()+"--"+list.get(i).getHaoMa()+"==money:"+(totalMoney-buyMoney)+"="+totalMoney+"-"+buyMoney);
				}
			}
		}
		System.out.println(totalMoney);
		return totalMoney;
	}
	
	
	
	
	
	
}
