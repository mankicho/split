package security.token;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResultView { // 토큰이 담겨져있는 객체
    private String result;
}
