package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class Student_Score {
	
	//성적 조회
	public void Score() {
		
		//connection, statement, ResultSet, 연결메소드, Scanner 선언
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Student_Login main = new Student_Login();
		
		try {
			//서버 연결
			conn = util.open("211.63.89.59","project","java1234");	
			//conn = util.open("localhost", "project", "java1234");
			
			//프로시저 호출
			String sql = "{ call procStdInfoScore( ?, ? ) }";
			
			//프로시저 저장
			stat = conn.prepareCall(sql);
			
			//1번은 입력받는 매개변수, 2번은 돌려주는(return) 값
			stat.setString(1, main.pwd);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			//실행
			stat.executeQuery();
			
			//cursor값을 결과셋에 저장
			rs = (ResultSet)stat.getObject(2);
			
			//\t\t\t교재명\t\t\t\t\t\t\t교사명\t출석배점\t필기배점\t실기배점\t출석점수\t필기점수\t실기점수\t시험날짜
//			+ "\t" + rs.getString("교재명") + "\t" + rs.getString("교사명") + "\t" 
//			+ rs.getInt("출석배점") + "\t" + rs.getInt("필기배점") + "\t" + rs.getInt("실기배점") + "\t" 
//			+ rs.getInt("출석점수") + "\t" + rs.getInt("필기점수") + "\t" + rs.getInt("실기점수") + "\t" 
//			+ rs.getDate("시험날짜")
			
			//출력문
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t과목명\t\t\t과목기간");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			while (rs.next()) {
				System.out.println("\t\t\t" + rs.getString("과목명") + "\t" + rs.getString("과목기간"));
				System.out.println();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t교재명");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t" + rs.getString("교재명"));
				System.out.println();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t교사명\t출석배점\t필기배점\t실기배점");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t" + rs.getString("교사명") + "\t" + rs.getInt("출석배점") + "\t" + rs.getInt("필기배점") + "\t" + rs.getInt("실기배점"));
				System.out.println();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t출석점수\t필기점수\t실기점수");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t" + rs.getInt("출석점수") + "\t" + rs.getInt("필기점수") + "\t" + rs.getInt("실기점수"));
				
			}//while
			
			//close
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("\t\t\t" + e);
		}
		
	}//Score
	
}//Student_Score