package api.pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@Builder
public class CardBuilder {
    @Builder.Default
    @JsonProperty("name")
    private String name = "Card " + RandomStringUtils.randomAlphabetic(3);
    @Builder.Default
    @JsonProperty("desc")
    private String desc = "Default description " + RandomStringUtils.randomAlphabetic(3);
    @Builder.Default
    @JsonProperty("pos")
    private String pos = "top";
    @Builder.Default
    @JsonProperty("due")
    private String due = "";
}