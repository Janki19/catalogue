package pl.kubie.catalogue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private  Connection connection=null;

//    public  static void main (String arg[]) throws SQLException {
//        DAO dao=new DAO();
//        List<ObjectModel> w=dao.displayAllRecords();
//        System.out.println(w);
//
//    }

    public DAO() {
        String url = "jdbc:mysql://localhost:3306/consolelibrary?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "Marceli19";

        //connect to database
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("DataBase connected sucsessful to " + url);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    Insert new record to database by two parameters "title" and "year".
     */
    public void insertNewRecord(String title,String year) throws SQLException {
        String insert="insert into console"
                +"(title,year)"
                +"values(?,?)";
        PreparedStatement myPrStm=connection.prepareStatement(insert);
        myPrStm.setString(1,title);
        myPrStm.setString(2,year);
        myPrStm.executeUpdate();
        myPrStm.close();
        connection.close();;
        System.out.println("New record added.");

    }

    /*
    Delete record from database by using the given "id" of record.
     */
    public void deleteRecord(int id) throws SQLException {
        String delete="delete from console where id=?";
        PreparedStatement myPrStm=connection.prepareStatement(delete);
        myPrStm.setInt(1,id);
        myPrStm.executeUpdate();
        myPrStm.close();
        connection.close();
        System.out.println("Record deleted.");
    }

    /*
    Display all records from data base.
     */
    public List<ObjectModel> displayAllRecords() {
            List<ObjectModel>list=new ArrayList<>();
        Statement myStm= null;
        try {
            myStm = connection.createStatement();
            ResultSet resSet=myStm.executeQuery("select * from console");

            while (resSet.next()) {
                ObjectModel tempObject=convertRowToObjectModel(resSet);
                list.add(tempObject);
            }
            resSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private ObjectModel convertRowToObjectModel(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("id");
        String title = myRs.getString("title");
        String year = myRs.getString("year");
        float rating = myRs.getFloat("rating");

        ObjectModel tempObject = new ObjectModel(id, title, year, rating);

        return tempObject;
    }










}
