package component.home;

import lombok.Data;

@Data
public class HomeDataDTO {
    private int allUsers;
    private int successUsersBy30Minutes;
    private int usersBy30Minutes;
}
