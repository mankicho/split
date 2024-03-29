package component.school.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassVO {
    private int classId;
    private int allMembers;
    private int authMembers;
}
