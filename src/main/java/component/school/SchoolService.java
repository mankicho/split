package component.school;

import component.school.dto.ClassDTO;
import component.school.dto.SchoolDTO;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolMapper schoolMapper;


    public List<SchoolVO> getSchools(int categoryId) {
        return schoolMapper.getSchools(categoryId);
    }

    public int registerSchool(SchoolDTO schoolDTO) {
        return schoolMapper.registerSchool(schoolDTO);
    }

    public List<ClassVO> getClasses(Map<String, Object> map) {
        return schoolMapper.getClasses(map);
    }

    public int registerClass(ClassDTO classDTO) {
        return schoolMapper.registerClass(classDTO);
    }

    public int saveHashTag(HashMap<String, Object> hashMap) {
        return schoolMapper.saveHashTag(hashMap);
    }

    public int saveSearchKeyword(Map<String, Object> map) {
        return schoolMapper.saveSearchKeyword(map);
    }

    public List<SchoolVO> getSchoolsBySearch(String keyword) {
        return schoolMapper.getSchoolsBySearch(keyword);
    }

}
