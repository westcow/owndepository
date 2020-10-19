package com.bjb.project.util;

public enum JsonResultData {
	UPLOAD_FIAL(302,"上传失败"),
	UPLOAD_SUCCESS(200,"上传成功"),
	DOWNLOAD_FIAL(302,"下载失败"),
	DOWNLOAD_SUCCESS(200,"下载成功"),
	ERROR(0, "系统内部错误，请稍后再试！"),
	PARAMETER_ERROR(302, "入参错误！"),
	USER_NO_EXIST(302, "用户不存在！"),
	PASSWORD_ERROR(302, "密码错误！"),
	SELECT_SUCCESS(200,"获取成功!" ),
	MOBILE_CODE_FAIL(302,"验证码无效"),
	SEND_FAIL(302,"短信发送失败!" ),
	GET_SUCCESS(200,"获取成功!" ),
	ADD_SUCCESS(200,"新增成功!" ),
	DELETE_SUCCESS(200,"删除成功!" ),
	UPDATE_SUCCESS(200,"更新成功!" ),
	EXPORT_SUCCESS(200,"导出成功!" );
	private JsonResultData(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}

	private int code;
	private String msg;
}
