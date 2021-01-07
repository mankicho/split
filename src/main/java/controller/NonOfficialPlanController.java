package controller;

import component.plan.nonoff.NonOfficialPlanDTO;
import component.plan.nonoff.NonOfficialPlanService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * free plan (non-official-plan)
 */
@RestController
@RequestMapping(value = "/split/nonplan")
@Log4j
public class NonOfficialPlanController {
    @Setter(onMethod_ = {@Autowired})
    private NonOfficialPlanService nonOfficialPlanService;


    /**
     * @param e 요청 파라미터 중 빠진 데이터가 있을 때
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public HashMap<String, String> handleExpectedException(Exception e) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("msg", e.getMessage());
        hashMap.put("code", "400"); // BAD REQUEST
        return hashMap;
    }

    /**
     * @param nonOfficialPlanDTO 비공식 플랜 예약 기능
     * @return
     */
    @PostMapping(value = "/reserve.do")
    public int insertNonOfficialPlan(@RequestBody NonOfficialPlanDTO nonOfficialPlanDTO) {
        int value = nonOfficialPlanService.insertNonOfficialPlan(nonOfficialPlanDTO);
        if (value == 0) {
            return 202;
        }
        return 200;
    }

    /**
     * @param request 비공식 플랜 삭제 기능
     * @return
     */
    @PostMapping(value = "/delete.do")
    public int deleteNonOfficialPlan(HttpServletRequest request) {
        String email = request.getParameter("email");
        String sDate = request.getParameter("sDate");
        String aTime = request.getParameter("aTime");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("sDate", sDate);
        hashMap.put("aTime", aTime);

        return nonOfficialPlanService.deleteNonOfficialPlan(hashMap);
    }

    /**
     * @param request 특정 날짜의 비공식 플랜 가져오기
     * @return
     */
    @PostMapping(value = "/select.do")
    public List<NonOfficialPlanDTO> getNonPlan(HttpServletRequest request) {
        String email = request.getParameter("email");
        String sDate = request.getParameter("sDate");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("sDate", sDate);

        return nonOfficialPlanService.getNonPlan(hashMap);
    }

    /**
     * @param nonPlanId 플랜 쉐어링 - 특정 유저의 비공식 플랜을 공유하는 기능
     * @return
     */
    @PostMapping(value = "/share.do")
    public NonOfficialPlanDTO nonOfficialPlanShare(@RequestParam("npid") int nonPlanId) {
        return nonOfficialPlanService.nonOfficialPlanShare(nonPlanId);
    }

    /**
     * @param request ID 와 Email 로 특정 비공식 플랜을 조회하기 (출석체크를 위한 기능)
     * @return
     */
    @PostMapping(value = "/selectByIdAndEmail.do")
    public Integer selectByIdAndEmail(HttpServletRequest request) {
        int nonPlanId = Integer.parseInt(request.getParameter("npid"));
        String email = request.getParameter("email");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("npid", nonPlanId);
        hashMap.put("email", email);

        Integer reVal = nonOfficialPlanService.selectByIdAndEmail(hashMap);
        return reVal == null ? -100 : reVal;
    }
}
