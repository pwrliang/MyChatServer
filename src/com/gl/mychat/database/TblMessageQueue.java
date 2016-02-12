package com.gl.mychat.database;

import java.util.Date;

/**
 * TblMessageQueue entity. @author MyEclipse Persistence Tools
 */

public class TblMessageQueue implements java.io.Serializable {

	// Fields

	private Long messageId;
	private TblUser tblUserByToId;
	private TblUser tblUserByFromId;
	private String content;
	private String contentType;
	private String status;
	private Date postDate;

	// Constructors

	/** default constructor */
	public TblMessageQueue() {
	}

	/** minimal constructor */
	public TblMessageQueue(TblUser tblUserByToId, TblUser tblUserByFromId,
			String content) {
		this.tblUserByToId = tblUserByToId;
		this.tblUserByFromId = tblUserByFromId;
		this.content = content;
	}

	/** full constructor */
	public TblMessageQueue(TblUser tblUserByToId, TblUser tblUserByFromId,
			String content, String contentType, String status, Date postDate) {
		this.tblUserByToId = tblUserByToId;
		this.tblUserByFromId = tblUserByFromId;
		this.content = content;
		this.contentType = contentType;
		this.status = status;
		this.postDate = postDate;
	}

	// Property accessors

	public Long getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public TblUser getTblUserByToId() {
		return this.tblUserByToId;
	}

	public void setTblUserByToId(TblUser tblUserByToId) {
		this.tblUserByToId = tblUserByToId;
	}

	public TblUser getTblUserByFromId() {
		return this.tblUserByFromId;
	}

	public void setTblUserByFromId(TblUser tblUserByFromId) {
		this.tblUserByFromId = tblUserByFromId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

}