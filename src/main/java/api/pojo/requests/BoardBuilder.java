package api.pojo.requests;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Data
@Builder
public class BoardBuilder {

    @Builder.Default
    @JsonProperty("name")
    private String name = "Board " + randomAlphabetic(3);
    @Builder.Default
    @JsonProperty("desc")
    private String desc = "Default description " + randomAlphabetic(3);
    @Builder.Default
    @JsonProperty("defaultLabels")
    private Boolean defaultLabels = true;
    @Builder.Default
    @JsonProperty("defaultLists")
    private Boolean defaultLists = true;
    @Builder.Default
    @JsonProperty("prefs_permissionLevel")
    private String prefs_permissionLevel = "public";
    @Builder.Default
    @JsonProperty("prefs_voting")
    private String prefs_voting = "public";
}
