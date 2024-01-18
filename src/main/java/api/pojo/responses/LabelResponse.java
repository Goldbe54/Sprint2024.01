package api.pojo.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class LabelResponse {
    private String name;
    private String color;
    private String idBoard;
    private String idCard;
    private String id;
}
