package component.school;

import component.member.vo.MemberDeviceVO;
import component.school.dto.ClassAuthDTO;
import component.school.dto.ClassDTO;
import component.school.dto.ClassJoinDTO;
import component.school.dto.SchoolDTO;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SchoolMapper {
    List<SchoolVO> getSchools(@Param("categoryId") int categoryId,@Param("weekday") int weekday); // 학교정보 가져오기

    List<SchoolVO> getSchoolsByPlanetCode(@Param("planetCode") String planetCode);

    int registerSchool(SchoolDTO schoolDTO); // 학교 만들기

    int saveHashTag(HashMap<String, Object> hashMap); // 학교 해시태그 저장

    List<ClassVO> getClasses(ClassDTO classDTOForSelect); // 클래스 가져오기

//    int registerClass(ClassDTO classDTO); // 클래스 등록하기(삭제예정)

    int saveSearchKeyword(Map<String, Object> map); // 검색 키워드 저장하기

    List<SchoolVO> getSchoolsBySearch(String keyword); // 검색으로 학교 가져오기

    int joinClassInOfficial(ClassJoinDTO classJoinDTO);

    int joinClassInNonOfficial(ClassJoinDTO classJoinDTO);

    List<MemberDeviceVO> getDevicesForPushNotificationOfAttendance(@Param("weekday") int weekday);

    int classAuth(ClassAuthDTO classAuthDTO);
}
