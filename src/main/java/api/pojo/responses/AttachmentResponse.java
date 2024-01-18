package api.pojo.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentResponse {

    private String id;
    private String bytes;
    private String date;
    private String edgeColor;
    private String idMember;
    private Boolean isUpload;
    private String mimeType;
    private String name;
    private String url;
    private int pos;
}