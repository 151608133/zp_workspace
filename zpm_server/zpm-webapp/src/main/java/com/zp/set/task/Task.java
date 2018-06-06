package com.zp.set.task;


import com.zp.set.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import com.zp.set.service.BakService;
import com.zp.set.util.BakUtil;


public class Task {

	@Autowired
	private ParamService paramService;
	@Autowired
	private BakService bakService;
	public void job1(){
		new BakUtil().bak(paramService, bakService);
	}
}
