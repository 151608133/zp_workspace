package com.zp.set.dao;

import com.zp.set.entity.SysBak;
import com.zp.set.entity.SysBakRestore;
import com.zp.set.entity.SysBakTask;

import java.util.List;
import java.util.Map;


public interface BakMapper {
	List<SysBakTask> getSysBakTask();
	void saveSysBak(SysBak sysBak);
	void saveSysBakTask(SysBakTask sysBakTask);
	void delSysBakTask();
	List<SysBak> listPageSysBak(SysBak sysBak);
	List<SysBak> getSysBak(SysBak sysBak);
	List<SysBak> getSysBak2(String bakFileName);
	List<SysBak> findlistPageSysBak(Map<String,Object> map);
	void updateSysBakStatus(SysBak sysBak);
	void saveSysBakRestore(SysBakRestore sysBakRestore);
	void updateSysBakRestore(SysBakRestore sysBakRestore);
	void updateSysBakRestore2();
	List<SysBakRestore> querySysBakRestore(Long user_id);
}
