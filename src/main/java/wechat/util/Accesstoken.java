package wechat.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Accesstoken {
	
	public static String accesstoken;
	public static long lastUpdatetime;
	
	public static String getAccesstoken(){
		Date now = new Date();
		long currentTime = now.getTime();
		if(accesstoken == null || currentTime - lastUpdatetime >= 7200 *1000){
			accesstoken = callApiforAccesstoken();
			lastUpdatetime = currentTime;
		}
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken){
		this.accesstoken = accesstoken;
	}
	
	public static String callApiforAccesstoken() {
		CloseableHttpResponse httpResponse = null;
		String accesstoken = "";
        try {
    		// 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE"); 
			sslContext.init(null, tm, new java.security.SecureRandom());
			
			 // 从上述SSLContext对象中得到SSLSocketFactory对象  
	        SSLConnectionSocketFactory  sslf = new SSLConnectionSocketFactory(sslContext);
	        
	        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslf).build();
	      
	        String url = ConstantUtil.tencentAccesstokenApiUrl.replace("APPID", ConstantUtil.appID).replace("APPSECRET", ConstantUtil.secretID);
	        HttpGet httpGet = new HttpGet(url);
	        
	        httpResponse = httpClient.execute(httpGet);
	        
	        if(httpResponse.getStatusLine().getStatusCode() == 200){
	        	HttpEntity entity = httpResponse.getEntity();
	        	String respJson = EntityUtils.toString(entity,"UTF-8");
	        	JSONObject jsonObj = new JSONObject(respJson);
	        	accesstoken = jsonObj.getString("access_token");
	        	
	        	System.out.println(accesstoken);
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return accesstoken;
	}
	
	
	public static void writeFile(String accesstoken, String lasUpdateTime){
		Properties prop = new Properties();
		try {
			OutputStream output = new FileOutputStream(".\\src\\main\\resources\\static\\config.properties");
			prop.setProperty("accesstoken", accesstoken);
			prop.setProperty("lasUpdateTime", lasUpdateTime);
			
			prop.store(output, "");
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> readFile(){
		Properties prop = new Properties();
		Map<String, String> dataMap = null;
		try {
			InputStream input = new FileInputStream(".\\src\\main\\resources\\static\\config.properties");
			prop.load(input);
			
			String accesstoken = prop.getProperty("accesstoken");
			String lasUpdateTime = prop.getProperty("lasUpdateTime");
			System.out.println(accesstoken);
			System.out.println(lasUpdateTime);
			
			dataMap = new HashMap<>();
			dataMap.put("accesstoken", accesstoken);
			dataMap.put("lasUpdateTime", lasUpdateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	
	public static String getAccesstokenFromProp(){
		Map<String, String> dataMap = readFile();
		String propLasUpdatetimestr = dataMap.get("lasUpdateTime");
		long propLasUpdatetime = Long.parseLong(propLasUpdatetimestr);
		
		String accesstoken = dataMap.get("accesstoken");
		long timeNow = System.currentTimeMillis();
		if(accesstoken == null || timeNow - propLasUpdatetime > 2 * 60 * 59 * 1000){
			accesstoken = callApiforAccesstoken();
			String timeNowStr = String.valueOf(timeNow);
			writeFile(accesstoken, timeNowStr);
		}
		return accesstoken;
	}
	
	public static void main(String[] args) {
		System.out.println(getAccesstokenFromProp());
	}
}
