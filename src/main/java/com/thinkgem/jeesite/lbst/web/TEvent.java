package com.thinkgem.jeesite.lbst.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.EventCollection;
import com.pingplusplus.model.Refund;
import com.pingplusplus.model.Summary;
import com.pingplusplus.model.Webhooks;

/**
 * 事件提示的类
 * @author hcc
 * 下午3:08:17
 */

@RequestMapping(value="/event")
@Controller
public class TEvent {

	/**
	 * pingpp 管理平台对应的 API key
	 */
	public static String apiKey = "sk_test_HWzXrHvvPebPzjnDu184mTm5";
	/**
	 * pingpp 管理平台对应的应用 ID
	 */
	public static String appId = "app_urHqnH0OKuz15GGu";

	/**
	 * 要查询的eventid
	 */
    public static String eventid ="YOUR-EVNETID";
    
    @RequestMapping(value="/retrieve")
    public void retrieve(String id){
    	Event event = null;
    	try {
            event = Event.retrieve(id);
            System.out.println(event);
            //解析webhooks 可以采用如下方法
            Object obj = Webhooks.parseEvnet(event.toString());
            if (obj instanceof Charge) {
                System.out.println("webhooks 发送了 Charge");
            } else if (obj instanceof Refund) {
                System.out.println("webhooks 发送了 Refund");
            } else if (obj instanceof Summary) {
                System.out.println("webhooks 发送了 Summary");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 批量查询event
     */
    public void all() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("limit", 3);
        try {
            EventCollection eventCollection = Event.all(params);
            System.out.println(eventCollection);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } 
    }
}
