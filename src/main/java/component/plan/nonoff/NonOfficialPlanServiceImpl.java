package component.plan.nonoff;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class NonOfficialPlanServiceImpl implements NonOfficialPlanService {

    @Setter(onMethod_ = {@Autowired})
    private NonOfficialPlanDAO nonOfficialPlanDAO;

    /**
     * @param nonOfficialPlanDTO 비공식 플랜 예약하기
     * @return
     */
    @Override
    public int insertNonOfficialPlan(NonOfficialPlanDTO nonOfficialPlanDTO) {
        return nonOfficialPlanDAO.insertNonOfficialPlan(nonOfficialPlanDTO);
    }

    /**
     * @param hashMap 비공식 플랜 가져오기(ex.. 11월~12월 사이의 모든 플랜)
     *                추후 클라이언트에서 캐시 자료구조를 통해 속도 개선 필요
     * @return
     */
    @Override
    public List<NonOfficialPlanDTO> getNonPlan(HashMap<String, Object> hashMap) {
        return nonOfficialPlanDAO.getNonPlan(hashMap);
    }

    /**
     * @param hashMap 비공식 플랜 삭제하기
     * @return
     */
    @Override
    public int deleteNonOfficialPlan(HashMap<String, Object> hashMap) {
        return nonOfficialPlanDAO.deleteNonOfficialPlan(hashMap);
    }

    /**
     * @param nonOfficialPlanLogId 비공식 플랜 쉐어링하기
     * @return
     */
    @Override
    public NonOfficialPlanDTO nonOfficialPlanShare(int nonOfficialPlanLogId) {
        return nonOfficialPlanDAO.nonOfficialPlanShare(nonOfficialPlanLogId);
    }

    /**
     * @param hashMap 로그 아이디와 이메일로 비공식 플랜이 존재하는지 조회(출석체크 할 때)
     * @return
     */
    @Override
    public int selectByIdAndEmail(HashMap<String, Object> hashMap) {
        return nonOfficialPlanDAO.selectByIdAndEmail(hashMap);
    }


}
