package com.tutorial;

import java.io.FileOutputStream;
import java.sql.*; 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


public class PdfKaydetme{ 
	public String PdfKaydet() throws Exception
    {
    	Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adres","root","12345");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT Isim, Adres, Sehir, Telefon FROM adresler");
        Document address_report = new Document();
        PdfWriter.getInstance(address_report, new FileOutputStream("Adres_Listesi.pdf"));
        address_report.open();            
        PdfPTable adres_tablosu = new PdfPTable(4);
        PdfPCell table_cell;
               
        while (result.next()) {                
        	String name = result.getString("Isim");
            table_cell=new PdfPCell(new Phrase(name));
            adres_tablosu.addCell(table_cell);
            String address=result.getString("Adres");
            table_cell=new PdfPCell(new Phrase(address));
            adres_tablosu.addCell(table_cell);
            String city=result.getString("Sehir");
            table_cell=new PdfPCell(new Phrase(city));
            adres_tablosu.addCell(table_cell);
            String tel=result.getString("Telefon");
            table_cell=new PdfPCell(new Phrase(tel));
            adres_tablosu.addCell(table_cell);
        }
        address_report.add(adres_tablosu);                       
        address_report.close();
                
        result.close();
        statement.close(); 
        connection.close();  
        return null;
    }
}