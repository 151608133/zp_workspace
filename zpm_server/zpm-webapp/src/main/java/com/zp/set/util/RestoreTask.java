package com.zp.set.util;


import com.zp.set.entity.SysBakRestore;
import com.zp.set.entity.SysBakTask;
import com.zp.set.service.BakService;
import com.zp.sys.entity.User;

public class RestoreTask implements Runnable{
	private BakService bakService;
	private SysBakRestore sysBakRestore;
	private User user;
	
	public RestoreTask(BakService bakService, SysBakRestore sysBakRestore,User user) {
		this.bakService = bakService;
		this.sysBakRestore = sysBakRestore;
		this.user = user;
	}

	public void run() {
		SysBakTask sysBakTask = new SysBakTask();
		sysBakTask.setTask_status(2);
		sysBakTask.setUser_id(user.getUserId());
		bakService.saveSysBakTask(sysBakTask);
		sysBakRestore.setUser_id(user.getUserId());
		sysBakRestore.setRestore_status(1);
		bakService.updateSysBakRestore2();
		new BakUtil().restore(bakService, sysBakRestore);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bakService.saveSysBakRestore(sysBakRestore);
		bakService.delSysBakTask();
	}

}
