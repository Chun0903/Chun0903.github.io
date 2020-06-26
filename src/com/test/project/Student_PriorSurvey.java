package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class Student_PriorSurvey {
	
	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);		
	}
	
	Student_Login main = new Student_Login();
	
	//사전평가 유무 검사
	public void PriorSurveyChk() {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		
		try {
			
			conn = util.open("211.63.89.59","project","java1234");	
			//conn = util.open("localhost", "project", "java1234");
			
			String sql = "{ call procStdSurveyChk( ?, ?, ? ) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, main.id);
			stat.setString(2, main.pwd);
			stat.registerOutParameter(3, OracleTypes.NUMBER);
			
			stat.executeQuery();
			
			if (stat.getInt(3) == 1) {
				System.out.println("\t\t\t사전 평가를 하지 않으셨습니다.");
				System.out.println();
				System.out.print("\t\t\t사전 평가를 하려면 엔터를 눌러주세요");
				System.out.println();
				scan.nextLine();
				PriorSurvey();
			} else if (stat.getInt(3) == 0) {
				System.out.println("\t\t\t사전 평가를 이미 하셨습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("\t\t\t" + e);
		}
		
	}//PriorSurveyChk
	
	//사전 평가
	public void PriorSurvey() {
		
		//connection, statement, resultset(결과셋), 연결메소드 선언
		Connection conn = null;
		Statement stat = null;
		Statement stat2 = null;
		CallableStatement stat3 = null;
		CallableStatement stat4 = null;
		
		ResultSet rs = null;
		ResultSet rs2 = null;
		DBUtil util = new DBUtil();
		
		try {
			//서버 연결
			conn = util.open("211.63.89.59","project","java1234");	
			//conn = util.open("localhost", "project", "java1234");
			
			//select를 하기 위한 createstatment 메소드 선언
			stat = conn.createStatement();
			stat2 = conn.createStatement();

			//select문 호출
			String sql = "select * from vwQuestion";
			String sql2 = "select * from vwItem where priorSurveySeq = ";
			
			//select문을 resultset에 저장
			rs = stat.executeQuery(sql);
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			//아래구문은 죄다 출력
			while(rs.next()) {
		
				rs2 = stat2.executeQuery(sql2 + rs.getString("seq"));
				
				System.out.println();
				System.out.println("\t\t\t" + rs.getString("번호") + "." + " " + rs.getString("문제내용"));
				System.out.println();
				
				while(rs2.next()) {
					
					System.out.println("\t\t\t\t" + rs2.getString("항목") + "." + " " + rs2.getString("항목(내용)"));
					
//					int k = rs2.getInt("항목");
//					k = 1;
//					
//					System.out.println();
//					
//					for (int i = 0; i < 1; i++) {
//						System.out.println("\t\t\t" + rs.getString("번호") + "." + " " + rs.getString("문제내용"));
//					} for (int j = 0; j < 4; j++) {
//						System.out.print("\t\t\t\t" + k + ".");
//						k++;
//						System.out.println(rsString("항목(내용)"));
//					}//for
					
					System.out.println();
					
				}//while(rs2)
				
				System.out.print("\t\t\t정답을 입력하세요 : ");	
				int num = scan.nextInt();
				
				String sql3 = "{ call procSurveyAnswer( ?, ?, ? ) }";
				String sql4 = "{ call procstdinfo( ?, ? ) }";
				
				//1.? -> num
				//3.? -> rs.getString("seq")
				//4.? -> student.seq
				
				stat3 = conn.prepareCall(sql3);
				stat4 = conn.prepareCall(sql4);
				
				stat4.setString(1, main.id);
				stat4.registerOutParameter(2, OracleTypes.NUMBER);
				
				stat4.executeUpdate();
				
				stat3.setInt(1, num);
				stat3.setInt(2, rs.getInt("seq"));
				stat3.setInt(3, stat4.getInt(2));
				
				stat3.executeUpdate();
				
			}//while(rs)
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			System.out.println("\t\t\t수고하셨습니다.");
			
			//처음에 선언한 메소드 close
			rs.close();
			rs2.close();
			stat.close();
			stat2.close();
			stat3.close();
			stat4.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("\t\t\t" + e);
		}
		
	}//PriorSurvey

}//Student_PriorSurvey