package api.models.cases;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaseRq {

    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private String preconditions;
    @Expose
    private String postconditions;
    @Expose
    private String steps_type;
    @Expose
    private Integer severity;
    @Expose
    private Integer priority;
    @Expose
    private Integer behavior;
    @Expose
    private Integer type;
    @Expose
    private Integer layer;
    @Expose
    private Integer is_flaky;
}