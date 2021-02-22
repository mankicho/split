package controller;

import component.member.MemberService;
import component.note.NoteMapper;
import component.note.NoteService;
import component.note.NoteUUID;
import component.note.NoteVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/split/note")
@RequiredArgsConstructor
@Log4j2
public class NoteController {

    private final NoteService noteService;
    private final MemberService memberService;

    @PostMapping(value = "/uuid/create.do") // 쪽지방 만들때
    public NoteUUID createUUID(HttpServletRequest request) {
        // todo 1. 이미 쪽지방이 만들어졌는지 확인
        String toEmail = request.getParameter("toEmail");
        String fromEmail = request.getParameter("fromEmail");
        NoteVO noteVO = noteService.getLastNode(toEmail, fromEmail);

        String toEmailInDB = memberService.isExistEmail(toEmail);
        String fromEmailInDB = memberService.isExistEmail(fromEmail);

        if(toEmailInDB == null || fromEmailInDB == null){
            return new NoteUUID("");
        }
        if (noteVO != null) {
            return new NoteUUID(noteVO.getNoteUUID());
        }
        // todo 2. noteUUID 를 DB 에 저장.

        return new NoteUUID(UUID.randomUUID().toString().replace("-", ""));
    }

    @PostMapping(value = "/all/notes/get.do")
    public List<NoteVO> getNotesByUUID(@RequestParam("uuid") String uuid) {
        return noteService.getNotesByUUID(uuid);
    }

    @PostMapping(value = "/all/noteBoxes/get.do")
    public List<NoteVO> getNoteBoxesByToEmail(@RequestParam("toEmail") String toEmail) {
        return noteService.getNoteBoxesByToEmail(toEmail);
    }
}
