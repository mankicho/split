package firebase;

import com.google.firebase.database.*;
import component.chat.ChattingMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
@RequiredArgsConstructor
public class FirebaseRealtimeDatabaseService {

    private final FirebaseUtil firebaseUtil;

    public void write(ChattingMessage cm) {
        firebaseUtil.initializeApp();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("chatting");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                log.info(document);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        log.info("write start!");
        DatabaseReference userRef = ref.child("/").child(cm.getSchoolName()).child(cm.getClassName());
        Map<String, Object> map = new HashMap<>();
//
//        ChattingMessage chattingMessage = ChattingMessage.builder()
//                .message(object.getString("message"))
//                .messageImagePath(object.getString("messageImagePath"))
//                .messageType(object.getInt("messageType"))
//                .memberEmail(object.getString("memberEmail"))
//                .nickname(object.getString("nickname"))
//                .profileImage(object.getString("profileImage"))
//                .transferTime(new Date().getTime())
//                .build();

        log.info("chattingMessage = " + cm);

        DatabaseReference newUserRef = userRef.push();
        map.put(newUserRef.getKey(), cm);
        userRef.updateChildrenAsync(map);
    }
}
