/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class SqlConection {
 
        static String daneZBazy;
        /**
         * @param args
         */
        public static void main(String[] args) {
               
                String polaczenieURL = "jdbc:mysql://192.168.101.54/edenland_adi?user=edenland_adi&password=edenland73";
                //Tworzymy proste zapytanie doa bazy danych
                String query = "Select * FROM pracownicy";
               
                Connection conn = null;
               
                try {
 
                        //Ustawiamy dane dotyczące podłączenia
                        conn = DriverManager.getConnection(polaczenieURL);
                       
                        //Ustawiamy sterownik MySQL
                        Class.forName("com.mysql.jdbc.Driver");
                       
                        //Uruchamiamy zapytanie do bazy danych
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
       
                        while (rs.next()) {
                                wyswietlDaneZBazy(rs);
                        }
       
                        conn.close();
                }
                //Wyrzuć wyjątki jężeli nastąpią błędy z podłączeniem do bazy danych lub blędy zapytania o dane
                catch(ClassNotFoundException wyjatek) {
                        System.out.println("Problem ze sterownikiem");
                }
 
                catch(SQLException wyjatek) {
                        //e.printStackTrace();
                        //System.out.println("Problem z logowaniem\nProsze sprawdzic:\n nazwę użytkownika, hasło, nazwę bazy danych lub adres IP serwera");
                        System.out.println("SQLException: " + wyjatek.getMessage());
                    System.out.println("SQLState: " + wyjatek.getSQLState());
                    System.out.println("VendorError: " + wyjatek.getErrorCode());
                }
 
        }
        static void wyswietlDaneZBazy(ResultSet rs){
                try{
                daneZBazy = rs.getString(1);
                System.out.println("\n" + daneZBazy + " ");
                daneZBazy = rs.getString(2);
                System.out.println(daneZBazy + " ");
                daneZBazy = rs.getString(3);
                System.out.println(daneZBazy);
                }catch(SQLException e) {
                        e.printStackTrace();
                }
        }
               
}
/**
 *
 * @author Dell
 */
//public class SqlConection {
//    
//}
