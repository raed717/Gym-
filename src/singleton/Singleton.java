/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author saisi
 */
public class Singleton {
     String url ="jdbc:mysql://localhost:3306/geeks";
     String login ="root";
     String pwd="";
     Connection  connexion;
     private static Singleton instance;

    
   private Singleton(){
     
        
        try {
            connexion =  DriverManager.getConnection(url, login, pwd);
          
           if (connexion != null) {
        System.out.println("Connected");}
          
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
  public static Singleton getInstance(){
    if (instance == null)
        instance = new Singleton();
    return instance;
    }

    public Connection getConnexion() {
        return connexion;
    }
    
   }