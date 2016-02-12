package com.gl.mychat.database;

import java.util.Date;

/**
 * TblTimeline entity. @author MyEclipse Persistence Tools
 */

public class TblTimeline implements java.io.Serializable {

	// Fields

	private Long timelineId;
	private TblUser tblUser;
	private String content;
	private String visibility;
	private Date pushDate;

	// Constructors

	/** default constructor */
	public TblTimeline() {
	}

	/** minimal constructor */
	public TblTimeline(TblUser tblUser, String content) {
		this.tblUser = tblUser;
		this.content = content;
	}

	/** full constructor */
	public TblTimeline(TblUser tblUser, String content, String visibility,
			Date pushDate) {
		this.tblUser = tblUser;
		this.content = content;
		this.visibility = visibility;
		this.pushDate = pushDate;
	}

	// Property accessors

	public Long getTimelineId() {
		return this.timelineId;
	}

	public void setTimelineId(Long timelineId) {
		this.timelineId = timelineId;
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

	public String getVisibility() {
		return this.visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Date getPushDate() {
		return this.pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

}