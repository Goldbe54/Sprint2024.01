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
public class CardResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("subscribed")
    private String subscribed;

    @JsonProperty("checkItems")
    private String checkItems;

    @JsonProperty("checkItemsChecked")
    private String checkItemsChecked;

    @JsonProperty("comments")
    private String comments;

    @JsonProperty("attachments")
    private Object attachments;

    @JsonProperty("description")
    private String description;

    @JsonProperty("due")
    private String due;

    @JsonProperty("start")
    private String start;

    @JsonProperty("dueComplete")
    private String dueComplete;

    @JsonProperty("closed")
    private String closed;

    @JsonProperty("dateLastActivity")
    private String dateLastActivity;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("email")
    private String email;

    @JsonProperty("idBoard")
    private String idBoard;
}
