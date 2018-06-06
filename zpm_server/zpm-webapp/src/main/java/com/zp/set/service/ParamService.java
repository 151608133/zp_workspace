package com.zp.set.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zp.set.dao.ParamMapper;
import com.zp.set.entity.Param;


@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class ParamService{
	@Autowired
	private ParamMapper paramMapper;
	
	/**
	 * 获取系统设置
	 * @param param
	 * @return
	 */
	public Param getParam() {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", 1);//默认就取唯一的这一条
		return paramMapper.getParam(param);
	}
	
	/**
	 * 修改用户状态值  
	 * */
	public void updateState(Param param) {
		paramMapper.updateParam(param);
	}
}
