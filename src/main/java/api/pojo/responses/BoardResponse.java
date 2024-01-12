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
public class BoardResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("closed")
    private Boolean closed;
    @JsonProperty("idOrganization")
    private String idOrganization;
    @JsonProperty("pinned")
    private Boolean pinned;
    @JsonProperty("shortUrl")
    private String shortUrl;
    @JsonProperty("prefs")
    private Prefs prefs;
}
