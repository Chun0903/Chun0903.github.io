package com.test.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//JAVA DOC 주석 (/** 작성 후 엔터)
/**
 * 
 * @author 희선
 * 오라클 연결 클래스입니다.
 */
public class DBUtil {
	
	private Connection conn = null;
	
	/**
	 * 서버에 연결합니다.
	 * @return 연결 객체를 반환합니다.
	 */
	
	public Connection open() {
		
		
		String url = "jdbc:oracle:thin:@211.63.89.59:1521:xe";
		String id = "project";
		String pw = "java1234";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url,id,pw);
			
			return conn; //접속 성공 
			
		} catch (Exception e) {
			System.out.println("DBUtil.getConnectio()");
			e.printStackTrace();
		}
		
		return null; //접속 실패 
		
	}//open
	
	
	
	/**
	 * 
	 * 서버에 연결합니다.
	 * @param host 서버 주소
	 * @param id 계정명
	 * @param pw 비밀번호 
	 * @return 연결 객체를 반환합니다.
	 */
	//open 메소드 오버로딩 
	public Connection open(String host, String id, String pw) {
		
		
		String url = "jdbc:oracle:thin:@" + host + ":1521:xe";

		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url,id,pw);
			
			return conn; //접속 성공 
			
		} catch (Exception e) {
			System.out.println("DBUtil.getConnectio()");
			e.printStackTrace();
		}
		
		return null; //접속 실패 
		
	}//open
	
	
	
	
	/**
	 * 연결을 종료합니다.
	 */
	public void close() {
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//close
	
	
	

}
