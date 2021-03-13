package controller;

import component.school.SchoolService;
import component.school.dto.ClassDTO;
import component.school.dto.ClassJoinDTO;
import component.school.dto.SchoolDTO;
import component.school.view.JoinClassResult;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import rank.RankServerStatus;
import rank.SearchKeywordBroker;
//import rank.KeywordCollector;
//import rank.UserKeywordExtractor;
//import rank.KeywordCollector;
//import rank.UserKeywordExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/school")
@Log4j2
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    private final SearchKeywordBroker broker = new SearchKeywordBroker();

//    @ExceptionHandler(ParseException.class)
//    @ResponseBody
//    public JoinClassResult handleValidationException(ParseException e) {
//        JoinClassResult result = new JoinClassResult();
//        result.setStatus(400);
//        result.setInsertedRow(-1);
//        return result;
//    }

    // 학교 개설
    @PostMapping(value = "/create.do")
    public SchoolDTO createSchool(@RequestBody SchoolDTO schoolDTO) {
        log.info(schoolDTO);

        int insertedRow = schoolService.registerSchool(schoolDTO);
        if (insertedRow == 0) {
            return null;
        }

        HashMap<String, Object> mapForInsert = new HashMap<>();
        mapForInsert.put("schoolId", schoolDTO.getSchoolId());
        mapForInsert.put("list", schoolDTO.getHashtags());
        log.info(mapForInsert);
        int insertedRowOfSchoolHashTags = schoolService.saveHashTag(mapForInsert);

        log.info(insertedRowOfSchoolHashTags);
        if (insertedRowOfSchoolHashTags == 0) {
            return null;
        }

        return schoolDTO;
    }

    // 학교 정보 가져오기
    @GetMapping(value = "/get.do")
    public List<SchoolVO> getSchools(@RequestParam("categoryId") int categoryId) {
        return schoolService.getSchools(categoryId);
    }

    // 학교의 클래스 정보들 가져오기
    @PostMapping(value = "/class/get.do")
    public List<ClassVO> getClasses(@RequestBody @Valid ClassDTO classDTOForSelect) {
        log.info(classDTOForSelect);
        return schoolService.getClasses(classDTOForSelect);
    }

    // 학교 검색
    @GetMapping(value = "/by/search/get.do")
    public List<SchoolVO> getSchoolsBySearch(@RequestParam("keyword") String keyword) {
        RankServerStatus serverStatus = broker.sendKeywordToSearchRankServer(keyword); // 인기검색어 형태소 분석 후 랭킹서버에 저장
        log.info(serverStatus);
        return schoolService.getSchoolsBySearch(keyword);
    }

    // 인기검색어 가져오기
    @GetMapping(value = "/popular/search/terms/get.do")
    public List<Object> getPopularSearchKeyword() {
        String s = broker.getKeywordNumFromRankServer();
        if (s == null || s.equals("")) {
            return new ArrayList<>();
        }
        JSONArray array = new JSONArray(s);

        return array.toList();
    }

    @PostMapping(value = "/join/class")
    public JoinClassResult joinClass(@RequestBody @Valid ClassJoinDTO classJoinDTO) throws ParseException {
        log.info(classJoinDTO);
        JoinClassResult result = new JoinClassResult(); // 클래스 신청에 대한 유저 view

        int insertedRow = schoolService.joinClass(classJoinDTO);

        if (insertedRow == -1) {
            throw new ParseException(classJoinDTO.getStartDate() + " or " + classJoinDTO.getEndDate(), 0);
        }

        result.setInsertedRow(insertedRow);

        if (insertedRow == 0) {
            result.setStatus(500); // DB 오류
        } else {
            result.setStatus(202);
        }

        log.info(result);
        return result;

    }
}
