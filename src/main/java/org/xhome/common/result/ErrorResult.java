package org.xhome.common.result;

import org.xhome.common.constant.Status;

/**
 * @project common
 * @author jhat
 * @email cpf624@126.com
 * @date Feb 4, 20138:13:29 PM
 */
public class ErrorResult extends Result {
	
	private static final long	serialVersionUID	= -3694537079190994308L;
	
	public ErrorResult(String message) {
		super(message);
		super.setStatus(Status.ERROR);
	}
	
	public ErrorResult(String message, Object results) {
		super(message, results);
		super.setStatus(Status.ERROR);
	}
	
}
