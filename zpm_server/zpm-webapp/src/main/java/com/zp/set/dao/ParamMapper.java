package com.zp.set.dao;

import com.zp.set.entity.Param;

import java.util.Map;




public interface ParamMapper {
	void updateParam(Param param);
	Param getParam(Map<String,Object> param);
}
