package component.school;

import component.member.vo.MemberDeviceVO;
import component.school.dto.ClassAuthDTO;
import component.school.dto.ClassDTO;
import component.school.dto.ClassJoinDTO;
import component.school.dto.SchoolDTO;
import component.school.view.DefaultSchoolResultView;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import exception.error.SchoolErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolMapper schoolMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<SchoolVO> getSchools(int categoryId) {
        return schoolMapper.getSchools(categoryId);
    }

    public int registerSchool(SchoolDTO schoolDTO) {
        return schoolMapper.registerSchool(schoolDTO);
    }

    public List<ClassVO> getClasses(ClassDTO classDTOForSelect) {
        return schoolMapper.getClasses(classDTOForSelect);
    }

//    public int registerClass(ClassDTO classDTO) {
//        return schoolMapper.registerClass(classDTO);
//    }

    public int saveHashTag(HashMap<String, Object> hashMap) {
        return schoolMapper.saveHashTag(hashMap);
    }

    public int saveSearchKeyword(Map<String, Object> map) {
        return schoolMapper.saveSearchKeyword(map);
    }

    public List<SchoolVO> getSchoolsBySearch(String keyword) {
        return schoolMapper.getSchoolsBySearch(keyword);
    }

    public int joinClass(ClassJoinDTO classJoinDTO) throws ParseException {
        Date today = new Date();
        if (simpleDateFormat.parse(classJoinDTO.getStartDate()).compareTo(today) < 0
                || simpleDateFormat.parse(classJoinDTO.getEndDate()).compareTo(today) < 0) {
            // 예약 날짜가 오늘 이전이면
            return -1;
        }
        return schoolMapper.joinClass(classJoinDTO);
    }

    public DefaultSchoolResultView classAuth(ClassAuthDTO classAuthDTO, long timestamp) {
        DefaultSchoolResultView view = new DefaultSchoolResultView();
        if (new Date(timestamp).compareTo(new Date()) > 0) { // qr 코드의 timestamp 값이 현재보다 크면
            SchoolErrorCode errorCode = SchoolErrorCode.FutureThanCurrentTimeError;
            return new DefaultSchoolResultView(errorCode);
        }

        int insertedRow = schoolMapper.classAuth(classAuthDTO);
        view.setInsertedRow(insertedRow);

        if (insertedRow == 0) {
            view.setStatus(500);
            view.setMessage("server error");
        } else {
            view.setStatus(202);
            view.setMessage("success");
        }
        return view;

    }
}
