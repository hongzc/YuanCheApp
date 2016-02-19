/**
  * 文件说明
  * @Description:扩展说明
  * @Copyright: 2015 dreamtech.com.cn Inc. All right reserved
  * @Version: V6.0
  */
package com.thinkgem.jeesite.lbst.message.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**  
 * @Author: feizi
 * @Date: 2015年4月17日 上午9:24:48 
 * @ModifyUser: feizi
 * @ModifyDate: 2015年4月17日 上午9:24:48 
 * @Version:V6.0
 */
public class SendMsgUtil {
	
	/**
	 * 发送短信消息
	  * 方法说明
	  * @Discription:扩展说明
	  * @param phones
	  * @param content
	  * @return
	  * @return String
	  * @Author: feizi
	  * @Date: 2015年4月17日 下午7:18:08
	  * @ModifyUser：feizi
	  * @ModifyDate: 2015年4月17日 下午7:18:08
	 */
	@SuppressWarnings("deprecation")
	public static String sendMsg(String phones,String content){
		//短信接口URL提交地址
		String url = " http://dx.10659com.com:83/ApiService.asmx/Send";
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("account", "123456");
		params.put("pwd", "123456");
		params.put("product", "666666");
		params.put("message", "hongzhengcan");
		
		//手机号码，多个号码使用英文逗号进行分割
		params.put("mobile", phones);
		//将短信内容进行URLEncoder编码
		params.put("nr", URLEncoder.encode(content));
		
		return HttpRequestUtil.getRequest(url, params);
	}
	
	/**
	 * 随机生成6位随机验证码
	  * 方法说明
	  * @Discription:扩展说明
	  * @return
	  * @return String
	  * @Author: feizi
	  * @Date: 2015年4月17日 下午7:19:02
	  * @ModifyUser：feizi
	  * @ModifyDate: 2015年4月17日 下午7:19:02
	 */
	public static String createRandomVcode(){
		//验证码
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int)(Math.random() * 9);
		}
		return vcode;
	}
	
	/**
	 * 测试
	  * 方法说明
	  * @Discription:扩展说明
	  * @param args
	  * @return void
	  * @Author: feizi
	  * @Date: 2015年4月17日 下午7:26:36
	  * @ModifyUser：feizi
	  * @ModifyDate: 2015年4月17日 下午7:26:36
	 */
	public static void main(String[] args) {
		System.out.println(SendMsgUtil.createRandomVcode());
		System.out.println("---------------------------------------");
		System.out.println("&ecb=12".substring(1));
		System.out.println("---------------------------------------kk");
		System.out.println(sendMsg("18123456789,15123456789", "尊敬的用户，您的验证码为" + SendMsgUtil.createRandomVcode() + "，有效期为60秒，如有疑虑请详询400-069-2886（客服电话）【XXX中心】"));
	}
}
