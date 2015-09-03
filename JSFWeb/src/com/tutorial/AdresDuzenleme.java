package com.tutorial;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import javax.faces.context.*;

@ManagedBean
@RequestScoped
@ViewScoped
public class AdresDuzenleme implements Serializable {
	
	Integer id;
	String name="";
	String address="";
	String city="";
	Integer tel;
	boolean editable=true;
	
	public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public boolean isEditable() {
        return editable;
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
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
    
    private static final long serialVersionUID = 1L;
    List<AdresDuzenleme> liste=new ArrayList<AdresDuzenleme>();
    Connection con;
    
    public List<AdresDuzenleme> getListe()
    {
        return liste;
    }
 
    public String adresCek()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/adres","root","12345");
            String sql="SELECT * FROM adresler";
            PreparedStatement state=con.prepareStatement(sql);
            ResultSet result=state.executeQuery();
            liste=new ArrayList<AdresDuzenleme>();
            while(result.next())
            {
            	AdresDuzenleme veri=new AdresDuzenleme();
            	veri.setId(result.getInt("Id"));
            	veri.setName(result.getString("Isim"));
            	veri.setAddress(result.getString("Adres"));
            	veri.setCity(result.getString("Sehir"));
            	veri.setTel(result.getInt("Telefon"));
            	veri.setEditable(true);
            	
            	liste.add(veri);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
       return "edit";
    }
    
    public String adresGuncelle()
    {
        try{
        	Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/adres","root","12345");
            PreparedStatement state=con.prepareStatement("UPDATE adresler SET Isim=?,Adres=?,Sehir=?,Telefon=? WHERE Id=?");
        	for(AdresDuzenleme item : liste)
        	{
        		state.setString(1, item.name);
            	state.setString(2, item.address);
            	state.setString(3, item.city);
            	state.setInt(4, item.tel);
            	state.setInt(5, item.id);
                state.executeUpdate();
        	}
        }
        catch(Exception e)
        {
            System.err.print(e);
        }
        return "listed";
    }
    
    private int ID;
    public String adresSil(){
    	PreparedStatement state;
		FacesContext fc = FacesContext.getCurrentInstance();
		this.ID = Integer.parseInt(IDParametresiniAl(fc));
                try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/adres","root","12345");
                state=con.prepareStatement("DELETE FROM adresler WHERE ID=?");
                state.setInt(1, ID);
                state.executeUpdate();
                }
                catch(Exception e)
                {
                    System.out.print(e);
                }
                
                    return "listed";
	}
    public String IDParametresiniAl(FacesContext fc)
    {
        Map<String,String> parametreler = fc.getExternalContext().getRequestParameterMap();
		return parametreler.get("ID");
    }
    
}