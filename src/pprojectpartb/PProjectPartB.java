package pprojectpartb;


import dao.UsersDao;
import java.util.Scanner;

public class PProjectPartB {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Helper.login(sc);
        Menu.mainMenu(sc);
        sc.close();

//        System.out.println("Life goes on");
    }

}
