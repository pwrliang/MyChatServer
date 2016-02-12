package com.gl.mychat.database;

/**
 * TblFriendId entity. @author MyEclipse Persistence Tools
 */

public class TblFriendId implements java.io.Serializable {

	// Fields

	private TblUser tblUser;
	private TblUser tblUser_1;

	// Constructors

	/** default constructor */
	public TblFriendId() {
	}

	/** full constructor */
	public TblFriendId(TblUser tblUser, TblUser tblUser_1) {
		this.tblUser = tblUser;
		this.tblUser_1 = tblUser_1;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblFriendId))
			return false;
		TblFriendId castOther = (TblFriendId) other;

		return ((this.getTblUser() == castOther.getTblUser()) || (this
				.getTblUser() != null && castOther.getTblUser() != null && this
				.getTblUser().equals(castOther.getTblUser())))
				&& ((this.getTblUser_1() == castOther.getTblUser_1()) || (this
						.getTblUser_1() != null
						&& castOther.getTblUser_1() != null && this
						.getTblUser_1().equals(castOther.getTblUser_1())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTblUser() == null ? 0 : this.getTblUser().hashCode());
		result = 37 * result
				+ (getTblUser_1() == null ? 0 : this.getTblUser_1().hashCode());
		return result;
	}

}