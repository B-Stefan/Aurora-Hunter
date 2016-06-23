package de.hs_bremen.aurora_hunter.commons.prediction.models;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class InlineResponse2001  {
  
  @SerializedName("exists")
  private Boolean exists = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getExists() {
    return exists;
  }
  public void setExists(Boolean exists) {
    this.exists = exists;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2001 inlineResponse2001 = (InlineResponse2001) o;
    return (exists == null ? inlineResponse2001.exists == null : exists.equals(inlineResponse2001.exists));
  }

  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + (exists == null ? 0: exists.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2001 {\n");
    
    sb.append("  exists: ").append(exists).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
