package security.login;

import component.member.MemberDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class CustomMember extends User {
    private static final long serialVersionUID = 1L;

    private MemberDTO memberDTO;

    public CustomMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomMember(MemberDTO memberDTO) {
        super(memberDTO.getEmail(), memberDTO.getPw(), memberDTO.getAuthList().stream().
                map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
        this.memberDTO = memberDTO;
    }
}
