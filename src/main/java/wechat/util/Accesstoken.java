package wechat.util;

import java.util.Date;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Accesstoken {
	
	public static String accesstoken;
	public static long lastUpdatetime = System.currentTimeMillis();
	
	public static String getAccesstoken(){
		if(accesstoken==null||System.currentTimeMillis()-lastUpdatetime >=7200 *1000){
			accesstoken = callApiforAccesstoken();
			lastUpdatetime = System.currentTimeMillis();
		}
		return accesstoken;
	}
	

	public void setAccesstoken(String accesstoken){
		this.accesstoken = accesstoken;
	}
	
	private static String callApiforAccesstoken() {
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
	        	accesstoken = EntityUtils.toString(entity,"UTF-8");
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return accesstoken;
	}
	
	
	public static void main(String[] args) {
		String acctoken = getAccesstoken();
		System.out.println(acctoken);
	}
}
