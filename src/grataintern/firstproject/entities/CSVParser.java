package grataintern.firstproject.entities;
import grataintern.firstproject.services.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVParser
{
    public CSVParser() {}
    Users parse(String directory)
    {
        File myFile = new File(directory);
        Users all_users = new Users();
        try(FileReader fr = new FileReader(myFile))
        {
            int cnt = 0;
            char[] chars = new char[(int) myFile.length()];
            fr.read(chars);

            String buff = "";

            User buff_user;
            String firstname = "", lastname = "", username = "", password = "", birth_date = "";
            for(int i = 0; i < myFile.length(); i++)
            {
                if(chars[i] != '\n')
                {
                    if(chars[i] == ',')
                    {
                        if(cnt == 0)
                        {
                            username = buff;
                        }
                        else if(cnt == 1)
                        {
                            password = buff;
                        }
                        else if (cnt == 2)
                        {
                            firstname = buff;
                        }
                        else if (cnt == 3)
                        {
                            lastname = buff;
                        }
                        cnt += 1;
                        buff = "";
                    }
                    else
                    {
                        buff += chars[i];
                    }
                }
                else if(chars[i] == '\n')
                {
                    birth_date = buff;
                    buff_user = new User(username, password, firstname, lastname, birth_date);
                    all_users.users.add(buff_user);
                    cnt = 0;
                    buff = "";
                }
            }
            SqlConnection sqlConnect = new SqlConnection();
            sqlConnect.convertListToTable(all_users);
            System.out.println("Данные успешно загружены.");
            return all_users;
            /*for (User temp_user : all_users.users) {
                System.out.println(temp_user.getUsername() + " " + temp_user.getPassword() + " " + temp_user.getFirstname() + " " + temp_user.getLastname() + " " + temp_user.getBirthDate());
            }*/

        }
        catch (IOException e) {
            System.out.println("Не правильно выбран путь.");
            return null;
        }
    }
}
