package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class Student_Attend {
	
	DBUtil util = new DBUtil();
	Student_Login main = new Student_Login();
	
	private static Scanner scan;
	String word;
	String word2;
	
	static {
		scan = new Scanner(System.in);
	} 
	
	public void menu() {
		
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
		System.out.println("\t\t\t1. 전체조회");
		System.out.println();
		System.out.println("\t\t\t2. 월별 조회");
		System.out.println();
		System.out.println("\t\t\t3. 일별 조회");
		System.out.println();
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println();
		System.out.println("\t\t\tb. 처음으로가기");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
		System.out.print("\t\t\t번호를 누르세요 : ");
		word = scan.nextLine();
		System.out.println();
		
		//출결 조회 - 전체 조회
		if (word.equals("1")) {
			Total();
		//출결 조회 - 월별 조회
		} else if (word.equals("2")) {
			Month();
		//출결 조회 - 일별 조회
		} else if (word.equals("3")) {
			Day();
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\ta. 뒤로 가기");
			System.out.println("\t\t\tb. 처음으로 가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t입력하세요 : ");
			word2 = scan.nextLine();
			if (word2.equals("a")) {
				menu();
			} else if (word2.equals("b")) {
				
			} else {
				pause(scan);
			}
		} else if (word.equals("a")) {
			//뒤로가기
			Student_Menu student = new Student_Menu();
			student.menu();
			
		} else if (word.equals("b")) {
			//처음으로 가기 
			Menu home = new Menu();
			home.home();
			
		} else {	
			System.out.println("잘못 입력하였습니다.");
			System.out.println("다시 입력해 주세요.");
			pause(scan);
		}
		
	}//menu
	
	//전체 조회
	public void Total() {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			
			//서버 연결
			conn = util.open("211.63.89.59","project","java1234");	
			//conn = util.open("localhost", "project", "java1234");
			
			String sql = "call procStdAttend( ?, ?, ? )";
			
			//프로시저 저장
			stat = conn.prepareCall(sql);
			
			//1번은 입력받는 매개변수, 2번은 돌려주는(return) 값
			stat.setString(1, main.id);
			stat.setString(2, main.pwd);
			stat.registerOutParameter(3, OracleTypes.CURSOR);
			
			//실행
			stat.executeQuery();
			
			//cursor값을 결과셋에 저장
			rs = (ResultSet)stat.getObject(3);
			
			//출력문
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\tNo.\t\t\t\t일자\t\t\t\t상태");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			while (rs.next()) {
	
//				System.out.printf("\t\t\t%s\t%s\n"  , rs.getString("일자")
//													, rs.getString("상태"));
				list.add("\t\t\t" + rs.getString("일자") + "\t" + rs.getString("상태") + "\n");
				
			}//while
			
			page(list);
			
			System.out.println();
			
			//close
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("\t\t\t" + e);
		}
		
	}//Total
	
	//월별 조회
	public void Month() {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			
			//서버 연결
			conn = util.open("211.63.89.59","project","java1234");	
			//conn = util.open("localhost", "project", "java1234");
			
			String sql = "call procStdAttendMonth( ?, ?, ?, ? )";
			
			System.out.print("\t\t\t조회할 월(月)을 입력하세요 : ");
			String month = scan.nextLine();
			
			
			if(!(month.equals("1") || month.equals("2") || month.equals("3") ||month.equals("4") || month.equals("5") 
					|| month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9") || month.equals("10") 
					|| month.equals("11") || month.equals("12"))) {
				
				//유효한 달이 없으면 
				System.out.println();
				System.out.println("\t\t\t잘못된 월을 입력하였습니다.");
				
				System.out.println("\t\t\t다시 입력해 주세요.");
				System.out.println();
				Month();
				
			}
				
			
			
			
			
			
			//프로시저 저장
			stat = conn.prepareCall(sql);
			
			//1~3번은 입력받는 매개변수, 4번은 돌려주는(return) 값
			stat.setString(1, main.id);
			stat.setString(2, main.pwd);
			stat.setString(3, month);
			stat.registerOutParameter(4, OracleTypes.CURSOR);
			
			//실행
			stat.executeQuery();
			
			//cursor값을 결과셋에 저장
			rs = (ResultSet)stat.getObject(4);
			
			//출력문
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\tNo.\t\t\t\t일자\t\t\t상태");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			while (rs.next()) {
	
//				System.out.printf("\t\t\t%s\t%s\n"  , rs.getString("일자")
//													, rs.getString("상태"));
				
				list.add("\t\t\t" + rs.getString("일자") + "\t" + rs.getString("상태") + "\n");
				
			}//while
			
			page(list);
			
			System.out.println();
			
			//close
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("\t\t\t" + e);
		}
		
	}//Month
	
	//일별 조회
	public void Day() {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {
			
			//서버 연결
			conn = util.open("211.63.89.59","project","java1234");	
			//conn = util.open("localhost", "project", "java1234");
			
			String sql = "call procStdAttendDay( ?, ?, ?, ?, ? )";
			
			System.out.print("\t\t\t조회할 월(月)을 입력하세요 : ");
			String month = scan.nextLine();			
			System.out.print("\t\t\t조회할 일(日)을 입력하세요 : ");
			String day = scan.nextLine();
			
			//일 비교 하기 위해 . 1~31일 까지 
			ArrayList<String> list = new ArrayList<String>();
			
			for(int i=0; i<32; i++) {
				String result = String.format("%02d", i);
				list.add((result));
			}
			
	
			
			//일 검사 
			//월 잘못 입력 
			if(!(month.equals("1") || month.equals("2") || month.equals("3") ||month.equals("4") || month.equals("5") 
					|| month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9") || month.equals("10") 
					|| month.equals("11") || month.equals("12"))) {
				
				//유효한 달이 없으면 
				System.out.println("\t\t\t잘못된 월을 입력하였습니다.");
				System.out.println("\t\t\t다시 입력해 주세요.");
				Day();
				
			} 
			
			//월 알맞게 입력 
			if(month.equals("1") || month.equals("2") || month.equals("3") ||month.equals("4") || month.equals("5") 
					|| month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9") || month.equals("10") 
					|| month.equals("11") || month.equals("12")){
				
				//알맞은 달을 입력하면
				if(!list.contains(day)) {
					//일 비교
					//유효하지 않는 일을 입력하면 
					System.out.println("\t\t\t잘못된 일을 입력하였습니다.");
					System.out.println("\t\t\t다시 입력해주세요.");
					Day();
					
				}
				
			}
			
			
			
			
			
			
			//프로시저 저장
			stat = conn.prepareCall(sql);
			
			//1~4번은 입력받는 매개변수, 5번은 돌려주는(return) 값
			stat.setString(1, main.id);
			stat.setString(2, main.pwd);
			stat.setString(3, month);
			stat.setString(4, day);
			stat.registerOutParameter(5, OracleTypes.CURSOR);
			
			//실행
			stat.executeQuery();
			
			//cursor값을 결과셋에 저장
			rs = (ResultSet)stat.getObject(5);
			
			//출력문
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t일자\t\t\t상태");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			while (rs.next()) {
	
				System.out.printf("\t\t\t%s\t%s\n"  , rs.getString("일자")
													, rs.getString("상태"));
				
			}//while
			
			System.out.println();
			
			//close
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("\t\t\t" + e);
		}
		
	}//Day
	
	public void page(List<String> arrayList) {
        
        
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
          System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
          System.out.printf("\t\t\t"+"%d페이지 입니다.\n", count + 1);
          System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
          System.out.println("\t\t\t"+"1. 이전 페이지");
          System.out.println("\t\t\t"+"2. 다음 페이지");
          System.out.println();
          System.out.println();
          System.out.println("\t\t\t"+"a. 뒤로가기");
          System.out.println("\t\t\t"+"b. 처음으로가기");
          System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
          //번호 고르기
          Scanner scan = new Scanner(System.in);
          System.out.print("\t\t\t"+"입력:  ");
          String answer = scan.nextLine();
          scan.skip("\r\n"); //엔터 무시
          System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
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
             menu();
             
          } else if(answer.equals("b")) {
             
             //처음으로 가기
             //home();
          }
             
          
          else {
             //System.out.println("\t\t\t"+"페이지 프로그램을 종료합니다.");
             
             
             break;
          }
          
          
          
          
       } //while

       }
       
       
    }
	
	private static void pause(Scanner scan) {
		
		System.out.println();
		System.out.println("\t\t\t잘못 입력했습니다.");
		System.out.println("\t\t\t계속하실려면 엔터를 입력하세요.");
		
		scan.nextLine();
		
	}//pause
	
}//Student_Attend