package javadb;

import java.sql.*;

public class databaseTestClass {
    private static Connection connection;
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        System.out.println("Driver loaded");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/grent", "root", "");
        connection.setAutoCommit(true);


        //prepared statement - select query
        PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM registereduser WHERE gender = ?");
        pstmt.setString(1,"Male");
        ResultSet resultSet1 = pstmt.executeQuery();
        displayResultSet(resultSet1);

        //prepared statement - insert query
        PreparedStatement pstmt2 = connection.prepareStatement(
                "INSERT INTO ");
        pstmt.setString(1,"Male");
        ResultSet resultSet3 = pstmt2.executeQuery();
        displayResultSet(resultSet1);

        //statament - select query
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet2 = statement.executeQuery("Select * FROM registereduser WHERE gender = 'Male' ");
        displayResultSet(resultSet2);





    }

    private static void displayResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        resultSet.beforeFirst();
        while (resultSet.next()) {
            for (int i = 1; i <= rsMetaData.getColumnCount(); i++)
                System.out.print(resultSet.getObject(i)+" ");
            System.out.println();
        }
    }
}
