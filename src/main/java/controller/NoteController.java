package controller;

import component.note.NoteMapper;
import component.note.NoteService;
import component.note.NoteVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/split/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping(value = "/get.do")
    public List<NoteVO> getNotes(HttpServletRequest request) {
        String toEmail = request.getParameter("toEmail");
        String fromEmail = request.getParameter("fromEmail");

        return noteService.getNotes(toEmail, fromEmail);
    }
}
