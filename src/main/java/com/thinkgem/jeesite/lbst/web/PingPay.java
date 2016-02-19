package com.thinkgem.jeesite.lbst.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.App;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.ChargeCollection;

/**
 * 该类用于app支付相关操作
 * @author hcc
 * 上午11:52:28
 */

@RequestMapping(value="/pingpay")
@Controller
public class PingPay {
	
	/**
	 * pingpp 管理平台对应的 API key
	 */
	public static String apiKey = "sk_test_HWzXrHvvPebPzjnDu184mTm5";
	/**
	 * pingpp 管理平台对应的应用 ID
	 */
	public static String appId = "app_urHqnH0OKuz15GGu";

	
	//阿里支付
	@RequestMapping(value="/alipay")
	@ResponseBody
	public Charge alipay(@RequestParam(value="amount",required=false)Integer amount,
			@RequestParam(value="orderno")String orderno,
			@RequestParam(value="channel")String channel) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException{
		String id = appId;
		Pingpp.apiKey = apiKey;
		
		Map<String,Object> chargeParams  = new HashMap<String,Object>();
		chargeParams.put("order_no",  orderno);
	    chargeParams.put("amount", amount);
		
	    Map<String, String> app = new HashMap<String, String>();
	    app.put("id", id);
	    chargeParams.put("app",app);
	    chargeParams.put("channel",channel);
	    chargeParams.put("currency","cny");
	    chargeParams.put("client_ip","127.0.0.1");
	    chargeParams.put("subject","Your Subject");
	    chargeParams.put("body","Your Body");
	    Charge charge = Charge.create(chargeParams);
	    System.out.println("支付宝准备支付--------------");
	    System.out.println(charge.getId());
	    System.out.println("--------------");
		
	    return charge;
	}
	
	//查询单个charge
	@RequestMapping(value="/retrieveCharge")
	@ResponseBody
    public Charge retrieve(@RequestParam("id")String id) {
		
		Charge charge = null;
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            List<String> expande = new ArrayList<String>();
            expande.add("app");
            param.put("expand", expande);
            //Charge charge = Charge.retrieve(id);
            //Expand app
            charge = Charge.retrieve(id, param);
            if (charge.getApp() instanceof App) {
                //App app = (App) charge.getApp();
                // System.out.println("App Object ,appId = " + app.getId());
            } else {
                // System.out.println("String ,appId = " + charge.getApp());
            }

            System.out.println(charge);

        } catch (PingppException e) {
            e.printStackTrace();
        }
        return charge;
    }
	
	@RequestMapping(value="/pageRetrieve")
	@ResponseBody
	public ChargeCollection all() {
        ChargeCollection chargeCollection = null;
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("limit", 3);

//增加此处设施，刻意获取app expande 
//        List<String> expande = new ArrayList<String>();
//        expande.add("app");
//        chargeParams.put("expand", expande);

        try {
            chargeCollection = Charge.all(chargeParams);
            System.out.println(chargeCollection);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return chargeCollection;
	}
	
}

