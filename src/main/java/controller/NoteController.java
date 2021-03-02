package controller;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import component.member.MemberService;
import component.note.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/split/note")
@RequiredArgsConstructor
@Log4j2
public class NoteController { // 유저가 요청을 하면

    private final NoteService noteService;
    private final MemberService memberService;

    @PostMapping(value = "/uuid/create.do") //
    public NoteUUID getUUID(HttpServletRequest request) {
        String toEmail = request.getParameter("toEmail");
        String fromEmail = request.getParameter("fromEmail");

        log.info(toEmail,fromEmail);
        String uuid = noteService.getUUID(toEmail, fromEmail); // 쪽지방이 존재하는가

        if (uuid == null || uuid.equals("")) { // 존재하지 않으면
            String noteBoxUUID = UUID.randomUUID().toString().replace("-", "");
            int insertedRow = noteService.createNoteBox(toEmail, fromEmail, noteBoxUUID);
            if (insertedRow == 2) {
                return new NoteUUID(noteBoxUUID, new ArrayList<>());
            }
            return new NoteUUID("", new ArrayList<>());
        }
        // 존재하면
        else {
            List<NoteVO> notes = noteService.getNotes(toEmail);

            return new NoteUUID(uuid, notes);
        }
    }

    @PostMapping(value = "/all/noteBoxes/get.do")
    public List<NoteVO> getNoteBoxes(@RequestParam("toEmail") String toEmail) {

        return noteService.getNotes(toEmail);
    }

    @PostMapping(value = "/all/notes/get.do")
    public List<NoteVO> getAllNotes(@RequestParam("fromEmail") String fromEmail, @RequestParam("uuid") String uuid) {
        int affectedRow = noteService.updateLastCheckTime(fromEmail, uuid); // 쪽지 방에 들어가면 last_check_time 을 바꿔준다.
        log.info(affectedRow);
        return noteService.getAllNotes(uuid, fromEmail);
    }
}
