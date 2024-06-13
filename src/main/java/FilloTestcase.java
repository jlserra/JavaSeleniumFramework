
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import utilities.ConfigUtilities;

public class FilloTestcase {

    public static void main(String[] args) {

//        <dependency>
//            <groupId>com.codoid.products</groupId>
//            <artifactId>fillo</artifactId>
//            <version>1.22</version>
//        </dependency>

        String query = "SELECT * FROM Fillo where ID = '5800'";
        String filePath = ConfigUtilities.resourceDirectory + "/Testdata.xlsx";

        try {
            Fillo fillo = new Fillo();
            Connection connection = fillo.getConnection(filePath);
            Recordset recordset = connection.executeQuery(query);
            while (recordset.next()) {
                System.out.println(recordset.getField("ID") + " " + recordset.getField("Name") + " " + recordset.getField("Age"));
            }

            String updateQuery=" Update Fillo Set Name='UpdatedFillo' where ID='5800' ";
            connection.executeUpdate(updateQuery);
//
            String insertQuery="INSERT INTO Fillo(ID,Name,Age) VALUES(5801,'Mark','69')";
            connection.executeUpdate(insertQuery);

            recordset.close();
            connection.close();

        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

