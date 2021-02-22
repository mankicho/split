package security.login;

import component.member.vo.MemberVO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class CustomMember extends User {
    private static final long serialVersionUID = 1L;

    private MemberVO memberVO;

    public CustomMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomMember(MemberVO memberVO) {
        super(memberVO.getEmail(), memberVO.getPw(), memberVO.getAuthList().stream().
                map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
        this.memberVO = memberVO;
    }
}
