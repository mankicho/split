package rank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 인기검색어 순위 데이터 모델
public class KeywordNum {
    private String keyword;
    private Long num;
}
