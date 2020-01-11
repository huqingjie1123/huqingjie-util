package com.huqingjie.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: DateUtil
 * @Description: 日期工具类 * @author: charles
 * @date: 2019年12月5日 下午3:01:03
 */
public class DateUtil {
	
	/**
	 * 
	 * @Title: getAge 
	 * @Description: 根据出生日期获取年龄
	 * @param date
	 * @return
	 * @return: int
	 */
	public static int getAge(Date birthday) {
		// 获取当前系统的日历类
				Calendar c = Calendar.getInstance();
				// 获取当前年月日
				int yearNow = c.get(Calendar.YEAR);
				int monthNow = c.get(Calendar.MONTH);
				int dateNow = c.get(Calendar.DAY_OF_MONTH);
				c.setTime(birthday);// 用生日初始化日历类
				int yearBirth = c.get(Calendar.YEAR);
				int monthBirth = c.get(Calendar.MONTH);
				int dateBirth = c.get(Calendar.DAY_OF_MONTH);
				// 年龄 1990-02-10 2019-02-08
				int age = yearNow - yearBirth;
				// 出生月份大于前月份 年龄减少1
				if (monthBirth > monthNow) {
					age--;
				}
				// 如果月份一致并且 日期大于当前日期 年龄减少1
				if (monthBirth == monthNow && dateBirth > dateNow) {
					age--;
				}
				return age;
				
			
		
		
	}
	
	/**
	 * 
	 * @Title: getMonthEnd 
	 * @Description: 返回指定月份的月末
	 * @param date
	 * @return
	 * @return: Date
	 */
	public static Date getMonthEnd(Date date) {
		Calendar c = Calendar.getInstance();
		//用传入的日期初始化日历类
		c.setTime(date);
		//让当前月份+1
		c.add(Calendar.MONTH, 1);
		//让日期变成下个月月初
		
		Date init = getMonthInit(c.getTime());
		
		//用月初再次初始日历类
		c.setTime(init);
		//日期的秒数-1
		c.add(Calendar.SECOND, -1);
		
		return c.getTime();
		
		
	}
	
	
	/**
	 * 
	 * @Title: getMonthInit 
	 * @Description: //返回 指定月份月初时间
	 * @param date
	 * @return
	 * @return: Date
	 */
	public static Date getMonthInit(Date date) {
		//获取的当前系统的时间的日历类
		Calendar c = Calendar.getInstance();
		//用传入的日期初始化日历类
		c.setTime(date);
		//设置月份的天为 1
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		
		return c.getTime();
		
	}
	
	
	
	
	
	// 返回个某一个时间段的随机日期

	public static Date random(Date minDate, Date maxDate) {
		// 开始毫秒数
		long t1 = minDate.getTime();
		// 结束毫秒数
		long t2 = maxDate.getTime();
        //介于t2 t1之间的毫米数
		long t3 = (long) (Math.random() * (t2 - t1 + 1) + t1);

		return new Date(t3);

	}

	public static void main(String[] args) {
		for (int i = 0;i<100;i++) {
			Date date = DateUtil.random(new Date(900000000), new Date());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(df.format(date));
		}
	}
	
	  /***
     * 从yyyy-MM-dd HH:mm格式转成yyyyMMddHH格式
     * @param dateStr
     * @return
     */
    public static String formatStr(String dateStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = simpleDateFormat.parse(dateStr);
            simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");
            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 获取指定日期的凌晨
     * @return
     */
    public static Date toDayStartHour(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date start = calendar.getTime();
        return start;
    }


    /***
     	* 时间增加N分钟
     * @param date
     * @param minutes
     * @return
     */
    public static Date addDateMinutes(Date date,int minutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);// 24小时制
        date = calendar.getTime();
        return date;
    }

    /***
     	* 时间递增N小时
     * @param hour
     * @return
     */
    public static Date addDateHour(Date date,int hour){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);// 24小时制
        date = calendar.getTime();
        return date;
    }

    /***
     * 获取时间菜单
     * @return
     */
    public static List<Date> getDateMenus(){
        //定义一个List<Date>集合，存储所有时间段
        List<Date> dates = new ArrayList<Date>();
        //循环12次
        Date date = toDayStartHour(new Date()); //凌晨
        for (int i = 0; i <12 ; i++) {
            //每次递增2小时,将每次递增的时间存入到List<Date>集合中
            dates.add(addDateHour(date,i*2));
        }

        //判断当前时间属于哪个时间范围
        Date now = new Date();
        for (Date cdate : dates) {
            //开始时间<=当前时间<开始时间+2小时
            if(cdate.getTime()<=now.getTime() && now.getTime()<addDateHour(cdate,2).getTime()){
                now = cdate;
                break;
            }
        }

        //当前需要显示的时间菜单
        List<Date> dateMenus = new ArrayList<Date>();
        for (int i = 0; i <5 ; i++) {
            dateMenus.add(addDateHour(now,i*2));
        }
        return dateMenus;
    }

    /***
     * 时间转成yyyyMMddHH
     * @param date
     * @return
     */
    public static String date2Str(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");
        return simpleDateFormat.format(date);
    }
	
	
}
