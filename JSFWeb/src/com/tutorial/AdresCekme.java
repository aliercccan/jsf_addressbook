package com.tutorial;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;
import java.util.*;

@ManagedBean
@RequestScoped
public class AdresCekme {
	Integer id=0;
	String name="";
	String address="";
	String city="";
	Integer tel;    
	
	public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
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
    
    public List<AdresCekme> getListe() throws ClassNotFoundException, SQLException
    {	
    	PreparedStatement state=null;
        Connection con=null;
        ResultSet result=null;
        
    	Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/adres","root","12345");
        String sql="SELECT * FROM adresler";
        state = con.prepareStatement(sql);
        result = state.executeQuery();
        List<AdresCekme> liste = new ArrayList<AdresCekme>();
        while(result.next())
            {
        		AdresCekme veri=new AdresCekme();
        		
        		veri.setId(result.getInt("Id"));
            	veri.setName(result.getString("Isim"));
            	veri.setAddress(result.getString("Adres"));
            	veri.setCity(result.getString("Sehir"));
            	veri.setTel(result.getInt("Telefon"));
            	
                liste.add(veri);
            }
    	return liste;
    }
}    