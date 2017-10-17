package wechat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import wechat.message.resp.Article;
import wechat.message.resp.NewsMessage;
import wechat.message.resp.TextMessage;
import wechat.util.MessageUtil;

/** 
 * 核心服务类 
 *  
 * @author liufeng 
 * @date 2013-05-20 
 */  
public class CoreService {  
    /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = "";  
        try {  
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！";  
  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
            //消息内容
            String content = requestMap.get("Content");
            
            // 回复文本消息  
            TextMessage textMessage = new TextMessage(); 
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
            
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            
            List<Article> articleList = new ArrayList<Article>(); 
            
            if(list.contains(content)){
            	if ("1".equals(content)) { 
            		
            		NewsMessage newsMessage = new NewsMessage();  
                    newsMessage.setToUserName(fromUserName);  
                    newsMessage.setFromUserName(toUserName);  
                    newsMessage.setCreateTime(new Date().getTime());  
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                    newsMessage.setFuncFlag(0);  
            		
            		
                    Article article = new Article();  
                    article.setTitle("桑迪哥户外");  
                    article.setDescription("车展第一视角，街头问卷，夜西安 欢迎捧场");  
                    article.setPicUrl("http://182.61.20.52/wechat/image/20170924_204219.jpg");  
                    article.setUrl("http://m.yizhibo.com/l/RbEJp2grZEc-dziX.html?memberid=JBupLn07oWmgG2mV&from=singlemessage&isappinstalled=0");  
                    articleList.add(article);  
                    // 设置图文消息个数  
                    newsMessage.setArticleCount(articleList.size());  
                    // 设置图文消息包含的图文集合  
                    newsMessage.setArticles(articleList);  
                    // 将图文消息对象转换成xml字符串  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                
                }else {
                	
                	TextMessage commonMessage = new TextMessage();  
                	commonMessage.setToUserName(fromUserName);  
                	commonMessage.setFromUserName(toUserName);  
                	commonMessage.setCreateTime(new Date().getTime());  
                	commonMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
                	commonMessage.setContent("敬请期待！");  
                	respMessage = MessageUtil.textMessageToXml(commonMessage);
                }
            	
            	
            }else if ("?".equals(content)){
            	
            	TextMessage mainMessage = new TextMessage(); 
            	mainMessage.setToUserName(fromUserName);  
            	mainMessage.setFromUserName(toUserName);  
            	mainMessage.setCreateTime(new Date().getTime());  
            	mainMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            	mainMessage.setFuncFlag(0);  
            	
            	StringBuffer sb = new StringBuffer();  
            	sb.append("您好，我是小邓，请回复数字选择服务：").append("\n\n");  
            	sb.append("1 户外主播实况").append("\n");  
            	sb.append("2  获取主播微信").append("\n");  
            	sb.append("3  最新动态").append("\n");  
            	sb.append("4 水友社区").append("\n");   
            	sb.append("回复“?”显示此帮助菜单");  
            	
            	mainMessage.setContent(sb.toString());
            	respMessage = MessageUtil.textMessageToXml(mainMessage);
            	
            }else if("18792891300".equals(content)){
        		TextMessage commonMessage = new TextMessage();  
            	commonMessage.setToUserName(fromUserName);  
            	commonMessage.setFromUserName(toUserName);  
            	commonMessage.setCreateTime(new Date().getTime());  
            	commonMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            	commonMessage.setContent("Dy_deng");  
            	respMessage = MessageUtil.textMessageToXml(commonMessage);
            }
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }  
}  
