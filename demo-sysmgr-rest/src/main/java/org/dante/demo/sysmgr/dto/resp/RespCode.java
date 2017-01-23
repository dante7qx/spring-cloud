package org.dante.demo.sysmgr.dto.resp;

public enum RespCode {
	SUCCESS(1000, "响应成功"),
	FAILURE(2000, "后台错误"),
	LACK_PARAM(2001, "缺少参数");
	
	private final int code;
	private final String msg;
	
	private RespCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int code() {
		return code;
	}

	public String msg() {
		return msg;
	}
}