package de.hs_bremen.aurora_hunter.commons.prediction.models;

import de.hs_bremen.aurora_hunter.commons.prediction.models.KpIndex;
import java.util.Date;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class KpIndexDayPrediction  {
  
  @SerializedName("date")
  private Date date = null;
  @SerializedName("min")
  private KpIndex min = null;
  @SerializedName("max")
  private KpIndex max = null;
  @SerializedName("id")
  private Double id = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public KpIndex getMin() {
    return min;
  }
  public void setMin(KpIndex min) {
    this.min = min;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public KpIndex getMax() {
    return max;
  }
  public void setMax(KpIndex max) {
    this.max = max;
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
    KpIndexDayPrediction kpIndexDayPrediction = (KpIndexDayPrediction) o;
    return (date == null ? kpIndexDayPrediction.date == null : date.equals(kpIndexDayPrediction.date)) &&
        (min == null ? kpIndexDayPrediction.min == null : min.equals(kpIndexDayPrediction.min)) &&
        (max == null ? kpIndexDayPrediction.max == null : max.equals(kpIndexDayPrediction.max)) &&
        (id == null ? kpIndexDayPrediction.id == null : id.equals(kpIndexDayPrediction.id));
  }

  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + (date == null ? 0: date.hashCode());
    result = 31 * result + (min == null ? 0: min.hashCode());
    result = 31 * result + (max == null ? 0: max.hashCode());
    result = 31 * result + (id == null ? 0: id.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class KpIndexDayPrediction {\n");
    
    sb.append("  date: ").append(date).append("\n");
    sb.append("  min: ").append(min).append("\n");
    sb.append("  max: ").append(max).append("\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
