package com.gl.mychat.database;

/**
 * TblFriendapplication entity. @author MyEclipse Persistence Tools
 */

public class TblFriendapplication implements java.io.Serializable {

	// Fields

	private TblFriendapplicationId id;
	private String description;
	private String status;

	// Constructors

	/** default constructor */
	public TblFriendapplication() {
	}

	/** minimal constructor */
	public TblFriendapplication(TblFriendapplicationId id) {
		this.id = id;
	}

	/** full constructor */
	public TblFriendapplication(TblFriendapplicationId id, String description,
			String status) {
		this.id = id;
		this.description = description;
		this.status = status;
	}

	// Property accessors

	public TblFriendapplicationId getId() {
		return this.id;
	}

	public void setId(TblFriendapplicationId id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}