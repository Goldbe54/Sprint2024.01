package api.pojo.requests;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Data
@Builder
public class AttachmentOnCardBuilder {

    @Builder.Default
    @JsonProperty("name")
    private String name = "The life cycle of a bug " + randomAlphabetic(3);
    @Builder.Default
    @JsonProperty("mimeType")
    private String mimeType = "image/jpg";
    @Builder.Default
    @JsonProperty("url")
    private String url = "https://drive.google.com/uc?id=1pImx9lc2gQ1BE6buTe7RsQ-9TeZ2LPve";
    @Builder.Default
    @JsonProperty("setCover")
    private Boolean setCover = false;
}