package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.BeanWeather;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DBUtil {
	private static String drivers = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql:///showweather?useUnicode=true&characterEncoding=UTF-8";
	private static String user = "root";
	private static String password = "5555";
	private static Connection con = null;
	static List<BeanWeather> list=null;
	static{
		
		try {
			Class.forName(drivers);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("OK...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//根据前台传来的page和rows来从获取响应数据
	public static String getJSONString(int page,int rows){
		String sql="select * from info order by dateTime desc limit "+(page-1)*10+","+rows+";";
		JSONObject object=new JSONObject();
		object.put("total", getTotal(""));
		List<BeanWeather> list=getQuery(sql);
		object.put("rows", JSONArray.fromObject(list));
		return object.toString();
	}
	//根据提供的sql来执行查询并得到list集合
	public static List<BeanWeather> getQuery(String sql){
		list=new ArrayList<BeanWeather>();
		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet set=stat.executeQuery(sql);
			while(set.next()){
				BeanWeather bean=new BeanWeather();
				bean.setSiteName(set.getString(1));
				bean.setCODMn(set.getString(2));
				bean.setAttribute(set.getString(4));
				bean.setDateTime(set.getString(5));
				bean.setLevel(set.getString(6));
				bean.setStatus(set.getString(7));
				System.out.println(bean.toString());
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static String getJSONSearch(int page,int rows,String search){
		String sql="";
		JSONObject object=new JSONObject();
		if(search==""||search==null){
			sql="select * from info order by dateTime desc limit "+(page-1)*10+","+rows+";";
			object.put("total", getTotal(""));
		}else{
			sql="select * from info where siteName LIKE '%"+search+"%' order by dateTime desc limit "+(page-1)*10+","+rows+";";
			object.put("total", getTotal("where siteName LIKE '%"+search+"%';"));
		}
		object.put("rows", JSONArray.fromObject(getQuery(sql)));
		return object.toString();
	}
	//获取数据总数
	public static int getTotal(String str){
		int total=0;
		String sql="";
		try {
			Statement stat=con.createStatement();
			if(str==""||str==null){
				sql = "select count(*) totalCount from info";  
			}else{
				sql = "select count(*) totalCount from info "+str;  
			}
			ResultSet set=stat.executeQuery(sql);
			while(set.next()){
				total=set.getInt("totalCount"); 
				return total;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
}
