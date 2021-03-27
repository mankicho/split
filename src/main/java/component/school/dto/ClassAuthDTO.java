package component.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassAuthDTO {
        private int weekday;
        private String qrToken; // insert 용
        private String memberEmail; // 유저 이메일
        private double lat;
        private double lng;
        private long now;
        private String date;
}
