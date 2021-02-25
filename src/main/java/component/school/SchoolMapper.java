package component.school;

import component.school.dto.ClassDTO;
import component.school.dto.SchoolDTO;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;

import java.util.List;

public interface SchoolMapper {
    List<SchoolVO> getSchools(int categoryId);

    int registerSchool(SchoolDTO schoolDTO);

    List<ClassVO> getClasses(String today);

    int registerClass(ClassDTO classDTO);
}
