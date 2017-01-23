package org.dante.demo.getway.dto.resp;

import java.io.Serializable;

public class BaseResp<T> implements Serializable {
	private static final long serialVersionUID = -3925058361524798133L;

	private int resultCode = RespCode.SUCCESS.code();

	private String errorMsg;

	private T result;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
