package controller;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/peed")
public class FileUploadController {

    @PostMapping(value = "/file/upload")
    public void fileUpload(HttpServletRequest req, @RequestParam("file") MultipartFile multipartFile) {
        String path = req.getSession().getServletContext().getRealPath("/resources")+"/profile"; // 파일 경로
        File targetFile = new File(path + multipartFile.getOriginalFilename());
        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        System.out.println("path = " + path);
    }
}
