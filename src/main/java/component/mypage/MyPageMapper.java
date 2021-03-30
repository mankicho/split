package component.mypage;

public interface MyPageMapper {
    MyPageMainVO getMyMainPageVO(MyPageMainDTO myPageMainDTO); // myPage 의 main 화면 데이터(누적상금, 행성 레벨, 출석률) 가져오기
}
