//package rank;
//
//import component.school.dto.SchoolSearchLogDTO;
//import org.snu.ids.kkma.index.Keyword;
//import org.snu.ids.kkma.index.KeywordExtractor;
//import org.snu.ids.kkma.index.KeywordList;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Component
//public class UserKeywordExtractor {
//    private KeywordExtractor ke;
//
//    public UserKeywordExtractor() {
//        this.ke = new KeywordExtractor();
//    }
//
//    public List<SchoolSearchLogDTO> extract(List<String> strs) {
//        Map<String, Integer> map = new HashMap<>();
//        for (String s : strs) {
//            KeywordList kl = ke.extractKeyword(s, true);
//
//            for (Keyword kwrd : kl) {
//                map.putIfAbsent(kwrd.getString(), 0);
//                map.put(kwrd.getString(), map.get(kwrd.getString()) + kwrd.getCnt());
//            }
//        }
//
//        return map.entrySet().stream().map(entry -> new SchoolSearchLogDTO(entry.getKey(), entry.getValue())).collect(Collectors.toList());
//    }
//
//}
