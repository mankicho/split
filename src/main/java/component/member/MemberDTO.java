package component.member;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDTO {
    private int member_id;
    private String m_name;
    private Date birth_date;
    private String gender;
    private String job;
    private String nickname;
    private String phone_number;
    private String email;
    private List<AuthDTO> authList;

    public MemberDTO(String m_name, Date birth_date, String gender, String job, String nickname, String phone_number, String email) {
        this.m_name = m_name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.job = job;
        this.nickname = nickname;
        this.phone_number = phone_number;
        this.email = email;
    }
}

