package grataintern.firstproject.entities;
import grataintern.firstproject.services.*;
import java.util.Scanner;
import java.io.IOException;

public class UserService
{
    public User printLoginWindow(Users usrs)
    {
        String login, pass;
        User buff_user;
        System.out.println("Введите логин:");
        Scanner in = new Scanner(System.in);
        login = in.nextLine();
        buff_user = usrs.findUser(login);
        if(buff_user != null)
        {
            int cnt = 0;
            while(true)
            {
                if(cnt == 3)
                {
                    System.out.println("Неуспешная попытка авторизации.");
                    return null;
                }
                System.out.println("Введите пароль:");
                pass = in.nextLine();
                if(buff_user.checkPassword(pass))
                {
                    System.out.println("Успешная авторизация.");
                    System.out.println("Имя: " + buff_user.getFirstname() + "\nФамилия: " + buff_user.getLastname() + "\nДата рождения: " + buff_user.getBirthDate());
                    return buff_user;
                }
                else
                {
                    System.out.println("ОШИБКА! Неправильный пароль, повторите попытку");
                    cnt += 1;
                }
            }
        }
        else
        {
            System.out.println("Пользователя с таким username не существует.");
            return null;
        }
    }
    public void printUserInfo(User user)
    {
        System.out.println("Имя: " + user.getFirstname() + "\nФамилия: " + user.getLastname() + "\nДата рождения: " + user.getBirthDate());
    }

    public void Run() throws Exception
    {
        Users all_users;
        User current_user;
        CSVParser parser = new CSVParser();
        Scanner in = new Scanner(System.in);
        boolean checker = true;
        while(true)
        {
            System.out.println("Укажите путь к файлу:");
            String dir = in.nextLine();
            all_users = parser.parse(dir);
            if(all_users != null)
            {
                while(true)
                {
                    current_user = printLoginWindow(all_users);
                    checker = true;
                    if(current_user != null)
                    {
                        while(true)
                        {
                            if(checker)
                            {
                                System.out.println("Выберите действие:\n1. Изменить данные о пользователе\n2. Выйти");
                                int answer = in.nextInt();
                                if(answer == 1)
                                {
                                    System.out.println("Выберите поле для изменений:\n1. Имя\n2. Фамилия\n3. Год рождения\n4. Пароль\n5. Выйти");
                                    answer = in.nextInt();
                                    if(answer == 1)
                                    {
                                        System.out.println("Введите новое имя:");
                                        in.nextLine();
                                        String new_name = in.nextLine();
                                        if(!current_user.changeFirstname(new_name))
                                        {
                                            System.out.println("Некорректно введены данные");
                                        }
                                        else
                                        {
                                            System.out.println("Данные успешно заменены.");
                                        }
                                    }
                                    else if(answer == 2)
                                    {
                                        System.out.println("Введите новую фамилию:");
                                        in.nextLine();
                                        String new_lastname = in.nextLine();
                                        if(!current_user.changeLastname(new_lastname))
                                        {
                                            System.out.println("Некорректно введены данные");
                                        }
                                        else
                                        {
                                            System.out.println("Данные успешно заменены.");
                                        }
                                    }
                                    else if(answer == 4)
                                    {
                                        System.out.println("Введите старый пароль:");
                                        in.nextLine();
                                        String old_pass = in.nextLine();
                                        System.out.println("Введите новый пароль:");
                                        String new_pass = in.nextLine();
                                        if(!current_user.changePassword(old_pass, new_pass))
                                        {
                                            System.out.println("Некорректно введены данные");
                                        }
                                        else
                                        {
                                            System.out.println("Данные успешно заменены.");
                                        }

                                    }
                                    else if(answer == 3)
                                    {
                                        System.out.println("Введите новую дату рождения:");
                                        in.nextLine();
                                        String new_birth_date = in.nextLine();
                                        if(!current_user.changeBirthDate(new_birth_date))
                                        {
                                            System.out.println("Некорректно введены данные");
                                        }
                                        else
                                        {
                                            System.out.println("Данные успешно заменены.");
                                        }
                                    }
                                    else if(answer == 5)
                                    {
                                        checker = false;
                                        break;
                                    }
                                    printUserInfo(current_user);
                                }
                                else if(answer == 2)
                                {
                                    checker = false;
                                    break;
                                }
                                else
                                {
                                    System.out.println("ОШИБКА! Некорректно выбрано число.");
                                }
                            }
                            else break;
                        }
                    }
                }
            }
        }
    }
}
