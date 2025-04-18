package ra.edu.business.config;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final String FILE_NAME = "database.properties";

    public static Properties load (){
        Properties p = new Properties();

        try(InputStream i = DatabaseConfig.class.getClassLoader().getResourceAsStream(FILE_NAME)){
            if(i == null) throw new IllegalArgumentException("không tìm thấy file cấu hình");
            p.load(i);
        }
        catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
            return null;
        }
        catch (Exception e){
            System.err.println("lỗi đọc file " + e.getMessage());
        }

        return p;
    }

}