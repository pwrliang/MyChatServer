package com.gl.mychat.database;

import java.util.Date;

/**
 * TblReply entity. @author MyEclipse Persistence Tools
 */

public class TblReply implements java.io.Serializable {

	// Fields

	private Long replyId;
	private TblComment tblComment;
	private TblUser tblUser;
	private String content;
	private Date replyDate;

	// Constructors

	/** default constructor */
	public TblReply() {
	}

	/** minimal constructor */
	public TblReply(TblComment tblComment, TblUser tblUser, String content) {
		this.tblComment = tblComment;
		this.tblUser = tblUser;
		this.content = content;
	}

	/** full constructor */
	public TblReply(TblComment tblComment, TblUser tblUser, String content,
			Date replyDate) {
		this.tblComment = tblComment;
		this.tblUser = tblUser;
		this.content = content;
		this.replyDate = replyDate;
	}

	// Property accessors

	public Long getReplyId() {
		return this.replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public TblComment getTblComment() {
		return this.tblComment;
	}

	public void setTblComment(TblComment tblComment) {
		this.tblComment = tblComment;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReplyDate() {
		return this.replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

}