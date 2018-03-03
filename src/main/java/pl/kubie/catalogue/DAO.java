package pl.kubie.catalogue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DAO {
    private Connection connection = null;
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
    public void save(MovieModel model) throws SQLException {
        String insert = "insert into console"
                + "(title,year)"
                + "values(?,?)";
        PreparedStatement myPrStm = connection.prepareStatement(insert);
        myPrStm.setString(1, model.getTitle());
        myPrStm.setString(2, model.getYear());
        myPrStm.executeUpdate();
        myPrStm.close();
        System.out.println("New record added.");
    }

    /*
    Delete record from database by using the given "id" of record.
     */
    public void deleteRecord1(int id){
        String delete = "delete from console where id="+id;
        try {
            Statement myStm=connection.createStatement();
            myStm.execute(delete);
            myStm.close();
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    Display all records from data base.
     */
    public List<MovieModel> displayAllRecords() {
        List<MovieModel> list = new ArrayList<>();
        Statement myStm;
        try {
            myStm = connection.createStatement();
            ResultSet resSet = myStm.executeQuery("select * from console");
            while (resSet.next()) {
                MovieModel tempObject = convertRowToObjectModel(resSet);
                list.add(tempObject);
            }
            resSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public MovieModel displayById(int id){
        MovieModel movie=new MovieModel();
        String selectId="select * from console where id="+id;
        try {
            Statement myStm=connection.createStatement();
            ResultSet resSet=myStm.executeQuery(selectId);
            while (resSet.next()) {
                movie=convertRowToObjectModel(resSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    private MovieModel convertRowToObjectModel(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("id");
        String title = myRs.getString("title");
        String year = myRs.getString("year");
        float rating = myRs.getFloat("rating");

        return new MovieModel(id, title, year, rating);
    }

    public void closeConnetion() {
        try {
            connection.close();
            System.out.println("Connection closed with " + url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
