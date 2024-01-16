package api.pojo.requests;

import lombok.Builder;
import lombok.Data;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Data
@Builder
public class ListBuilder {

    @Builder.Default
    private String name = "List " + randomAlphabetic(3);
    @Builder.Default
    private String pos = "top";
}
