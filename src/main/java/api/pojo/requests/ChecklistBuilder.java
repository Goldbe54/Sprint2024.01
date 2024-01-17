package api.pojo.requests;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@Builder
public class ChecklistBuilder {

    @Builder.Default
    private String name = "Checklist " + RandomStringUtils.randomAlphabetic(3);
    @Builder.Default
    private String pos = "top";
}
