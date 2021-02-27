package component.school;

import component.school.dto.ClassDTO;
import component.school.dto.SchoolDTO;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SchoolMapper {
    List<SchoolVO> getSchools(int categoryId);

    int registerSchool(SchoolDTO schoolDTO);

    int saveHashTag(HashMap<String, Object> hashMap);

    List<ClassVO> getClasses(Map<String,Object> map);

    int registerClass(ClassDTO classDTO);
}
