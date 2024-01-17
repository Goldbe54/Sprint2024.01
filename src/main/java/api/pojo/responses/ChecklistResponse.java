package api.pojo.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistResponse {

    private String id;
    private Boolean closed;
    private String dateLastActivity;
    private String desc;
    private String idBoard;
    private String idList;
    private String name;
}