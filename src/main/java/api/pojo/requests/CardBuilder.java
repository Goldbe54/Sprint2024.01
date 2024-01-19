package api.pojo.requests;

import lombok.Builder;
import lombok.Data;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Data
@Builder
public class CardBuilder {

    @Builder.Default
    private String name = "Card " + randomAlphabetic(3);
    @Builder.Default
    private String desc = "Default description " + randomAlphabetic(3);
    @Builder.Default
    private String pos = "top";
    @Builder.Default
    private String due = "";
    @Builder.Default
    private String start ="";
}