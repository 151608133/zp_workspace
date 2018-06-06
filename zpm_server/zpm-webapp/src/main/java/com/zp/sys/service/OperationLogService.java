package com.zp.sys.service;

import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import com.zp.core.util.Const;
import com.zp.sys.dao.OperationLogMapper;
import com.zp.sys.entity.User;
import com.zp.sys.util.IpMacUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zp.set.entity.OperationLog2;


/**
 * 操作日志的服务类
 * @author TangHaiBo
 * @since 2016-07-26
 */
@Service
@Transactional
public class OperationLogService{
	@Autowired
	private OperationLogMapper operationLogMapper;
	
	/**
	 * 记录系统日志
	 * @param request
	 * @param operType  操作类型（点击模块名,登陆成功,登陆失败,注销,用户设置,角色设置，自动采集，手动采集，导入，基站重启，PING测试，RNC备份，RNC配置，告警配置，任务配置）
	 * @param clickName  操作类型是 点击模块,这里填模块名称,否则填null
	 * @param resultCode  成功,失败，
	 * @param description  描述,说明（增加|删除|修改|查询操作）、（导入数量类型）、（配置备注）等等
	 */
	public void insertOperationLog(HttpServletRequest request,String operType,String description,
			String clickName,String resultCode) {
		try{
			OperationLog2 ol=new OperationLog2();
			User user = (User)request.getSession().getAttribute(Const.SESSION_USER);
			if(user != null){
				ol.setUserId(user.getUserId());
			}else{
				ol.setUserId(0L);
			}
			ol.setIp(IpMacUtils.getRemoteAddress(request));
			ol.setOperType(operType);
			ol.setResultCode(resultCode);
			ol.setClickName(clickName);
			ol.setDescription(description);
			operationLogMapper.insertOperationLog(ol);
		} catch (Exception e){
			System.out.println("====<<<<====<<<<====<<<<====<<<< 日志记录异常，请检查 ====<<<<====<<<<====<<<<====<<<<");
		}
	}
	
	public List<OperationLog2> getLoglistPage(OperationLog2 operationLog){
		return operationLogMapper.getLoglistPage(operationLog);
	}
	
	public void delOperationLog(String logId){
		operationLogMapper.delOperationLog(logId);
	}

	public List<HashMap<String, Object>> getLoglistPage2(
			OperationLog2 operationLog) {
		return operationLogMapper.getLog2(operationLog);
	}
}
