package controller;

import component.school.SchoolService;
import component.school.dto.*;
import component.school.explorer.dto.SchoolExplorerDTO;
import component.school.explorer.dto.SchoolExplorerRewardDTO;
import component.school.explorer.vo.*;
import component.school.view.ClassAuthView;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import component.zone.ZoneService;
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
    private final ZoneService zoneService;

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
    public ResultView joinClass(@RequestBody ClassJoinDTO classJoinDTO, @RequestParam("type") int type) throws ParseException {
        DefaultResultView result = new DefaultResultView(); // 클래스 신청에 대한 유저 view

        int insertedRow = schoolService.joinClass(classJoinDTO, type);

        if (insertedRow == -1) {
            throw new ParseException(classJoinDTO.getStartDate() + " or " + classJoinDTO.getEndDate(), 0);
        }

        if (insertedRow == 0) {
            result.setStatus(500); // DB 오류
            result.setMsg("join class fail");
        } else {
            result.setStatus(202);
            result.setMsg("join class success");
        }

        return result;
    }

    // 공식 갤럭시 인증하기
    @GetMapping(value = "/class/auth")
    public ClassAuthView classAuthDo(ClassAuthDTO classAuthDTO) {
        return schoolService.classAuth(classAuthDTO);
    }

    // 탐험단 - 상금 탭 가져오기
    @PostMapping(value = "/explorer/reward/get.do")
    public SchoolRewardVO getExplorerReward(@RequestBody SchoolExplorerRewardDTO schoolExplorerRewardDTO) {
        SchoolRewardVO schoolRewardVO = schoolService.getExplorerReward(schoolExplorerRewardDTO);
        int predictReward = schoolService.getPredictReward(schoolExplorerRewardDTO);
        schoolRewardVO.setPredictReward(predictReward);
        return schoolRewardVO;
    }

    // 탐험단 정보 가져오기
    @PostMapping(value = "/explorer/att/list/get.do")
    public SchoolExplorerVO getExplorerVO(@RequestBody SchoolExplorerDTO schoolExplorerDTO) {
        int schoolId = schoolExplorerDTO.getSchoolId();
        int classId = schoolExplorerDTO.getClassId();
        List<SchoolExplorerAttendanceListVO> schoolExplorerAttendanceListVOS = schoolService.getAttendanceList(schoolExplorerDTO);
        SchoolClassAvgAttendanceRateVO schoolClassAvgAttendanceRateVO = schoolService.getAttendanceRate(schoolId, classId);

        return new SchoolExplorerVO(schoolExplorerAttendanceListVOS, schoolClassAvgAttendanceRateVO);
    }

    @PostMapping(value = "/explorer/my/info/get.do")
    public SchoolExplorerMyInfo getMyInfo(@RequestParam("schoolId") int schoolId, @RequestParam("classId") int classId,
                                          @RequestParam("memberEmail") String memberEmail) {
        return schoolService.getMyInfo(schoolId, classId, memberEmail);
    }

    @PostMapping(value = "/my/explorer/list/get.do")
    public List<SchoolMyExplorersVO> getMyExplorersVO(@RequestParam("memberEmail") String memberEmail) {
        return schoolService.getMyExplorersVO(memberEmail);
    }
}
