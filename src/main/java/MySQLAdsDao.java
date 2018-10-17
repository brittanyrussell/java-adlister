import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.jdbc.Driver;


public class MySQLAdsDao implements Ads{

Connection connection;
Config config = new Config();

    public MySQLAdsDao() {
        try{
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUsername(),
                    config.getPassword()
            );
        }catch (SQLException e){

        }
    }


    @Override
    public List<Ad> all() {
        String query = "SELECT * FROM ads";
        List<Ad> ads = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                ads.add(createAdObj(rs)
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ads;
    }


    public Ad createAdObj(ResultSet rs) {
        try {
            return new Ad(rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long insert(Ad ad) {
        System.out.println("get here");
        String query = String.format("insert into ads(user_id, title, description) values('%s', '%s','%s')", ad.getUserId(), ad.getTitle(), ad.getDescription());
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate( query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("Inserted a new record! New id: " + rs.getLong(1));
                return rs.getLong(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


        return null;
    }
}

