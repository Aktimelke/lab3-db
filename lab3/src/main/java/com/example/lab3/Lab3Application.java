package com.example.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class Lab3Application {

	public static void main(String[] args)  {
		checkTables();
		file();
		SpringApplication.run(Lab3Application.class, args);
	}

	private static void checkTables (){
		Connection connection=null;
		Statement statement=null;
		Connect c=new Connect();
		connection=c.get_Connection();
		try{
			String query="create table if not exists info ( id SERIAL PRIMARY KEY , address varchar(20), typeof varchar(20), model varchar(20), mader varchar(20), addingdate varchar(20))";
			statement=connection.createStatement();
			statement.executeUpdate(query);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	private static void file() {
		Connection connection = null;
		Statement statement = null;
		Connect c = new Connect();
		connection = c.get_Connection();
		ResultSet res = null;
		File file = new File("C:\\Users\\NIGERS\\Desktop\\info.txt");


		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {
			String query = "select * from info";
			statement = connection.createStatement();
			res = statement.executeQuery(query);;
			while (res.next()) {
				writer.write(res.getString("id") + ", ");
				writer.write(res.getString("address") + ", ");
				writer.write(res.getString("typeof") + ", ");
				writer.write(res.getString("model") + ", ");
				writer.write(res.getString("mader") + ", ");
				writer.write(res.getString("addingdate") + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
