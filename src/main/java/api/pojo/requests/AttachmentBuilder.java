package api.pojo.requests;

import lombok.Builder;
import lombok.Data;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Data
@Builder
public class AttachmentBuilder {

    @Builder.Default
    private String name = "The life cycle of a bug " + randomAlphabetic(3);
    @Builder.Default
    private String mimeType = "image/jpg";
    @Builder.Default
    private String url = "https://drive.google.com/uc?id=1pImx9lc2gQ1BE6buTe7RsQ-9TeZ2LPve";
    @Builder.Default
    private Boolean setCover = false;
}