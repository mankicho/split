package component.peed.db;

public interface PeedMapper {
    void search();// 게시글 검색기능

    void follow(); // 팔로우 기능

    void editProfile(); // 프로필 편집

    void uploadPeed(); // 게시글 등록
}
