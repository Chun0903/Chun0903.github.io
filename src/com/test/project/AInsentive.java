package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class AInsentive {

	//스캐너
	static Scanner scan = new Scanner(System.in);
	//교사 번호 리스트
	static ArrayList<String> tseq = new ArrayList<String>();
	//개설과정 번호 리스트
	static ArrayList<String> ocseq = new ArrayList<String>();
	
//	public static void main(String[] args) {
//		
//		
//		A_insentive();
//		
//	}//main
	
	public static void A_insentive() {
		
		System.out.println();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t" + "<교사 인센티브 관리>");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		
		System.out.println();
		System.out.println("\t\t\t1. 인센티브 관리");
		System.out.println("\t\t\t2. 개설 과정별 설문 내용 조회");
		System.out.println("\t\t\t3. 교사평가 설문지 추가");
		System.out.println("\n");
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println("\t\t\tb. 처음으로가기");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t입력 : ");
		
		String num = scan.nextLine();
		
		
		if(num.equals("1")) {

			insentive(); //교사월급 조회 > 인센티브 주기
	
		}else if(num.equals("2")) {
			
			surveyOpenclass(); //개설과정 조회 > 과정+설문내용

		}else if(num.equals("3")) {
			
			AinsentivePlus ainsentivePlus = new AinsentivePlus();
			ainsentivePlus.assPlus();
			
		}else if(num.equals("a")) {
			
			AdminMainPage adminMainPage = new AdminMainPage();
			adminMainPage.adminMain();
			
		}
		else if(num.equals("b")) {
//			home();
		}else {
			System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
			A_insentive();
		}
		
		
	}//AImain
	
	private static void surveyOpenclass() {
		System.out.println();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t" + "<개설 과정 목록>");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		openClassList();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		
		//과정번호 입력
		
		System.out.println("\t\t\t" + "a. 뒤로가기");
		System.out.println("\t\t\t" + "b. 처음으로가기");
		System.out.println("\n");
		System.out.print("\t\t\t" + "과정의 번호를 입력하세요: ");
		
		
		//번호 입력
		String openClassSeq = scan.nextLine();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
		
		//존재하는 과정번호
		if(ocseq.contains(openClassSeq)) {
			
			openClassSurvey(openClassSeq);
			
			System.out.println("\t\t\t" + "a. 뒤로가기");
			System.out.println("\t\t\t" + "b. 처음으로가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t입력 : ");
			
			String backHome = scan.nextLine();
			
			if(backHome.equals("a")) {
				surveyOpenclass();
			} else if(backHome.equals("b")) {
//				home();
			} else {
				System.out.println();
				System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
				System.out.println();
				
				openClassSurvey(openClassSeq);
			}
			
		
		}else if(openClassSeq.equals("a")) {
			
			
			A_insentive();
			
			
		}else if(openClassSeq.equals("b")) {
			
//			home();
			
		}else {
	         System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
		}
	}
	
	private static void openClassSurvey(String openClassSeq) {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		
		
		try {
			conn = util.open("211.63.89.59","project","java1234");

			
			String sql = "";
			
			//과정 + 질문 가져오기			
			sql =  "{ call procAssClassNQ(?,?) }";
	
			stat = conn.prepareCall(sql);
			
			stat.setString(1, openClassSeq);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(2);
			
			while (rs.next()) {
				System.out.printf("\t\t\t<%s>\n\t\t\t%s.%s\n"
						,rs.getString("name")
						,rs.getString("outnum")
						,rs.getString("question"));
				
				// 과정 + 질문 <- 항목들 가져오기
				sql = "{ call procAssClassCon(?,?,?,?) }";
				
				CallableStatement stat2 = null;
				ResultSet rs2 = null;
				stat2 = conn.prepareCall(sql);
				
				stat2.setString(1, openClassSeq);
				stat2.setString(2, rs.getString("name"));
				stat2.setObject(3, rs.getString("aseq"));
				stat2.registerOutParameter(4, OracleTypes.CURSOR);
				
				stat2.executeQuery();
				
				rs2 = (ResultSet)stat2.getObject(4);
				
				while (rs2.next()) {
					System.out.println("\t\t\t      " + rs2.getString("content"));
				}
				System.out.println("\t\t\t--------------------------------------------------------------");
			}
			
			
		} catch (Exception e) {
			System.out.println("AS_001()");
			e.printStackTrace();
		}
		
	}

	private static void openClassList() {
		Connection conn = null;
		Statement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;	
		
		ocseq.clear();
		
		try {
			
			String sql = "";
			sql = "select DISTINCT ocseq, name from vwAssurveyClass order by ocseq";
			
			conn = util.open("211.63.89.59","project","java1234");
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			System.out.println("\t\t\t" + "[개설 과목]");
			while (rs.next()) {
				System.out.printf("\t\t\t" + "%s. %s\n"
								, rs.getString("ocseq")						
								, rs.getString("name"));
				ocseq.add(rs.getString("ocseq"));
			}
			System.out.println();
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("AS_001()");
			e.printStackTrace();
		}
		
	}
	
	private static void insentive() {
		
		//교사 월급 목록
		tSalary();
		
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t" + "a. 뒤로가기");
		System.out.println("\t\t\t" + "b. 처음으로가기");
		System.out.println("\n");
		System.out.print("\t\t\t" +"인센티브를 줄 교사 번호를 입력해주세요 : ");
		String teacherSeq =  scan.nextLine();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		
		
		if(tseq.contains(teacherSeq)) {
			
			//인센티브 지급
			insentiveView(teacherSeq);
			
			System.out.println("\t\t\t" + "a. 뒤로가기");
			System.out.println("\t\t\t" + "b. 처음으로가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t입력 : ");
			
			String backHome = scan.nextLine();
			
			if(backHome.equals("a")) {
				//joo
				insentive();
				
				
			} else if(backHome.equals("b")) {
				
//				home();
				
			} else {
				
				System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
				System.out.println();
				
				insentive();
			}
			

			
		}else if(teacherSeq.equals("a")) {
			
			A_insentive();
			
		}else if(teacherSeq.equals("b")) {
			
//			home();
			
		}else {

			System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
			insentive();
		}
		
	}
	
	private static void insentiveView(String teacherSeq) {
		// TODO Auto-generated method stub
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		
		try {
			
			conn = util.open("211.63.89.59","project","java1234");

			
			String sql = "";
			
			//과정 + 질문 가져오기			
			sql =  "{ call procIncentiveView(?,?,?,?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, teacherSeq);
			stat.registerOutParameter(2, OracleTypes.NUMBER);
			stat.registerOutParameter(3, OracleTypes.NUMBER);
			stat.registerOutParameter(4, OracleTypes.NUMBER);
			
			stat.executeQuery();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.printf("\t\t\t%s번 교사의 총첨\n", teacherSeq);
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[설문 점수]\t[취업률]\t[총점]");
			
			System.out.print("\t\t\t" + (int)(stat.getFloat(3) * 100));
			System.out.print("\t" + (int)(stat.getFloat(4) * 100));
			System.out.println("\t" + stat.getString(2));
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			
			stat.close();
			
			System.out.println("\t\t\t1. 인센티브 주기");
			System.out.println("\t\t\t2. 인센티브 총점 배점 수정");
			System.out.println("\n");
			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t입력 : ");
			
			String next = scan.nextLine();
			
			if(next.equals("1")) {
				insentiveUpdate(teacherSeq);
			}else if(next.equals("2")) {
				insentivePoint(teacherSeq);
			}else if(next.equals("a")) {
				//joo 뒤로가기
				insentive();
			}else if(next.equals("b")) {
//				home();
				
			}else {
				System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
				insentiveView(teacherSeq);
			}
			
			
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private static void insentivePoint(String teacherSeq) {
		// TODO Auto-generated method stub
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		
		try {
			
			conn = util.open("211.63.89.59","project","java1234");

			
			String sql = "";
			Statement stat1 = null;
			ResultSet rs1 = null;
			
			stat1 = conn.createStatement();
		
			sql = "select * from tblincentivepoint";
			rs1 = stat1.executeQuery(sql);
			
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t현재 배점");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			System.out.println("\t\t\t[설문점수 배점]\t[취업률 배점]");
			if(rs1.next()) {
				System.out.println("\t\t\t" + rs1.getString("score") +"\t\t" + rs1.getString("employeeratio"));
			}else {
				System.out.println("없음");
			}
			
			rs1.close();
			stat1.close();
			
			System.out.println();
			
			sql = "{ call procIncentivepointUpdate(?,?) }";
			stat = conn.prepareCall(sql);
			
			
			System.out.print("\t\t\t설문점수 배점 입력 : ");
			String score = scan.nextLine();
			
			System.out.print("\t\t\t취업률 배점 입력 : ");
			String employeeratio = scan.nextLine();
			
			stat.setString(1, score);
			stat.setString(2, employeeratio);
			
			stat.executeUpdate();
			
			System.out.println();
			System.out.println("\t\t\t수정 완료");
			System.out.println();
			while(true) {
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t" + "a. 뒤로가기");
			System.out.println("\t\t\t" + "b. 처음으로가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t입력 : ");
			
			String backHome = scan.nextLine();
			
				if(backHome.equals("a")) {
					//joo
					insentiveView(teacherSeq);
					
					
				} else if(backHome.equals("b")) {
					
	//				home();
					
				} else {
					
					System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
					System.out.println();
					
					
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	private static void insentiveUpdate(String teacherSeq) {

		try {
			
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();

			String sql = "";
			sql = "{ call procIncentiveUpdate(?) }";
			
			conn = util.open("211.63.89.59","project","java1234");
			stat = conn.prepareCall(sql);
			
			stat.setString(1, teacherSeq);
			
			stat.executeUpdate();
			
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.printf("\t\t\t%s번 교사 인센티브 지급 완료\n", teacherSeq);
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			stat.close();
			conn.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	private static void tSalary() {
		Connection conn = null;
		Statement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		
		tseq.clear();
		
		try {
			conn = util.open("211.63.89.59","project","java1234");
			String sql = "";
			
			sql = "select * from vwTSalary";

			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			System.out.println("\t\t\t" + "<교사 급여>");
			System.out.println("\t\t\t" + "[번호]\t[이름]\t[급여]\t  [인센티브]");
			while (rs.next()) {
				System.out.printf("\t\t\t" + "%s\t%s\t%s\t  %s\n"
								, rs.getString("seq")						
								, rs.getString("name")
								, rs.getString("salary")
								, rs.getString("incentive"));
				
				tseq.add(rs.getString("seq"));
			}
			System.out.println();
			rs.close();
			stat.close();

			} catch (Exception e) {
				// TODO: handle exception
			}
		
	}
	
	
	
}//AI
