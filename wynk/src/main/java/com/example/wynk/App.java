package com.example.wynk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class App 
{
	 public static final String Driver="com.mysql.cj.jdbc.Driver";
	 public static final String Username="root";
	 public static final String Password="root";
	 public static Connection conn;
	 public static PreparedStatement pmst;
    public static void main( String[] args )
    {
      Scanner src=new Scanner(System.in) ;
      int i;
      do {
    	  display();
    	  System.out.println("enter your choice:");
    	  i=Integer.parseInt(src.next());
    	  switch(i) {
    	  case 1:
    		  createdatabase();
    		  break;
    	  case 2:
    		  dropdatabase();
    		  break;
    	  case 3:
    		  createtable();
    		  break;
    	  case 4:
    		  droptable();
    		  break;
    	  case 5:
    		  adddata();
    		  break;
    	  case 6:
    		  editdata();
    		  break;
    	  case 7:
    		  deletedata();
    		  break;
    	  case 8:
    		  getallrecords();
    		  break;
    	  case 9:
    		  getrecordbyemail();
    		  break;
    	  case 10:
    		  System.exit(0);
    		  break;
    		default:
    			System.out.println("invalid operation");
    			break;
    		    
    	  }
		
	} while (i>0);
    	  
    }
 

	private static void getrecordbyemail() {
		Scanner src = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/"+src.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url,Username,Password);
			System.out.println("Enter table name");
			String sql = "select * from "+ src.next()+ " where email = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter employee email");
			pmst.setInt(1, src.nextInt());
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
			System.out.println("id"+ rs.getInt("id")+ 
					"name"+rs.getString("name")+
					"email"+rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private static void getallrecords() {
		Scanner src = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/"+src.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url,Username,Password);
			System.out.println("Enter table name");
			String sql = "select * from "+src.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
			System.out.println("id"+ rs.getInt("id")+ 
					"name"+rs.getString("name")+
					"email"+rs.getString("email"));
			}      
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	private static void deletedata() {
		Scanner src = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/"+src.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter table name");
			String sql = "delete from "+src.next()+ " where id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter id");
			pmst.setInt(1, src.nextInt());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Deleted");
			}
			else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private static void editdata() {
		Scanner src = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/"+src.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter Table name:");
			String sql = "update "+src.next()+" set name = ?,email = ? where id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter name:");
			pmst.setString(1, src.next());
			System.out.println("Enter Email:");
			pmst.setString(2, src.next());
			System.out.println("Enter Id:");
			pmst.setInt(3, src.nextInt());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Updated");
			}
			else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();  
		}		
	}
	private static void adddata() {
		Scanner src=new Scanner(System.in);
		 System.out.println("enter the database name");
		 String url="jdbc:mysql://localhost:3306/" +src.next();
		 try {
			 Class.forName(Driver);
		 conn=DriverManager.getConnection(url, Username, Password);
		 System.out.println("enter table name:"); 		 
		 String sql="insert into "+src.next()+"(id,name,email)values(?,?,?)";
		  pmst=conn.prepareStatement(sql);
		  System.out.println("enter the id");
		  pmst.setInt(1,src.nextInt());
		  System.out.println("enter the name");
		  pmst.setString(2, src.next());
		  System.out.println("enter the email");
		  pmst.setString(3, src.next());
		 int i=pmst.executeUpdate(); 
		 if(i>0) {
			 System.out.println("success");
		 }
		 else {
			 System.out.println("error");
		 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		
	}


	private static void droptable() {
		Scanner src=new Scanner(System.in);
		System.out.println("enter data base name");
		  String Url="jdbc:mysql://localhost:3306/"+src.next();
		 try {
			 Class.forName(Driver);
			 conn=DriverManager.getConnection(Url,Username,Password);
			 System.out.println("enter table name");
			 String sql="drop table "+src.next();
			 pmst=conn.prepareStatement(sql);
			 int i=pmst.executeUpdate();
			 if(i==0) {
				 System.out.println("success");
			 }
			 else {
				 System.out.println("error");
			 }		 
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		
	}


	private static void createtable() {
		Scanner src=new Scanner(System.in);
		System.out.println("enter the data base name");
		  String Url="jdbc:mysql://localhost:3306/" +src.next();
		 try {
			 Class.forName(Driver);
			 conn=DriverManager.getConnection(Url,Username,Password);
			 System.out.println("enter table name");
			 String sql="create table "+src.next() + "(id int , name varchar(20), email varchar(20))";
			 pmst=conn.prepareStatement(sql);
			 int i=pmst.executeUpdate();
			 if(i==0) {
				 System.out.println("success");
			 }
			 else {
				 System.out.println("error");
			 }		 
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		
	}


	private static void dropdatabase() {
		Scanner src=new Scanner(System.in);
		  String Url="jdbc:mysql://localhost:3306/";
		 try {
			 Class.forName(Driver);
			 conn=DriverManager.getConnection(Url,Username,Password);
			 System.out.println("enter data base name");
			 String sql="drop database "+src.next();
			 pmst=conn.prepareStatement(sql);
			 int i=pmst.executeUpdate();
			 if(i == 0) {
				 System.out.println("success");
			 }
			 else {
				 System.out.println("error");
			 }		 
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	}


	private static void createdatabase()
	{
		Scanner src=new Scanner(System.in);
		  String Url="jdbc:mysql://localhost:3306/";
		 try {
			 Class.forName(Driver);
			 conn=DriverManager.getConnection(Url,Username,Password);
			 System.out.println("enter data base name");
			 String sql="create database "+src.next();
			 pmst=conn.prepareStatement(sql);
			 int i=pmst.executeUpdate();
			 if(i>0) {
				 System.out.println("success");
			 }
			 else {
				 System.out.println("error");
			 }		 
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		
		
	}


	private static void display() {
		System.out.println("\t1.create database");
		System.out.println("\t2.drop database");
		System.out.println("\t3.create table");
		System.out.println("\t4.drop table");
		System.out.println("\t5.add data");
		System.out.println("\t6.edit data");
		System.out.println("\t7.delete data");
		System.out.println("\t8.get all records");
		System.out.println("\t9.get record by email");
		System.out.println("\t10.exit");
		System.out.println("*******************");
		
		
		
	}
}
