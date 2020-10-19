package com.bjb.project.util;

import com.github.pagehelper.Page;

import java.util.Calendar;
import java.util.Date;

public class ReturnJsonObject {

	public int returnCode;

	public String msg;

	public Object data;

	public Date nowTime;

	public ReturnJsonObject(JsonResultData error, Object data) {
		this.returnCode = error.getCode();
		this.msg = error.getMsg();
		if (data instanceof Page<?>) {
			data = PageUtils.pageToMap(data);
		}
		this.data = data;
		this.nowTime = Calendar.getInstance().getTime();
	}

	public ReturnJsonObject(){
		super();
	}
}
