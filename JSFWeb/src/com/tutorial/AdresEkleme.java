package com.tutorial;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;

@ManagedBean
@RequestScoped
public class AdresEkleme {
	Integer id;
	String name="";
	String address="";
	String city;
	Integer tel;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getCity() {
   		return city;
    }
    public void setCity(String city) {
   		this.city = city;
    }
    
    public Integer getTel() {
        return tel;
    }
    public void setTel(Integer tel) {
        this.tel = tel;
    }
    
    public String saveAddress()
    {
        CallableStatement statement=null;
        Connection connection=null;
        if(city.equals("Sisli")
        		||city.equals("Besiktas")||city.equals("Sariyer")||city.equals("Kagithane")
        		||city.equals("Beyoglu")||city.equals("Fatih")||city.equals("Zeytinburnu")
        		||city.equals("Manisa")||city.equals("Ankara")||city.equals("Izmir")){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/adres","root","12345");
            String sql="INSERT INTO adresler(Isim, Adres, Sehir, Telefon) VALUES(?,?,?,?)";
            statement = connection.prepareCall(sql);
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, city);
            statement.setInt(4, tel);
            statement.execute();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try{
            connection.close();
            statement.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        	return "saved";
        }
        else
        	return null;
    }
    
    public String listele(){
    	id = null;
    	name="";
    	address="";
    	city="";
    	tel=null;
    	return "listed";
    }

}