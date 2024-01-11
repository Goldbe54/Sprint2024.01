package api.pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@Builder
public class ListBuilder {

    @Builder.Default
    @JsonProperty("name")
    private String name = "List " + RandomStringUtils.randomAlphabetic(3);
    @Builder.Default
    @JsonProperty("pos")
    private String pos = "top";
}
