package com.gl.mychat.database;

import java.util.Date;

/**
 * TblFriendapplicationId entity. @author MyEclipse Persistence Tools
 */

public class TblFriendapplicationId implements java.io.Serializable {

	// Fields

	private TblUser tblUser;
	private TblUser tblUser_1;
	private Date applyDate;

	// Constructors

	/** default constructor */
	public TblFriendapplicationId() {
	}

	/** full constructor */
	public TblFriendapplicationId(TblUser tblUser, TblUser tblUser_1,
			Date applyDate) {
		this.tblUser = tblUser;
		this.tblUser_1 = tblUser_1;
		this.applyDate = applyDate;
	}

	// Property accessors

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public TblUser getTblUser_1() {
		return this.tblUser_1;
	}

	public void setTblUser_1(TblUser tblUser_1) {
		this.tblUser_1 = tblUser_1;
	}

	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblFriendapplicationId))
			return false;
		TblFriendapplicationId castOther = (TblFriendapplicationId) other;

		return ((this.getTblUser() == castOther.getTblUser()) || (this
				.getTblUser() != null && castOther.getTblUser() != null && this
				.getTblUser().equals(castOther.getTblUser())))
				&& ((this.getTblUser_1() == castOther.getTblUser_1()) || (this
						.getTblUser_1() != null
						&& castOther.getTblUser_1() != null && this
						.getTblUser_1().equals(castOther.getTblUser_1())))
				&& ((this.getApplyDate() == castOther.getApplyDate()) || (this
						.getApplyDate() != null
						&& castOther.getApplyDate() != null && this
						.getApplyDate().equals(castOther.getApplyDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTblUser() == null ? 0 : this.getTblUser().hashCode());
		result = 37 * result
				+ (getTblUser_1() == null ? 0 : this.getTblUser_1().hashCode());
		result = 37 * result
				+ (getApplyDate() == null ? 0 : this.getApplyDate().hashCode());
		return result;
	}

}