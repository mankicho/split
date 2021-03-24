package component.school;

import component.school.dto.*;
import component.school.explorer.dto.SchoolExplorerDTO;
import component.school.explorer.dto.SchoolExplorerRewardDTO;
import component.school.explorer.vo.SchoolExplorerMyInfo;
import component.school.view.ClassAuthView;
import component.school.explorer.vo.SchoolClassAvgAttendanceRateVO;
import component.school.explorer.vo.SchoolExplorerAttendanceListVO;
import component.school.explorer.vo.SchoolRewardVO;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import component.zone.ZoneMapper;
import component.zone.vo.ZoneLatLngVO;
import exception.error.SchoolErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolMapper schoolMapper; // DB 쿼리문을 수행하는 mapper

    private final ZoneMapper zoneMapper;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 날짜를 변환하기위한 포매터

    public List<SchoolVO> getSchools(int categoryId, int weekday) { // 학교 가져오기
        return schoolMapper.getSchools(categoryId, weekday);
    }

    public List<SchoolVO> getSchoolsByPlanetCode(String planetCode) { // 특정 행성코드로 학교 정보 가져오기
        return schoolMapper.getSchoolsByPlanetCode(planetCode);
    }

    public int registerSchool(SchoolDTO schoolDTO) {
        return schoolMapper.registerSchool(schoolDTO);
    } // 학교 등록하기

    public List<ClassVO> getClasses(ClassDTO classDTOForSelect) { // 클래스정보 가져오기
        List<ClassVO> classVOList = schoolMapper.getClasses(classDTOForSelect);
        addClassVO(classVOList); // 출석체크 할 학생이 없는 class 도 list 에 추가한다.
        return classVOList;
    }


    public int saveHashTag(HashMap<String, Object> hashMap) {
        return schoolMapper.saveHashTag(hashMap);
    } // 해시태그 저장하기

    public int saveSearchKeyword(Map<String, Object> map) {
        return schoolMapper.saveSearchKeyword(map);
    }

    public List<SchoolVO> getSchoolsBySearch(String keyword) {
        return schoolMapper.getSchoolsBySearch(keyword);
    } // 검색으로 학교 가져오기

    public int joinClass(ClassJoinDTO classJoinDTO, int type) throws ParseException { // 클래스 등록하기(플랜 예약하기)
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

    // 출석 인증하기
    public ClassAuthView classAuth(ClassAuthDTO classAuthDTO, long timestamp, double lat, double lng) {

        // qr 코드의 timestamp 값이 현재보다 크면
        if (new Date(timestamp).compareTo(new Date()) > 0) {
            SchoolErrorCode errorCode = SchoolErrorCode.FutureThanCurrentTimeError;
            ClassAuthView view = new ClassAuthView(errorCode);
            view.setAuthenticatedRow(-1);
            return view;
        }

        ClassAuthView view = new ClassAuthView();

        // 유저의 위치와 카페의 위치가 맞지않으면
        String planetCode = classAuthDTO.getPlanetCode();
        if (planetCode != null && planetCode.equals("")) {
            ZoneLatLngVO latLngVO = zoneMapper.getZone(planetCode);

            double zoneLat = latLngVO.getLat();
            double zoneLng = latLngVO.getLng();

            double dist = distance(lat, lng, zoneLat, zoneLng);

            if(dist <= 50.0){
                view.setMsg("invalid lat,lng from speck zone");
                view.setStatus(400);
                view.setAuthenticatedRow(-1);
                return view;
            }

        }

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

    // 탐험단 - 상금 탭 정보 가져오기
    public SchoolRewardVO getExplorerReward(SchoolExplorerRewardDTO schoolExplorerRewardDTO) {
        int schoolId = schoolExplorerRewardDTO.getSchoolId();
        int classId = schoolExplorerRewardDTO.getClassId();
        int weekday = schoolExplorerRewardDTO.getWeekday();
        return schoolMapper.getExplorerReward(schoolId, classId, weekday);
    }

    // 특정 학교,클래스의 출석, 비출석 유저 정보 가져오기(사진, 닉네임, 인증횟수)
    public List<SchoolExplorerAttendanceListVO> getAttendanceList(SchoolExplorerDTO dto) {
        return schoolMapper.getAttendanceList(dto);
    }

    // 탐험단 탭의 출석율 가져오기
    public SchoolClassAvgAttendanceRateVO getAttendanceRate(int schoolId, int classId) {
        return schoolMapper.getAttendanceRate(schoolId, classId);
    }

    // 예상 상금 가져오기
    public int getPredictReward(SchoolExplorerRewardDTO schoolExplorerPredictRewardDTO) {
        return schoolMapper.getPredictReward(schoolExplorerPredictRewardDTO);
    }

    public SchoolExplorerMyInfo getMyInfo(int schoolId, int classId, String memberEmail){
        return schoolMapper.getMyInfo(schoolId,classId,memberEmail);
    }
    private void addClassVO(List<ClassVO> classVOS) { // 클래스 정보 가져오기에서 유저가 예약하지 않은 클래스정보를 추가하기위한 메소드
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

    // 경위도 계산거리

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private double distance(double lat, double lng, double zoneLat, double zoneLng) {
        double theta = lng - zoneLng;
        double dist = Math.sin(deg2rad(lat)) * Math.sin(deg2rad(zoneLat)) +
                Math.cos(deg2rad(lat)) * Math.cos(deg2rad(zoneLat)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return dist * 1609.344;
    }


}
