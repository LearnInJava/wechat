package wechat.util;

import java.security.KeyManagementException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class WechatUtil {
	
	public static String deleteMenu(){
    
		CloseableHttpResponse httpResponse = null;
		String result = "";
        try {
    		// 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE"); 
			sslContext.init(null, tm, new java.security.SecureRandom());
			
			 // 从上述SSLContext对象中得到SSLSocketFactory对象  
	        SSLConnectionSocketFactory  sslf = new SSLConnectionSocketFactory(sslContext);
	        
	        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslf).build();
	        
	        //https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN
	        String accessToken = "TwYblDbC5arrddZ3mSoItNnycJzupqEJXtlKeurl7RLBgg23jkjbD5aWa2OomGAoYEfNhCO3d989tlwUNtGJsz_T6K5pk9OHVH-00s4-Akx6I292iI56uZO8Term79rdPQNjAAABCZ";
	        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", accessToken);
	        HttpGet httpGet = new HttpGet(url);
	        
	        httpResponse = httpClient.execute(httpGet);
	        
	        if(httpResponse.getStatusLine().getStatusCode() == 200){
	        	HttpEntity entity = httpResponse.getEntity();
	        	result = EntityUtils.toString(entity,"UTF-8");
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}  
       
		return result;
	}
	
	
	public static String uploadNews(){
	    
		CloseableHttpResponse httpResponse = null;
		String result = "";
        try {
    		// 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE"); 
			sslContext.init(null, tm, new java.security.SecureRandom());
			
			 // 从上述SSLContext对象中得到SSLSocketFactory对象  
	        SSLConnectionSocketFactory  sslf = new SSLConnectionSocketFactory(sslContext);
	        
	        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslf).build();
	        
	        //https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN
	        String accessToken = "m0gHTkEPNojrdtV2y1kTPuTBT-mV6A5hSV81UwSNNnUVKsgjSmSCSyajGpM1P54odF9XoXHGR9mH8kG_1tkGcONeo9hr84GzkgULtLL9bK-SPx24pdBoND8nlQkANEKYCKPjAEAWEJ";
	        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", accessToken);
	        HttpPost httpPost = new HttpPost(url);
	        
	        JSONObject object = new JSONObject();
	        object.put("thumb_media_id", "tN6wacy8pcg5I_Xt6-WG_rh8FWfFdFjUxl9UAJO-Mgk6JCNjHfFhUrXHh8iSxT8D");
	        object.put("author", "sunday");
	        object.put("title", "sunday is become better");
	        object.put("content_source_url", "www.qq.com");
	        object.put("content", "sunday is a good boy ,he is cleaver and work hard ,he can be famous and rich in the near future");
	        object.put("digest", "");
	        object.put("show_cover_pic", 1);
	        
	        JSONArray array = new JSONArray();
	        array.put(object);
	        
	        JSONObject root = new JSONObject();
	        root.put("articles", array);
	        
	        StringEntity stringEntity = new StringEntity(root.toString());
	        httpPost.setEntity(stringEntity);
	        
	        httpResponse = httpClient.execute(httpPost);
	        
	        if(httpResponse.getStatusLine().getStatusCode() == 200){
	        	HttpEntity entity = httpResponse.getEntity();
	        	result = EntityUtils.toString(entity,"UTF-8");
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}  
       
        		
		return result;
	}
	
	public static String sendMsg(){
	    
		CloseableHttpResponse httpResponse = null;
		String result = "";
        try {
    		// 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE"); 
			sslContext.init(null, tm, new java.security.SecureRandom());
			
			 // 从上述SSLContext对象中得到SSLSocketFactory对象  
	        SSLConnectionSocketFactory  sslf = new SSLConnectionSocketFactory(sslContext);
	        
	        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslf).build();
	        
	        //https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN
	        String accessToken = "FdJnOkEtXoXrgwEx-UfZH_Oi38L6y9E0e0Uzof2rWmEVf6SvW41b_jJ3RJqA62PLJJ7sTZtWqvwBWP0zxtXA4_6caWyg6YDYcYw1YHvFGxPSwIaJYqysCGR4PCKNo_K6FEOiACAVUE";
	        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", accessToken);
	        HttpPost httpPost = new HttpPost(url);
	        
	        JSONObject filter = new JSONObject();
	        filter.put("is_to_all", true);
	        filter.put("tag_id", 1);
	        JSONObject text = new JSONObject();
	        text.put("content", "Today is  national day , coding is the good idea ");
	        
	        JSONObject root = new JSONObject();
	        root.put("filter", filter);
	        root.put("text", text);
	     
	        root.put("msgtype", "text");
	    
	        
	        StringEntity stringEntity = new StringEntity(root.toString());
	        httpPost.setEntity(stringEntity);
	        
	        httpResponse = httpClient.execute(httpPost);
	        
	        if(httpResponse.getStatusLine().getStatusCode() == 200){
	        	HttpEntity entity = httpResponse.getEntity();
	        	result = EntityUtils.toString(entity,"UTF-8");
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return result;
	}
	
	public static void sendTemplateMsg(){
		CloseableHttpResponse httpResponse = null;
		String result = "";
        try {
    		// 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE"); 
			sslContext.init(null, tm, new java.security.SecureRandom());
			
			 // 从上述SSLContext对象中得到SSLSocketFactory对象  
	        SSLConnectionSocketFactory  sslf = new SSLConnectionSocketFactory(sslContext);
	        
	        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslf).build();
	        
	        //https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN
	        String accessToken = "CZ_0y2UVwNmB7AvpHCSK3AD3dxU0GXekGZv2CGuoyXSNFQrJfFvIj0hT96TBIzq7OkK84lOZYxaQajO_LNR2PjQsWYlIyy22IaC4P5QiyOxs64SO9favBSdKKJT1aip1GLKeAJAQVU";
	        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", accessToken);
	        HttpPost httpPost = new HttpPost(url);
	        
	        JSONObject f = new JSONObject();
	        f.put("value", "恭喜您购物成功！");
	        f.put("color", "#173177");
	        
	        JSONObject product = new JSONObject();
	        product.put("value", "韩版西服");
	        product.put("color", "#173177");
	        
	        JSONObject price = new JSONObject();
	        price.put("value", "149元");
	        price.put("color", "#173177");

	        JSONObject time = new JSONObject();
	        time.put("value", "2014-12-04 13:09:17");
	        time.put("color", "#173177");
	        
	        JSONObject remark = new JSONObject();
	        remark.put("value", "感谢您的光临，我们将尽快发货！");
	        remark.put("color", "#173177");
	        
	        JSONObject combine = new JSONObject();
	        combine.put("f", f);
	        combine.put("product", product);
	        combine.put("price", price);
	        combine.put("time", time);
	        combine.put("remark", remark);
	        
	        JSONObject root = new JSONObject();
	        root.put("touser", "oat3y0fJF9a5YgwLjkUeAY32_bHM");
	        root.put("template_id", "nfzgP48aScqAPW5qS3QIYDaZgMQ5Rz28Y8K8AQNtRZk");
	        root.put("url", "http://weixin.qq.com/download");
	        root.put("topcolor", "#FF0000");
	        root.put("data", combine);
	        
	        StringEntity stringEntity = new StringEntity(root.toString(),"UTF-8");
	        httpPost.setEntity(stringEntity);
	        
	        httpResponse = httpClient.execute(httpPost);
	        
	        if(httpResponse.getStatusLine().getStatusCode() == 200){
	        	HttpEntity entity = httpResponse.getEntity();
	        	result = EntityUtils.toString(entity,"UTF-8");
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}  
        System.out.println(result);
	}
	
	
	
	public static void main(String[] args) {
		sendTemplateMsg();
	}
}
