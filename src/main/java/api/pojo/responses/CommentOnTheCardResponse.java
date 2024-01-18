package api.pojo.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CommentOnTheCardResponse {

    private String id;
    private String idMemberCreator;
    private Object data;
    private String type;
    private String date;
    private Object limits;
    private Object display;
    private Object memberCreator;
}