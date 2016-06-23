package de.hs_bremen.aurora_hunter.commons.prediction.models;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class InlineResponse200  {
  
  @SerializedName("count")
  private Double count = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public Double getCount() {
    return count;
  }
  public void setCount(Double count) {
    this.count = count;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200 inlineResponse200 = (InlineResponse200) o;
    return (count == null ? inlineResponse200.count == null : count.equals(inlineResponse200.count));
  }

  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + (count == null ? 0: count.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200 {\n");
    
    sb.append("  count: ").append(count).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
