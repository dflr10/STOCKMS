
package Model;

import Controller.HomeController;
import View.Home;
import View.LoginUI;

/**
 * 
 * @author Daniel Felipe Lozada Ramirez Dev <felipe_lozada04102@elpoli.edu.co>
 */
public class StockMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Home h=new Home();
        LoginUI login= new LoginUI();
        HomeController hc= new HomeController(h);
    }
    
}
