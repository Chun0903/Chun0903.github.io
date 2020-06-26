package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.internal.OracleTypes;

public class Teacher {
	
	//교사 로그인 후 교사 번호 값 받아오기
//	static int teacherNum = 1;
	//TLogin login = new TLogin();
	
//	static int teacherNum = tseqLogin.get(0);
	
	//과목번호 입력한 값 받아오기
	//static int subjectNum ;
	
	   //교사 번호 
//	   static int teacherNum = Integer.parseInt(tseqLogin.get(0));
	
	//public static void main(String[] args) {
		
		
		//T_001_in();  		//교사가 강의를 마친 과목에 대한 성적 처리를 위해서 배점 입력 > 배점 , 시험문제/날짜 입력
		//T_001_out();  	//교사가 과목에 대한 성적 처리를 위해서 배점 출력
		//T_002(); 		 	//과목 목록 자세히 출력 
		//T_004();			//선택한 과목을 듣고 있는 학생 목록 출력 > 학생 선택 > 성적 입력
		//T_007();			//교사가 진행하고 있는 과정의 과목 목록 출력 > 과목 선택시 해당 학생 정보,점수 출력 
		//T_009();			//과정 중 선택하면 수강했던 학생들 근태상황 출력 ( 출결 전체 조회 ) 
		//T_010();			//교사가 현재 진행하고 있는 과정 중에서 중도 탈락한 학생들의 여부 , 날짜 
		//T_011();			// ( 출결 월별 조회 ) 
		//T_012();			// ( 출결 일별 조회 ) 
		//T_013();			// 사전평가 조회 
	
		//SurveyScore()		//학생이 참여한 사전평가 설문지 점수 확인 
		//SurveyAnswer() 	//사전평가 설문지 답 , 대답 
		
		
		
		
	//}//main
	
	
	Menu home = new Menu();
	
//	Menu.home();
	
	
	static void T_013() {
		
		//사전평가 조회
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
		System.out.println("\t\t\t1. 답안지 확인");
		System.out.println();
		System.out.println("\t\t\t2. 점수 확인");
		System.out.println();
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println();
		System.out.println("\t\t\tb. 처음으로가기");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
		System.out.print("\t\t\t메뉴를 선택하세요 : ");
		String output = scan.nextLine();
		System.out.println();
		
		if (output.equals("1")) {
			SurveyAnswer();
		} else if (output.equals("2")) {
			SurveyScore();
		} else if (output.toLowerCase().equals("a")) {
			TLogin.tMenu();
		} else if ( output.toLowerCase().equals("b")) {
			Menu home = new Menu();
			home.home();
		}
		
	}//T_013
	
	
	
	
	
		//학생이 참여한 사전평가 설문지 점수 확인 
		static void SurveyScore() {
		
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stat = null;
		Scanner scan = new Scanner(System.in);
		DBUtil util = new DBUtil();
		
		try {
			
			conn = util.open("211.63.89.59" , "project", "java1234");
			
			String sql = "call procPriorScore( ?, ?, ? )";
			
			stat = conn.prepareCall(sql);
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			System.out.println();
			System.out.print("\t\t\t이름을 입력하세요 : ");
			String name = scan.nextLine();
			
			stat.setString(1, name);
			stat.registerOutParameter(2, OracleTypes.VARCHAR);
			stat.registerOutParameter(3, OracleTypes.NUMBER);
			
			stat.executeQuery();
			
			System.out.println("\t\t\t" + stat.getString(2) + "의 점수는 " + stat.getInt(3) + "점입니다.");
			System.out.println();
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.println("\t\t\ta. 뒤로 가기");
			System.out.println("\t\t\tb. 처음으로 가기");
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t메뉴를 선택하세요 : ");
			String output = scan.nextLine();
			
			if (output.equals("a")) {
				//뒤로가기
				T_013();
				
			} else if (output.equals("b")) {
				//처음으로 가기 
				Menu.home();
				
			}
			
		} catch (Exception e) {
			System.out.println("\t\t\t" + e);
		}
		
	}
	
		
		
		
	//사전평가 설문지 답 , 대답 
	static void SurveyAnswer() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		DBUtil util = new DBUtil();
		
		try {
			
			conn = util.open("211.63.89.59" , "project", "java1234");
			
			stat = conn.createStatement();
			
			String sql = "select * from vwSurveyResult";
			
			rs = stat.executeQuery(sql);
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[학생이름]\t[문제번호]\t[질문]\t\t\t\t\t\t[정답]\t[학생이 고른 답]");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			while(rs.next()) {
				
				System.out.println();
				System.out.printf("\t\t\t%s\t%d\t%s\t%d\t%d\n"	  , rs.getString("학생이름")
																	  , rs.getInt("번호")
																	  , rs.getString("질문")
																	  , rs.getInt("정답")
																	  , rs.getInt("학생이 고른 답"));
				
				System.out.println();
				
			}//while
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.println("\t\t\ta. 뒤로 가기");
			System.out.println("\t\t\tb. 처음으로 가기");
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t메뉴를 선택하세요 : ");
			String output = scan.nextLine();
			
