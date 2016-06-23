package de.hs_bremen.aurora_hunter.commons.prediction.models;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class SunInfromation  {
  
  @SerializedName("altitude")
  private Double altitude = null;
  @SerializedName("azimuth")
  private Double azimuth = null;
  @SerializedName("id")
  private Double id = null;

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public Double getAltitude() {
    return altitude;
  }
  public void setAltitude(Double altitude) {
    this.altitude = altitude;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public Double getAzimuth() {
    return azimuth;
  }
  public void setAzimuth(Double azimuth) {
    this.azimuth = azimuth;
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
    SunInfromation sunInfromation = (SunInfromation) o;
    return (altitude == null ? sunInfromation.altitude == null : altitude.equals(sunInfromation.altitude)) &&
        (azimuth == null ? sunInfromation.azimuth == null : azimuth.equals(sunInfromation.azimuth)) &&
        (id == null ? sunInfromation.id == null : id.equals(sunInfromation.id));
  }

  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + (altitude == null ? 0: altitude.hashCode());
    result = 31 * result + (azimuth == null ? 0: azimuth.hashCode());
    result = 31 * result + (id == null ? 0: id.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class SunInfromation {\n");
    
    sb.append("  altitude: ").append(altitude).append("\n");
    sb.append("  azimuth: ").append(azimuth).append("\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
