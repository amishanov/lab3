package com.company;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzaSystem {
    public static void main(String[] args) {
        ArrayList<User> users=new ArrayList<>();
        Map map=new Map();
        User currentUser=new User();
        String currentLocation="";
        Client a=new Client("Alex","A","1234","Москва");
        Client b=new Client("Ivan","I","1234","Подольск");
        users.add(a);users.add(b);
        System.out.println(findUser(users,"A","1234").getPlace());
        map.addPath("Подольск","Москва",10);map.addPath("Подольск","Климовск",2);map.addPath("Климовск","Москва",7);
        map.addPath("Москва","A",2); map.addPath("B","A",2);
        Scanner in=new Scanner(System.in);
        System.out.println("1.Добавить пользователя"+'\n'+"2.Добавить путь"+'\n'+"3.Войти в систему"+'\n'+"4.Ввести место курьера"+'\n'+"5.Оформить заказ(получить путь и время)");
        int n=0;
        String str;
        while (n!=8){
            System.out.println("Выберите действие");
            n=in.nextInt();
            switch(n){
                case 1:
                    System.out.println("Введите тип (1-админ и ничего не делает)"); int t=in.nextInt(); in.nextLine();
                    System.out.println("Введите имя"); String s1=in.nextLine();
                    System.out.println("Введите логин"); String s2=in.nextLine();
                    System.out.println("Введите пароль"); String s3=in.nextLine();
                    if (t!=1)
                    {System.out.println("Введите место"); String s4=in.nextLine(); users=addClient(users,s1,s2,s3,s4);}
                    else
                        users=addUser(users,s1,s2,s3,s3);
                    break;
                case 2:
                    System.out.println("Введите первое место"); in.nextLine(); String place1=in.nextLine();
                    System.out.println("Введите второе место"); String place2=in.nextLine();
                    System.out.println("Введите время");int time=in.nextInt();
                    map.addPath(place1,place2,time);
                    break;
                case 3:
                    System.out.println("Введите логин"); in.nextLine();String login=in.nextLine();
                    System.out.println("Введите пароль"); String password=in.nextLine();
                    if (findUser(users,login,password)!=null){
                        currentUser=findUser(users,login,password);
                        System.out.println("Вы вошли в систему как "+currentUser.getLogin());
                        if (currentUser.getPlace()!=null)
                            System.out.println("Ваше местоположение: "+currentUser.getPlace());
                    }
                    else System.out.println("Неверные данные");
                    break;
                case 4:
                    System.out.println("Введите текущую локацию");in.nextLine(); String loc=in.nextLine();
                    currentLocation=loc; break;
                case 5:
                    if (currentUser==null || currentLocation=="" || currentUser.getPlace()==null){
                        System.out.println("Необходимо войти в систему и ввести текущее место курьера"); break;
                    }
                    System.out.println(map.genShortWay(currentLocation,currentUser.getPlace()).toString());
            }

        }
    }

    public static ArrayList<User> addUser(ArrayList<User>users,String name, String login, String password, String rep){
        users.add(new User(name,login,password));
        return(users);
    }
    public static ArrayList<User> addClient(ArrayList<User>users, String name, String login, String password, String place){
        users.add(new Client(name,login,password,place));
        return(users);
    }
    public static User findUser(ArrayList<User>users,String login, String password){
        for (User i: users
             ) {
            String s1=i.getLogin(); String s2=i.getPassword();
        if ((s1.equals(login)) && (s2.equals(password)))
            return i;
        }
        return null;
    }

}
