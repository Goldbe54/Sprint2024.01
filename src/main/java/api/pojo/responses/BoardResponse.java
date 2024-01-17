package api.pojo.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

    private String id;
    private String name;
    private String desc;
    private Boolean closed;
    private String idOrganization;
    private Boolean pinned;
    private String shortUrl;
    private Prefs prefs;
}