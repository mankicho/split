package component.mypage;

import component.note.NoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MyPageMapper myPageMapper;

    public MyPageMainVO getMyMainPageVO(MyPageMainDTO myPageMainDTO) {
        return myPageMapper.getMyMainPageVO(myPageMainDTO);
    }

}
