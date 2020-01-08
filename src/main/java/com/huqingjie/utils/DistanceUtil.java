package com.huqingjie.utils;

import java.awt.geom.Point2D;

public class DistanceUtil {
	 private static final double EARTH_RADIUS = 6371393; // 平均半径,单位：m
	 //输入经纬度，计算两个坐标间的距离
	  public static double getDistance(double j1,double w1,double j2,double w2) {
	    	 Point2D pointDD = new Point2D.Double(j1, w1);
		     Point2D pointXD = new Point2D.Double(j2, w2);
			return calculate(pointDD, pointXD);
	    }
	    /**
	     * 通过AB点经纬度获取距离
	     * @param pointA A点(经，纬)
	     * @param pointB B点(经，纬)
	     * @return 距离(单位：米)
	     */
	    public static double calculate(Point2D pointA, Point2D pointB) {
	        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
	        double radiansAX = Math.toRadians(pointA.getX()); // A经弧度
	        double radiansAY = Math.toRadians(pointA.getY()); // A纬弧度
	        double radiansBX = Math.toRadians(pointB.getX()); // B经弧度
	        double radiansBY = Math.toRadians(pointB.getY()); // B纬弧度
	        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
	                + Math.sin(radiansAY) * Math.sin(radiansBY);
	        double acos = Math.acos(cos); // 反余弦值
	        return EARTH_RADIUS * acos; // 最终结果
	    }
	    
	  
	    public static void main(String[] args) {
	        // 北京 东单地铁站
	       // Point2D pointDD = new Point2D.Double(116.425249, 39.914504);
	        // 北京 西单地铁站
	       // Point2D pointXD = new Point2D.Double(116.382001, 39.913329);
	    	double d = getDistance(116.425249, 39.914504,116.382001, 39.913329);
	        System.out.println("两点的距离:"+d+"米");
	    }
	    
	    /*
	     * 编写工具类：根据给定的距离天安门公式，输入参数 经纬度，可以计算返回具体的被抓拍车辆出现在北京市区几环
	     */
	    public static int looplevelTest(int jd,int wd) {
	    	 long b = Math.round( Math.sqrt( Math.pow(jd - 39 ,2  ) +  Math.pow(wd - 116 ,2  ) ));
	    	 if (b<=15) {
				 return 2;
	    	 }else if(b<=30) {
	    		 return 3;
	    	 }else if(b<=40) {
	    		 return 4;
	    	 }else if(b<=60){
	    		 return 5;
	    	 }else {
	    		 return 6;
	    	 }
	    }
	    
	    /**
	     * (3)编写工具类：传递参数为机动车类型，以及提供的距离天安门距离计算公式按给定的伪代码判断得到违反交规类型（5分）
	     */
	    public static String passType(String cardid,String typeid,int jd,int wd) {
	    	// 距离
	    	long b = Math.round( Math.sqrt( Math.pow(jd - 39 ,2  ) +  Math.pow(wd - 116 ,2  ) ));
	    	if (typeid=="A" && b<15) {
				return "摩托车A进入2环";
			}else if(typeid=="B" && b<40){
				return "摩托车B进入4环";
			}else if(typeid=="C" && !cardid.startsWith("京") && b<60) {
				return "外地牌照不能进入5环";
			}else if(typeid=="C" && cardid.startsWith("京")) {
				return "可以出入";
			}else {
				return "无违规";
			}
	    	
	    }
	    
	    /*
	     * (2)编写工具类，根据传递车牌号码，传递的日期 判断是否符合单双号限行规则（5分）
	     */
	    public static String passynTest(String carid) {
	    	char c = carid.charAt(carid.length()-1);
	    	
	    	if (c%2==0) {
				return "否";
			}else {
				return "是";
			}
	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}
