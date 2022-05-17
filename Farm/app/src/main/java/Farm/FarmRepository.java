package Farm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class FarmRepository {
    private static final String DWELLING_TABLE_SQL = "CREATE TABLE DWELLING(ID BIGINT AUTO_INCREMENT, " +
        "NAME VARCHAR(255), CLASSTYPE INT DEFAULT 0)";
    private static final String CONNECTION_STRING_FILE = "jdbc:h2:~/farm_db"; 
    
    public static boolean createDwellingTable(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONNECTION_STRING_FILE);
            var stmt = con.createStatement();
            stmt.execute("DROP TABLE IF EXISTS DWELLING");
            return stmt.execute(DWELLING_TABLE_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            }catch(Exception ignore){System.err.println(ignore);}
        }
        return false;
    }    

    public void createDwellingRecord(Dwelling dwelling) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONNECTION_STRING_FILE);
            var stmt = con.prepareStatement("INSERT INTO DWELLING(NAME, CLASSTYPE) VALUES(?,?)");
            stmt.setString(1, dwelling.getName());
            if(dwelling instanceof House) {
                stmt.setInt(2, 1); // Use int value of 1 to represent a House class   
            }
            else if(dwelling instanceof Stable) {
                stmt.setInt(2, 2); // Use int value of 2 to represent a Stable class
            }
            stmt.execute(); // Write to database

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close(); // Just try to close the connection
            }catch(Exception ignore){}
        }  
    }

    public Dwelling findDwellingById(int id) {
        Connection con = null;
        Dwelling dwelling = null;
        try {
            con = DriverManager.getConnection(CONNECTION_STRING_FILE);
            var stmt = con.prepareStatement("SELECT ID, NAME, CLASSTYPE FROM DWELLING WHERE ID = ?");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if(rs.next()) {
                String dwellingName = rs.getString(2);
                int classType = rs.getInt(3);

                if(classType == 1) { // Should be a house
                    dwelling = new House(dwellingName);
                }
                else if(classType == 2) { // Should be a stable
                    dwelling = new Stable(dwellingName);
                }
            }
        } catch(Exception e) {

        } finally {
            try {
                con.close(); // Just try to close the connection
            } catch(Exception ignore){}
        }
        return dwelling;
    }

    public void loadRepository(Farm farm) {
        Connection con = null;
        System.out.println("Load database");
        try {
            con = DriverManager.getConnection(CONNECTION_STRING_FILE);
            var stmt = con.prepareStatement("SELECT * FROM DWELLING");
            var rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.print("Id: " + rs.getInt(1) + ", ");
                System.out.print("Name: " + rs.getString(2) + ", ");
                System.out.println("Type: " + rs.getInt(3));

                Dwelling dwelling = null;
                if(rs.getInt(3) == 1) {
                    dwelling = new House(rs.getString(2));
                }
                else if(rs.getInt(3) == 2) {
                    dwelling = new Stable(rs.getString(2)); 
                }
                farm.add(dwelling);
            }
        } catch(Exception ignore) {}       
    }

    public void saveRepository(Farm farm) {
        HashMap<String, Dwelling> map = farm.getMap();
        createDwellingTable();
        
        for(HashMap.Entry<String, Dwelling> entry : map.entrySet()) {
            Dwelling value = entry.getValue();
            this.createDwellingRecord(value);
        }      
    }
}
