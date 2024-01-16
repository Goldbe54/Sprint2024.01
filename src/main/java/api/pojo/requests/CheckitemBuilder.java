package api.pojo.requests;

import lombok.Builder;
import lombok.Data;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Data
@Builder
public class CheckitemBuilder {

    @Builder.Default
    private String name = "CheckItem " + randomAlphabetic(3);
    @Builder.Default
    private String pos = "top";
    @Builder.Default
    private Boolean checked = false;
}
