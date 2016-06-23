package de.hs_bremen.aurora_hunter.commons.prediction.models;

import de.hs_bremen.aurora_hunter.commons.prediction.models.GeoPoint;
import de.hs_bremen.aurora_hunter.commons.prediction.models.KpIndex;
import de.hs_bremen.aurora_hunter.commons.prediction.models.MoonInformation;
import de.hs_bremen.aurora_hunter.commons.prediction.models.SunInfromation;
import java.util.Date;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class Probability  {
  
  @SerializedName("date")
  private Date date = null;
  @SerializedName("kpInformation")
  private KpIndex kpInformation = null;
  @SerializedName("location")
  private GeoPoint location = null;
  @SerializedName("moonInformation")
  private MoonInformation moonInformation = null;
  @SerializedName("probability")
  private Double probability = null;
  @SerializedName("sunInformation")
  private SunInfromation sunInformation = null;

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public KpIndex getKpInformation() {
    return kpInformation;
  }
  public void setKpInformation(KpIndex kpInformation) {
    this.kpInformation = kpInformation;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public GeoPoint getLocation() {
    return location;
  }
  public void setLocation(GeoPoint location) {
    this.location = location;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public MoonInformation getMoonInformation() {
    return moonInformation;
  }
  public void setMoonInformation(MoonInformation moonInformation) {
    this.moonInformation = moonInformation;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public Double getProbability() {
    return probability;
  }
  public void setProbability(Double probability) {
    this.probability = probability;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public SunInfromation getSunInformation() {
    return sunInformation;
  }
  public void setSunInformation(SunInfromation sunInformation) {
    this.sunInformation = sunInformation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Probability probability = (Probability) o;
    return (date == null ? probability.date == null : date.equals(probability.date)) &&
        (kpInformation == null ? probability.kpInformation == null : kpInformation.equals(probability.kpInformation)) &&
        (location == null ? probability.location == null : location.equals(probability.location)) &&
        (moonInformation == null ? probability.moonInformation == null : moonInformation.equals(probability.moonInformation)) &&
        (probability == null ? probability.probability == null : probability.equals(probability.probability)) &&
        (sunInformation == null ? probability.sunInformation == null : sunInformation.equals(probability.sunInformation));
  }

  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + (date == null ? 0: date.hashCode());
    result = 31 * result + (kpInformation == null ? 0: kpInformation.hashCode());
    result = 31 * result + (location == null ? 0: location.hashCode());
    result = 31 * result + (moonInformation == null ? 0: moonInformation.hashCode());
    result = 31 * result + (probability == null ? 0: probability.hashCode());
    result = 31 * result + (sunInformation == null ? 0: sunInformation.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Probability {\n");
    
    sb.append("  date: ").append(date).append("\n");
    sb.append("  kpInformation: ").append(kpInformation).append("\n");
    sb.append("  location: ").append(location).append("\n");
    sb.append("  moonInformation: ").append(moonInformation).append("\n");
    sb.append("  probability: ").append(probability).append("\n");
    sb.append("  sunInformation: ").append(sunInformation).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
