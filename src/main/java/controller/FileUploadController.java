package controller;

import file.FileUploadService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @Value("#{path['local_home']}")
    private String home;

//    @Value("#{path['server_home']}")
//    private String home;

    @PostMapping(value = "/main/upload")
    public void fileUploadMain(@RequestParam("file") MultipartFile multipartFile) {
        String path = home + "/profile/main/"; // 파일 경로
        fileUploadService.fileUpload(path, multipartFile);
        log.info("called");
    }

    @PostMapping(value = "/back/upload")
    public void fileUploadBackground(@RequestParam("file") MultipartFile multipartFile) {
        String path = home + "/profile/main/"; // 파일 경로
        fileUploadService.fileUpload(path, multipartFile);
        log.info("called");
    }


    private String getServletContextRealPath(HttpServletRequest req) { // resources path 가져오기
        return req.getSession().getServletContext().getRealPath("/resources");
    }

}
