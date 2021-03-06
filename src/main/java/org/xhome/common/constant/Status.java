package org.xhome.common.constant;

/**
 * @project common
 * @author jhat
 * @email cpf624@126.com
 * @date Jan 3, 20131:51:35 PM
 */
public final class Status {
	
	public final static short	OK					= 1;	// 可正常使用的数据
	public final static short	LOCK				= 5;	// 锁定数据
	public final static short	NO_UPDATE			= 10;	// 不允许修改的数据（不允许删除）
	public final static short	NO_DELETE			= 15;	// 不允许删除的数据（允许修改）
															
	public final static short	EXISTS				= 20;	// 已经存在
	public final static short	NOT_EXISTS			= 25;	// 不存在
	public final static short	VERSION_NOT_MATCH	= 30;	// 版本不匹配
	public final static short	PASSWD_NOT_MATCH	= 35;	// 密码不匹配
	public final static short	CODE_NOT_MATCH		= 40;	// 验证码不匹配
	
	public final static short	BLOCKED				= 45;	// 操作被阻断
															
	public final static short	SUCCESS				= 0;	// 操作成功
	public final static short	ERROR				= 1;	// 操作失败
															
}
