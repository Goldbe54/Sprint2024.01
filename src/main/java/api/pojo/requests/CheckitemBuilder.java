package api.pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Data
@Builder
public class CheckitemBuilder {

    @Builder.Default
    @JsonProperty("name")
    private String name = "CheckItem " + randomAlphabetic(3);
    @Builder.Default
    @JsonProperty("pos")
    private String pos = "top";
    @Builder.Default
    @JsonProperty("checked")
    private Boolean checked = false;
}
