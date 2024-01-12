package api.pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Data
@Builder
public class CardBuilder {

    @Builder.Default
    @JsonProperty("name")
    private String name = "Card " + randomAlphabetic(3);
    @Builder.Default
    @JsonProperty("desc")
    private String desc = "Default description " + randomAlphabetic(3);
    @Builder.Default
    @JsonProperty("pos")
    private String pos = "top";
    @Builder.Default
    @JsonProperty("due")
    private String due = "";
}