package grataintern.firstproject.entities;
import grataintern.firstproject.services.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class SqlConnection {
    public void convertListToTable(Users all_users)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/";
            String login = "postgres";
            String password = "1111";
            Connection con = DriverManager.getConnection(url, login, password);
            Statement stmt = con.createStatement();
            for(User u : all_users.users)
            {
                String userinfo = u.getInfo();
                String strr = String.format("INSERT INTO test.\"User\" (username, userpassword, firstname, lastname, birthdate) VALUES %s", userinfo);
                System.out.println(userinfo);
                //System.out.println(strr);
                //stmt.executeUpdate(str);
            }
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("ОШИБКА! Неизвестная ошибка!\n");
        }
    }
    /*public void connectToDatabase()
    {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/";
            String login = "postgres";
            String password = "1111";
            try
            {
                Connection con = DriverManager.getConnection(url, login, password);
                con.close();
            }
            catch(SQLException e)
            {
                String ss = e.getSQLState();
                //if(ss.equals())
                {

                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }*/

    public void testDatabase(User user) {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/";
            String login = "postgres";
            String password = "1111";
            Connection con = DriverManager.getConnection(url, login, password);
            try
            {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO test.\"User\"(Username, UserPassword, FirstName, LastName, BirthDate) VALUES ('" +  user.getUsername() + "', '" +  user.getPassword() + "', '" +  user.getFirstname() + "', '" +  user.getLastname() + "', '" +  user.getBirthDate() + "')");
                ResultSet rs = stmt.executeQuery("SELECT * FROM test.\"User\"");
                while (rs.next()) {
                    String str = rs.getString("username") + ":" + rs.getString(2) + ":" + rs.getString(3) + ":" + rs.getString(4);
                    System.out.println("User info: " + str);
                }
                rs.close();
                stmt.close();
            }
            finally
            {
                con.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
