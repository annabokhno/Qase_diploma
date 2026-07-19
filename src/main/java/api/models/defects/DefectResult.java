package api.models.defects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DefectResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("actual_result")
    @Expose
    private String actual_result;
}