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
@RequestMapping(value = "/peed")
@Log4j
public class FileUploadController {

    @Value("#{path['local_home']}")
    private String home;

//    @Value("#{path['server_home']}")
//    private String home;

    @PostMapping(value = "/file/main/upload")
    public void fileUploadMain(HttpServletRequest req, @RequestParam("file") MultipartFile multipartFile) {
        String path = home + "/profile/main/"; // 파일 경로
        String ext = getExtension(multipartFile);
        File targetFile = new File(path + getUUID() + ext);
        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            log.info("upload success");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        log.info("file upload finish");
        log.info("----------------");
    }

    @PostMapping(value = "/file/back/upload")
    public void fileUploadBackground(HttpServletRequest req, @RequestParam("file") MultipartFile multipartFile) {
        String path = home + "/profile/main/"; // 파일 경로
        String ext = getExtension(multipartFile);
        if (ext == null) {
            return;
        }
        File targetFile = new File(path + getUUID() + ext);
        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            log.info("upload success");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        log.info("file upload finish");
        log.info("----------------");
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
}
