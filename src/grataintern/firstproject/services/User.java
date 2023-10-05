package grataintern.firstproject.services;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class User
{
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String birth_date;

    public User(){}

    public User(User buff_user)
    {
        this.username = buff_user.getUsername();
        this.firstname = buff_user.getFirstname();
        this.lastname = buff_user.getLastname();
        this.password = buff_user.getPassword();
        this.birth_date = buff_user.getBirthDate();
    }

    public User(String username, String password,String firstname, String lastname, String birth_date)
    {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth_date = birth_date;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public String getBirthDate()
    {
        return birth_date;
    }
    public String getInfo()
    {
        return String.format("('%s', '%s', '%s', '%s', '%s')", username, password, firstname, lastname, birth_date);
    }


    public boolean checkPassword(String pass)
    {
        if(password.equals(pass))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean changePassword(String old_pass, String new_pass)
    {
        if(checkPassword(old_pass))
        {
            long int_count = new_pass.codePoints().filter(Character::isDigit).count();
            long str_count = new_pass.length() - int_count;
            if(int_count >= 2 && str_count >= 2)
            {
                password = new_pass;
                System.out.println("Пароль заменён.");
                return true;
            }
            else
            {
                System.out.println("Новый пароль не соответсвует требованием.");
                return false;
            }
        }
        else
        {
            System.out.println("Старый пароль введен неверно!");
            return false;
        }
    }

    public boolean changeFirstname(String new_firstname)
    {
        if(new_firstname.length() >= 2)
        {
            firstname = new_firstname;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean changeLastname(String new_lastname)
    {
        if(new_lastname.length() >= 2)
        {
            lastname = new_lastname;
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean changeBirthDate(String new_birth_date) throws Exception
    {
        Date current_date = Calendar.getInstance().getTime();
        Date new_date = new SimpleDateFormat("yyyy-MM-dd").parse(new_birth_date);
        if(current_date.getTime() > new_date.getTime())
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(new_date);
            birth_date = strDate;
            return true;
        }
        else return false;
    }
}
