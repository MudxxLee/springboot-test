package com.laiwen.dao;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "account_set")
public class AccountSet implements Serializable {
	
	private static final long serialVersionUID = 2479814181525096045L;

	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @Column(name = "id")
    private String id;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	
    @Column(name = "status",nullable = false)
    private Integer status;

    @Column(name = "type",nullable = false)
    private Integer type;
    
    @Column(name = "customer_name")
    private String customerName;
    
    @Column(name = "tel")
    private String tel;
    
    @Column(name = "create_time")
    private Date createTime;

    @Column(name ="is_internal_user",columnDefinition="tinyint default 0")
	private Integer isInternalUser;

	@Column(name ="is_del",columnDefinition = "tinyint default 0")
	private Integer isDel;

	@Column(name = "del_date")
	private Date delDate;

	@Column(name = "del_by")
	private String delBy;

	@Column(name = "is_recycling_source" ,columnDefinition = "tinyint default 0")
	private Integer isRecyclingSource;
	
	@Column(name = "agile_one_type",columnDefinition = "tinyint default 0")
	private Integer agileOneType;


	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsInternalUser() {
		return isInternalUser;
	}

	public void setIsInternalUser(Integer isInternalUser) {
		this.isInternalUser = isInternalUser;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	public String getDelBy() {
		return delBy;
	}

	public void setDelBy(String delBy) {
		this.delBy = delBy;
	}

	public Integer getIsRecyclingSource() {
		return isRecyclingSource;
	}

	public void setIsRecyclingSource(Integer isRecyclingSource) {
		this.isRecyclingSource = isRecyclingSource;
	}

	public Integer getAgileOneType() {
		return agileOneType;
	}

	public void setAgileOneType(Integer agileOneType) {
		this.agileOneType = agileOneType;
	}


}
