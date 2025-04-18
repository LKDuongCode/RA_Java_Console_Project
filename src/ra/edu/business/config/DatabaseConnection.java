package ra.edu.business.config;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    public static Connection connectToDatabase (){
        Connection c = null;

        try{
            //lấy thông tin db
            Properties p = DatabaseConfig.load();
            if(p == null){
                throw new IllegalArgumentException("không load được db.");
            }

            String url = p.getProperty("db.url");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");

            c = DriverManager.getConnection(url,username,password);
        }
        catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
        catch (SQLException e){
            System.err.println("lỗi kết nối đến database.");
        }
        catch (Exception e) {
            System.err.println("lỗi bất định " + e.getMessage());
        }

        return c;
    }

    public static void close (Connection c){
        try {
            if(c!= null && !c.isClosed()) c.close();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        catch (Exception e){
            System.err.println("lỗi đóng connection.");
        }
    }

    public static void close (CallableStatement c){
        try {
            if(c != null && c.isClosed()) c.close();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        catch (Exception e){
            System.err.println("lỗi đóng callableStatement");
        }
    }
}