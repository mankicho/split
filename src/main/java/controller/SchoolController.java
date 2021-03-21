package controller;

import component.school.SchoolService;
import component.school.dto.ClassAuthDTO;
import component.school.dto.ClassDTO;
import component.school.dto.ClassJoinDTO;
import component.school.dto.SchoolDTO;
import component.school.view.ClassAuthView;
import exception.view.DefaultErrorView;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;
import rank.RankServerStatus;
import rank.SearchKeywordBroker;
import security.token.TokenGeneratorService;
import view.DefaultResultView;
import view.ResultView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/school")
@Log4j2
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    private final TokenGeneratorService tokenGeneratorService;
    private final SearchKeywordBroker broker = new SearchKeywordBroker();

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
    public List<SchoolVO> getSchools(@RequestParam("categoryId") int categoryId, @RequestParam("weekday") int weekday) {
        return schoolService.getSchools(categoryId, weekday);
    }

    @GetMapping(value = "/by/planet/code/get.do")
    public List<SchoolVO> getSchoolsByPlanetCode(@RequestParam("planetCode") String planetCode) {
        return schoolService.getSchoolsByPlanetCode(planetCode);
    }

    // 학교의 클래스 정보들 가져오기
    @PostMapping(value = "/class/get.do")
    public List<ClassVO> getClasses(@RequestBody ClassDTO classDTOForSelect) {
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

    // 클래스 가입하기
    @PostMapping(value = "/join/class")
    public ResultView joinClass(@RequestBody ClassJoinDTO classJoinDTO,@RequestParam("type") int type) throws ParseException {
        log.info(classJoinDTO);
        DefaultResultView result = new DefaultResultView(); // 클래스 신청에 대한 유저 view

        int insertedRow = schoolService.joinClass(classJoinDTO,type);

        if (insertedRow == -1) {
            throw new ParseException(classJoinDTO.getStartDate() + " or " + classJoinDTO.getEndDate(), 0);
        }

        if (insertedRow == 0) {
            result.setStatus(500); // DB 오류
        } else {
            result.setStatus(202);
        }

        log.info(result);
        return result;
    }

    // 인증하기
    @GetMapping(value = "/class/auth")
    public ClassAuthView classAuthDo(HttpServletRequest request) {
        // 파라미터 가져오기
        int schoolId = Integer.parseInt(request.getParameter("schoolId"));
        int classId = Integer.parseInt(request.getParameter("classId"));
        String qrToken = request.getParameter("qr-token");
        String emailToken = request.getParameter("mail-token");
        String now = request.getParameter("now");

        // timestamp 값
        long timestamp = Long.parseLong(now);

        // 토큰값에서 subject 추출.
        String planet = tokenGeneratorService.getSubject(qrToken);
        String email = tokenGeneratorService.getSubject(emailToken);

        // 인증에 필요한 데이터 dto
        ClassAuthDTO authDTO = new ClassAuthDTO(schoolId, classId, planet, email);

        return schoolService.classAuth(authDTO, timestamp);
    }
}
