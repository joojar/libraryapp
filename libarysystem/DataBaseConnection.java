/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package libarysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DataBaseConnection 
{
    static Connection conn;
    static Statement stm;
    ResultSet result;
    public static void dbConnection() throws SQLException
    {
        String dbName="jdbc:mysql://localhost:3306/libusers";
        String username="root";
        String password="";
        
        conn=DriverManager.getConnection(dbName,username,password);
        stm=conn.createStatement();
    }
    public void addUsers(Users users) throws SQLException
    {
        dbConnection();
        
        String sql="insert into users(Name,Password,Address,Gender)"
        + " values('"+users.getName()+"','"+users.getPass()+"','"+users.getAddress()+"','"+users.getGender()+"')";
        
        int i=stm.executeUpdate(sql);
        if(i>0)
            System.out.println( "Successful");
        
        conn.close();
    }
    public String[][] viewAllUsers() throws SQLException
    {
        dbConnection();
        
        ArrayList<Users> list=new ArrayList<Users>();
        String query="Select * from users";
        result=stm.executeQuery(query);
        
        while(result.next())
        {
            Users users=new Users();
            users.setName(result.getString(2));
            users.setPass(result.getInt(3));
            users.setAddress(result.getString(4));
            users.setGender(result.getString(5));
            list.add(users);
            
        }
        String[][] data=new String[list.size()][4];
        for(int i=0;i<data.length;i++)
        {
            Users users=list.get(i);
            data[i][0]=users.getName();
            data[i][1]=String.valueOf(users.getPass());
            data[i][2]=users.getAddress();
            data[i][3]=users.getGender();
            
        }
        conn.close();
        return data;
            
    }
    public String[][] viewUsersByGenders(String gender) throws SQLException
    {
        dbConnection();
        ArrayList<Users> list=new ArrayList<Users>();
        String query="Select * from users where gender='"+gender+"'";
        result=stm.executeQuery(query);
        
        while(result.next())
        {
            Users users=new Users();
            users.setName(result.getString(2));
            users.setPass(result.getInt(3));
            users.setAddress(result.getString(4));
            users.setGender(result.getString(5));
        }
        String[][] data=new String[list.size()][4];
        for(int i=0;i<data.length;i++)
        {
            Users users=list.get(i);
            
            data[i][0]=users.getName();
            data[i][1]=String.valueOf(users.getPass());
            data[i][2]=users.getAddress();
            data[i][3]=users.getGender();
        }
        conn.close();
        return data;
    }
    public String[] getAllID() throws SQLException
    {
        dbConnection();
        ArrayList<String> list=new ArrayList<String>();
        String query="Select id from users";
        result=stm.executeQuery(query);
        while(result.next())
        {
            list.add(result.getString(1));
            
        }
        String[] id=list.toArray(new String[list.size()]);
			conn.close();
			return id;
        
    }
    public void deleteUsers(int id) throws SQLException
    {
        dbConnection();
        String query="Delete from users where id='"+id+"'";
        int i=stm.executeUpdate(query);
        if(i>0)
            System.out.println("Delete Successful");
        conn.close();
    }
    public void getBooks(Books books) throws SQLException
    {
        dbConnection();
        String sql="insert into books(Categories,Name,Author,Publisher,Value)"
        + " values('"+books.getCategorie()+"','"+books.getName()+"','"+books.getAuthor()+"','"+books.getPublisher()+"','"+books.getValue()+"')";
        int i=stm.executeUpdate(sql);
        if(i>0)
            System.out.println("Insert Successful");
        conn.close();
    }
    public String[] getBooksID() throws SQLException
    {
        dbConnection();
        ArrayList<String> list=new ArrayList<String>();
        String query="Select id from books";
        result=stm.executeQuery(query);
        while(result.next())
        {
            list.add(result.getString(1));
            
        }
        String[] id=list.toArray(new String[list.size()]);
			conn.close();
			return id;
        
    }
    
	public void updateBooks(Books books) throws ClassNotFoundException, SQLException
	{
		dbConnection();
                System.out.println(books.getValue()+"  "+books.getId());
		String sql="Update books set  Value='"+books.getValue()+"'"
                        + " where id="+books.getId()+"";
		int i=stm.executeUpdate(sql);
		if(i>0)
			System.out.println("Update Successful");
		conn.close();
	}public String[][] getAllBooks() throws ClassNotFoundException, SQLException
	{
		dbConnection();
		ArrayList<Books>  list=new ArrayList<Books>();
		String query="select * from books";
		result=stm.executeQuery(query);
		while(result.next())
		{
			Books books=new Books();
			books.setId(result.getInt(1));
			books.setCategorie(result.getString(2));
			books.setName(result.getString(3));
			books.setAuthor(result.getString(4));
			books.setPublisher(result.getString(5));
			books.setValue(result.getInt(6));
                        books.setPdf(result.getString(7));
			list.add(books);
		}
		String[][] data=new String[list.size()][7];
		for(int i=0;i<list.size();i++)
		{
			Books books =list.get(i);
			data[i][0]=String.valueOf(books.getId());
			data[i][1]=books.getCategorie();
			data[i][2]=books.getName();
			data[i][3]=books.getAuthor();
			data[i][4]=books.getPublisher();
			data[i][5]=String.valueOf(books.getValue());
                        data[i][6]=books.getPdf();
		}
		conn.close();
		return data;
	}
        public String[][] getBooksbyC(String categories) throws ClassNotFoundException, SQLException
	{
		dbConnection();
		ArrayList<Books>  list=new ArrayList<Books>();
		String query="select * from books where Categories='"+categories+"'";
		result=stm.executeQuery(query);
		while(result.next())
		{
			
			Books books=new Books();
			books.setId(result.getInt(1));
			books.setCategorie(result.getString(2));
			books.setName(result.getString(4));
			books.setAuthor(result.getString(5));
			books.setPublisher(result.getString(6));
			books.setValue(result.getInt(7));
                        books.setPdf(result.getString(8));
			list.add(books);
		}
		String[][] data=new String[list.size()][7];
		for(int i=0;i<list.size();i++)
		{
			Books books =list.get(i);
			data[i][0]=String.valueOf(books.getId());
			data[i][1]=books.getCategorie();
			data[i][2]=books.getName();
			data[i][3]=books.getAuthor();
			data[i][4]=books.getPublisher();
			data[i][5]=String.valueOf(books.getValue());
                        data[i][6]=books.getPdf();
		}
        
		conn.close();
		return data;
	    }
    
}