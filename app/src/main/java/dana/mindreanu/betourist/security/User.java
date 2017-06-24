package dana.mindreanu.betourist.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User
{
    private int userId, serverUserId;
    private String userName;
    private UserType type;
    private List<String> associatedUsers;
    private String parentUser;
    private String cookieVal;
    private int isGcmRegistered;

    public User(){
        associatedUsers = new ArrayList<String>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public List<String> getAssociatedUsers() {
        return associatedUsers;
    }


    public String getParentUser() {
        return parentUser;
    }

    public void setParentUser(String parentUserEmail) {
        this.parentUser = parentUserEmail;
    }

    public void setAssociatedUsersString(String str){
        associatedUsers = new ArrayList<String>(Arrays.asList(str.split(",")));
        if(associatedUsers.size() == 1 && associatedUsers.get(0).length() == 0){
            associatedUsers.clear();
        }
    }

    public String getCookieVal() {
        return cookieVal;
    }

    public void setCookieVal(String cookieVal) {
        this.cookieVal = cookieVal;
    }

    public int getIsGcmRegistered() {
        return isGcmRegistered;
    }

    public void setIsGcmRegistered(int isGcmRegistered) {
        this.isGcmRegistered = isGcmRegistered;
    }

    public int getServerUserId() {
        return serverUserId;
    }

    public void setServerUserId(int serverUserId) {
        this.serverUserId = serverUserId;
    }

    public void addAssociatedUser(String guardian) {
        getAssociatedUsers().add(guardian);
    }

    public String getAssociatedUsersString() {
        StringBuilder builder = new StringBuilder();

        for(String email : associatedUsers){
            builder.append(email).append(',');
        }
        if(builder.length() > 0)
            builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
