package com.gl.mychat.database;

import java.util.Date;

/**
 * TblNotification entity. @author MyEclipse Persistence Tools
 */

public class TblNotification implements java.io.Serializable {

	// Fields

	private Long notificationId;
	private TblUser tblUser;
	private String notification;
	private Date postDate;
	private String status;

	// Constructors

	/** default constructor */
	public TblNotification() {
	}

	/** minimal constructor */
	public TblNotification(TblUser tblUser, String notification) {
		this.tblUser = tblUser;
		this.notification = notification;
	}

	/** full constructor */
	public TblNotification(TblUser tblUser, String notification, Date postDate,
			String status) {
		this.tblUser = tblUser;
		this.notification = notification;
		this.postDate = postDate;
		this.status = status;
	}

	// Property accessors

	public Long getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public String getNotification() {
		return this.notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}