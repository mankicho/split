package rank;

import component.school.dto.SchoolSearchLogDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KeywordCollector {
    public static List<String> keywords = new ArrayList<>();

    public static void collect(String str) {
        keywords.add(str);
    }


}
