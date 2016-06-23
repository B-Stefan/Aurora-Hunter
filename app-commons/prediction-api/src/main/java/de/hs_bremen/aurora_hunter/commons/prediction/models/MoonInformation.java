package de.hs_bremen.aurora_hunter.commons.prediction.models;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class MoonInformation  {
  
  @SerializedName("altitude")
  private Double altitude = null;
  @SerializedName("angle")
  private Double angle = null;
  @SerializedName("azimuth")
  private Double azimuth = null;
  @SerializedName("fraction")
  private Double fraction = null;
  @SerializedName("phase")
  private Double phase = null;
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
  public Double getAngle() {
    return angle;
  }
  public void setAngle(Double angle) {
    this.angle = angle;
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
  @ApiModelProperty(required = true, value = "")
  public Double getFraction() {
    return fraction;
  }
  public void setFraction(Double fraction) {
    this.fraction = fraction;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public Double getPhase() {
    return phase;
  }
  public void setPhase(Double phase) {
    this.phase = phase;
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
    MoonInformation moonInformation = (MoonInformation) o;
    return (altitude == null ? moonInformation.altitude == null : altitude.equals(moonInformation.altitude)) &&
        (angle == null ? moonInformation.angle == null : angle.equals(moonInformation.angle)) &&
        (azimuth == null ? moonInformation.azimuth == null : azimuth.equals(moonInformation.azimuth)) &&
        (fraction == null ? moonInformation.fraction == null : fraction.equals(moonInformation.fraction)) &&
        (phase == null ? moonInformation.phase == null : phase.equals(moonInformation.phase)) &&
        (id == null ? moonInformation.id == null : id.equals(moonInformation.id));
  }

  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + (altitude == null ? 0: altitude.hashCode());
    result = 31 * result + (angle == null ? 0: angle.hashCode());
    result = 31 * result + (azimuth == null ? 0: azimuth.hashCode());
    result = 31 * result + (fraction == null ? 0: fraction.hashCode());
    result = 31 * result + (phase == null ? 0: phase.hashCode());
    result = 31 * result + (id == null ? 0: id.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class MoonInformation {\n");
    
    sb.append("  altitude: ").append(altitude).append("\n");
    sb.append("  angle: ").append(angle).append("\n");
    sb.append("  azimuth: ").append(azimuth).append("\n");
    sb.append("  fraction: ").append(fraction).append("\n");
    sb.append("  phase: ").append(phase).append("\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
