package com.laiwen.secdao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "platform_action_log")
public class PlatformActionLog implements Serializable{
	
	private static final long serialVersionUID = 3907759467636848181L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Long id;
	
	@Column(name = "request_id",nullable = true)
	private String requestId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "model",nullable = false)
	private String model;
	
	@Column(name = "action",nullable = false)
	private String action;
	
	@Column(name = "operate_date",nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date operateDate;
	
	@Column(name = "ip",nullable = false)
	private String ip;
	
	@Column(name = "operate_result",nullable = false)
	private Boolean operateResult;
	
	@Column(name = "uri",nullable = false)
	private String uri;
	
	@Column(name = "body")
	private String body;
	
	@Column(name = "query_parameter")
	private String queryParam;
	
	@Column(name = "account_set_id")
	private String accountSetId;
	
	@Column(name = "api_type")
	private Integer apiType;
	
	@Column(name = "app_id")
	private String appId;
	
	@Column(name = "header")
	private String header;
	
	@Column(name = "action_method")
	private String actionMethod;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Boolean getOperateResult() {
		return operateResult;
	}

	public void setOperateResult(Boolean operateResult) {
		this.operateResult = operateResult;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getAccountSetId() {
		return accountSetId;
	}

	public void setAccountSetId(String accountSetId) {
		this.accountSetId = accountSetId;
	}

	public Integer getApiType() {
		return apiType;
	}

	public void setApiType(Integer apiType) {
		this.apiType = apiType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}
}