package pl.kubie.catalogue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private  Connection connection=null;
    private final String url = "jdbc:mysql://localhost:3306/consolelibrary?autoReconnect=true&useSSL=false";
    private final String user = "root";
    private final String password = "Marceli19";

    //connect to database
    public DAO() {
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
    public void save(FilmsModel model) throws SQLException {
        String insert="insert into console"
                +"(title,year)"
                +"values(?,?)";
        PreparedStatement myPrStm=connection.prepareStatement(insert);
        myPrStm.setString(1,model.getTitle());
        myPrStm.setString(2,model.getYear());
        myPrStm.executeUpdate();
        myPrStm.close();
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
        System.out.println("Record deleted.");
    }

    /*
    Display all records from data base.
     */
    public List<FilmsModel> displayAllRecords() {
            List<FilmsModel>list=new ArrayList<>();
        Statement myStm= null;
        try {
            myStm = connection.createStatement();
            ResultSet resSet=myStm.executeQuery("select * from console");

            while (resSet.next()) {
                FilmsModel tempObject=convertRowToObjectModel(resSet);
                list.add(tempObject);
            }
            resSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private FilmsModel convertRowToObjectModel(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("id");
        String title = myRs.getString("title");
        String year = myRs.getString("year");
        float rating = myRs.getFloat("rating");

        FilmsModel tempObject = new FilmsModel(id, title, year, rating);

        return tempObject;
    }

    public void closeConnetion(){
        try {
            connection.close();
            System.out.println("Connection closed with "+url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }










}
