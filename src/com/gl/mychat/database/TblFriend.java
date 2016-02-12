package com.gl.mychat.database;

/**
 * TblFriend entity. @author MyEclipse Persistence Tools
 */

public class TblFriend implements java.io.Serializable {

	// Fields

	private TblFriendId id;
	private String description;
	private Boolean block;

	// Constructors

	/** default constructor */
	public TblFriend() {
	}

	/** minimal constructor */
	public TblFriend(TblFriendId id) {
		this.id = id;
	}

	/** full constructor */
	public TblFriend(TblFriendId id, String description, Boolean block) {
		this.id = id;
		this.description = description;
		this.block = block;
	}

	// Property accessors

	public TblFriendId getId() {
		return this.id;
	}

	public void setId(TblFriendId id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getBlock() {
		return this.block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

}