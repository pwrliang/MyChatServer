package com.gl.mychat.database;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TblUser entity. @author MyEclipse Persistence Tools
 */

public class TblUser implements java.io.Serializable {

	// Fields

	private Long userId;
	private TblPicture tblPicture;
	private String username;
	private String password;
	private String nickname;
	private String gender;
	private String region;
	private String description;
	private Date registDate;
	private String phoneNumber;
	private Set tblMessageQueuesForFromId = new HashSet(0);
	private Set tblMessageQueuesForToId = new HashSet(0);
	private Set tblFriendapplicationsForTargetId = new HashSet(0);
	private Set tblFriendsForFriendId = new HashSet(0);
	private Set tblFriendapplicationsForApplicantId = new HashSet(0);
	private Set tblReplies = new HashSet(0);
	private Set tblNotifications = new HashSet(0);
	private Set tblTimelines = new HashSet(0);
	private Set tblFriendsForFuserId = new HashSet(0);
	private Set tblCommentsForTimelineId = new HashSet(0);
	private Set tblCommentsForFromId = new HashSet(0);

	// Constructors

	/** default constructor */
	public TblUser() {
	}

	/** minimal constructor */
	public TblUser(String username, String password, String nickname,
			String phoneNumber) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
	}

	/** full constructor */
	public TblUser(TblPicture tblPicture, String username, String password,
			String nickname, String gender, String region, String description,
			Date registDate, String phoneNumber, Set tblMessageQueuesForFromId,
			Set tblMessageQueuesForToId, Set tblFriendapplicationsForTargetId,
			Set tblFriendsForFriendId, Set tblFriendapplicationsForApplicantId,
			Set tblReplies, Set tblNotifications, Set tblTimelines,
			Set tblFriendsForFuserId, Set tblCommentsForTimelineId,
			Set tblCommentsForFromId) {
		this.tblPicture = tblPicture;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.gender = gender;
		this.region = region;
		this.description = description;
		this.registDate = registDate;
		this.phoneNumber = phoneNumber;
		this.tblMessageQueuesForFromId = tblMessageQueuesForFromId;
		this.tblMessageQueuesForToId = tblMessageQueuesForToId;
		this.tblFriendapplicationsForTargetId = tblFriendapplicationsForTargetId;
		this.tblFriendsForFriendId = tblFriendsForFriendId;
		this.tblFriendapplicationsForApplicantId = tblFriendapplicationsForApplicantId;
		this.tblReplies = tblReplies;
		this.tblNotifications = tblNotifications;
		this.tblTimelines = tblTimelines;
		this.tblFriendsForFuserId = tblFriendsForFuserId;
		this.tblCommentsForTimelineId = tblCommentsForTimelineId;
		this.tblCommentsForFromId = tblCommentsForFromId;
	}

	// Property accessors

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public TblPicture getTblPicture() {
		return this.tblPicture;
	}

	public void setTblPicture(TblPicture tblPicture) {
		this.tblPicture = tblPicture;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRegistDate() {
		return this.registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set getTblMessageQueuesForFromId() {
		return this.tblMessageQueuesForFromId;
	}

	public void setTblMessageQueuesForFromId(Set tblMessageQueuesForFromId) {
		this.tblMessageQueuesForFromId = tblMessageQueuesForFromId;
	}

	public Set getTblMessageQueuesForToId() {
		return this.tblMessageQueuesForToId;
	}

	public void setTblMessageQueuesForToId(Set tblMessageQueuesForToId) {
		this.tblMessageQueuesForToId = tblMessageQueuesForToId;
	}

	public Set getTblFriendapplicationsForTargetId() {
		return this.tblFriendapplicationsForTargetId;
	}

	public void setTblFriendapplicationsForTargetId(
			Set tblFriendapplicationsForTargetId) {
		this.tblFriendapplicationsForTargetId = tblFriendapplicationsForTargetId;
	}

	public Set getTblFriendsForFriendId() {
		return this.tblFriendsForFriendId;
	}

	public void setTblFriendsForFriendId(Set tblFriendsForFriendId) {
		this.tblFriendsForFriendId = tblFriendsForFriendId;
	}

	public Set getTblFriendapplicationsForApplicantId() {
		return this.tblFriendapplicationsForApplicantId;
	}

	public void setTblFriendapplicationsForApplicantId(
			Set tblFriendapplicationsForApplicantId) {
		this.tblFriendapplicationsForApplicantId = tblFriendapplicationsForApplicantId;
	}

	public Set getTblReplies() {
		return this.tblReplies;
	}

	public void setTblReplies(Set tblReplies) {
		this.tblReplies = tblReplies;
	}

	public Set getTblNotifications() {
		return this.tblNotifications;
	}

	public void setTblNotifications(Set tblNotifications) {
		this.tblNotifications = tblNotifications;
	}

	public Set getTblTimelines() {
		return this.tblTimelines;
	}

	public void setTblTimelines(Set tblTimelines) {
		this.tblTimelines = tblTimelines;
	}

	public Set getTblFriendsForFuserId() {
		return this.tblFriendsForFuserId;
	}

	public void setTblFriendsForFuserId(Set tblFriendsForFuserId) {
		this.tblFriendsForFuserId = tblFriendsForFuserId;
	}

	public Set getTblCommentsForTimelineId() {
		return this.tblCommentsForTimelineId;
	}

	public void setTblCommentsForTimelineId(Set tblCommentsForTimelineId) {
		this.tblCommentsForTimelineId = tblCommentsForTimelineId;
	}

	public Set getTblCommentsForFromId() {
		return this.tblCommentsForFromId;
	}

	public void setTblCommentsForFromId(Set tblCommentsForFromId) {
		this.tblCommentsForFromId = tblCommentsForFromId;
	}

}