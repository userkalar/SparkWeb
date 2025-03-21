package sparkweb.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class JdbcUtil {
    private static DataSource dataSource;
    static {
        InputStream is =JdbcUtil
                .class
                .getClassLoader()
                .getResourceAsStream("db.properties");
        Properties p =new Properties();
        try {
            p.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static DataSource getDataSource(){
        return dataSource;
    }

}
