package component.school;

import component.school.dto.ClassAuthDTO;
import component.school.dto.ClassDTO;
import component.school.dto.ClassJoinDTO;
import component.school.dto.SchoolDTO;
import component.school.view.ClassAuthView;
import component.school.vo.SchoolExplorerVO;
import exception.view.DefaultErrorView;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import exception.error.SchoolErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolMapper schoolMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<SchoolVO> getSchools(int categoryId, int weekday) {
        return schoolMapper.getSchools(categoryId, weekday);
    }

    public List<SchoolVO> getSchoolsByPlanetCode(String planetCode) {
        return schoolMapper.getSchoolsByPlanetCode(planetCode);
    }

    public int registerSchool(SchoolDTO schoolDTO) {
        return schoolMapper.registerSchool(schoolDTO);
    }

    public List<ClassVO> getClasses(ClassDTO classDTOForSelect) {
        List<ClassVO> classVOList = schoolMapper.getClasses(classDTOForSelect);
        addClassVO(classVOList); // 출석체크 할 학생이 없는 class 도 list 에 추가한다.
        return classVOList;
    }


    public int saveHashTag(HashMap<String, Object> hashMap) {
        return schoolMapper.saveHashTag(hashMap);
    }

    public int saveSearchKeyword(Map<String, Object> map) {
        return schoolMapper.saveSearchKeyword(map);
    }

    public List<SchoolVO> getSchoolsBySearch(String keyword) {
        return schoolMapper.getSchoolsBySearch(keyword);
    }

    public int joinClass(ClassJoinDTO classJoinDTO, int type) throws ParseException {
        Date today = new Date();
        if (simpleDateFormat.parse(classJoinDTO.getStartDate()).compareTo(today) < 0
                || simpleDateFormat.parse(classJoinDTO.getEndDate()).compareTo(today) < 0) {
            // 예약 날짜가 오늘 이전이면
            return -1;
        }

        if (type == 0) { // 비공식
            return schoolMapper.joinClassInOfficial(classJoinDTO);
        } else { // 공식
            return schoolMapper.joinClassInNonOfficial(classJoinDTO);
        }
    }

    public ClassAuthView classAuth(ClassAuthDTO classAuthDTO, long timestamp) {
        if (new Date(timestamp).compareTo(new Date()) > 0) { // qr 코드의 timestamp 값이 현재보다 크면
            SchoolErrorCode errorCode = SchoolErrorCode.FutureThanCurrentTimeError;
            ClassAuthView view = new ClassAuthView(errorCode);
            view.setAuthenticatedRow(-1);
            return view;
        }

        ClassAuthView view = new ClassAuthView();
        int insertedRow = schoolMapper.classAuth(classAuthDTO); // 출석체크

        if (insertedRow == 0) {  // DB 에러
            view.setStatus(500);
            view.setAuthenticatedRow(0);
            view.setMsg("server error");
        } else {
            view.setAuthenticatedRow(1);
            view.setStatus(202);
            view.setMsg("success");
        }
        return view;
    }

    public SchoolExplorerVO getExplorer(int schoolId, int classId, int weekday) {
        return schoolMapper.getExplorer(schoolId, classId, weekday);
    }

    private void addClassVO(List<ClassVO> classVOS) {
        for (int i = 1; i <= 24; i++) {
            boolean isSame = false;
            for (ClassVO classVO : classVOS) {
                if (i == classVO.getClassId()) {
                    isSame = true;
                    break;
                }
            }
            if (!isSame) {
                classVOS.add(new ClassVO(i, 0, 0));
            }
        }
    }
}
