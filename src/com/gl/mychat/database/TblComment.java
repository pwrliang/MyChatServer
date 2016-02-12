package com.gl.mychat.database;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TblComment entity. @author MyEclipse Persistence Tools
 */

public class TblComment implements java.io.Serializable {

	// Fields

	private Long commentId;
	private TblUser tblUserByTimelineId;
	private TblUser tblUserByFromId;
	private String content;
	private Date commentDate;
	private Set tblReplies = new HashSet(0);

	// Constructors

	/** default constructor */
	public TblComment() {
	}

	/** minimal constructor */
	public TblComment(TblUser tblUserByTimelineId, TblUser tblUserByFromId,
			String content) {
		this.tblUserByTimelineId = tblUserByTimelineId;
		this.tblUserByFromId = tblUserByFromId;
		this.content = content;
	}

	/** full constructor */
	public TblComment(TblUser tblUserByTimelineId, TblUser tblUserByFromId,
			String content, Date commentDate, Set tblReplies) {
		this.tblUserByTimelineId = tblUserByTimelineId;
		this.tblUserByFromId = tblUserByFromId;
		this.content = content;
		this.commentDate = commentDate;
		this.tblReplies = tblReplies;
	}

	// Property accessors

	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public TblUser getTblUserByTimelineId() {
		return this.tblUserByTimelineId;
	}

	public void setTblUserByTimelineId(TblUser tblUserByTimelineId) {
		this.tblUserByTimelineId = tblUserByTimelineId;
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

	public Date getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Set getTblReplies() {
		return this.tblReplies;
	}

	public void setTblReplies(Set tblReplies) {
		this.tblReplies = tblReplies;
	}

}