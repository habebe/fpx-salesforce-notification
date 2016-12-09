package com.fpx.abebe.salesforce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "UserTable")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends SalesForceObject
{
	@JsonProperty(value="City")
	private String city;
	@JsonProperty(value="FirstName")
	private String firstName;
	@JsonProperty(value="Title")
	private String title;
	@JsonProperty(value="LastName")
	private String lastName;
	@JsonProperty(value="LastModifiedById")
	private String lastModifiedById;
	@JsonProperty(value="Latitude")
	private String latitude;
	@JsonProperty(value="State")
	private String state;
	@JsonProperty(value="Department")
	private String department;
	@JsonProperty(value="ProfileId")
	private String profileId;
	@JsonProperty(value="Email")
	private String email;
	@JsonProperty(value="Username")
	private String username;
	@JsonProperty(value="Division")
	private String division;
	@JsonProperty(value="UserType")
	private String userType;
	@JsonProperty(value="LastModifiedDate")
	private Date lastModifiedDate;
	@JsonProperty(value="Phone")
	private String phone;
	@JsonProperty(value="ContactId")
	private String contactId;
	@JsonProperty(value="CreatedById")
	private String createdById;
	@JsonProperty(value="UserRoleId")
	private String userRoleId;
	@JsonProperty(value="LastViewedDate")
	private Date lastViewedDate;
	@JsonProperty(value="AccountId")
	private String accountId;
	@JsonProperty(value="Name")
	private String name;
	@JsonProperty(value="Country")
	private String country;
	@JsonProperty(value="Longitude")
	private String longitude;
	@JsonProperty(value="Alias")
	private String alias;
	@JsonProperty(value="PostalCode")
	private String postalCode;
	@JsonProperty(value="MobilePhone")
	private String mobilePhone;
	@JsonProperty(value="CompanyName")
	private String companyName;
	@JsonProperty(value="CommunityNickname")
	private String communityNickname;
	@JsonProperty(value="ManagerId")
	private String managerId;
	@JsonProperty(value="Street")
	private String street;
	@JsonProperty(value="SystemModstamp")
	private String systemModstamp;
	@JsonProperty(value="LastLoginDate")
	private Date lastLoginDate;
	@JsonProperty(value="CreatedDate")
	private Date createdDate;
	@Id
	@JsonProperty(value="Id")
	private String id;
	@JsonProperty(value="IsActive")
	private boolean isActive;
	
	@JsonIgnore
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	@JsonIgnore
	public void setLastViewedDate(Date lastViewedDate) {
		this.lastViewedDate = lastViewedDate;
	}
	@JsonIgnore
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	@JsonIgnore
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastModifiedById() {
		return lastModifiedById;
	}
	public void setLastModifiedById(String lastModifiedById) {
		this.lastModifiedById = lastModifiedById;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = this.parseDateFromString(lastModifiedDate);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getCreatedById() {
		return createdById;
	}
	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public Date getLastViewedDate() {
		return lastViewedDate;
	}
	public void setLastViewedDate(String lastViewedDate) {
		this.lastViewedDate = this.parseDateFromString(lastViewedDate);
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCommunityNickname() {
		return communityNickname;
	}
	public void setCommunityNickname(String communityNickname) {
		this.communityNickname = communityNickname;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getSystemModstamp() {
		return systemModstamp;
	}
	public void setSystemModstamp(String systemModstamp) {
		this.systemModstamp = systemModstamp;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = this.parseDateFromString(lastLoginDate);
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = this.parseDateFromString(createdDate);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "User [city=" + city + ", firstName=" + firstName + ", title=" + title + ", lastName=" + lastName
				+ ", lastModifiedById=" + lastModifiedById + ", latitude=" + latitude + ", state=" + state
				+ ", department=" + department + ", profileId=" + profileId + ", email=" + email + ", username="
				+ username + ", division=" + division + ", userType=" + userType + ", lastModifiedDate="
				+ lastModifiedDate + ", phone=" + phone + ", contactId=" + contactId + ", createdById=" + createdById
				+ ", userRoleId=" + userRoleId + ", lastViewedDate=" + lastViewedDate + ", accountId=" + accountId
				+ ", name=" + name + ", country=" + country + ", longitude=" + longitude + ", alias=" + alias
				+ ", postalCode=" + postalCode + ", mobilePhone=" + mobilePhone + ", companyName=" + companyName
				+ ", communityNickname=" + communityNickname + ", managerId=" + managerId + ", street=" + street
				+ ", systemModstamp=" + systemModstamp + ", lastLoginDate=" + lastLoginDate + ", createdDate="
				+ createdDate + ", id=" + id + ", isActive=" + isActive + "]";
	}
	
}
