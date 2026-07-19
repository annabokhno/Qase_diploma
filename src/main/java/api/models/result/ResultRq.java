package api.models.result;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultRq {

    @Expose
    private Integer case_id;
    @Expose
    private String status;
}
