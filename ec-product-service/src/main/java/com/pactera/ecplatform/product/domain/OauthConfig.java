package com.pactera.ecplatform.product.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "oauth_config")
public class OauthConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column
	private String token;

	@Column
	private String check_token;

	public OauthConfig() {
	}

	public OauthConfig(String token, String check_token) {
		super();
		this.token = token;
		this.check_token = check_token;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCheck_token() {
		return check_token;
	}

	public void setCheck_token(String check_token) {
		this.check_token = check_token;
	}

}
