package api.pojo.requests;

import lombok.Builder;
import lombok.Data;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Data
@Builder

public class BoardBuilder {

    @Builder.Default
    private String name = "Board " + randomAlphabetic(3);
    @Builder.Default
    private String desc = "Default description " + randomAlphabetic(3);
    @Builder.Default
    private Boolean defaultLabels = true;
    @Builder.Default
    private Boolean defaultLists = true;
    @Builder.Default
    private String prefs_permissionLevel = "public";
    @Builder.Default
    private String prefs_voting = "public";
}