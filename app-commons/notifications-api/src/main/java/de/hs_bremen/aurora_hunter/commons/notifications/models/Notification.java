package de.hs_bremen.aurora_hunter.commons.notifications.models;

import de.hs_bremen.aurora_hunter.commons.notifications.models.GeoPoint;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class Notification  {
  
  @SerializedName("token")
  private String token = null;
  @SerializedName("threshold")
  private Double threshold = null;
  @SerializedName("location")
  private GeoPoint location = null;
  @SerializedName("id")
  private Double id = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Double getThreshold() {
    return threshold;
  }
  public void setThreshold(Double threshold) {
    this.threshold = threshold;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public GeoPoint getLocation() {
    return location;
  }
  public void setLocation(GeoPoint location) {
    this.location = location;
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
    Notification notification = (Notification) o;
    return (token == null ? notification.token == null : token.equals(notification.token)) &&
        (threshold == null ? notification.threshold == null : threshold.equals(notification.threshold)) &&
        (location == null ? notification.location == null : location.equals(notification.location)) &&
        (id == null ? notification.id == null : id.equals(notification.id));
  }

  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + (token == null ? 0: token.hashCode());
    result = 31 * result + (threshold == null ? 0: threshold.hashCode());
    result = 31 * result + (location == null ? 0: location.hashCode());
    result = 31 * result + (id == null ? 0: id.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Notification {\n");
    
    sb.append("  token: ").append(token).append("\n");
    sb.append("  threshold: ").append(threshold).append("\n");
    sb.append("  location: ").append(location).append("\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
