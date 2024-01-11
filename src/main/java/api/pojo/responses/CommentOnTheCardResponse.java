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
public class CommentOnTheCardResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("idMemberCreator")
    private String idMemberCreator;

    @JsonProperty("data")
    private Object data;

    @JsonProperty("type")
    private String type;

    @JsonProperty("date")
    private String date;

    @JsonProperty("limits")
    private Object limits;

    @JsonProperty("display")
    private Object display;

    @JsonProperty("memberCreator")
    private Object memberCreator;
}