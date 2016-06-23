package de.hs_bremen.aurora_hunter.commons.prediction.models;

import java.util.Date;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class KpIndex  {
  
  @SerializedName("date")
  private Date date = null;
  @SerializedName("kpValue")
  private Double kpValue = null;
  @SerializedName("original")
  private String original = null;
  @SerializedName("utc")
  private Double utc = null;

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
  @ApiModelProperty(value = "")
  public Double getKpValue() {
    return kpValue;
  }
  public void setKpValue(Double kpValue) {
    this.kpValue = kpValue;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getOriginal() {
    return original;
  }
  public void setOriginal(String original) {
    this.original = original;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public Double getUtc() {
    return utc;
  }
  public void setUtc(Double utc) {
    this.utc = utc;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KpIndex kpIndex = (KpIndex) o;
    return (date == null ? kpIndex.date == null : date.equals(kpIndex.date)) &&
        (kpValue == null ? kpIndex.kpValue == null : kpValue.equals(kpIndex.kpValue)) &&
        (original == null ? kpIndex.original == null : original.equals(kpIndex.original)) &&
        (utc == null ? kpIndex.utc == null : utc.equals(kpIndex.utc));
  }

  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + (date == null ? 0: date.hashCode());
    result = 31 * result + (kpValue == null ? 0: kpValue.hashCode());
    result = 31 * result + (original == null ? 0: original.hashCode());
    result = 31 * result + (utc == null ? 0: utc.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class KpIndex {\n");
    
    sb.append("  date: ").append(date).append("\n");
    sb.append("  kpValue: ").append(kpValue).append("\n");
    sb.append("  original: ").append(original).append("\n");
    sb.append("  utc: ").append(utc).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
