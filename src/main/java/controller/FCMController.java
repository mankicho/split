package controller;

import component.chat.ChattingMessage;
import firebase.FirebaseCloudMessagingService;
import firebase.FirebaseCloudStorageService;
import firebase.FirebaseRealtimeDatabaseService;
import firebase.response.FileDownloadURLResponse;
import firebase.response.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@RestController
@RequestMapping(value = "/fcm")
@RequiredArgsConstructor
@Log4j2
public class FCMController {

    private final FirebaseCloudMessagingService firebaseCloudMessagingService;
    private final FirebaseCloudStorageService firebaseCloudStorageService;
    private final FirebaseRealtimeDatabaseService firebaseRealtimeDatabaseService;


    @GetMapping(value = "/test.do")
    public void sendMsg(HttpServletRequest request) {
        String tokenId = request.getParameter("tokenId");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        firebaseCloudMessagingService.sendFCM(tokenId, title, content);
    }

    @PostMapping(value = "/file/upload")
    public FileUploadResponse fileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        return firebaseCloudStorageService.uploadFile("test/manki/", multipartFile);
    }

    @PostMapping(value = "/file/image/download/url/get.do")
    public FileDownloadURLResponse getDownloadURL() {
        firebaseCloudStorageService.getFileDownloadingURL();
        return null;
    }

    @PostMapping(value = "/real/time/database/write")
    public void write(@RequestBody ChattingMessage chattingMessage) {
        log.info("write");
        firebaseRealtimeDatabaseService.write(chattingMessage);
        firebaseCloudMessagingService.sendChattingMessage(chattingMessage);
    }

}
