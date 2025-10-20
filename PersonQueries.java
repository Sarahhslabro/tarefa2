import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PersonQueries {

    private static final String URL = "jdbc:derby:AddressBook";
    private static final String USERNAME = "deitel";
    private static final String PASSWORD = "deitel";
    private Connection connection = null;
    private PreparedStatement selectAllPeople=null;
    private PreparedStatement selectPeopleByLastName=null;
    private PreparedStatement insertNewPerson=null;

    public PersonQueries() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            selectAllPeople = connection.prepareStatement("SELECT * FROM Addresses");
            selectPeopleByLastName = connection.prepareStatement("SELECT * FROM Addresses WHERE LastName = ?");
            insertNewPerson = connection.prepareStatement("INSERT INTO Addresses"+"(FirstName, LastName, Email, PhoneNumber)"+"VALUES (?, ?, ?, ?)");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    public List< Person > getAllPeople() {
        List< Person > results = null;
        ResultSet resultSet = null;
        try {
            resultSet = selectAllPeople.executeQuery();
            results = new ArrayList<Person>();
            while (resultSet.next()) {
                results.add(new Person(
                        resultSet.getInt("addressID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return results;
    }

    public List<Person> getPeopleByLastName(String name) {
        List<Person> results = null;
        ResultSet resultSet = null;
        try {
            selectPeopleByLastName.setString(1, name);
            resultSet = selectPeopleByLastName.executeQuery();
            results = new ArrayList<Person>();
            while (resultSet.next()) {
                results.add(new Person(
                        resultSet.getInt("addressID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return results;
    }

    public int addPerson(String fname, String lname, String email, String phone) {
        int result = 0;
        try {
            insertNewPerson.setString(1, fname);
            insertNewPerson.setString(2, lname);
            insertNewPerson.setString(3, email);
            insertNewPerson.setString(4, phone);
            result = insertNewPerson.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }

    public void close() {
        try {
           connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}