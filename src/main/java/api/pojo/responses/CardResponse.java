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

    private String id;
    private String subscribed;
    private String checkItems;
    private String checkItemsChecked;
    private String comments;
    private Object attachments;
    private String description;
    private String due;
    private String start;
    private String dueComplete;
    private String closed;
    private String dateLastActivity;
    private String desc;
    private String email;
    private String idBoard;
}