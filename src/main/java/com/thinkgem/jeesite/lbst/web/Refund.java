package com.thinkgem.jeesite.lbst.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.ChargeRefundCollection;

/**
 * 该类主要用于操作退款相关
 * @author hcc
 * 上午11:53:28
 */
@RequestMapping(value="/refund")
@Controller
public class Refund {
	
	/**
	 * pingpp 管理平台对应的 API key
	 */
	public static String apiKey = "sk_test_SWT4qDq1u1WLG4K48018mTWH";
	/**
	 * 退款的chargeId
	 */
	public static String chargeId = "ch_znPO0G8iDiPCrb54W1ePq9eL";
	
	@RequestMapping(value="/refund")
	@ResponseBody
	public com.pingplusplus.model.Refund aliRefund(@RequestParam(value="charge",required=false)Charge charge,
			@RequestParam(value="description",required=false)String description){
		
		
		com.pingplusplus.model.Refund refund = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("description", description);

        try {
        	refund = charge.getRefunds().create(params);
            System.out.println(refund);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return refund;
	}
	
	/**
	 * 查询退款
	 * @param id
	 * @param charge
	 */
	@RequestMapping(value="/retrieveRefund")
	@ResponseBody
	public com.pingplusplus.model.Refund retrieve(@RequestParam(value="id")String id, 
			@RequestParam("charge")Charge charge) {

		com.pingplusplus.model.Refund refund = null;
        try {
        	refund = charge.getRefunds().retrieve(id);
            System.out.println(refund);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return refund;
    }
	
	/**
	 * 分页查询退款
	 * @param charge
	 * @return 
	 */
	@RequestMapping(value="/pageRefund")
	@ResponseBody
	public ChargeRefundCollection all(Charge charge) {
        Map<String, Object> refundParams = new HashMap<String, Object>();
        refundParams.put("limit", 3);
        
        ChargeRefundCollection refunds = null;
        
        try {
            refunds = charge.getRefunds().all(refundParams);
            System.out.println(refunds);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } 
        
        return refunds;
    }

}
