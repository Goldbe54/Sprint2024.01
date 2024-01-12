package api.pojo.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prefs {

    @JsonProperty("permissionLevel")
    private String permissionLevel;
    @JsonProperty("hideVotes")
    private Boolean hideVotes;
    @JsonProperty("voting")
    private String voting;
    @JsonProperty("comments")
    private String comments;
}
