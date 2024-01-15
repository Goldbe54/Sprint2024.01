package api.pojo.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("closed")
    private Boolean closed;
    @JsonProperty("dateLastActivity")
    private String dateLastActivity;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("idBoard")
    private String idBoard;
    @JsonProperty("idList")
    private String idList;
    @JsonProperty("name")
    private String name;
}
