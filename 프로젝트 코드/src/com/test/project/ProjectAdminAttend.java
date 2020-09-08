package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class ProjectAdminAttend {
	static Scanner scan;
	
	static {

		scan = new Scanner(System.in);
		
	}
	
	
	

	public void adminAttendMain() {
		
		while(true) {
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t1. 과정별 교육생 출결 현황");
			System.out.println("\t\t\t2. 교육생 개인별 출결 조회");
			System.out.println();
			System.out.println();
			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로 가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t번호를 입력해주세요 : ");
			String AdminInput = scan.nextLine();

			if (AdminInput.equals("1")) {	
				// 과정별 출결 조회 선택시
				
				classAttendFirst();	
				classAttendSecond();
				
				break;
			} else if (AdminInput.equals("2")) { 
				// 교육생 개인별 출결 조회 선택시
				StudentAttendFirst();
				studentAttendSecond();
				
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





	private static void classAttendSecond() { //출결현황 조회 - 과정별 출결 - 두번째화면
		
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t출결조회 - 과정별 출결조회");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓");


		Connection conn = null;	//Connection 클래스생성
		CallableStatement stat = null;	//CallableStatement 클래스생성 - 프록시저 전용 Statement
		DBUtil util = new DBUtil();
		ResultSet rs= null;
		// 페이징 배열
		List<String> list = new ArrayList<String>();
		
		try {
			
			String sql = "{ call procSelectClassStudentAttend(?,?,?,?) }";	//프록시저호출
			
			System.out.print("\t\t\t검색할 과정번호를 입력하세요 : ");
			
			String num = scan.nextLine();
			
			System.out.print("\t\t\t검색할 시작날짜 ex)20/04/25) : ");
			
			String indate = scan.nextLine();
			
			System.out.print("\t\t\t검색할 끝날짜 ex)20/05/02) : ");
			
			String outdate = scan.nextLine();

			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t교육생이름 \t날짜\t\t출퇴근시간\t\t\t출결상황");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			//DB연결
			conn = util.open();
			stat = conn.prepareCall(sql);
			
			stat.setString(1,num);		//검색할 과정번호
			stat.setString(2,indate);	//검색할 시작날짜
			stat.setString(3,outdate);	//검색할 끝날짜
			stat.registerOutParameter(4, OracleTypes.CURSOR);	//커서를 통해 값을 가져온다. 
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(4);	//해당하는값을 결과셋에 넣어준다.
			
			
			
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			
			while(rs.next()) {
				
				System.out.print("\t\t\t");
				
				
				list.add(rs.getString("studentname") + "\t"+
						rs.getString("attendday") + "\t"+
						rs.getString("attendindate") + "~"+
						rs.getString("attendoutdate") + "\t"+
						rs.getString("state"));
				
			}
			
			page(list);
			
			
			rs.close();
			stat.close();
			conn.close();
			
			
		} catch (Exception e) {

		}
		
	}
	
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
          System.out.println();
          System.out.println();
           System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
          System.out.printf("\t\t\t"+"%d페이지 입니다.\n", count + 1);
           System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
          System.out.println("\t\t\t"+"1. 이전 페이지");
          System.out.println("\t\t\t"+"2. 다음 페이지");
          System.out.println();
          System.out.println("\t\t\t"+"a. 뒤로가기");
          System.out.println("\t\t\t"+"b. 처음으로가기");
           System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");   
          //번호 고르기
          Scanner scan = new Scanner(System.in);
          System.out.print("\t\t\t"+"입력:  ");
          String answer = scan.nextLine();
          //scan.skip("\r\n"); //엔터 무시
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
             
             ProjectAdminAttend adAttend = new ProjectAdminAttend();
             adAttend.adminAttendMain();
             
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


	private static void StudentAttendFirst() {
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t출결조회 - 학생별 출결조회");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓");
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs= null;
		
		try {
			
			String sql = "{ call procStudentSpecificAttend(?,?) }";
			
			
			
			System.out.print("\t\t\t출결조회 - 학생이름을 입력하세요 : ");
			
			String name = scan.nextLine();
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t교육생번호\t교육생이름\t과정명\t\t\t\t과정기간");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			conn = util.open();
			stat = conn.prepareCall(sql);
			
			stat.setString(1,name);
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(2);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			
			while(rs.next()) {
				
				System.out.print("\t\t\t");	
				System.out.print(rs.getString("studentseq")+ "\t");
				System.out.print(rs.getString("studentname")+ "\t");
				System.out.print(rs.getString("classname")+ "\t");
				System.out.print(rs.getString("classstartdate")+ "~");
				System.out.print(rs.getString("classenddate"));
					
				
				
				System.out.println();
			}
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			
			if(rs.getRow() == 0) {	//커서의 인덱스 값을 조회해서 값이없을 경우 해당이름이 없는것

				System.out.println("\t\t\t교육생중에'"+ name +"'(이)라는 이름이 없습니다. 다시입력해주세요.");
				StudentAttendFirst();	//다시시작한다.
			}
			
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {

		}
		
	}

	

	
	
	
	
	public static void studentAttendSecond() {
		System.out.println("\t\t\t출결조회 - 학생번호와 기간을 입력해주세요.");
		
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs= null;
		
		try {
			

			
			String sql = "{ call procPeriodAttend(?,?,?,?) }";
			
			System.out.print("\t\t\t검색할 학생번호 : " );
			
			String num = scan.nextLine();
			
			
			System.out.print("\t\t\t검색할 시작날짜ex)20/03/20) : ");
			
			String indate = scan.nextLine();
			
			System.out.print("\t\t\t검색할 끝날짜 ex)20/05/15) : ");
			
			String outdate = scan.nextLine();
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t교육생이름 \t날짜\t\t출퇴근시간\t\t\t출결상황");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			conn = util.open();
			stat = conn.prepareCall(sql);
			
			stat.setString(1,num);
			stat.setString(2,indate);
			stat.setString(3,outdate);
			stat.registerOutParameter(4, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(4);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			
			
			while(rs.next()) {
				
				System.out.print("\t\t\t");
				System.out.print(rs.getString("studentname")+"\t");	//교육생이름 출력
				System.out.print(rs.getString("attendday")+"\t");	//교육생이름 출력
				System.out.print(rs.getString("attendindate")+"~");	//출근시간 출력
				System.out.print(rs.getString("attendoutdate")+"\t");	//퇴근시간 출력
				System.out.print(rs.getString("state"));	//츨결상황 출력

				System.out.println();
			}
			while(true) {
				
				
				
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\ta.뒤로가기");
				System.out.println("\t\t\tb.처음으로가기");
				
				System.out.print("\t\t\t돌아갈화면을 입력하세요. : ");
				
				String back = scan.nextLine();
				
				if(back.equals("a")) {
					ProjectAdminAttend adAttend = new ProjectAdminAttend();	//관리자 - 출결관리 객체생성
					adAttend.adminAttendMain();
					
					break;
				} else if(back.equals("b")) {
					
					//home();
					break;
				} else {
					
					System.out.println("\t\t\t잘못된 입력입니다. 다시입력해주세요.");
				}
			
			rs.close();
			stat.close();
			conn.close();
			}
		} catch (Exception e) {

		}
		
	}
	
	
	



	public static void classAttendFirst() {	//출결현황 조회 - 과정별 출결 - 첫화면
	
	
	Connection conn = null;	//Connection 클래스 생성
	Statement stat = null;	//Statement 클래스 생성
	ResultSet rs = null;	//ResultSet 클래스 생성
	DBUtil util = new DBUtil();

	System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	System.out.println("\t\t\t과정번호\t과정명\t\t\t\t\t과정기간\t\t\t교사명\t강의실명");
	System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	try {
		
		
		conn = util.open();	//DB접속
		stat = conn.createStatement();//Statement 객체 생성

		String sql = "select * from vwStudentClassAttend";	//뷰에 저장된 전체정보를 가져온다.
		
		rs = stat.executeQuery(sql);	//sql 쿼리문으로 가져온 값을 Resultset에 저장
		
		// 레코드(컬럼) 정보 얻어오기
		ResultSetMetaData rsmd = rs.getMetaData();
		
			
			while(rs.next()) {
				System.out.print("\t\t\t");
				System.out.print(rs.getString("seq")+"\t");	//과정번호
				System.out.print(rs.getString("classname")+"\t");	//과정명
				System.out.print(rs.getString("startdate")+"~");	//과정시작날짜
				System.out.print(rs.getString("enddate")+"\t");		//과정끝날짜
				System.out.print(rs.getString("teachername")+"\t");	//교사명
				System.out.println(rs.getString("roomname")+"\t");	//강의실명
			}
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
		
		//접속종료
		rs.close();
		stat.close();
		conn.close();

	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
	
	
	
	

}
