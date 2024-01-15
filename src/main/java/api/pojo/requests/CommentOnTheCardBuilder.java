package api.pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentOnTheCardBuilder {

    @Builder.Default
    @JsonProperty("text")
    private String text = "New Comment";
}