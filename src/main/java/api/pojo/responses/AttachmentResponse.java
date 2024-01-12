package api.pojo.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("bytes")
    private String bytes;
    @JsonProperty("date")
    private String date;
    @JsonProperty("edgeColor")
    private String edgeColor;
    @JsonProperty("idMember")
    private String idMember;
    @JsonProperty("isUpload")
    private Boolean isUpload;
    @JsonProperty("mimeType")
    private String mimeType;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("pos")
    private int pos;
}