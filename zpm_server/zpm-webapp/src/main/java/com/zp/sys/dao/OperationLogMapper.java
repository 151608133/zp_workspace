package com.zp.sys.dao;

import com.zp.set.entity.OperationLog2;
import com.zp.sys.entity.OperationLogTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 操作日志数据持久接口
 * @author TangHaiBo
 * @since  2016-07-25
 */
public interface OperationLogMapper {
	void insertOperationLog(OperationLog2 ol);
	List<OperationLog2> getLoglistPage(OperationLog2 operationLog);
	void delOperationLog(String logId);
	OperationLogTemplate getOperationContentTemplate(Map map);
	List<HashMap<String, Object>> getLog2(OperationLog2 operationLog);
	int testOperAgain(OperationLog2 ol);
}