			if (output.equals("a")) {
				//뒤로가기
				T_013();
				
				
			} else if (output.equals("b")) {
				
				//처음으로 가기 
				Menu.home();
				
			}
			
		} catch (Exception e) {
			System.out.println("\t\t\t" + e);
		}
		
	}//TeacerAnswer
	
	
	
	
	
	
	
	
	
	

		
		
		static void page(List<String> arrayList) {
			
			
			 //10개씩 분할
			List<String[]> depart = new ArrayList<String[]>();
			int firstindex = 0;
			int lastindex = 10;
			int index = 0;
			int totalcount = arrayList.size();

			while (true) {
				// 10개의 String 이 들어갈 묶음
				String[] ranking = new String[10];

				for (int j = firstindex; j < lastindex; j++) {
					if (j >= arrayList.size()) {
						break;
					}
					ranking[j - (index) * 10] = arrayList.get(j);
					}
				depart.add(ranking);
				
				firstindex = firstindex + 10;
				lastindex = lastindex + 10;
				index++;

				if (firstindex >= totalcount) {
					break;
				}
			
			}
			
			
			
			//페이지 수 
			int count = 0;
			
			int num = 1;
			
			//페이지가 한개만 있는 경우
			if(depart.size() == 1) {
				//첫페이지 보여주기
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				for(int i = 0; i < depart.get(0).length; i++) {
					if( depart.get(0)[i] != null) {
					System.out.printf("\t\t\t%d. %s\n",i+1,depart.get(0)[i]);
					}
				}
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t"+"넘어갈 페이지가 없습니다.");
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			}
			else {
			
			//페이지가 여러개인 경우
			while(true) {
				
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			    System.out.println();
				for(int i = 0; i < 10; i++) {
					System.out.printf("\t\t\t%d. ", num);
					if( depart.get(count)[i] != null) {
						System.out.printf("\t%s\t" , depart.get(count)[i]);
					}
					System.out.println();
					num++;
				}
				//페이지 반복
				System.out.println();
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.printf("\t\t\t"+"%d페이지 입니다.\n", count + 1);
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t"+"1. 이전 페이지");
				System.out.println("\t\t\t"+"2. 다음 페이지");
				System.out.println("\t\t\t"+"3. 선택하기");
				System.out.println();
				System.out.println("\t\t\t"+"a. 뒤로가기");
				System.out.println("\t\t\t"+"b. 처음으로가기");
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");	
				//번호 고르기
				Scanner scan = new Scanner(System.in);
				System.out.print("\t\t\t"+"입력:  ");
				String answer = scan.nextLine();
				//scan.skip("\r\n"); 
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				//이전페이지
				if(answer.equals("1")) {
					//첫페이지 일 경우
					if(count == 0) {
						count = depart.size() - 1 ;
						num = depart.size() * 10 -9;
					} else {
						count = count-1;
						num -= 20;
					}
				} else if(answer.equals("2")){
					//다음페이지
					
					//마지막 페이지일 경우
					if(count == depart.size() -1 ) {
						count = 0;
						num = 1;
					} else {
						count = count+1;
						
					}
				} else if(answer.equals("a")) {
					
					//뒤로가기
					TLogin.tMenu();
					
				} else if(answer.equals("b")) {
					
					//처음으로 가기
					Menu.home();
				}
					
				
				else {
					//System.out.println("\t\t\t"+"페이지 프로그램을 종료합니다.");
					
					
					break;
				}
				
				
				
				
			} //while

			}
			
			
		}
	
	
	
	static void T_012() {
		
		//출결 일별 조회 
		Connection conn = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		CallableStatement stat = null;
		
		try {
			
			conn = util.open("211.63.89.59" , "project", "java1234");
			
			System.out.println();
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로가기");
			System.out.println();
			System.out.print("\t\t\t조회할 일(dd)을 입력하세요: ");
			Scanner scan = new Scanner(System.in);
			String date = scan.nextLine();
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			
			
			if(date.toLowerCase().equals("a")) {
				//뒤로가기
				//교사 로그인 후 메뉴 화면으로 
				TLogin.tMenu();
				
			} else if(date.toLowerCase().equals("b")) {
				
				//처음으로 가기
				Menu.home();
			} 
			
			
			String sql = "{call procDate(?,?)}";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, date);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			rs = stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(2);
			
			//페이징 할 목록
     		ArrayList<String> list = new ArrayList<String>();
     		
			
     		System.out.println();
     		System.out.println("\t\t\t[이름]\t[출근시간]\t\t[퇴근시간]\t\t[근태상황]\t[날짜]");
     		
     		while(rs.next()) {
     			
     			list.add(rs.getString("이름")+"\t"+rs.getString("출근시간")+"\t"+rs.getString("퇴근시간")+"\t"+rs.getString("근태상황")+"\t"+rs.getString("날짜"));
     			
     		}
     		
     		
     		page(list);
     		
     		System.out.println();
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     		System.out.println("\t\t\ta.뒤로가기");
     		System.out.println("\t\t\tb.처음으로가기");
     		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     		System.out.println();
     		
     		System.out.println("\t\t\t메뉴를 선택해 주세요: ");
     		scan = new Scanner(System.in);
     		String input = scan.nextLine();
     		
     		if(input.toLowerCase().equals("a")) {
     			
     			//뒤로가기
     			TLogin.tMenu();
     			
     		} else if(input.toLowerCase().equals("b")) {
     			
     			//처음으로가기
     			Menu.home();
     			
     		}
			
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	static void T_011() {
		
		//출결 월별 조회 
		Connection conn = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		CallableStatement stat = null;
		
		try {
			
			conn = util.open("211.63.89.59" , "project", "java1234");
			
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로가기");
			System.out.println();
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t조회할 월(mm)을 입력하세요: ");
			
			Scanner scan = new Scanner(System.in);
			String month = scan.nextLine();
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			
			
			if(month.toLowerCase().equals("a")) {
				//뒤로가기
				//교사 로그인 후 메뉴 화면으로 
				TLogin.tMenu();
				
			} else if(month.toLowerCase().equals("b")) {
				
				//처음으로 가기
				Menu.home();
			} 
			
			
			
			
			
			String sql = "{call procMonth(?,?)}";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, month);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			rs = stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(2);
			
			//페이징 할 목록
     		ArrayList<String> list = new ArrayList<String>();
			
     		System.out.println();
     		System.out.println("\t\t\t[No.]\t[이름]\t[출근시간]\t[퇴근시간]\t[근태상황]\t[날짜]");
     		
     		while(rs.next()) {
     			
     			list.add(rs.getString("이름")+"\t"+rs.getString("출근시간")+"\t"+rs.getString("퇴근시간")+"\t"+rs.getString("근태상황")+"\t"+rs.getString("날짜"));
     			
     		}
     		
     		page(list);
     		
     		System.out.println();
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     		System.out.println("\t\t\ta.뒤로가기");
     		System.out.println("\t\t\tb.처음으로가기");
     		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     		System.out.println();
     		
     		System.out.print("\t\t\t메뉴를 선택해 주세요: ");
     		scan = new Scanner(System.in);
     		String input = scan.nextLine();
     		
     		if(input.toLowerCase().equals("a")) {
     			
     			//뒤로가기
     			TLogin.tMenu();
     			
     		} else if(input.toLowerCase().equals("b")) {
     			
     			//처음으로가기
     			Menu.home();
     			
     		}
     		
  	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	static void T_010() {
		// 중도 탈락 여부 확인
		
		Connection conn = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		CallableStatement stat = null;
		Statement stat2 = null;
		
		try {
			
			conn = util.open("211.63.89.59", "project", "java1234");

			//1. 
			//vwTeacherIo2 출력
			//교사가 진행하고 있는 과정에서 중도탈락한 애들.
			String sql = "{call procFail(?,?)}";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, TLogin.teacherNum + "");
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
     		rs = stat.executeQuery();
     		
     		rs = (ResultSet)stat.getObject(2);
			
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[이름]\t[과정번호]\t\t[중도탈락여부]\t[중도탈락날짜]");
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			while(rs.next()) {
			
				System.out.printf( "\t\t\t%s\t%s\t\t%s\t\t%s\n"
						, rs.getString("이름"), rs.getString("과정번호"), rs.getString("중도탈락여부"), rs.getString("중도탈락날짜"));
			
			}
			
			
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로가기");
			System.out.println();
			
			System.out.print("\t\t\t메뉴를 선택해 주세요: ");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			
			if(input.toLowerCase().equals("a")) {
				//뒤로가기
				//교사 로그인 후 메뉴 화면으로 
				TLogin.tMenu();
				
			} else if(input.toLowerCase().equals("b")) {
				
				//처음으로 가기
				Menu.home();
				
			} else {
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

				System.out.println();
				System.out.println("\t\t\t"+"잘못 입력하였습니다.");
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

				T_010();
				
			}
			
			
			TLogin.tMenu();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	static void T_009() {
		
		//교사가 강의한 과정에 한해 선택하는 경우 모든 교육생의 출결을 조회
		// : 교사가 강의한 과정
		
		Connection conn = null;
		Statement stat2 = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		CallableStatement stat = null;
		
		

		try {

			conn = util.open("211.63.89.59", "project", "java1234");
			
			
			//교사가 강의한 과정 목록 
			String sql = "{ call procTeacherClass(?,?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, TLogin.teacherNum +"");
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
     		rs = stat.executeQuery();
     		
     		rs = (ResultSet)stat.getObject(2);
     		
     		//과정 번호
     		ArrayList<String> list = new ArrayList<String>();
     		

     		
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		    System.out.println();

     		System.out.println("\t\t\t[과정번호]\t[과정명]");
     		System.out.println();
     		
     		while(rs.next()) {
     			
     			System.out.printf("\t\t\t%s\t%s\n"
     								,rs.getString("과정번호"),rs.getString("과정명"));
     			
     			list.add(rs.getString("과정번호"));
     			
     		}
     		
     		
     		
     		System.out.println();
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

     		System.out.print("\t\t\t과정번호를 입력해 주세요: ");
     		Scanner scan = new Scanner(System.in);
     		int classNum = scan.nextInt();
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

     		
			if(!list.contains(classNum+"")) {
				
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

				System.out.println();
				System.out.println("\t\t\t"+"존재하지 않는 과정번호 입니다.");
				System.out.println("\t\t\t"+"다시 입력해 주세요.");
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

				

			}
     		
     		
     		//과정 선택한 경우 교육생 출결정보 출력 
     		//procStudentAttend
     		
     		sql = "{call procStudentAttend(?,?)}";
     		
			stat = conn.prepareCall(sql);
			
     		
     		stat.setInt(1, classNum);
     		stat.registerOutParameter(2, OracleTypes.CURSOR);
     		
     		rs = stat.executeQuery();
     		
     		
     		rs = (ResultSet)stat.getObject(2);
     		
     		//페이징 할 목록
     		ArrayList<String> list2 = new ArrayList<String>();
     		
     		
     		
     		System.out.println();
     		System.out.println("\t\t\t[No.]\t[이름]\t[출근시간]\t\t[퇴근시간]\t\t[근태상황]\t[날짜]");
     		
     		while(rs.next()) {
     			
     			list2.add(rs.getString("이름")+"\t"+rs.getString("출근시간")+"\t"+rs.getString("퇴근시간")+"\t"+rs.getString("근태상황")+"\t"+rs.getString("날짜"));
     		}
     		
     		page(list2);
     		
     		System.out.println();
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     		System.out.println("\t\t\ta.뒤로가기");
     		System.out.println("\t\t\tb.처음으로 가기");
     		System.out.println();
     		System.out.print("\t\t\t메뉴를 선택해 주세요: ");
     		
     		scan = new Scanner(System.in);
     		String input = scan.nextLine();
     		
     		if(input.toLowerCase().equals("a")) {
     			//뒤로가기
     			TLogin.tMenu();
     			
     		} else if(input.toLowerCase().equals("b")) {
     			//처음으로 가기
     			Menu.home();
     			
     		} else {
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     			System.out.println();
     			System.out.println("\t\t\t"+"잘못 입력하였습니다.");
     			System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

     		}
     		
     		
     		TLogin.tMenu();
     		
     		
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	static void T_007() {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		
		CallableStatement stat2;
		
		try {
			
			conn = util.open("211.63.89.59", "project", "java1234");
			
			//vwTeacherIo2 출력
			//교사가 강의하고 있는 과정 목록 
			
			
			//과정명, 과정기간, 강의실 출력 ( 공통된 부분 1번만 출력 )
			String sql = "{call procPreList(?,?)}";
			
			stat2 = conn.prepareCall(sql);
			
			stat2.setString(1, TLogin.teacherNum + "");
			stat2.registerOutParameter(2, OracleTypes.CURSOR);
			
     		rs = stat2.executeQuery();
     		
     		rs = (ResultSet)stat2.getObject(2);
			
     		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     		System.out.println("\t\t\t[과정명]\t\t\t\t\t[과정 시작 날짜]\t\t\t[과정 끝 날짜]\t\t\t[강의실]");
     		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

     		
     		
			if(rs.next()) {
				
				System.out.printf("\t\t\t%s\t\t\t%s\t\t%s\t\t%s\n"
									,rs.getString("과정명"),rs.getString("시작날짜"),rs.getString("끝날짜"),rs.getString("강의실"));
				
				
			}
			
			System.out.println();
			System.out.println();
			
			
			//그 과정에 해당하는 과목 목록 출력 
			sql = "{call procSubjectOut(?,?)}";
			
			stat2 = conn.prepareCall(sql);
			
			stat2.setString(1, TLogin.teacherNum + "");
			stat2.registerOutParameter(2, OracleTypes.CURSOR);
			
     		rs = stat2.executeQuery();
     		
     		rs = (ResultSet)stat2.getObject(2);
     		
     		ArrayList<String> list = new ArrayList<String>();
     		
     		
     		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     		System.out.println("\t\t\t[과목번호]\t[과목명]\t\t\t[과목 시작 날짜]\t[과목 끝 날짜]\t\t[교재명]\t\t\t\t[출결 배점]\t\t[필기 배점]\t\t[실기 배점]\t[성적 등록여부]");
     		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			
			while(rs.next()) {
				
				System.out.printf( "\t\t\t%s\t%s\t\t\t%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t%s\n"
						, rs.getString("과목번호"), rs.getString("과목명"), rs.getString("과목시작"), rs.getString("과목끝")
						, rs.getString("교재명"), rs.getString("출결"), rs.getString("필기"), rs.getString("실기"), rs.getString("성적등록여부"));
				
				list.add(rs.getString("과목번호"));
			
			}
			
			
			//과목번호 선택 
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로가기");
			
			System.out.println();
			System.out.print("\t\t\t과목 번호를 선택해 주세요: ");
			Scanner scan = new Scanner(System.in);
			String subNum = scan.nextLine();
			
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			
			
			if(subNum.toLowerCase().equals("a")) {
				//뒤로가기
				//교사 로그인 후 메뉴 선택 화면 
				TLogin.tMenu();
				
				
			} else if(subNum.toLowerCase().equals("b")) {
				
				//처음으로가기
				Menu.home();

				
			}
			
			
			if(!list.contains(subNum)) {
				
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t"+"존재하지 않는 과목번호 입니다.");
				System.out.println("\t\t\t"+"다시 입력해 주세요.");
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			}
			
			
			//교육생 정보 및 성적 출력 
			//procStudentScoreOut
	
     		
			sql = "{ call procStudentInfo(?,?)}";
			
			stat = conn.prepareCall(sql);
			
			//선택한 과목번호 매개변수로 ,, 
			stat.setString(1,subNum);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			rs = stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(2);
			
			System.out.println("\t\t\t[학생번호]\t[이름]\t[전화번호]\t\t\t[수료 및 탈락]\t[출결점수]\t\t[필기점수]\t\t[실기점수]");
			
			while(rs.next()) {
				
				if(rs.getString("수료및탈락").equals("중도탈락") || rs.getString("수료및탈락").isEmpty()) {
					
					System.out.printf("\t\t\t%s\t%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n"
							,rs.getString("학생번호"),rs.getString("이름"),rs.getString("전화번호"),rs.getString("수료및탈락")
							,null,null,null);
					
				} else {
					
				
				System.out.printf("\t\t\t%s\t%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n"
									,rs.getString("학생번호"),rs.getString("이름"),rs.getString("전화번호"),rs.getString("수료및탈락")
									,rs.getString("출결점수"),rs.getString("필기점수"),rs.getString("실기점수"));
				
				}
				
			}
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로가기");
			System.out.println();
			
			System.out.print("\t\t\t메뉴를 선택해 주세요: ");
			
			scan = new Scanner(System.in);
			String num = scan.nextLine();
			
			
			if(num.toLowerCase().equals("a")) {
				//뒤로가기
				//교사 로그인 후 메뉴 선택 화면 
				//TLogin.tMenu();
				T_007();
				
			} else if(num.toLowerCase().equals("b")) {
				
				//처음으로가기
				Menu.home();
				
			} else {
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t"+"메뉴를 잘못 입력하였습니다.");
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			}
			
			
			TLogin.tMenu();
			
			
			rs.close();
			stat.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	static void T_004() {
		
		//마친 과목에 대한 성적 입출력 
		//procStudentList
		
		Connection conn = null;
		CallableStatement stat = null;
		
		Statement stat2 = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		
		try {
			
			conn = util.open("211.63.89.59", "project", "java1234");
			//1. 
			//vwTeacherIo2 출력
			//교사가 강의한 과목중 끝난 과목 목록 
			String sql = String.format("select * from vwTeacherIo2 where 교사번호 = %d" , TLogin.teacherNum);
			
			stat2 = conn.createStatement();
			
     		rs = stat2.executeQuery(sql);
     		
     		ArrayList<String> list = new ArrayList<String>();
			
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[과목번호]\t[과목명]");
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			while(rs.next()) {
			
				System.out.printf( "\t\t\t%s\t%s\n"
						, rs.getString("과목번호"), rs.getString("과목이름"));
			
				list.add(rs.getString("과목번호"));
			}
			
			System.out.println();
			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로가기");
			System.out.println();
			
			System.out.print("\t\t\t과목번호를 선택해 주세요: ");
			Scanner scan = new Scanner(System.in);
			String num = scan.nextLine();
			System.out.println();
			
			if(num.toLowerCase().equals("a")) {
				
				//뒤로가기
				//교사 로그인 후 메뉴 선택 화면 
				TLogin.tMenu();
				
			} else if(num.toLowerCase().equals("a")) {
				//처음으로가기
				Menu.home();
				
			} 
			
			
			
			if(!list.contains(num)) {
				//과목번호에 해당하지 않는 값을 입력 했을 때 
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t"+"존재하지 않는 과목번호 입니다.");
				System.out.println("\t\t\t"+"다시 입력해 주세요.");
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				T_004();
			}
			
			
			//2.
			//procStudentList
			//선택한 과목에 해당하는 학생 정보,점수 출력
			
			//procStudentList
			sql = "{ call procStudentList(?,?) }";
			stat = conn.prepareCall(sql);
			
			
			stat.setString(1, num);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			
			rs = (ResultSet)stat.getObject(2);
			
			//학생번호 
			ArrayList<String> list2 = new ArrayList<String>();
			
			//페이징 할 목록 
			ArrayList<String> list3 = new ArrayList<String>();
			
			int index = 0 ;
			
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[No.]\t[학생번호]\t[학생이름]\t[출결]\t[필기]\t[실기]");
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			
			while(rs.next()) {
				
				
				list2.add(rs.getString("학생번호"));
				
				//arrayList에 추가하기
				list3.add(rs.getString("학생번호")+"\t"+rs.getString("학생이름")+"\t"+rs.getString("출결")+"\t"+rs.getString("필기")+"\t"+rs.getString("실기"));

				index++;
			}
			
			
			page(list3);
			
			
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.print("\t\t\t학생 번호를 선택해 주세요: ");
			scan = new Scanner(System.in);
			int snum = scan.nextInt();
			
			//잘못된 학생번호 입력했을 때 
			if(!list2.contains(snum+"")) {
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t"+"존재하지 않는 학생번호 입니다.");
				System.out.println("\t\t\t"+"다시 입력해 주세요.");
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				T_004();
			}
			
			
			
			System.out.println();
			System.out.print("\t\t\t출결 점수를 입력해 주세요: ");
			scan = new Scanner(System.in);
			int attend = scan.nextInt();
			
			System.out.println();
			System.out.print("\t\t\t필기 점수를 입력해 주세요: ");
			scan = new Scanner(System.in);
			int write = scan.nextInt();
			
			System.out.println();
			System.out.print("\t\t\t실기 점수를 입력해 주세요: ");
			scan = new Scanner(System.in);
			int practice = scan.nextInt();
			

			
			//4.
			//성적 update
			//rs.getString("배점") > tblScore.pointSeq 대입 
			//학생 번호 = snum > tblScore.classRegseq 대입 
			sql = "{call procTeacherScoreIn(?,?,?,?,?)}";
			stat = conn.prepareCall(sql);
			
			stat.setString(1, num); //과목번호 
			stat.setInt(2, attend); //출결 점수
			stat.setInt(3, write); //필기 점수
			stat.setInt(4, practice); //실기 점수 
			stat.setInt(5, snum);  //학생 번호 

			
			stat.executeQuery();
			
			System.out.println();
			System.out.println("\t\t\t점수가 정상적으로 입력되었습니다.");
			System.out.println();
			
			
			TLogin.tMenu();
			
			
			rs.close();
			stat2.close();
			

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private static void T_002() {
		
		
		//vwSubjectOut
		
		Connection conn = null;
		Statement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		CallableStatement stat2;
		
		try {
			
			
			
			//과목번호, 과정명, 과정기간(시작 년월일, 끝 년월일), 강의실, 과목명, 과목기간(시작 년월일, 끝 년월일), 교재명, 출결, 필기, 실기 배점 출력
			conn = util.open("211.63.89.59", "project", "java1234");
			
			
			stat = conn.createStatement();
			
			
			//과정명, 과정기간, 강의실 출력 ( 공통된 부분 1번만 출력 )
			String sql = "{call procPreList(?,?)}";
			
			stat2 = conn.prepareCall(sql);
			
			stat2.setString(1, TLogin.teacherNum + "");
			stat2.registerOutParameter(2, OracleTypes.CURSOR);
			
     		rs = stat2.executeQuery();
     		
     		rs = (ResultSet)stat2.getObject(2);
			
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     		System.out.println("\t\t\t[과정명]\t\t\t\t\t[과정 시작 날짜]\t\t\t[과정 끝 날짜]\t\t\t[강의실]");
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

     		
     		
			if(rs.next()) {
				
				System.out.printf("\t\t\t%s\t\t\t\t\t%s\t\t\t%s\t\t\t%s\n"
									,rs.getString("과정명"),rs.getString("시작날짜"),rs.getString("끝날짜"),rs.getString("강의실"));
				
				
			}
			
			System.out.println();
			System.out.println();
			
			
			//그 과정에 해당하는 부분 출력 
			sql = String.format("select * from vwSubjectOut where 교사번호 = %d" , TLogin.teacherNum );
			
     		rs = stat.executeQuery(sql);
			
    		ArrayList<String> list = new ArrayList<String>();

    		System.out.println("\t\t\t[과목번호]\t[강의실]\t[과목명]\t\t\t[과목 시작 날짜]\t[과목 끝 날짜]\t\t[교재명]\t\t\t\t[출결 배점]\t\t\t\t[필기 배점]\t\t\t\t[실기 배점]");
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			while(rs.next()) {
			
				System.out.printf( "\t\t\t%s\t%s\t%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("과목번호"), rs.getString("과목명"), rs.getString("과목시작"), rs.getString("과목끝")
						, rs.getString("교재명"), rs.getString("출결"), rs.getString("필기"), rs.getString("실기"));
				
				list.add(rs.getString("과목번호"));
			
			}
			
			
			//과목번호 선택
			System.out.println();
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.println("\t\t\t" + "a.뒤로가기");
			System.out.println("\t\t\t" + "b.처음으로 가기");
			System.out.println();
			System.out.print("\t\t\t과목번호를 선택해 주세요: ");
			Scanner scan = new Scanner(System.in);
			int subjectNum = scan.nextInt();
			System.out.println();
			
			
			if((subjectNum+"").toLowerCase().equals("a")) {
				
				//뒤로가기
				//교사 로그인 후 메뉴 선택 화면으로 이동
				TLogin.tMenu();
				
			} else if((subjectNum+"").toLowerCase().equals("b")) {
				
				//처음으로 가기
				Menu.home();
				
			}
			
			
			
			if(!list.contains(subjectNum+"")) {
				//과목번호에 해당하지 않는 값을 입력 했을 때 
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t존재하지 않는 과목번호 입니다.");
				System.out.println("\t\t\t다시 입력해 주세요.");
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				
			}
			
			//배점 입력 
			pointIn(subjectNum + "",conn, list);
			
			//시험 점수, 날짜 입력 
			testIn(subjectNum + "",conn);
			
			
			TLogin.tMenu();
			
			rs.close();
			stat.close();
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	static void T_001_out() {
		//교사가 담당하고 있는 과목에 대한 성적 처리를 위해서 배점 출력
		//procTeacherPointOut
		
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		Statement stat2 = null;	//view 출력 
		
		try {
			
			

			conn = util.open("211.63.89.59" , "project", "java1234");
			stat2 = conn.createStatement();
			

			String sql = String.format("select * from vwTeacherIo2 where 교사번호 = %d" , TLogin.teacherNum);
			
     		rs = stat2.executeQuery(sql);
			
     		System.out.println();
     		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[과목번호]\t[과목명]\t\t\t\t\t[출결배점]\t[필기배점]\t[실기배점]");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			while(rs.next()) {
			
				System.out.printf( "\t\t\t%s\t%-10s\t\t\t%s\t\t%s\t%s\n"
						, rs.getString("과목번호"), rs.getString("과목이름")
						, rs.getString("출결배점"), rs.getString("필기배점"), rs.getString("실기배점"));
			
			}
			
			
			rs.close();
			stat2.close();
			
			
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t" + "a.뒤로가기");
			System.out.println("\t\t\t" + "b.처음으로 가기");
			System.out.println();
			
			System.out.print("\t\t\t메뉴를 선택해 주세요: ");
			Scanner scan = new Scanner(System.in);
			String num = scan.nextLine();
			
			if(num.toLowerCase().equals("a")) {
				
				//뒤로 가기
				//교사 로그인 후 메뉴 선택 화면 
				TLogin.tMenu();
				
			} else if (num.toLowerCase().equals("b")) {
				
				//처음으로 가기 
				Menu.home();
				
			} else {
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t"+"잘못 입력하였습니다.");
				System.out.println("\t\t\t다시 입력해 주세요.");
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				T_001_out();
			}
			
			
			TLogin.tMenu();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}//T_001_out


	static void T_001_in() {
		// 교사가 강의를 마친 과목에 대한 성적 처리를 위해서 배점 입력
		//procTeacherPointIn
		
		
		Connection conn = null;
		CallableStatement stat = null;
		Statement stat2 = null;	//view 출력 
		DBUtil util = new DBUtil();
		ResultSet rs = null; 

		
		//과목번호 담을 배열 
		ArrayList<String> list = new ArrayList<String>();

		
		
		try {
			
			//로그인한 교사 번호(teacherNum) > view에 교사번호랑 일치하는 것만 출력해줌.
			//교사가 현재 진행하고 있는 과정의 해당하는 과목 목록 출력 			
			
			
			conn = util.open("211.63.89.59" , "project", "java1234");
			stat2 = conn.createStatement();
			

			String sql = String.format("select * from vwTeacherIo2 where 교사번호 = %d" , TLogin.teacherNum);
			
     		rs = stat2.executeQuery(sql);
			
     		System.out.println();
     		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[과목번호]\t[과목명]\t\t\t\t\t\t[시작날짜-끝날짜]");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			while(rs.next()) {

				
				System.out.printf( "\t\t\t%s\t%-10s\t\t\t%20s - %s\n"
						, rs.getString("과목번호"), rs.getString("과목이름")
						, rs.getString("시작날짜"), rs.getString("끝날짜"));
				
				
				list.add(rs.getString("과목번호"));
				
		
			
			}
			
			
			
			rs.close();
			stat2.close();
		
			Scanner scan;
			
			int subjectNum = 0 ;
			
			//배점 입력
			subjectNum = pointIn(subjectNum + "",conn, list);

				
			//시험날짜,문제 입력 	
			testIn(subjectNum+"", conn);
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}//T_001_in

	
	
	//시험 입력 
	private static void testIn(String subjectNum, Connection conn) throws SQLException {
		CallableStatement stat;
		String sql;
		Scanner scan;
		while (true) {
			//시험날짜 , 시험문제 추가
			//procTeacherTestAdd
			sql = "{ call procTeacherTestAdd(?,?,?,?,?) }";
			
			stat = conn.prepareCall(sql);
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			System.out.print("\t\t\t시험날짜를 입력해 주세요('yy/mm/dd'): ");
			scan = new Scanner(System.in);
			String testDate = scan.nextLine();
			


			System.out.println();	
			System.out.print("\t\t\t필기 문제를 입력해 주세요: ");
			scan = new Scanner(System.in);
			String writeTest = scan.nextLine();
			
			
			System.out.println();
			System.out.print("\t\t\t실기 문제를 입력해 주세요: ");
			
			scan = new Scanner(System.in);
			String practiceTest = scan.nextLine();
			System.out.println();


			
			stat.setString(1,subjectNum); 
			stat.setString(2,testDate); 
			stat.setString(3,writeTest); 
			stat.setString(4,practiceTest); 
			stat.registerOutParameter(5, OracleTypes.NUMBER);
			
			stat.executeQuery();
			
			
			
			if(stat.getInt(5) != 1) {
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
		        System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				continue;
			}
			
			
			System.out.println();
			System.out.println("\t\t\t정상적으로 추가 되었습니다.");
			System.out.println();
			
			
		    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로가기");
			System.out.println();
			System.out.print("\t\t\t메뉴를 선택해 주세요: ");
			
			scan = new Scanner(System.in);
			String input = scan.nextLine();
			
			if(input.toLowerCase().equals("a")) {
				
				//뒤로가기
				T_001_in();
				
			} else if(input.toLowerCase().equals("b")) {
				
				//처음으로 가기 
				Menu.home();
			}
			
			
			stat.close();
			
			break;
			
		}//while
	}


	
	
	//배점 입력 
	private static int pointIn(String subjectNum, Connection conn , ArrayList<String> list) throws SQLException {
		CallableStatement stat;
		String sql;
		Scanner scan = null;
		
		while (true) {
			//목록 과목 번호 선택 
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.println("\t\t\t" + "a.뒤로가기");
			System.out.println("\t\t\t" + "b.처음으로 가기");
			System.out.println();
			System.out.print("\t\t\t과목 번호를 선택해 주세요: ");
			
			
			scan = new Scanner(System.in);
			subjectNum = scan.nextLine();

			
			
			if(subjectNum.toLowerCase().equals("a")) {
				//T_001_in();
				//뒤로가기
				//교사 로그인 후 메뉴 선택 화면 
				TLogin.tMenu();
				
			} else if(subjectNum.toLowerCase().equals("b")) {
				//처음으로 가기
				Menu.home();
			}
			
			
			if(!list.contains(subjectNum)) {
				//과목번호에 해당하지 않는 값을 입력 했을 때 
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t존재하지 않는 과목번호 입니다.");
				System.out.println("\t\t\t다시 입력해 주세요.");
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				T_001_in();
			}

			

			sql = "{ call procTeacherPointIn(?, ?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			System.out.println();
			System.out.print("\t\t\t출결배점을 입력해 주세요: ");
			
			scan = new Scanner(System.in);
			String attend = scan.nextLine();


			
			System.out.println();
			System.out.print("\t\t\t필기배점을 입력해 주세요: ");
			scan = new Scanner(System.in);
			String write = scan.nextLine();

			
			
			System.out.println();
			System.out.print("\t\t\t실기배점을 입력해 주세요: ");
			scan = new Scanner(System.in);
			String practice = scan.nextLine();
			System.out.println();
			
			
			stat.setString(1, subjectNum);
			stat.setString(2, attend);
			stat.setString(3, write);
			stat.setString(4, practice);
			stat.registerOutParameter(5, OracleTypes.NUMBER);
			
					
			stat.executeQuery();

			
			if(stat.getInt(5) != 1) {
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
		        System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
				System.out.println();
			    System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				//T_001_in();
				continue;
			}
			
			System.out.println();
			System.out.println("\t\t\t배점 입력이 정상적으로 완료되었습니다.");
			System.out.println();

			
			return Integer.parseInt(subjectNum);
		}//while

	}
	
	

}
