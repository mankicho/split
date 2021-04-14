package component.home.dto;

import lombok.*;

@Builder
@Setter
@Getter
@ToString
public class HomeTicketDTO {
    private String memberEmail;
    private int weekday;
    private int classId; // 탐험단
    private int tid; // 예약정보 구별하는 기본키
}
