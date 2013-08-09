package org.xhome.common.result;

import java.io.Serializable;

import org.xhome.common.constant.Status;

/**
 * @project common
 * @author jhat
 * @email cpf624@126.com
 * @date Feb 4, 20138:11:13 PM
 */
public class Result implements Serializable {
	
	private static final long	serialVersionUID	= -1196254114557273208L;
	private short				status				= Status.SUCCESS;
	private String				message;
	private Object				results;
	
	public Result(short status) {
		this.setStatus(status);
	}
	
	public Result(short status, String message) {
		this.setStatus(status);
		this.setMessage(message);
	}
	
	public Result(short status, String message, Object results) {
		this.setStatus(status);
		this.setMessage(message);
		this.setResults(results);
	}
	
	public Result(String message) {
		this.setMessage(message);
	}
	
	public Result(String message, Object results) {
		this.setMessage(message);
		this.setResults(results);
	}
	
	public void setStatus(short status) {
		this.status = status;
	}
	
	public short getStatus() {
		return status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setResults(Object results) {
		this.results = results;
	}
	
	public Object getResults() {
		return results;
	}
	
}
