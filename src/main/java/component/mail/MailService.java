package component.mail;

public interface MailService {
    boolean sendToMemberToFindPassword(String subject,String text, String from, String to);
}
