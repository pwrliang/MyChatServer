package com.gl.mychat.database;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TblPicture entity. @author MyEclipse Persistence Tools
 */

public class TblPicture implements java.io.Serializable {

	// Fields

	private Long picId;
	private byte[] picture;
	private Date postDate;
	private Set tblUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public TblPicture() {
	}

	/** minimal constructor */
	public TblPicture(byte[] picture) {
		this.picture = picture;
	}

	/** full constructor */
	public TblPicture(byte[] picture, Date postDate, Set tblUsers) {
		this.picture = picture;
		this.postDate = postDate;
		this.tblUsers = tblUsers;
	}

	// Property accessors

	public Long getPicId() {
		return this.picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Set getTblUsers() {
		return this.tblUsers;
	}

	public void setTblUsers(Set tblUsers) {
		this.tblUsers = tblUsers;
	}

}