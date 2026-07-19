package api.models.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProjectsResult {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("filtered")
    @Expose
    private Integer filtered;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("entities")
    @Expose
    private List<ProjectResult> entities;
}
