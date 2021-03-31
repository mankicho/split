package component.school;

import component.member.vo.MemberDeviceVO;
import component.school.dto.*;
import component.school.explorer.dto.SchoolExplorerDTO;
import component.school.explorer.dto.SchoolExplorerRewardDTO;
import component.school.explorer.vo.*;
import component.school.vo.ClassAuthVO;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SchoolMapper {
    List<SchoolVO> getSchools(@Param("categoryId") int categoryId, @Param("weekday") int weekday); // 학교정보 가져오기

    List<SchoolVO> getSchoolsByPlanetCode(@Param("planetCode") String planetCode);

    int registerSchool(SchoolDTO schoolDTO); // 학교 만들기

    int saveHashTag(HashMap<String, Object> hashMap); // 학교 해시태그 저장

    List<ClassVO> getClasses(ClassDTO classDTOForSelect); // 클래스 가져오기

    int saveSearchKeyword(Map<String, Object> map); // 검색 키워드 저장하기

    List<SchoolVO> getSchoolsBySearch(String keyword); // 검색으로 학교 가져오기

    int joinClassInOfficial(ClassJoinDTO classJoinDTO); // 공식 갤럭시 예약하기

    int joinClassInNonOfficial(ClassJoinDTO classJoinDTO); // 비공식 갤럭시 예약하기

    List<MemberDeviceVO> getDevicesForPushNotificationOfAttendance(@Param("weekday") int weekday); // 푸시알림 보낼 디바이스정보 가져오기

    int classAuth(ClassAuthLogDTO classAuthLogDTO); // 인증하기

    // 탐험단 - 상금
    SchoolRewardVO getExplorerReward(@Param("schoolId") int schoolId, @Param("classId") int classId, @Param("weekday") int weekday);

    // 탐험단 - 탐험중인 유저 리스트
    List<SchoolExplorerAttendanceListVO> getAttendanceList(SchoolExplorerDTO schoolExplorerDTO);

    // 탐험단 -- 평균 출석률
    SchoolClassAvgAttendanceRateVO getAttendanceRate(@Param("schoolId") int schoolId, @Param("classId") int classId);

    // 나의 예상 상금
    int getPredictReward(SchoolExplorerRewardDTO schoolExplorerPredictRewardDTO);

    // 탐험단 - 나의정보
    SchoolExplorerMyInfo getMyInfo(@Param("schoolId") int schoolId, @Param("classId") int classId,
                                   @Param("memberEmail") String memberEmail);

    // 출석체크 인증할때 나의 갤럭시 정보들 가져오기
    List<ClassAuthVO> getMyClassMembers(ClassAuthDTO classAuthDTO);

    // 나의 탐험단
    List<SchoolMyExplorersVO> getMyExplorersVO(@Param("memberEmail") String memberEmail);

    SchoolTestVO getTest(@Param("schoolId") int schoolId);
}
