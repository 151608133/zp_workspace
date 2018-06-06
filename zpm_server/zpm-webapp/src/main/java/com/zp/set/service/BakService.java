package com.zp.set.service;

import com.zp.set.dao.BakMapper;
import com.zp.set.entity.SysBak;
import com.zp.set.entity.SysBakRestore;
import com.zp.set.entity.SysBakTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class BakService {

	@Autowired
	private BakMapper bakMapper;
	/**
	 * 查询当前备份还原是否有人在操作
	 */
	public List<SysBakTask> getSysBakTask(){
		return bakMapper.getSysBakTask();
	}
	/**
	 * 记录一条备份还原操作信息
	 * @param sysBakTask
	 */
	public void saveSysBakTask(SysBakTask sysBakTask){
		bakMapper.saveSysBakTask(sysBakTask);
	}
	/**
	 * 删除备份还原操作信息
	 */
	public void delSysBakTask(){
		bakMapper.delSysBakTask();
	}
	/**
	 * 保存备份信息
	 * @param sysBak
	 */
	public void saveSysBak(SysBak sysBak){
		bakMapper.saveSysBak(sysBak);
	}
	/**
	 * 获取备份信息
	 * @param sysBak
	 * @return
	 */
	public List<SysBak> listPageSysBak(SysBak sysBak){
		return bakMapper.listPageSysBak(sysBak);
	}
	public List<SysBak> getSysBak(SysBak sysBak){
		return bakMapper.getSysBak(sysBak);
	}
	public List<SysBak> getSysBak2(String bakFileName){
		return bakMapper.getSysBak2(bakFileName);
	}
	/**
	 * 根据条件查询备份信息
	 * @param map
	 * @return
	 */
	public List<SysBak> findlistPageSysBak(Map<String,Object> map){
		return bakMapper.findlistPageSysBak(map);
	}
	
	/**
	 * 删除备份信息(假删)
	 * @param sysBak
	 */
	public void updateSysBakStatus(SysBak sysBak){
		bakMapper.updateSysBakStatus(sysBak);
	}
	/**
	 * 保存还原信息
	 * @param sysBakRestore
	 */
	public void saveSysBakRestore(SysBakRestore sysBakRestore){
		bakMapper.saveSysBakRestore(sysBakRestore);
	}
	/**
	 * 修改还原信息的状态
	 * @param sysBakRestore
	 */
	public void updateSysBakRestore(SysBakRestore sysBakRestore){
		bakMapper.updateSysBakRestore(sysBakRestore);
	}
	/**
	 * 将所有还原信息的状态设置为已提示状态
	 */
	public void updateSysBakRestore2(){
		bakMapper.updateSysBakRestore2();
	}
	/**
	 * 获取最后一次还原信息,且没有进行过提示的
	 * @param user_id
	 * @return
	 */
	public List<SysBakRestore> querySysBakRestore(Long user_id){
		return bakMapper.querySysBakRestore(user_id);
	}
}
