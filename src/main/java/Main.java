//import org.snu.ids.kkma.index.Keyword;
//import org.snu.ids.kkma.index.KeywordExtractor;
//import org.snu.ids.kkma.index.KeywordList;
//import org.snu.ids.kkma.ma.MExpression;
//import org.snu.ids.kkma.ma.MorphemeAnalyzer;
//import org.snu.ids.kkma.ma.Sentence;
//import org.snu.ids.kkma.util.Timer;
//
//import java.io.File;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) throws Exception {
////        maTest();
//        // string to extract keywords
//        String strToExtrtKwrd = "하 실시간 인기검색어 빡새네 경찰공무원";
//        String[] strs = new String[]{
//                "경찰 준비중인 학교",
//                "공무원",
//                "공무원 준비 학교",
//                "경찰을 열정적으로 준비하실 분",
//                "경찰",
//                "공무원",
//                "인하대학교",
//                "인하대학교 중간고사",
//                "인하대학교 기말고사",
//                "인하인들의 모임",
//                "경찰이 되고싶은 분들",
//                "경찰공",
//                "경찰학교"
//        };
//
//        Map<String, Integer> map = new HashMap<>();
//
//        // init KeywordExtractor
//        KeywordExtractor ke = new KeywordExtractor();
//        // extract keywords
//        for (int i = 0; i < strs.length; i++) {
//            String s = strs[i];
//            KeywordList kl = ke.extractKeyword(s, true);
//
//            for (int j = 0; j < kl.size(); j++) {
//                Keyword kwrd = kl.get(j);
//                map.putIfAbsent(kwrd.getString(), 0);
//
//                map.put(kwrd.getString(), map.get(kwrd.getString()) + kwrd.getCnt());
//            }
//        }
//
//
//        map.forEach((key, value) -> System.out.print(key + "\t" + value + "\n"));
//        // print result
//    }
//
//    public static void maTest() {
//        String string = "이것도분석하는게가능한부분인가요오지고지리고돌리고인정?어인정";
//        try {
//            MorphemeAnalyzer ma = new MorphemeAnalyzer();
//            ma.createLogger(null);
//            Timer timer = new Timer();
//            timer.start();
//            List<MExpression> ret = ma.analyze(string);
//            timer.stop();
//            timer.printMsg("Time");
//            ret = ma.postProcess(ret);
//            ret = ma.leaveJustBest(ret);
//            List<Sentence> stl = ma.divideToSentences(ret);
//            for (int i = 0; i < stl.size(); i++) {
//                Sentence st = stl.get(i);
//                System.out.println("=============================================  " + st.getSentence());
//                for (int j = 0; j < st.size(); j++) {
//                    System.out.println(st.get(j));
//                }
//
//            }
//            ma.closeLogger();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
