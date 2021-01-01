package exception;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExceptionMessage {
    private Map<String, Object> hashMap = new HashMap<>();

    public void addMessage(Exception e) {
        hashMap.put("msg", e.getMessage());
        hashMap.put("class", e.getClass().getName());
    }

    @Override
    public String toString() {
        return hashMap.toString();
    }
}
