package controller;

import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping(value = "/file")
@Log4j
public class FileUploadController {

//    @Value("#{path['local_home']}")
//    private String home;
//
    @Value("#{path['server_home']}")
    private String home;

    @PostMapping(value = "/main/upload")
    public FileUploadReturnMessage fileUploadMain(@RequestParam("file") MultipartFile multipartFile) {
        String path = home + "/profile/main/"; // 파일 경로
        return fileUpload(path, multipartFile);

    }

    @PostMapping(value = "/back/upload")
    public FileUploadReturnMessage fileUploadBackground(@RequestParam("file") MultipartFile multipartFile) {
        String path = home + "/profile/main/"; // 파일 경로
        return fileUpload(path, multipartFile);
    }

    private FileUploadReturnMessage fileUpload(String path, MultipartFile multipartFile) {
        String ext = getExtension(multipartFile);
        if (ext == null) {
            return new FileUploadReturnMessage(400, "적절하지않은 확장자 입니다.");
        }
        File targetFile = new File(path + getUUID() + ext); // file 생성
        try {
            InputStream fileStream = multipartFile.getInputStream(); // file 업로드
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            log.info("upload success");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
            return new FileUploadReturnMessage(500, "서버 내부 오류");
        }
        log.info("file upload finish");
        log.info("----------------");
        if(targetFile.exists()){
            return new FileUploadReturnMessage(202, "파일 업로드 성공");
        }else{
            return new FileUploadReturnMessage(500,"알수없는 오류");
        }
    }

    private String getServletContextRealPath(HttpServletRequest req) { // resources path 가져오기
        return req.getSession().getServletContext().getRealPath("/resources");
    }

    private String getUUID() { // 고유 식별자 만들기
        return UUID.randomUUID().toString().replace("-", "");
    }

    private String getExtension(MultipartFile multipartFile) { // 확장자 가져오기
        String fileName = multipartFile.getOriginalFilename();
        if (fileName != null) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return null;
    }


    private static class FileUploadReturnMessage {
        int status;
        String message;

        public FileUploadReturnMessage(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }
}
