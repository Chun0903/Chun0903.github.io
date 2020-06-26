package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class ProjectAdminScore {
	static Scanner scan;
	
	static {

		scan = new Scanner(System.in);
		
	}
	
	

	public void adminScoreMain() {
		
		while(true) {
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t1. 과정별 성적 조회");
			System.out.println("\t\t\t2. 교육생 개인별 성적조회");
			System.out.println();
			System.out.println();
			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로 가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t번호를 입력해주세요 : ");
			String AdminInput = scan.nextLine();

			if (AdminInput.equals("1")) {	
				// 과정별 성적 조회 선택시
				
				firstClassScore();	
				secondClassScore();
				procSelectSpecificClass();
				
				break;
			} else if (AdminInput.equals("2")) { 
				// 교육생 개인별 성적 조회 선택시
				StudentScoreFirst();
				StudentScoreSecond();
				
				break;
			} else if (AdminInput.equals("a")){
				// 뒤로가기
				
				
				AdminMainPage admain = new AdminMainPage();
				admain.adminMain();
				
				break;
			} else if (AdminInput.equals("b")) {
				// 처음으로가기
				//home(); 
				
				break;
			} else {
				System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요");
			}

			}
		
	}



	public static void procSelectSpecificClass() {	//과정-> 과목-> 학생 성적출력
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs= null;
		
		try {
			
			String sql = "{ call procSelectSpecificClass(?, ?)}";
			
			System.out.print("\t\t\t성적조회할 과목번호를 입력하세요 : ");
			
			
			
			String num = scan.nextLine();
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t성적조회 - 과정별 성적 - 과목별 성적화면");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");



			
			conn = util.open();
			stat = conn.prepareCall(sql);
			
			stat.setString(1, num);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			//cursor == resultset
			rs = (ResultSet)stat.getObject(2);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			

			
					
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t[교육생명]\t[필기점수]\t[실기점수]");
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			while(rs.next()) {
					System.out.print("\t\t\t");
				for(int i=1; i<=rsmd.getColumnCount(); i++) {
					
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println();
			}
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로가기");
			
			System.out.print("\t\t\t돌아갈화면을 입력하세요 : ");
			String back = scan.nextLine();
			
			if(back.equals("a")) {
				
				ProjectAdminScore adScore = new ProjectAdminScore();	//관리자 - 성적관리 클래스생성
				adScore.adminScoreMain();
				
			} else if(back.equals("b")) {
				
				//home();
				
			}
		
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			
			
		}
		
	}

	

	

	
	
	
	
	private static void StudentScoreFirst() {	
		
		//m2() -> m3() 확장
		//직원 번호 입력 -> 직원 정보 출력
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t성적조회 - 교육생이름 입력");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t성적 조회를 하고싶은 학생이름를 입력해주세요.");
		System.out.print("\t\t\t교육생 이름 : ");
		String name = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {

			conn = util.open();
			stat = conn.createStatement();

			String sql = String.format("select distinct studentseq, studentname, pwd, classname , startDate, endDate, roomname from vwStudentIndividualInfo where studentname = '%s'", name);
			
			rs = stat.executeQuery(sql);
			
				
				//2. 레코드(컬럼) 정보 얻어오기
				ResultSetMetaData rsmd = rs.getMetaData();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t[번호]\t[교육생이름]\t[주민번호뒷자리]\t[과정명]\t\t\t\t\t[과정기간]\t\t\t[강의실명]");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				while(rs.next()) {
					System.out.print("\t\t\t");
					System.out.print(rs.getString("studentseq")+"\t");
					System.out.print(rs.getString("studentname")+"\t\t");
					System.out.print(rs.getString("pwd")+"\t\t");
					System.out.print(rs.getString("classname")+"\t");
					System.out.print(rs.getString("startDate")+"~");
					System.out.print(rs.getString("endDate")+"\t");
					System.out.println(rs.getString("roomname")+"\t");
				}
				
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				
				
				if(rs.getRow() == 0) {	//커서의 인덱스 값을 조회해서 값이없을 경우

					System.out.println("\t\t\t교육생중에'"+ name +"'(이)라는 이름이 없습니다. 다시입력해주세요.");
					StudentScoreFirst();	//다시시작한다.
				}
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}
	
	private static void StudentScoreSecond() {	
		
		//m2() -> m3() 확장
		//직원 번호 입력 -> 직원 정보 출력
		Scanner scan = new Scanner(System.in);
		System.out.println("\t\t\t성적조회를 하고싶은 학생의 번호를 입력하세요.");
		System.out.print("\t\t\t교육생 번호 : ");
		String num = scan.nextLine();
		
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t[과목명]\t\t\t\t[과목기간]\t\t\t[교사명]\t[필기점수]\t[실기점수]");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {

			conn = util.open();
			stat = conn.createStatement();

			String sql = String.format("select * from vwStudentSujectScore where studentseq = %s", num);
			
			rs = stat.executeQuery(sql);
			
				
				
				//2. 레코드(컬럼) 정보 얻어오기
				ResultSetMetaData rsmd = rs.getMetaData();
				
				while(rs.next()) {
					System.out.print("\t\t\t");
					System.out.print(rs.getString("sujectname")+"\t\t");
					System.out.print(rs.getString("sujectstartDate")+"~");
					System.out.print(rs.getString("sujectendDate")+"\t");
					System.out.print(rs.getString("teachername")+"\t");
					System.out.print(rs.getString("writeScore")+"\t");
					System.out.println(rs.getString("practiceScore"));
					
				}
				
			
			
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			while(true) {
			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로가기");
			
			System.out.print("\t\t\t돌아갈 화면 : ");
			String back = scan.nextLine();
			
			if(back.equals("a")) {
				ProjectAdminScore adScore = new ProjectAdminScore();	//관리자 - 성적관리 클래스생성
				adScore.adminScoreMain();
				break;
			} else if(back.equals("b")) {
				//home();
				break;
			} else {
				System.out.println("잘못입력했습니다. 다시입력하세요.");
			}
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void firstClassScore() {	//과정별 성적 조회 첫화면 
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t성적조회 - 과정별 성적");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t[과정번호]\t[과정명]\t\t\t\t\t[과정기간]\t\t[교사명]\t[강의실명]");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		try {

			conn = util.open();
			stat = conn.createStatement();

			String sql = "select * from vwclassScore";
			
			rs = stat.executeQuery(sql);
			
			//2. 레코드(컬럼) 정보 얻어오기
			ResultSetMetaData rsmd = rs.getMetaData();
			
				
				while(rs.next()) {
					System.out.print("\t\t\t");
					System.out.print(rs.getString("seq")+"\t");
					System.out.print(rs.getString("classname")+"\t");
					System.out.print(rs.getString("startdate")+"~");
					System.out.print(rs.getString("enddate")+"\t");
					System.out.print(rs.getString("teachername")+"\t");
					System.out.println(rs.getString("roomname")+"\t");
				}
				
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				
			
			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void secondClassScore() {	//과정별 성적 조회 두번째화면 
	
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		
	

		try {

			conn = util.open();
			stat = conn.createStatement();
			
			System.out.print("\t\t\t검색할 과정번호 : " );
			
			String num = scan.nextLine();
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t성적조회 - 과정별 성적");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			String sql = String.format("select distinct openSubjectSeq,subjectname, bookname, startDate, endDate, scoreReg  from vwSelectSpecificClass where openClassSeq = %s order by openSubjectSeq", num);
			
			rs = stat.executeQuery(sql);
			
	
				
				//2. 레코드(컬럼) 정보 얻어오기
				ResultSetMetaData rsmd = rs.getMetaData();
				System.out.print("\t\t\t조회중인 과정명 : " + "\r\n");
				
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t[과목번호]\t[과목명]\t\t\t[교재명]\t\t[과목기간]\t\t\t[성적등록여부]");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				
				while(rs.next()) {
						
						System.out.print("\t\t\t");
						System.out.print(rs.getString("openSubjectSeq") +"\t");
						System.out.print(rs.getString("subjectname") +"\t");
						System.out.print(rs.getString("bookname") +"\t");
						System.out.print(rs.getString("startDate") +"~");
						System.out.print(rs.getString("endDate") +"\t");
						System.out.println(rs.getString("scoreReg"));
					
					
				}

				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			stat.close();
			conn.close();

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	public static void classAttendFirst() {	//출결현황 조회 - 과정별 출결 - 첫화면
	
	
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	DBUtil util = new DBUtil();

	System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	System.out.println("\t\t\t[과정번호]\t[과정명]\t\t[과정기간]\t\t\t[교사명]\t[강의실명]");
	System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	try {

		conn = util.open();
		stat = conn.createStatement();

		String sql = "select * from vwStudentAttend";
		
		rs = stat.executeQuery(sql);
		
		//2. 레코드(컬럼) 정보 얻어오기
		ResultSetMetaData rsmd = rs.getMetaData();
		
			
			while(rs.next()) {
				System.out.print("\t\t\t");
				System.out.print(rs.getString("seq")+"\t");
				System.out.print(rs.getString("classname")+"\t");
				System.out.print(rs.getString("startdate")+"~");
				System.out.print(rs.getString("enddate")+"\t");
				System.out.print(rs.getString("teachername")+"\t");
				System.out.println(rs.getString("roomname")+"\t");
			}
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로가기");
		
		
		rs.close();
		stat.close();
		conn.close();

	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
	
	
	
	

}
