package firebase.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class FileUploadResponse {
    private int status;
    private String msg;
    private String fileName;
}
