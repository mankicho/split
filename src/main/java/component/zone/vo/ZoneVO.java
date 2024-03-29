package component.zone.vo;

import component.zone.dto.ZoneImageDTO;
import component.zone.dto.ZoneOperDTO;
import lombok.Data;

import java.util.List;

@Data
public class ZoneVO {
    private String code; // 행성 코드
    private String name; // 행성 이름
    private String doroName; // 도로명 주소
    private String phoneNumber; // 행성 전화번호
    private double lat; // 위도
    private double lng; // 경도
    private int todayVisitor; // 오늘 방문자 수
    private int num;
    private ZoneOperDTO zoneOperDTO; // 운영시간
    private List<ZoneImageDTO> zoneImageDTOList;
}


