package controller;

import component.school.SchoolService;
import component.school.dto.ClassDTO;
import component.school.dto.SchoolDTO;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import rank.RankServerStatus;
import rank.SearchKeywordBroker;
//import rank.KeywordCollector;
//import rank.UserKeywordExtractor;
//import rank.KeywordCollector;
//import rank.UserKeywordExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    // 학교 개설
    @PostMapping(value = "/create.do")
    public SchoolDTO createSchool(@RequestBody @Valid SchoolDTO schoolDTO) {
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
    public List<ClassVO> getClasses(@RequestBody Map<String, Object> map) {
        return schoolService.getClasses(map);
    }

    // 클래스 생성하기
    @PostMapping(value = "/class/create.do")
    public int registerClass(@RequestBody ClassDTO classDTO) {
        return schoolService.registerClass(classDTO);
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

}
