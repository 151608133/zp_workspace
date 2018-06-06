package com.zp.sys.dao;

import java.util.List;

import com.zp.sys.entity.ProvinceCity;
import org.apache.ibatis.annotations.Param;


public interface ProvinceCityMapper {
	List<ProvinceCity> provinceCityList();
	List<ProvinceCity> provinceCityListByUid(int uid);
	String getCityNameById(int cityId);
	String getProvinceIdByuid(int cityId);
	List<ProvinceCity> getCityList();
	List<ProvinceCity> provinceCityListByUser(Long userId);
	
	//查找所有的市集合,用于验证excel表格导入时城市的验证
	public List<String> getAllCity();
	
	//RNC模板导入时根据网元名称得到网元名称归属ID
	public Integer getCityIdByName(@Param("cityName") String cityName);
}
