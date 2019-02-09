import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Main {
	
	private static final String gurunaviAPI= "45d1cf2d117064b49cf98712b54645ab";
	
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
	
	public static void main(String args[]) {
		
		// get the data from Restaurant APi and put in Db
		
		ArrayList<RestaurantDto> restaurants= getRestaurants();
		System.out.println(restaurants.size());
		try {
			fillRestaurantDb(restaurants);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static ArrayList<RestaurantDto> getRestaurants() {
		// using 
	try {
		int page=1;
		String url= "https://api.gnavi.co.jp/ForeignRestSearchAPI/v3?keyid="+gurunaviAPI+"&category_s=RSFST16005&category_s=RSFST16007&category_s=RSFST16003&lang=en&hit_per_page=100&offset="+page;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new URL(url).openStream());
		
		doc.getDocumentElement().normalize();

		//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("rest");
		//System.out.println(doc);
		ArrayList<RestaurantDto> restaurants= new ArrayList<RestaurantDto>();
		
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				RestaurantDto restaurant= new RestaurantDto();
				Element eElement = (Element) nNode;
				
				restaurant.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
				restaurant.setGurunaviURL(eElement.getElementsByTagName("url").item(0).getTextContent());
				restaurant.setId(eElement.getElementsByTagName("id").item(0).getTextContent());
				restaurant.setArea(eElement.getElementsByTagName("areaname_s").item(0).getTextContent());
				restaurant.setImageURL(eElement.getElementsByTagName("thumbnail").item(0).getTextContent());
				restaurant.setPrefacture(eElement.getElementsByTagName("prefname").item(0).getTextContent());				
				restaurant.setBudget(Integer.parseInt(eElement.getElementsByTagName("budget").item(0).getTextContent()));
				restaurant.setLattitude(Double.parseDouble(eElement.getElementsByTagName("latitude").item(0).getTextContent()));
				restaurant.setLongitude(Double.parseDouble(eElement.getElementsByTagName("longitude").item(0).getTextContent()));
				restaurant.setResttype("Japanese");
				restaurants.add(restaurant);
			}
		}
		
		return restaurants;
	   
	  } catch(Exception e) {
		e.printStackTrace();
		return null;
	}
	
}

  public static void fillRestaurantDb(ArrayList<RestaurantDto> restaurants) throws Exception {
      try {
          // This will load the MySQL driver, each DB has its own driver
          Class.forName("com.mysql.jdbc.Driver");
          // Setup the connection with the DB
          connect = DriverManager
                  .getConnection("jdbc:mysql://localhost:3306/restaurants?"
                          + "user=root&password=lproadmin");

          // Statements allow to issue SQL queries to the database
          statement = connect.createStatement();
          // Result set get the result of the SQL query
//          resultSet = statement
//                  .executeQuery("select * from restaurants.restaurant");
//          writeResultSet(resultSet);

          // PreparedStatements can use variables and are more efficient
          for(RestaurantDto restaurant: restaurants) {
              preparedStatement = connect
                      .prepareStatement("insert into  restaurants.restaurant(id, restaurant, resttype, webpage, image, area, prefacture, budget, latitude, longitude) values (?, ?, ?, ?, ? , ?, ?, ?, ?, ?)ON DUPLICATE KEY UPDATE ID =id");
              // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
              preparedStatement.setString(1, restaurant.getId());
              preparedStatement.setString(2, restaurant.getName());
              preparedStatement.setString(3, restaurant.getResttype());
              preparedStatement.setString(4, restaurant.getGurunaviURL());
              preparedStatement.setString(5, restaurant.getImageURL());
              preparedStatement.setString(6, restaurant.getArea());
              preparedStatement.setString(7, restaurant.getPrefacture());
              preparedStatement.setInt(8, restaurant.getBudget());
              preparedStatement.setDouble(9, restaurant.getLattitude());
              preparedStatement.setDouble(10, restaurant.getLongitude());
              preparedStatement.executeUpdate();
              //System.out.println(restaurant.getResttype());
          }

      } catch (Exception e) {
          throw e;
      } finally {
          close();
      }
        

  }
  
  // You need to close the resultSet
  private static void close() {
      try {
          if (resultSet != null) {
              resultSet.close();
          }

          if (statement != null) {
              statement.close();
          }

      } catch (Exception e) {

      }
  }
  
  

		

}
