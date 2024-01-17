package api.pojo.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentOnTheCardBuilder {

    @Builder.Default
    private String text = "New Comment";
}