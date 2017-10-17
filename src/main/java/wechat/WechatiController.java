package wechat;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wechat.exception.AesException;
import wechat.service.CoreService;
import wechat.util.Accesstoken;
import wechat.util.MessageUtil;

@Controller
@RequestMapping(value="wechat")
public class WechatiController {
	
	private static String token = "wechat116";
	
	@RequestMapping(value="/connect", method = {RequestMethod.GET, RequestMethod.POST})
	public void connectWechat (HttpServletRequest request , HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		
		if(isGet){
			PrintWriter out = response.getWriter();
			//signature timestamp nonce echostr
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			
			System.out.println(signature);
			System.out.println(timestamp);
			System.out.println(nonce);
			System.out.println(echostr);
			
			String hexstr = MessageUtil.getSHA1(token, timestamp, nonce);
			if(signature.equals(hexstr)){
				System.out.println("signature == hexstr");
				out.print(echostr);
			}
			out.close();
			out = null;
		}else{
			
			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
	        request.setCharacterEncoding("UTF-8");  
	        response.setCharacterEncoding("UTF-8");  
	  
	        // 调用核心业务类接收消息、处理消息  
	        String respMessage = CoreService.processRequest(request);  
	          
	        // 响应消息  
	        PrintWriter out = response.getWriter();  
	        out.print(respMessage);  
	        out.close();  
			
			
		}
	}
	
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public void testCache(){
		String acctoken = Accesstoken.getAccesstoken();
		System.out.println(acctoken);
	}
	
}
