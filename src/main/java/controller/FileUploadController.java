package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/peed")
public class FileUploadController {

    @PostMapping(value = "/file/upload")
    public void fileUpload(HttpServletRequest req) {
        String path = req.getSession().getServletContext().getRealPath("/resources"); // 파일 경로
        System.out.println("path = " + path);
    }
}
