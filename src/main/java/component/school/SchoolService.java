package component.school;

import component.school.dto.ClassDTO;
import component.school.dto.SchoolDTO;
import component.school.vo.ClassVO;
import component.school.vo.SchoolVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SchoolService {

    @Setter(onMethod_ = {@Autowired})
    private SchoolMapper schoolMapper;

    public List<SchoolVO> getSchools(int categoryId) {
        return schoolMapper.getSchools(categoryId);
    }

    public int registerSchool(SchoolDTO schoolDTO) {
        return schoolMapper.registerSchool(schoolDTO);
    }

    public List<ClassVO> getClasses(String today) {
        return schoolMapper.getClasses(today);
    }

    public int registerClass(ClassDTO classDTO) {
        return schoolMapper.registerClass(classDTO);
    }

}
