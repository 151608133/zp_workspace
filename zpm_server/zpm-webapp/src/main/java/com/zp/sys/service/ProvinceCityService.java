package com.zp.sys.service;

import java.util.List;

import com.zp.sys.entity.ProvinceCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zp.sys.dao.ProvinceCityMapper;


@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class ProvinceCityService{
	@Autowired
	private ProvinceCityMapper provinceCityMapper;
	
	/**
	 * 获取省列表
	 * @return
	 */
	@Cacheable(value="basicCache",key="'provinceCityList'")
	public List<ProvinceCity> provinceCityList() {
		return provinceCityMapper.provinceCityList();
	}
	
	/**
	 * 获取市列表
	 * @return
	 */
	@Cacheable(value="basicCache",key="'provinceCityListByUid'+#uid")
	public List<ProvinceCity> provinceCityListByUid(int uid) {
		return provinceCityMapper.provinceCityListByUid(uid);
	}
	
	/**
	 * 根据ID获得城市名称
	 * */
	public String getCityNameById(int cityId){
		return provinceCityMapper.getCityNameById(cityId);
	}
	
	public String getProvinceIdByuid(int cityId){
		return provinceCityMapper.getProvinceIdByuid(cityId);
	}

	public List<ProvinceCity> getCityList() {
		
		return provinceCityMapper.getCityList();
	}
	
	public List<ProvinceCity> provinceCityListByUser(Long userId) {
		
		return provinceCityMapper.provinceCityListByUser(userId);
	}

}
