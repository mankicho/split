package controller;

import file.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping(value = "/file")
@Log4j
@RequiredArgsConstructor
public class FileController {
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

    @GetMapping(value = "/get.do", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String path = req.getParameter("imagePath");
        FileInputStream fis = new FileInputStream(home + path);
        OutputStream out = res.getOutputStream();
        FileCopyUtils.copy(fis, out);
        out.flush();
    }

    private String getServletContextRealPath(HttpServletRequest req) { // resources path 가져오기
        return req.getSession().getServletContext().getRealPath("/resources");
    }


}
