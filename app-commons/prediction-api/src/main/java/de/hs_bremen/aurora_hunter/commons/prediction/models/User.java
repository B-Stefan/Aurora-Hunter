package de.hs_bremen.aurora_hunter.commons.prediction.models;

import java.util.Date;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class User  {
  
  @SerializedName("realm")
  private String realm = null;
  @SerializedName("username")
  private String username = null;
  @SerializedName("credentials")
  private Object credentials = null;
  @SerializedName("challenges")
  private Object challenges = null;
  @SerializedName("email")
  private String email = null;
  @SerializedName("emailVerified")
  private Boolean emailVerified = null;
  @SerializedName("status")
  private String status = null;
  @SerializedName("created")
  private Date created = null;
  @SerializedName("lastUpdated")
  private Date lastUpdated = null;
  @SerializedName("id")
  private Double id = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public String getRealm() {
    return realm;
  }
  public void setRealm(String realm) {
    this.realm = realm;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Object getCredentials() {
    return credentials;
  }
  public void setCredentials(Object credentials) {
    this.credentials = credentials;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Object getChallenges() {
    return challenges;
  }
  public void setChallenges(Object challenges) {
    this.challenges = challenges;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getEmailVerified() {
    return emailVerified;
  }
  public void setEmailVerified(Boolean emailVerified) {
    this.emailVerified = emailVerified;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Date getCreated() {
    return created;
  }
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Date getLastUpdated() {
    return lastUpdated;
  }
  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Double getId() {
    return id;
  }
  public void setId(Double id) {
    this.id = id;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return (realm == null ? user.realm == null : realm.equals(user.realm)) &&
        (username == null ? user.username == null : username.equals(user.username)) &&
        (credentials == null ? user.credentials == null : credentials.equals(user.credentials)) &&
        (challenges == null ? user.challenges == null : challenges.equals(user.challenges)) &&
        (email == null ? user.email == null : email.equals(user.email)) &&
        (emailVerified == null ? user.emailVerified == null : emailVerified.equals(user.emailVerified)) &&
        (status == null ? user.status == null : status.equals(user.status)) &&
        (created == null ? user.created == null : created.equals(user.created)) &&
        (lastUpdated == null ? user.lastUpdated == null : lastUpdated.equals(user.lastUpdated)) &&
        (id == null ? user.id == null : id.equals(user.id));
  }

  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + (realm == null ? 0: realm.hashCode());
    result = 31 * result + (username == null ? 0: username.hashCode());
    result = 31 * result + (credentials == null ? 0: credentials.hashCode());
    result = 31 * result + (challenges == null ? 0: challenges.hashCode());
    result = 31 * result + (email == null ? 0: email.hashCode());
    result = 31 * result + (emailVerified == null ? 0: emailVerified.hashCode());
    result = 31 * result + (status == null ? 0: status.hashCode());
    result = 31 * result + (created == null ? 0: created.hashCode());
    result = 31 * result + (lastUpdated == null ? 0: lastUpdated.hashCode());
    result = 31 * result + (id == null ? 0: id.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("  realm: ").append(realm).append("\n");
    sb.append("  username: ").append(username).append("\n");
    sb.append("  credentials: ").append(credentials).append("\n");
    sb.append("  challenges: ").append(challenges).append("\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  emailVerified: ").append(emailVerified).append("\n");
    sb.append("  status: ").append(status).append("\n");
    sb.append("  created: ").append(created).append("\n");
    sb.append("  lastUpdated: ").append(lastUpdated).append("\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
