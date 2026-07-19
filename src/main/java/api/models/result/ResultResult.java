package api.models.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ResultResult {

    @SerializedName("id")
    @Expose
    private Integer id;
}
