package de.hs_bremen.aurora_hunter.commons.notifications.models;

import java.math.BigDecimal;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class GeoPoint  {
  
  @SerializedName("lat")
  private BigDecimal lat = null;
  @SerializedName("lng")
  private BigDecimal lng = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public BigDecimal getLat() {
    return lat;
  }
  public void setLat(BigDecimal lat) {
    this.lat = lat;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public BigDecimal getLng() {
    return lng;
  }
  public void setLng(BigDecimal lng) {
    this.lng = lng;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeoPoint geoPoint = (GeoPoint) o;
    return (lat == null ? geoPoint.lat == null : lat.equals(geoPoint.lat)) &&
        (lng == null ? geoPoint.lng == null : lng.equals(geoPoint.lng));
  }

  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + (lat == null ? 0: lat.hashCode());
    result = 31 * result + (lng == null ? 0: lng.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeoPoint {\n");
    
    sb.append("  lat: ").append(lat).append("\n");
    sb.append("  lng: ").append(lng).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
