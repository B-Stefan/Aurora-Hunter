package de.hs_bremen.aurora_hunter.commons.prediction.models;

import de.hs_bremen.aurora_hunter.commons.prediction.models.Probability;
import java.util.*;
import java.util.Date;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class ProbabilityConclusion  {
  
  @SerializedName("sunrise")
  private Date sunrise = null;
  @SerializedName("sunset")
  private Date sunset = null;
  @SerializedName("max")
  private Probability max = null;
  @SerializedName("hours")
  private List<Probability> hours = null;
  @SerializedName("id")
  private Double id = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public Date getSunrise() {
    return sunrise;
  }
  public void setSunrise(Date sunrise) {
    this.sunrise = sunrise;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Date getSunset() {
    return sunset;
  }
  public void setSunset(Date sunset) {
    this.sunset = sunset;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Probability getMax() {
    return max;
  }
  public void setMax(Probability max) {
    this.max = max;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public List<Probability> getHours() {
    return hours;
  }
  public void setHours(List<Probability> hours) {
    this.hours = hours;
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
    ProbabilityConclusion probabilityConclusion = (ProbabilityConclusion) o;
    return (sunrise == null ? probabilityConclusion.sunrise == null : sunrise.equals(probabilityConclusion.sunrise)) &&
        (sunset == null ? probabilityConclusion.sunset == null : sunset.equals(probabilityConclusion.sunset)) &&
        (max == null ? probabilityConclusion.max == null : max.equals(probabilityConclusion.max)) &&
        (hours == null ? probabilityConclusion.hours == null : hours.equals(probabilityConclusion.hours)) &&
        (id == null ? probabilityConclusion.id == null : id.equals(probabilityConclusion.id));
  }

  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + (sunrise == null ? 0: sunrise.hashCode());
    result = 31 * result + (sunset == null ? 0: sunset.hashCode());
    result = 31 * result + (max == null ? 0: max.hashCode());
    result = 31 * result + (hours == null ? 0: hours.hashCode());
    result = 31 * result + (id == null ? 0: id.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProbabilityConclusion {\n");
    
    sb.append("  sunrise: ").append(sunrise).append("\n");
    sb.append("  sunset: ").append(sunset).append("\n");
    sb.append("  max: ").append(max).append("\n");
    sb.append("  hours: ").append(hours).append("\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
