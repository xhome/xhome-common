package org.xhome.common;

import java.sql.Timestamp;

/**
 * @project common
 * @author jhat
 * @email cpf624@126.com
 * @date Jan 3, 201312:29:04 PM
 */
public abstract class Base implements java.io.Serializable {
	
	private static final long	serialVersionUID	= 4129037464672502700L;
	protected Long				id;
	protected Long				owner;
	protected Long				modifier;
	protected Timestamp			created;
	protected Timestamp			modified;
	protected Long				version				= 0L;
	protected Short				status				= Status.OK;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setOwner(Long owner) {
		this.owner = owner;
	}
	
	public Long getOwner() {
		return owner;
	}
	
	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}
	
	public Long getModifier() {
		return modifier;
	}
	
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	public Timestamp getCreated() {
		return created;
	}
	
	public void setModified(Timestamp modified) {
		this.modified = modified;
	}
	
	public Timestamp getModified() {
		return modified;
	}
	
	public void setStatus(Short status) {
		this.status = status;
	}
	
	public Short getStatus() {
		return status;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
	
	public Long getVersion() {
		return version;
	}
	
	public void incrementVersion() {
		this.version++;
	}
	
}
