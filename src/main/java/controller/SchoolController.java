package controller;

import component.school.SchoolService;
import component.school.dto.SchoolDTO;
import component.school.dto.SchoolSearchLogDTO;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import rank.KeywordCollector;
import rank.UserKeywordExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/school")
@Log4j2
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;
    private final UserKeywordExtractor extractor;

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

    @GetMapping(value = "/get.do")
    public List<SchoolVO> getSchools(@RequestParam("categoryId") int categoryId) {
        return schoolService.getSchools(categoryId);
    }

    @PostMapping(value = "/class/get.do")
    public List<ClassVO> getClasses(@RequestBody Map<String, Object> map) {
        return schoolService.getClasses(map);
    }

//    @GetMapping(value = "/by/search/get.do")
//    public List<SchoolVO> getSchoolsBySearch(@RequestParam("keyword") String keyword) {
//        KeywordCollector.collect(keyword);
//
//        if (KeywordCollector.keywords.size() >= 10) {
//            List<SchoolSearchLogDTO> list = extractor.extract(KeywordCollector.keywords);
//            Map<String, Object> map = new HashMap<>();
//            map.put("list", list);
//            log.info(map);
//            int insertedRow = schoolService.saveSearchKeyword(map);
//            log.info(insertedRow);
//        }
//
//        return null;
//    }
}
