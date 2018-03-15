package com.test;

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

public class DataDownUtil {

	/**
	 * 读取网站源码信息工具类
	 * @author hackxhao
	 * @version v1.0
	 * @param url 请求连接
	 * @param encoding 编码集
	 * @return buffer 
	 */
	public static String getHtmlResourceByUrl(String url,String encoding){
		// 存储源代码容器
		StringBuffer buffer = new StringBuffer();
		URL urlObj = null;
		URLConnection uc = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		
		try {
			// 建立网络连接
			urlObj = new URL(url);
			// 打开网络连接
			uc = urlObj.openConnection();
			// 建立文件写入流
			isr = new InputStreamReader(uc.getInputStream(),encoding);
			// 建立缓存流写入流
			reader = new BufferedReader(isr);
			// 建立临时文件
			String temp = null;
			while((temp = reader.readLine()) !=null){
				buffer.append(temp+"\n"); // 追加内容（一边读一边写）
			}
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			System.out.println("没有联网,检查设置");
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("打开网络连接失败，请稍后重试");
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
	 * 操作具体网站类
	 * @author hackxhao
	 * @version v1.0
	 * @param url 请求连接
	 * @param encoding 编码集
	 * @return maps
	 */
	public static List<HashMap<String,String>> getJobInfo(String url,String encoding){
		// 1.根据网站和页面的编码集获取网页源代码
		String html = getHtmlResourceByUrl(url, encoding);
		// 2.解析源代码
		Document document = Jsoup.parse(html);
		// 获取外层的div id="newlist_list_content_table"
		Element element= document.getElementById("newlist_list_content_table");
		// 获取工资结果集的列表 class="newlist"
		Elements elements= document.getElementsByClass("newlist");

		// 创建一个List集合
		List<HashMap<String,String>> maps = new ArrayList<HashMap<String,String>>();
		for (Element el :elements) {
			HashMap<String,String> map = new HashMap<String,String>();
			// 获取a连接
			String link = el.getElementsByTag("a").attr("href");
			System.out.println(link);
			// 获取公司名称
			String textTitle = el.getElementsByClass("gsmc").text();
			// 获取职位名称
			String jobName = el.getElementsByClass("zwmc").text();
			// 获取工资
			String money = el.getElementsByClass("zwyx").text();
			// 获取工资地点
			String address = el.getElementsByClass("gzdd").text();
			// 获取发布时间
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
	 * 解析a链接里面的内容
	 * @author hackxhao
	 * @version v1.0
	 */
	public static List<HashMap<String,String>> getLinkInfo(String url,String encoding){
				// 1.根据网站和页面的编码集获取网页源代码
				String html = getHtmlResourceByUrl(url, encoding);
				// 2.解析源代码
				Document document = Jsoup.parse(html);
				// 获取工资结果集的列表 class="tab-inner-cont"
				Elements elements= document.select("div.tab-inner-cont > p");
				System.out.println(elements);
				List<HashMap<String,String>> maps = new ArrayList<HashMap<String,String>>();
				
				for (Element el :elements) {
					HashMap<String,String> map = new HashMap<String,String>();
					// 获取a连接
					
					// 获取描述
					String ms = el.getElementsByTag("p").text();
					
					map.put("ms",ms);
					
					maps.add(map);
				}
				
				return maps;
	}
	
	
	
	/**
	 * java 入口
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
	
	public static void test1(){
//		String url = "https://776588a.com/lotteryV3/trend.do?lotCode=FFC";
//		String url = "https://776588a.com/lottery/trendChart/index.do?lotCode=FFC";
		String url = "https://776588a.com/lottery/trendChart/lotteryOpenNum.do?lotCode=FFC&recentDay=1&rows=1440&timestamp="+System.currentTimeMillis();
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
		
		int a=0;
		for(CaipiaoBean bean:list){
			if(a<6){
				System.out.println(bean.getQiHao()+"======="+bean.getHaoMa());
			}
			a++;
//			System.out.println("list.add(new CaipiaoBean(\""+bean.getQiHao()+"\", "+bean.getEndTime()+"l, "+bean.getOpenTime()+"l, \""+bean.getHaoMa()+"\"));");
		}
		
		int shuzhi=0;//位数
		int sameCount=10;
		int actSame=1;
		int totalMoney=400;
		boolean hasBuy=false;
		System.out.println("万-----"+"千-----"+"百-----"+"十-----"+"个-----");
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
//				System.out.print(actSame+"大--");
				result.append(actSame+"大----");
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
//				System.out.print(actSame+"小--");
				result.append(actSame+"小----");
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