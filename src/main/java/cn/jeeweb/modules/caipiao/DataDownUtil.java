package cn.jeeweb.modules.caipiao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.jeeweb.modules.caipiao.entity.CaipiaoBean;

public class DataDownUtil {

	/**
	 * 璇诲彇缃戠珯婧愮爜淇℃伅宸ュ叿绫�
	 * @author hackxhao
	 * @version v1.0
	 * @param url 璇锋眰杩炴帴
	 * @param encoding 缂栫爜闆�
	 * @return buffer 
	 */
	public static String getHtmlResourceByUrl(String url,String encoding){
		// 瀛樺偍婧愪唬鐮佸鍣�
		StringBuffer buffer = new StringBuffer();
		URL urlObj = null;
		URLConnection uc = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		
		try {
			// 寤虹珛缃戠粶杩炴帴
			urlObj = new URL(url);
			// 鎵撳紑缃戠粶杩炴帴
			uc = urlObj.openConnection();
			// 寤虹珛鏂囦欢鍐欏叆娴�
			isr = new InputStreamReader(uc.getInputStream(),encoding);
			// 寤虹珛缂撳瓨娴佸啓鍏ユ祦
			reader = new BufferedReader(isr);
			// 寤虹珛涓存椂鏂囦欢
			String temp = null;
			while((temp = reader.readLine()) !=null){
				buffer.append(temp+"\n"); // 杩藉姞鍐呭锛堜竴杈硅涓�杈瑰啓锛�
			}
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			System.out.println("娌℃湁鑱旂綉,妫�鏌ヨ缃�");
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("鎵撳紑缃戠粶杩炴帴澶辫触锛岃绋嶅悗閲嶈瘯");
		}finally{
			if(isr!=null){
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return buffer.toString();
	}
	/***
	 * 鎿嶄綔鍏蜂綋缃戠珯绫�
	 * @author hackxhao
	 * @version v1.0
	 * @param url 璇锋眰杩炴帴
	 * @param encoding 缂栫爜闆�
	 * @return maps
	 */
	public static List<HashMap<String,String>> getJobInfo(String url,String encoding){
		// 1.鏍规嵁缃戠珯鍜岄〉闈㈢殑缂栫爜闆嗚幏鍙栫綉椤垫簮浠ｇ爜
		String html = getHtmlResourceByUrl(url, encoding);
		// 2.瑙ｆ瀽婧愪唬鐮�
		Document document = Jsoup.parse(html);
		// 鑾峰彇澶栧眰鐨刣iv id="newlist_list_content_table"
		Element element= document.getElementById("newlist_list_content_table");
		// 鑾峰彇宸ヨ祫缁撴灉闆嗙殑鍒楄〃 class="newlist"
		Elements elements= document.getElementsByClass("newlist");

		// 鍒涘缓涓�涓狶ist闆嗗悎
		List<HashMap<String,String>> maps = new ArrayList<HashMap<String,String>>();
		for (Element el :elements) {
			HashMap<String,String> map = new HashMap<String,String>();
			// 鑾峰彇a杩炴帴
			String link = el.getElementsByTag("a").attr("href");
			System.out.println(link);
			// 鑾峰彇鍏徃鍚嶇О
			String textTitle = el.getElementsByClass("gsmc").text();
			// 鑾峰彇鑱屼綅鍚嶇О
			String jobName = el.getElementsByClass("zwmc").text();
			// 鑾峰彇宸ヨ祫
			String money = el.getElementsByClass("zwyx").text();
			// 鑾峰彇宸ヨ祫鍦扮偣
			String address = el.getElementsByClass("gzdd").text();
			// 鑾峰彇鍙戝竷鏃堕棿
			String fadate = el.getElementsByClass("gxsj").text();
			map.put("textTitle",textTitle);
			map.put("jobName",jobName);
			map.put("money",money);
			map.put("address",address);
			map.put("fadate",fadate);
			map.put("link",link);
			maps.add(map);
		}
		
		return maps;
	}
	/**
	 * 瑙ｆ瀽a閾炬帴閲岄潰鐨勫唴瀹�
	 * @author hackxhao
	 * @version v1.0
	 */
	public static List<HashMap<String,String>> getLinkInfo(String url,String encoding){
				// 1.鏍规嵁缃戠珯鍜岄〉闈㈢殑缂栫爜闆嗚幏鍙栫綉椤垫簮浠ｇ爜
				String html = getHtmlResourceByUrl(url, encoding);
				// 2.瑙ｆ瀽婧愪唬鐮�
				Document document = Jsoup.parse(html);
				// 鑾峰彇宸ヨ祫缁撴灉闆嗙殑鍒楄〃 class="tab-inner-cont"
				Elements elements= document.select("div.tab-inner-cont > p");
				System.out.println(elements);
				List<HashMap<String,String>> maps = new ArrayList<HashMap<String,String>>();
				
				for (Element el :elements) {
					HashMap<String,String> map = new HashMap<String,String>();
					// 鑾峰彇a杩炴帴
					
					// 鑾峰彇鎻忚堪
					String ms = el.getElementsByTag("p").text();
					
					map.put("ms",ms);
					
					maps.add(map);
				}
				
				return maps;
	}
	
	
	
	/**
	 * java 鍏ュ彛
	 * @param args
	 */
	public static void main(String[] args) {
		long sleepTime=60;
		while (true) {
			try {
				test1();
				for(int i=0;i<sleepTime;i++){
					Thread.sleep(995);
					System.out.print(i+",");
					if(i==45){
						System.out.println();
					}
				}
				System.out.println();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}
	
	
	public static List<CaipiaoBean> getCaiPiaoList(int recentDay,int rows){
		String url = "https://776588a.com/lottery/trendChart/lotteryOpenNum.do?lotCode=FFC&recentDay="+recentDay+"&rows="+rows+"&timestamp="+System.currentTimeMillis();
		String encoding = "UTF-8";
		String html = getHtmlResourceByUrl(url, encoding);
		String jsonStr="{\"records\":"+html+"}";
		System.out.println(html);
		List<CaipiaoBean> list=new ArrayList<CaipiaoBean>();
		Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
		JSONArray records = (JSONArray) map.get("records");
		for (int i = 0; i < records.size(); i++) {
			Map sub = (Map) records.get(i);
			String qiHao = (String) sub.get("qiHao");
			Long endTime = (Long) sub.get("endTime");
			Long openTime = (Long) sub.get("openTime");
			String haoMa = (String) sub.get("haoMa");
			list.add(new CaipiaoBean(qiHao, endTime, openTime, haoMa));
		}
		Collections.sort(list, new Comparator<CaipiaoBean>() {
			@Override
			public int compare(CaipiaoBean arg0, CaipiaoBean arg1) {
				return arg1.getQiHao().compareTo(arg0.getQiHao());
			}
		});
		return list;
	}
	
	public static void test1(){
		List<CaipiaoBean> list=getCaiPiaoList(1,20);
		
		int a=0;
		for(CaipiaoBean bean:list){
			if(a<6){
				System.out.println(bean.getQiHao()+"======="+bean.getHaoMa());
			}
			a++;
//			System.out.println("list.add(new CaipiaoBean(\""+bean.getQiHao()+"\", "+bean.getEndTime()+"l, "+bean.getOpenTime()+"l, \""+bean.getHaoMa()+"\"));");
		}
		
		int shuzhi=0;//浣嶆暟
		int sameCount=10;
		int actSame=1;
		int totalMoney=400;
		boolean hasBuy=false;
		System.out.println("涓�-----"+"鍗�-----"+"鐧�-----"+"鍗�-----"+"涓�-----");
		StringBuilder result=new StringBuilder();
		for(int k=0;k<5;k++){
			hasBuy=false;
			shuzhi=k;
			actSame=1;
			for(int i=0;i<sameCount;i++){
//				System.out.println(list.get(i));
//				System.out.println(list.get(i+1));
//				System.out.println("========");
				boolean isSame=checkIsSame(Integer.parseInt(list.get(i).getHaoMa().split(",")[shuzhi]),Integer.parseInt(list.get(i+1).getHaoMa().split(",")[shuzhi]));
				if(!isSame){
					break;
				}else{
					actSame++;
				}
			}
			int beishu=10;
			if(Integer.parseInt(list.get(0).getHaoMa().split(",")[shuzhi])>4){
				if(actSame>4){
					if(totalMoney>0){
						for(int aa=0;aa<(actSame-2);aa++){
							beishu=beishu*3;
						}
//						System.out.println("total:"+totalMoney+"-"+beishu+"="+(totalMoney-beishu));
						totalMoney=totalMoney-beishu;
						hasBuy=true;
					}else{
//						System.out.println("xxxxxxxxxxxxxx");
					}
				}else{
//					System.out.println("not buy");
					if(hasBuy){
						totalMoney=totalMoney+beishu;
//						System.out.println("total:"+totalMoney+"+"+beishu+"="+(totalMoney+beishu));
					}
					beishu=10;
					hasBuy=false;
				}
//				System.out.print(actSame+"澶�--");
				result.append(actSame+"澶�----");
			}else{
				if(actSame>4){
					if(totalMoney>0){
						for(int aa=0;aa<(actSame-2);aa++){
							beishu=beishu*3;
						}
//						System.out.println("total:"+totalMoney+"-"+beishu+"="+(totalMoney-beishu));
						totalMoney=totalMoney-beishu;
						hasBuy=true;
					}else{
//						System.out.println("xxxxxxxxxxxxxx");
					}
				}else{
//					System.out.println("not buy");
					if(hasBuy){
						totalMoney=totalMoney+beishu;
//						System.out.println("total:"+totalMoney+"+"+beishu+"="+(totalMoney+beishu));
					}
					beishu=10;
					hasBuy=false;
				}
//				System.out.print(actSame+"灏�--");
				result.append(actSame+"灏�----");
			}
			
		}
		System.out.println(result.toString());
		System.out.println("=============================================================");
	}
	
	public static  boolean checkIsSame(int nowNum, int lastNum) {
		if(nowNum>4&&lastNum>4){
			return true;
		}
		if(nowNum<=4&&lastNum<=4){
			return true;
		}
		return false;
	}
}