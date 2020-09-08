package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class Student_Login {
	
	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);		
	}
	
	static String id;
	
	static String pwd;
	
	static String name;
	
	//외부에서 호출
	String getId() {
		return id;
	}
	
	String getPwd() {
		return pwd;
	}
	
	String getName() {
		return name;
	}


	public void Login() {
	
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		boolean loop = true;
		
		try {
			
			//서버 연결
			conn = util.open("211.63.89.59","project","java1234");			
			//conn = util.open("localhost", "project", "java1234");
			
			while (loop) {
				
				//로그인 하는 프로시저 호출
				String sql = "{ call procstdlogin( ?, ?, ?, ?, ?, ?, ?, ?, ? ) }";
				
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				
				System.out.println();
				System.out.println("\t\t\t수강생 로그인");
				
				System.out.println();
				
				//아이디 입력 : hzuwo7132
				System.out.print("\t\t\tid : ");
				id = scan.nextLine();
				
				System.out.println();
				
				//비밀번호 입력 : 4239216
				System.out.print("\t\t\tpwd : ");
				pwd = scan.nextLine();
				
				System.out.println();
				
				//statement에 프로시저 대입
				stat = conn.prepareCall(sql);
				
				//1,2 번은 입력받는 매개변수, 3~9번은 돌려주는(return) 값
				stat.setString(1, id);
				stat.setString(2, pwd);
				stat.registerOutParameter(3, OracleTypes.NUMBER);
				stat.registerOutParameter(4, OracleTypes.VARCHAR);
				stat.registerOutParameter(5, OracleTypes.VARCHAR);
				stat.registerOutParameter(6, OracleTypes.DATE);
				stat.registerOutParameter(7, OracleTypes.VARCHAR);
				stat.registerOutParameter(8, OracleTypes.VARCHAR);
				stat.registerOutParameter(9, OracleTypes.VARCHAR);
				
				//실행
				stat.executeQuery();
				
				//출력문
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				
				//return하는 결과값이 1이면 성공, 1이외의 값이면 실패
				if (stat.getInt(3) == 1) {
					
					System.out.println();
					
					System.out.println("\t\t\t로그인 성공");
					
					System.out.println();
					
					name = stat.getString(4);
					
					System.out.println("\t\t\t안녕하세요. " + stat.getString(4) + " 학생님");
					
					System.out.println();
					
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t이름\t핸드폰번호\t\t등록일\t\t과정명");
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t" + stat.getString(4) + "\t" + stat.getString(5) + "\t" + stat.getDate(6) + "\t" + stat.getString(7));
					System.out.println();
					System.out.println();
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t과정기간\t\t\t강의실명");
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t" + stat.getString(8) + "\t" + stat.getString(9));
					
					System.out.println();
					
					System.out.print("\t\t\t엔터를 누를 시 메뉴로 이동합니다.");
					scan.nextLine();
					
					Student_Menu menu = new Student_Menu();
					menu.menu();
					
				} else {
					
					System.out.println();
					System.out.println("\t\t\t로그인 실패");
					System.out.println();
					
				}//if
				
				//close
				stat.close();
				conn.close();
				
			}//while
			
		} catch (Exception e) {			
			
			System.out.println("\t\t\t잘못 입력했습니다. 다시 입력하세요.");
			
		}//try,catch
		
	}//main
	
}//Student_Main