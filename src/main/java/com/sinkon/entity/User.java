package com.sinkon.entity;

public class User {
private int userId;
private String openId;
private String memberId;
private String userName;
private String sex;
private String language;
private String city;
private String province;
private String country;
private String createTime;
private String headImgUrl;
private String attentionState;
private String isLeadership;
private String isLeadershipId;
private String recommendedId;
private String channelId;
public String getChannelId() {
	return channelId;
}
public void setChannelId(String channelId) {
	this.channelId = channelId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getOpenId() {
	return openId;
}
public void setOpenId(String openId) {
	this.openId = openId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	userName = userName;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getHeadImgUrl() {
	return headImgUrl;
}
public void setHeadImgUrl(String headImgUrl) {
	this.headImgUrl = headImgUrl;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getAttentionState() {
	return attentionState;
}
public void setAttentionState(String attentionState) {
	this.attentionState = attentionState;
}
public String getIsLeadership() {
	return isLeadership;
}
public void setIsLeadership(String isLeadership) {
	this.isLeadership = isLeadership;
}

public String getIsLeadershipId() {
	return isLeadershipId;
}
public void setIsLeadershipId(String isLeadershipId) {
	this.isLeadershipId = isLeadershipId;
}
public String getMemberId() {
	return memberId;
}
public void setMemberId(String memberId) {
	this.memberId = memberId;
}
public String getRecommendedId() {
	return recommendedId;
}
public void setRecommendedId(String recommendedId) {
	this.recommendedId = recommendedId;
}

public User(String openId, String memberId) {
	super();
	this.openId = openId;
	this.memberId = memberId;
}

public User(){}
}
