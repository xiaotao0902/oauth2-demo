package com.pactera.ecplatform.auth.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "users")
public class Users implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column
  private String name;

  @NotEmpty
  @Column(unique = true, nullable = false)
  private String username;

  @NotEmpty
  @Column
  private String password;

  @Column
  private boolean enabled;

  public Users() {}

  public Users(String name, String username, String password, boolean enabled) {
    super();
    this.name = name;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

}
