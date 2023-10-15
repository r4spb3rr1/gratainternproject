package grataintern.firstproject.services;
import java.util.ArrayList;
public class Users
{
    public ArrayList<User> users;
    public Users()
    {
        users = new ArrayList<User>();
    }
    public User findUser(String username)
    {
        for(User s: users)
        {
            if(s.getUsername().equals(username))
            {
                return s;
            }
        }
        return null;
    }
    public ArrayList<User> getUsers()
    {
        return users;
    }

}
