package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class AdminClass {
	
	//과정번호
	static String SubjectNameInput = "";
	//scan
	static Scanner scan = new Scanner(System.in);
	//교사정보seq
	static ArrayList<String> tseq = new ArrayList<String>();
	//강의실정보seq
	static ArrayList<String> rseq = new ArrayList<String>();
	//과정정보seq
	static ArrayList<String> cseq = new ArrayList<String>();
	//교재정보seq
	static ArrayList<String> bseq = new ArrayList<String>();
	//과목정보seq
	static ArrayList<String> subseq = new ArrayList<String>();
	//개설과정정보seq
	static ArrayList<String> ocseq = new ArrayList<String>();
	//개설과목정보seq
	static ArrayList<String> osseq = new ArrayList<String>();

	
	
	public static void ClassOfAdminMain() {
		//관리자 -> 개설과정 및 개설 과목 관리 메인페이지
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t개설 과정 및 개설 과목 관리 메인 페이지");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t1. 전체 개설 과정 정보 확인");
		System.out.println("\t\t\t2. 과정 신규 등록");
		System.out.println("\t\t\t3. 현재 개설 과정 확인");
		System.out.println("\t\t\t4. 현재 개설 과정 수정");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println("\t\t\tb. 처음으로 가기");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t번호를 입력해주세요 : ");
		String UserInput = scan.nextLine();

		if (UserInput.equals("1")) {
			// 1. 전체 개설 과정 정보 확인
			CheckInfo();

		} else if (UserInput.equals("2")) {
			// 2. 과정 신규 등록
			ClassRegister();

		} else if (UserInput.equals("3")) {
			// 3. 현재 개설 과정 확인
			CheckOpenclass();
			
		} else if (UserInput.equals("4")) {
			// 4. 현재 개설 과정 수정
			UpdateOpenSubject();

		} else if (UserInput.equals("5")) {
			// 5. 현재 개설 과정 삭제
			DeleteOpenSubject();
		
		} else if (UserInput.equals("a")) {
			// a. 뒤로가기
			
			AdminMainPage.adminMain();
		} else if (UserInput.equals("b")) {
			// b. 처음으로 가기
			//  Menu home = new Menu();
			   
			 // Menu.home();
		} else {
			System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요");
		}
		
	}

	

	static void page3(List<String> arrayList) {
		// 1. 전체 개설 과정 정보 확인
        
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
             
             //뒤로가기
        	  ClassOfAdminMain();
             
          } else if(answer.equals("b")) {
     
             //처음으로 가기
        	   //Menu home = new Menu();
        	   //Menu.home();
   
          } 
             
          
          else {
             //System.out.println("\t\t\t"+"페이지 프로그램을 종료합니다.");
             
             
             break;
          }      }
       
       
       
       
    } //while

    }
    
    
	static void page2(List<String> arrayList) {
        //특성개설과정
        
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
          System.out.println();
          System.out.println();
           System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
          System.out.printf("\t\t\t"+"%d페이지 입니다.\n", count + 1);
           System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
          System.out.println("\t\t\t"+"1. 이전 페이지");
          System.out.println("\t\t\t"+"2. 다음 페이지");
          System.out.println("\t\t\t3. 작업 진행 하기");

          System.out.println();
          System.out.println("\t\t\t"+"a. 뒤로가기");
          System.out.println("\t\t\t"+"b. 처음으로가기");
           System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");   
          //번호 고르기
          Scanner scan = new Scanner(System.in);
          System.out.print("\t\t\t"+"입력:  ");
          String answer = scan.nextLine();
         // scan.skip("\r\n"); //엔터 무시
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
        	  ClassOfAdminMain();
             
          } else if(answer.equals("b")) {
     
             //처음으로 가기
        	   //Menu home = new Menu();
        	   //Menu.home();
          }else if (answer.equals("3")) {
        	  CheckSubjectInfo2222();
        	  
        	 
          } 
             
          
          else {
             //System.out.println("\t\t\t"+"페이지 프로그램을 종료합니다.");
             
             
             break;
          }
          
          
          
          
       } //while

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
          System.out.println();
          System.out.println();
           System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
          System.out.printf("\t\t\t"+"%d페이지 입니다.\n", count + 1);
           System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
          System.out.println("\t\t\t"+"1. 이전 페이지");
          System.out.println("\t\t\t"+"2. 다음 페이지");
          System.out.println("\t\t\t"+"3. 작업 진행 하기");
          System.out.println();
          System.out.println("\t\t\t"+"a. 뒤로가기");
          System.out.println("\t\t\t"+"b. 처음으로가기");
           System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");   
          //번호 고르기
          Scanner scan = new Scanner(System.in);
          System.out.print("\t\t\t"+"입력:  ");
          String answer = scan.nextLine();
         // scan.skip("\r\n"); //엔터 무시
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
        	  ClassOfAdminMain();
             
          } else if(answer.equals("b")) {
     
             //처음으로 가기
        	   //Menu home = new Menu();
        	   //Menu.home();
          } else if(answer.equals("3")) {
        	  CheckSelectSubJejct222();
          }
             
          
          else {
             //System.out.println("\t\t\t"+"페이지 프로그램을 종료합니다.");
             
             
             break;
          }
          
          
          
          
       } //while

       }
       
       
    }
	private static void DeleteOpenSubject() {
		// 5. 개설과정 삭제

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		
		//리스트 출력
		try {

			String Sql = "{ call proSubjectInfomation(?) }";
			
			conn = util.open();
			stat= conn.prepareCall(Sql);
			
			stat.registerOutParameter(1, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(1);
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("[번호]\t[과정명]\t\t\t\t\t\t[과정기간]\t\t[강의실명]\t[개설과목명]\t[교육생등록인원]");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			while (rs.next()) {
				System.out.printf("%s\t%s\t%s - %s\t%s\t\t%s\t%s\n", 
								rs.getString("번호"), 
								rs.getString("개설과정명"),
								rs.getString("시작날짜"), 
								rs.getString("마지막날짜"), 
								rs.getString("강의실명"), 
								rs.getString("개설과목"),
								rs.getString("교육생등록인원"));
			}

		} catch (Exception e) {
			System.out.println("DeleteOpenSubject()에서 에러발생");
			e.printStackTrace();
		}
		
		  
		//삭제할 항목 출력 
	  try { 
		Statement stat1 = null;
	  	System.out.println();
		System.out.print("삭제 할 과정의 번호를 입력해주세요 : ");
		String DeleteNumInput = scan.nextLine();
		
		
		String Deletesql = String.format("Delete from tblSubject where seq = %s",DeleteNumInput);

		stat1 = conn.createStatement();
		stat1.executeUpdate(Deletesql);



			System.out.println("\t\t\t삭제 완료되었습니다.");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로 가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t번호를 입력해주세요 : ");
			String UserInput2 = scan.nextLine();

			if (UserInput2.equals("a")) {
				// 뒤로가기 
				ClassOfAdminMain();

			} else if (UserInput2.equals("b")) {
				// 처음으로 가기
				//    Menu home = new Menu();
				   
				//  Menu.home();

			} else {
				System.out.println("잘못된 입력입니다. 다시입력하세요.");
			}
	  		} catch (Exception e) {
			System.out.println("DeleteOpenSubject()에서 에러발생");
			e.printStackTrace();
		}
	}

	private static void UpdateOpenSubject() {
		// 개설 과정 수정

		// 특정 개설 과정 출력	
		Connection conn = null;
        CallableStatement stat = null;
        ResultSet rs = null;
        DBUtil util = new DBUtil();

		try {
			
		  conn = util.open();
		  
		  	Statement stat1 = null;
	  
		  	System.out.println();
			System.out.println("\t\t\t1. 과정명");
			System.out.println("\t\t\t2. 과정 시작 일");
			System.out.println("\t\t\t3. 과정 종료 일");
			System.out.println("\t\t\t4. 강의실명");
			System.out.println("\t\t\t5. 개설 과목명");
			System.out.println();
			System.out.println();
			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로 가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t수정할 목록의 번호를 입력해주세요 : ");
			String UpdateUserInput2 = scan.nextLine();

			if (UpdateUserInput2.equals("1")) {
				System.out.println();
				
				ClassList();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t수정할 개설 과정 번호를 입력해주세요 : ");
				String ClassNumInput = scan.nextLine();
				System.out.print("\n\t\t\t수정할 개설 과정 이름을 입력해주세요 : ");
				String ClassNameInput = scan.nextLine();

				conn = util.open();
				
				
				String Updatesql1 = String.format("update tblClass set name = '%s' where seq = %s",ClassNameInput, ClassNumInput);
				stat1 = conn.createStatement();
				stat1.executeUpdate(Updatesql1);
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t수정 완료되었습니다.");

				stat1.close();
				conn.close();

			} else if (UpdateUserInput2.equals("2")) {
				
				Statement stat2 = null;
				System.out.println();
			
				OpenClassList();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t수정할 개설 과정 번호를 입력해주세요 : ");
				String startdateInput = scan.nextLine();
				
				System.out.println("\t\t\t수정 내용을 입력해주세요.");
				System.out.print("\n\t\t\t과정 시작 일(YY/MM/DD) : ");
				String ClassStartDate = scan.nextLine();

				conn = util.open();
				String Updatesql2 = String.format("update tblOpenclass set startdate = '%s'  where seq = %s",ClassStartDate, startdateInput);
				stat2 = conn.createStatement();
				stat2.executeUpdate(Updatesql2);
	
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t수정 완료되었습니다.");

				stat2.close();
				conn.close();

			} else if (UpdateUserInput2.equals("3")) {
				
				Statement stat3 = null;
				System.out.println();
			
				OpenClassList();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t수정할 개설 과정 번호를 입력해주세요 : ");
				String enddateInput = scan.nextLine();
				
				System.out.print("\n\t\t\t과정 종료 일(YY/MM/DD):");
				String ClassEndDate = scan.nextLine();
				conn = util.open();
			
				String Updatesql3 = String.format("update tblOpenclass set enddate = '%s' where seq = %s ",ClassEndDate, enddateInput);
				stat3 = conn.createStatement();
				stat3.executeUpdate(Updatesql3);
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t수정 완료되었습니다.");

				stat3.close();
				conn.close();

			} else if (UpdateUserInput2.equals("4")) {
				
				Statement stat4 = null;
				System.out.println();
				
				Roomlist();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t수정할 강의실 번호를 입력해주세요 : ");
				String RoomNumbInput = scan.nextLine();
				
				System.out.print("\n\t\t\t강의실 명 :");
				String ClassRoomInput = scan.nextLine();
				conn = util.open();
			
				String Updatesql4 = String.format("update tblroom set name = '%s' where seq = %s",ClassRoomInput, RoomNumbInput);
				stat4 = conn.createStatement();
				stat4.executeUpdate(Updatesql4);
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t수정 완료되었습니다.");
			
				stat4.close();
				conn.close();

			} else if (UpdateUserInput2.equals("5")) {
				
				Statement stat5 = null;
				System.out.println();
		
				SubjectList();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t수정할 개설 과목 번호를 입력해주세요 : ");
				String SubNumInput = scan.nextLine();
				System.out.print("\n\t\t\t개설 과목 명 :");
				String SubjectNameInput = scan.nextLine();
				conn = util.open();
	
				String Updatesql5 = String.format("update tblSubject set name = '%s' where seq = %s", SubjectNameInput, SubNumInput);
				stat5 = conn.createStatement();
				stat5.executeUpdate(Updatesql5);
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\t수정 완료되었습니다.");

				stat5.close();
				conn.close();
			}
			
		} catch (Exception e) {
			System.out.println("ReviseSubjectInfo() 과목명에서 에러 발생");
		}

		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println("\t\t\tb. 처음으로 가기");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t번호를 입력해주세요 : ");
		String UserInput4 = scan.nextLine();

		if (UserInput4.equals("a")) {
			// 뒤로가기 
			ClassOfAdminMain();
			
		} else if (UserInput4.equals("b")) {
			// 처음으로 가기
			//  Menu home = new Menu();
			   
			  // Menu.home();

		} else {
			System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요.");
		}

	}

	private static void NewResisterSubject() {
		// 3-2. 개설 과목 신규 등록 페이지
		
		DBUtil util = new DBUtil();
	    ResultSet rs = null;
	  
	    
	    try {
	    	 String SubjectSql1 = "insert into tblSubject(seq,name,type,subjectPeriod,bookSeq) values (tblSubject_seq.nextVal, ?, ?, ? ,?)";
	 	     String OpenSubjectSql1 = "insert into tblOpenSubject(seq,startDate,endDate,OpenClassSeq,subjectSeq) values (tblOpenSubject_seq.nextVal, ?, ?, ? ,?)";
	 	    
	 	   Connection conn = null;
	 	    
	    	//과목정보 입력
			PreparedStatement stat0 = null;
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t 신규 개설 과목  추가 페이지입니다.");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t아래 내용을 입력해주세요.");
			System.out.print("\t\t\t1. 개설 과목명 : ");
			String SubjectNameInput = scan.nextLine();
			System.out.print("\t\t\t2. 개설 종류 : ");
			String SubjectTypeInput = scan.nextLine();
			System.out.println("\t\t\t3. 개설기간 : ");
			String SubjectPeriodInput = scan.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t교재 정보입니다.");
			BookList();
			System.out.print("\t\t\t4. 교재번호 : ");
			String BookSeq = scan.nextLine();
			stat0 = conn.prepareStatement(SubjectSql1);
			stat0.setString(1, SubjectNameInput); //개설과목명
			stat0.setString(2, SubjectTypeInput); //개설종류
			stat0.setString(3, SubjectPeriodInput); //개설기간
			stat0.setString(4, BookSeq); //교재번호
			
			//개설과목 입력
			PreparedStatement stat1 = null;
			System.out.print("\t\t\t5. 과목 시작 일(YY/MM/DD) : ");
			String ClassStartDayInput = scan.nextLine();
			System.out.print("\t\t\t6. 과목 종료 일(YY/MM/DD) : ");
			String ClassEndDayInput = scan.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t개설과정기간입니다.");
			OpenClassList();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t7. 개설과정 번호 : ");
			String OpenSubjectSeq = scan.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t개설 과목정보입니다.");
			SubjectList();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t8. 개설과목 번호 : ");
			String OpenClassSeq = scan.nextLine();
			stat1 = conn.prepareStatement(OpenSubjectSql1);
			stat1.setString(1, ClassStartDayInput); //과목시작일
			stat1.setString(2, ClassEndDayInput); //과목종료일
			stat1.setString(3, OpenSubjectSeq); //개설과정번호
			stat1.setString(4, OpenClassSeq); //개설과목번호
			

//			Calendar startdate = Calendar.getInstance();
//			startdate.set(ClassStartDayInput.substring(0,1),
//						  ClassStartDayInput.substring(3,1),
//						  ClassStartDayInput.substring(6,1));
//			
//			long start = startdate.getTimeInMillis();
//			Calendar enddate = Calendar.getInstance();
//			
//			enddate.set(ClassEndDayInput.substring(0,1),
//						ClassEndDayInput.substring(3,1),
//						ClassEndDayInput.substring(6,1));
//			long end = startdate.getTimeInMillis();
			
			
	    	
			// 공통부분
			System.out.println("\t\t\t신규 과정이 등록 완료 되었습니다.");
			System.out.println();

			stat0.close();
			stat1.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("ClassRegister()에서 에러발생");
			e.printStackTrace();
		}

		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println("\t\t\tb. 처음으로 가기");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t번호를 입력해주세요 : ");
		String UserInput = scan.nextLine();

		if (UserInput.equals("a")) {
			// 뒤로가기
			ClassOfAdminMain();
			
		} else if (UserInput.equals("b")) {
			// 처음으로 가기
			//    Menu home = new Menu();
			   
			 //  Menu.home();

		} else {
			System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요.");
		
		}
		
	}
	

	private static void CheckOpenclass() {
		// 3. 현재 개설 과정 확인

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		ArrayList <String> list = new ArrayList<String>();
		
		try {

			String Sql = "{ call proSubjectInfomation(?) }";
			
			conn = util.open();
			stat= conn.prepareCall(Sql);
			
			stat.registerOutParameter(1, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(1);
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[No.]\t[번호]\t[과정명]\t\t\t\t\t\t[과정기간]\t\t[강의실명]\t[개설과목명]\t[교육생등록인원]");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			
			while (rs.next()) { list.add(rs.getString("번호")+"\t"+rs.getString("개설과정명")+"\t"+rs.getString("시작날짜")+"~"+rs.getString("마지막날짜")
								+"\t"+rs.getString("강의실명")+"\t" + rs.getString("개설과목") + "\t"+rs.getString("교육생등록인원"));
			}

			page(list);
			
		} catch (Exception e) {
			System.out.println("CheckOpenSubject()에서 에러발생");
			e.printStackTrace();
		}
		
	}
	private static void CheckSelectSubJejct222() {
		
	
		  System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	 		System.out.println("\t\t\t1. 특정 개설 세부사항 확인하기 ");
	 		System.out.println("\t\t\t2. 개설 과목 정보 확인하기");
	 		System.out.println("\t\t\t3. 개설 과목 신규 등록하기 ");
	 		System.out.println();
	 		System.out.println();
	 		System.out.println("\t\t\ta. 뒤로가기");
	 		System.out.println("\t\t\tb. 처음으로 가기");
	 		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	 		System.out.print("\t\t\t번호를 입력해주세요 : ");
	 		String UserInput6 = scan.nextLine();

	 		if (UserInput6.equals("1")){
	 			//1. 특정 개설 세부사항 확인하기
	 			CheckSelectSubject();
	 			
	 		} else if (UserInput6.equals("2")) {
	 			//2. 개설 과목 정보 확인하기
	 			CheckSubjectInfo();
	 			
	 		} else if (UserInput6.equals("3")) {
	 			//3. 개설 과목 신규 등록하기 
	 			NewResisterSubject();
	 			
	 		} else if (UserInput6.equals("a")) {
	 			// 뒤로가기 -> admin 1번 메인
	 			ClassOfAdminMain();
	 		} else if (UserInput6.equals("b")) {
	 			// 처음으로 가기
	 			//Menu home = new Menu();
	 		   
	 		   //Menu.home();
	 		
	 		} else {
	 			System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요.");
	 			
	 		
	 		}
		
	}
	private static void CheckSelectSubject() {
		// 특정 개설 과정 확인 페이지
		 Connection conn = null;
         CallableStatement stat = null;
         ResultSet rs = null;
         DBUtil util = new DBUtil();
		
       
  
         // 특정 개설 과정 출력
		System.out.print("\t\t\t조회할 번호를 입력해주세요 : ");
		SubjectNameInput = scan.nextLine();
		
		try {
			
		  conn = util.open();
		  
		  Statement stat1 = null;
          ResultSet rs1 = null;
          
          stat1 = conn.createStatement();
		  String sql = String.format("select distinct * from vwChoiceClass where 과목번호 = %s", SubjectNameInput);
			
		  rs1 = stat1.executeQuery(sql);
		
		  while(rs1.next()) {
				System.out.printf("\t\t\t" + "[과목번호] : %s\n\t\t\t[과목명] : %s\n\t\t\t[과목기간] : %s ~ %s\n\t\t\t[교재명] : %s\n\t\t\t[교사명]: %s\n", 
						rs1.getString("과목번호"),
						rs1.getString("과목명"), 
						rs1.getString("시작일"),
						rs1.getString("종료일"), 
						rs1.getString("교재명"), 
						rs1.getString("교사명"));

			}


         Statement stat2 = null;
         ResultSet rs2 = null;
         
		//교육생정보
        System.out.println();
		System.out.println("\t\t\t" + "[교육생번호]\t[교육생 명]\t[주민번호뒷자리]\t[전화번호]\t[등록일]\t\t[상태]\t[수료날짜]");
		
		stat2 = conn.createStatement();
        String sql2 = "select * from vwChoiceStudent where 과목번호 = " + SubjectNameInput;
      
        rs2 = stat1.executeQuery(sql2);
        
        while (rs2.next()) {
           System.out.printf("\t\t\t" + "%s\t\t%s\t\t%s\t%s\t\t%s\t%s\t%s\n"        
                 ,rs2.getString("교육생번호")
                 ,rs2.getString("교육생이름")
                 ,rs2.getString("주민번호뒷자리")
                 ,rs2.getString("전화번호")
                 ,rs2.getString("등록일")
                 ,rs2.getString("수료및중도탈락")
           		 ,rs2.getString("수료날짜"));
        }

        stat2.close();
        conn.close();
		
		} catch (Exception e) {
			 e.printStackTrace();
		System.out.println("CheckSelectSubject()에서 발생");
		}
		System.out.println();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t1. 수료날짜 입력");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println("\t\t\tb. 처음으로 가기");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t번호를 입력해주세요 : ");
		String UserInput3 = scan.nextLine();

		if (UserInput3.equals("1")) {
			// 1. 수료날짜 입력
			CompletionDate();

		} else if (UserInput3.equals("a")) {
			//뒤로가기
			CheckSelectSubJejct222();
		} else if (UserInput3.equals("b")) {
			//처음으로가기
			//Menu home = new Menu();
			   
			// Menu.home();
		} else {
			System.out.println("잘못된 입력입니다. 다시입력하세요.");
		}
		}
	
	private static void CheckSubjectInfo2222() {
		
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t1.개설 과목 수정하기");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println("\t\t\tb. 처음으로 가기");	
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t번호를 입력해주세요 : ");
		String UserInput = scan.nextLine();

		if (UserInput.equals("1")) {
			// 1.개설 과목 수정하기
			ReviseSubjectInfo();

		} else if (UserInput.equals("2")) {
			//2.개설 과목 삭제하기
			DeleteSubjectInfo();

		} else if (UserInput.equals("a")) {
			// 뒤로가기 - > 특정 개설 과정 확인 페이지
			CheckSelectSubJejct222();

		} else if (UserInput.equals("b")) {
			// 처음으로 가기
			//  Menu home = new Menu();
			// Menu.home();

		} else {
			System.out.println("잘못된 입력입니다. 다시입력하세요");
		}
		
	}
	private static void CheckSubjectInfo() {
		// 3-3 개설 과목 정보 확인

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		ArrayList<String> list2 = new ArrayList<String>();
		try {

			String sql = "{ call ProvwOSInfo(?) }";

			conn = util.open();
			stat = conn.prepareCall(sql);

			stat.registerOutParameter(1, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(1);

			System.out
					.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out
					.println("\t\t\t[No.]\t[번호]\t[과정명]\t\t\t\t\t\t\t[과정기간]\t\t[강의실]\t[과목명]\t\t\t[과목기간]\t\t[교재명]\t\t\t[교사명]");
			System.out
					.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			while (rs.next()) { list2.add(rs.getString("번호") +"\t"+ rs.getString("과정명") + "\t\t" + rs.getString("과정기간") +"\t"+rs.getString("강의실명")+"\t"+ rs.getString("과목명")+"\t\t"+rs.getString("과목기간") +"\t"+ rs.getString("교재명")+"\t"+ rs.getString("교사명"));
		
			}
			page2(list2);
		} catch (Exception e) {
			System.out.println("CheckSubjectInfo()에서 에러발생");
			e.printStackTrace();
		}
		

	}

	private static void DeleteSubjectInfo() {
		// 3-3-2.개설 과목 삭제하기

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();

		String Deletesql = "Delete tblOpenSubject where seq = ?"; 

		try {

			conn = util.open();
			stat = conn.prepareCall(Deletesql);
			System.out.println("\t\t\t삭제할 번호를 입력해주세요");
			System.out.print("\t\t\t입력 : ");
			String DeleteUserInput = scan.nextLine();

			stat.setString(1, DeleteUserInput);

			System.out.println("\t\t\t삭제 완료되었습니다.");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로 가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t번호를 입력해주세요 : ");
			String UserInput2 = scan.nextLine();

			if (UserInput2.equals("a")) {
				// 뒤로가기 -> 전체 개설 과정 정보 페이지
				CheckSubjectInfo();

			} else if (UserInput2.equals("b")) {
				// 처음으로 가기
				// Menu home = new Menu();
				// Menu.home();

			} else {
				System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요.");
			}

		} catch (Exception e) {
			System.out.println("DeleteSubjectInfo()에서 에러발생");
		} finally {
			try {
				stat.close();
				conn.close();
			} catch (Exception e) {
			}
		}

	}

	private static void ReviseSubjectInfo() {
		//개설 과목 수정하기
		
		Connection conn = null;
        
        ResultSet rs = null;
        DBUtil util = new DBUtil();
	
		try {
		Statement stat = null;
		conn = util.open();
		System.out.println("\t\t\t1. 과목명");
		System.out.println("\t\t\t2. 과목 시작 일");
		System.out.println("\t\t\t3. 과목 종료 일");
		System.out.println("\t\t\t4. 교재명");
		System.out.println("\t\t\t5. 교사명");
		
		
		System.out.print("\t\t\t번호를 입력해주세요 : ");
		String UserInput = scan.nextLine();

	if (UserInput.equals("1")) {
		// 1.과목명 수정
		System.out.println("\t\t\t개설 과목 목록입니다.");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		SubjectList();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t수정할 개설 과목 번호를 입력해주세요 : ");
		String SubjectNumInput = scan.nextLine();
		System.out.print("\n\t\t\t수정 내용을 입력해주세요 : ");
		String SubjectNameInput = scan.nextLine();
		
		conn = util.open();
		
		String Updatesql3 = String.format("update tblSubject set name = '%s' where seq = %s ",SubjectNameInput, SubjectNumInput);

		stat = conn.createStatement();
		stat.executeUpdate(Updatesql3);

		System.out.println("\t\t\t수정 완료되었습니다.");

		stat.close();
		conn.close();
		
	} else if (UserInput.equals("2")) {
		// 2. 과목 시작 일
		Statement stat0 = null;
		System.out.println("\t\t\t개설 과목 목록입니다.");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		OpenSubjectList();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t수정할 개설 과목 시작일 번호를 입력해주세요 : ");
		String SubjectNumInput = scan.nextLine();
		System.out.print("\n\t\t\t과목 시작 일(YY/MM/DD) : ");
		String SubjectStartDateInput = scan.nextLine();

		
		conn = util.open();
		
		String Updatesql3 = String.format("update tblOpenSubject set startdate = '%s' where seq = %s ",SubjectStartDateInput, SubjectNumInput);

		stat0 = conn.createStatement();
		stat0.executeUpdate(Updatesql3);

		System.out.println("\t\t\t수정 완료되었습니다.");

		stat0.close();
		conn.close();
		
	} else if (UserInput.equals("3")) {
		// 3. 과목 종료 일
		Statement stat1 = null;
		System.out.println("\t\t\t개설 과목 목록입니다.");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		OpenSubjectList();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t수정할 개설 과목 종료일 번호를 입력해주세요 : ");
		String SubjectEnddateinput = scan.nextLine();
		System.out.print("\n\t\t\t과목 종료 일(YY/MM/DD) : ");
		String SubjectEndDateInput = scan.nextLine();
		
		conn = util.open();
		
		String Updatesql3 = String.format("update tblSubject set enddate = '%s' where seq = %s ",SubjectEndDateInput, SubjectEnddateinput);
	
		stat1 = conn.createStatement();
		stat1.executeUpdate(Updatesql3);

		System.out.println("\t\t\t수정 완료되었습니다.");

		stat1.close();
		conn.close();
		
		
	} else if (UserInput.equals("4")) {
		// 4. 교재명
		Statement stat2 = null;
		System.out.println("\t\t\t개설 과목 목록입니다.");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		BookList();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t수정할 교재번호를 입력해주세요 : ");
		String BookSeqInput = scan.nextLine();
		System.out.print("\n\t\t\t교재명 : ");
		String BookNameInput = scan.nextLine();
		
		conn = util.open();
		
		String Updatesql3 = String.format("update tblbook set name = '%s' where seq = %s ",BookNameInput, BookSeqInput);
	
		System.out.println("\t\t\t수정 완료되었습니다.");
		
		stat2 = conn.createStatement();
		stat2.executeUpdate(Updatesql3);
		
		stat2.close();
		conn.close();
		
	} else if (UserInput.equals("5")) {
		// 5. 교사명
		Statement stat3 = null;
		System.out.println("\t\t\t개설 과목 목록입니다.");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		tlist();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t수정할 교사번호를 입력해주세요 : ");
		String TeacherSeqInput = scan.nextLine();
		System.out.print("\n\t\t\t교사명 : ");
		String TeacherNameInput = scan.nextLine();
	
		conn = util.open();
		
		String Updatesql3 = String.format("update tblteacher set name = '%s' where seq = %s ",TeacherNameInput, TeacherSeqInput);
		
		stat3 = conn.createStatement();
		stat3.executeUpdate(Updatesql3);

		System.out.println("\t\t\t수정 완료되었습니다.");

		stat3.close();
		conn.close();
		}
		} catch (Exception e) {
		}
		
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println("\t\t\tb. 처음으로 가기");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t번호를 입력해주세요 : ");
		String UserInput4 = scan.nextLine();

		if (UserInput4.equals("a")) {
			// 뒤로가기 
			CheckSubjectInfo2222();
		} else if (UserInput4.equals("b")) {
			// 처음으로 가기
			//  Menu home = new Menu();
			   
			  // Menu.home();

		} else {
			System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요.");
		}
	}

	private static void CompletionDate() {
		// 특정 개설 과정 수료날짜 입력 페이지
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		CallableStatement stat2 = null;
		

		try {

			System.out.print("\t\t\t교육생 번호 : ");
			String studentSeq = scan.nextLine();
			
			System.out.print("\t\t\t수료날짜(YY/MM/DD): ");
			String CompletionInput = scan.nextLine();

			conn = util.open();
			
			String sql = "{call procDate(?,?,?)}";
			
			stat2 = conn.prepareCall(sql);
			
			stat2.setString(1, studentSeq);
			stat2.setString(2, SubjectNameInput);
			stat2.setString(3, CompletionInput);
	
			stat2.executeUpdate();

			System.out.println("\t\t\t수료날짜 입력 완료되었습니다.");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로 가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t번호를 입력해주세요 : ");
			String UserInput2 = scan.nextLine();

			if (UserInput2.equals("a")) {
				// 뒤로가기 -> 전체 개설 과정 정보 페이지
				CheckInfo();

			} else if (UserInput2.equals("b")) {
				// 처음으로 가기
				// Menu home = new Menu();
			   // Menu.home();

			} else {
				System.out.println("잘못된 입력입니다. 다시입력하세요.");
			}

		} catch (Exception e) {
			//System.out.println("CompletionDate()에서 에러 발생");
			e.printStackTrace();
			
		} finally {
			try {
				stat2.close();
				conn.close();
			} catch (Exception e) {
			}
		}

	}
	private static void OpenSubjectList() {
		
		//과정리스트
	  Connection conn = null;
	  CallableStatement stat = null;
	  ResultSet rs = null;
	  DBUtil util = new DBUtil();      
	  
	  osseq.clear();
	  
	  try {
	     //리스트 생성
	     
	     //프로시저 부르기
	     String sql = "{ call  ProvwChoiceClass(?) }";

	     //open
	     conn = util.open();
	     stat = conn.prepareCall(sql);
	     
	     //커서부르기
	     stat.registerOutParameter(1, OracleTypes.CURSOR);
	     
	     //출력요청
	     stat.executeQuery();
	     
	     //출력값 담기
	     rs = (ResultSet)stat.getObject(1);
	     
	     System.out.println();
	     System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	     System.out.println("\t\t\t" + "<과목 목록>");
	     System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	     
	     System.out.println("\t\t\t[번호]\t[과목시작일]\t[과목종료일]\t[과목명]");
	     
	     //출력
	     while (rs.next()) {
	        System.out.println("\t\t\t--------------------------------------------------------------");
	        System.out.printf("\t\t\t%s\t%s\t%s\t%s\n"
	              ,rs.getString("과목번호")
	              ,rs.getString("시작일")
	        	  ,rs.getString("종료일")
	        	  ,rs.getString("과목명"));
	       // cseq.add(rs.getString("seq"));
	     }

	     //close
	         rs.close();
	         stat.close();
	         conn.close();


	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	 }

	
	private static void OpenClassList() {
		
		//개설과정리스트
    Connection conn = null;
    Statement stat = null;
    DBUtil util = new DBUtil();
    ResultSet rs = null;     
	  
	  ocseq.clear();
	  
	  try {
		  //open
		     conn = util.open();
		     stat = conn.createStatement();
	     
	     //프로시저 부르기
	     String sql = "select c.seq as seq, to_char(oc.startdate,'yy/mm/dd') as startdate  , to_char(oc.enddate,'yy/mm/dd') as enddate , c.name as name from tblOpenClass oc inner join tblClass c on c.seq = oc.classseq";

	     //출력요청
	     rs = stat.executeQuery(sql);

	     System.out.println();
	     System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	     System.out.println("\t\t\t" + "<개설 과정 목록>");
	     System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	 
	     if (rs.next()) {
	     System.out.println("\t\t\t" + "[번호]\t[과정시작일]\t[과정종료일]\t[과정명]");
	     System.out.println("\t\t\t--------------------------------------------------------------");
         while (rs.next()) {
            System.out.printf("\t\t\t" + "%s\t %s\t%s\t%s\n"
                        , rs.getString("seq")                  
                        , rs.getString("startdate")
                       , rs.getString("enddate")
                       , rs.getString("name"));
           // ocseq.add(rs.getString("ocseq"));
         }

	     
	     
	     //close
         rs.close();
         stat.close();
         conn.close();

	     }

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	 }
	private static void ClassList() {
		
		//과정리스트
	  Connection conn = null;
	  CallableStatement stat = null;
	  ResultSet rs = null;
	  DBUtil util = new DBUtil();      
	  
	  cseq.clear();
	  
	  try {
	     //리스트 생성
	     
	     //프로시저 부르기
	     String sql = "{ call  ProClass(?) }";

	     //open
	     conn = util.open();
	     stat = conn.prepareCall(sql);
	     
	     //커서부르기
	     stat.registerOutParameter(1, OracleTypes.CURSOR);
	     
	     //출력요청
	     stat.executeQuery();
	     
	     //출력값 담기
	     rs = (ResultSet)stat.getObject(1);
	     
	     System.out.println();
	     System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	     System.out.println("\t\t\t" + "<과정 목록>");
	     System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	     
	     System.out.println("\t\t\t[번호]\t[과정기간]\t[과정명]");
	     
	     //출력
	     while (rs.next()) {
	        System.out.println("\t\t\t--------------------------------------------------------------");
	        System.out.printf("\t\t\t%s\t%s\t%s\n"
	              ,rs.getString("seq")
	              ,rs.getString("classPeriod")
	        	  ,rs.getString("name"));
	       cseq.add(rs.getString("seq"));
	     }

	     //close
	         rs.close();
	         stat.close();
	         conn.close();


	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	 }
	private static void ClassRegister() {
		// 2. 과정 신규 등록

		String ClassSql = "insert into tblClass(seq,name,classPeriod) values (tblClass_seq.nextVal, ?, ?)";
		String OpenClassSql = "insert into tblOpenClass (seq, startdate,enddate,teacherseq,roomseq,classseq) values (tblOpenClass_seq.nextVal, ?,?,?,?,?)";
		String RoomSql = "insert into tblRoom (seq,name,num) values (tblRoom_seq.nextVal, ?, ?)";
		String SubjectSql = "insert into tblSubject (seq,name,type,subjectPeriod,bookSeq) values (tblSubject_seq.nextVal, ?,?,?,?)";

        ResultSet rs = null;
        DBUtil util = new DBUtil();
		
		try {
			Connection conn = null;
			conn = util.open();
	
			PreparedStatement stat = null;
			stat = conn.prepareStatement(ClassSql);
			System.out.println("\t\t\t아래 내용을 입력해주세요.");
			System.out.print("\t\t\t1. 과정명 : ");
			String ClassNameInput = scan.nextLine();
			System.out.print("\t\t\t2. 과정기간(month) : ");
			String ClassPeriodInput = scan.nextLine();

			stat.setString(1, ClassNameInput); //과정명
			stat.setString(2,ClassPeriodInput); //과정기간
			int cnt = stat.executeUpdate();
			
			PreparedStatement stat0 = null;
			stat0 = conn.prepareStatement(OpenClassSql);
			System.out.print("\t\t\t3. 과정 시작 일(YY/MM/DD) : ");
			String ClassStartDayInput = scan.nextLine();
			System.out.print("\t\t\t4. 과정 종료 일(YY/MM/DD) : ");
			String ClassEndDayInput = scan.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t아래 정보를 참고하여 추가사항을 입력해주세요.");
			System.out.println("\t\t\t교사 정보입니다.");
			tlist();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\n\t\t\t5. 교사번호 : ");
			String TeacherSeq = scan.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t강의실 정보입니다.");
			Roomlist();
			System.out.print("\t\t\t5. 강의실번호 : ");
			String RoomSeq = scan.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t과정 정보입니다.");
			ClassList();
			System.out.print("\t\t\t7. 과정번호 : ");
			String ClassSeq = scan.nextLine();
		
			stat0.setString(1, ClassStartDayInput); //과정시작일
			stat0.setString(2, ClassEndDayInput); //과정종료일
			stat0.setString(3, TeacherSeq); //교사정보번호
			stat0.setString(4, RoomSeq); //강의실번호
			stat0.setString(5, ClassSeq); //과정번호
			
			int cnt0 = stat0.executeUpdate();
			
//			PreparedStatement stat1 = null;
//			String RoomName = null;
//			String RoomPax;
//			
//			if(RoomSeq.equals("1")) {
//				RoomName = "1강의실";
//				RoomPax = "30";
//			} else if (RoomSeq.equals("2")) {
//				RoomName = "2강의실";
//				RoomPax = "28";
//			} else if (RoomSeq.equals("3")) {
//				RoomName = "3강의실";
//				RoomPax = "24";
//			} else if (RoomSeq.equals("4")) {
//					RoomName = "4강의실";
//					RoomPax = "26";
//			} else if (RoomSeq.equals("5")) {
//				RoomName = "5강의실";
//				RoomPax = "24";
//			} else if (RoomSeq.equals("6")) {
//				RoomName = "6강의실";
//				RoomPax = "22";
//			} else { 
//				RoomName = "없음";
//				RoomPax = "0";
//			}
//			stat1 = conn.prepareStatement(RoomSql);
//			stat1.setString(1, RoomName); //강의실명
//			stat1.setString(2, RoomPax); //강의실인원
//			int cnt1 = stat1.executeUpdate();
			
			PreparedStatement stat2 = null;
			System.out.print("\t\t\t8. 개설 과목명 : ");
			String SubjectNameInput = scan.nextLine();
			stat2 = conn.prepareStatement(SubjectSql);
			System.out.print("\t\t\t9. 개설 종류 : ");
			String SubjectTypeInput = scan.nextLine();
			System.out.println("\t\t\t10. 개설기간 : ");
			String SubjectPeriodInput = scan.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t교재 정보입니다.");
			BookList();
			System.out.print("\t\t\t11. 교재번호 : ");
			String BookSeq = scan.nextLine();
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			stat2.setString(1, SubjectNameInput); //개설과목명
			stat2.setString(2,SubjectTypeInput); //개설종류
			stat2.setString(3, SubjectPeriodInput); //개설기간
			stat2.setString(4, BookSeq); //교재번호
			int cnt2 = stat2.executeUpdate();

			// 공통부분
			System.out.println("\t\t\t신규 과정이 등록 완료 되었습니다.");
			System.out.println();

			stat.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("ClassRegister()에서 에러발생");
			e.printStackTrace();
		}

		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println("\t\t\tb. 처음으로 가기");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t번호를 입력해주세요 : ");
		String UserInput = scan.nextLine();

		if (UserInput.equals("a")) {
			// 뒤로가기
			ClassOfAdminMain();
			
		} else if (UserInput.equals("b")) {
			// 처음으로 가기
			//  Menu home = new Menu();
			   
//			   Menu.home();

		} else {
			System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요.");
		}

	}

	private static void BookList() {
		
		//교재리스트
	      Connection conn = null;
	      CallableStatement stat = null;
	      ResultSet rs = null;
	      DBUtil util = new DBUtil();      
	      
	      bseq.clear();
	      
	      try {
	         //리스트 생성
	         
	         //프로시저 부르기
	         String sql = "{ call  ProBook(?) }";

	         //open
	         conn = util.open();
	         stat = conn.prepareCall(sql);
	         
	         //커서부르기
	         stat.registerOutParameter(1, OracleTypes.CURSOR);
	         
	         //출력요청
	         stat.executeQuery();
	         
	         //출력값 담기
	         rs = (ResultSet)stat.getObject(1);
	         
	         System.out.println();
	         System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	         System.out.println("\t\t\t" + "<교재 목록>");
	         System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	         
	         System.out.println("\t\t\t[번호]\t[출판사]\t\t[교재명]");
	         
	         //출력
	         while (rs.next()) {
	            System.out.println("\t\t\t--------------------------------------------------------------");
	            System.out.printf("\t\t\t%s\t%s\t%s\n"
	                  ,rs.getString("seq")
	                  ,rs.getString("publisher")
	            	  ,rs.getString("name"));
	            bseq.add(rs.getString("seq"));
	         }

	         //close
	         rs.close();
	         stat.close();
	         conn.close();


	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	 }
	
	private static void SubjectList() {
	
	//과정리스트
  Connection conn = null;
  CallableStatement stat = null;
  ResultSet rs = null;
  DBUtil util = new DBUtil();      
  
  subseq.clear();
  
  try {
     //리스트 생성
     
     //프로시저 부르기
     String sql = "{ call  ProSubject(?) }";

     //open
     conn = util.open();
     stat = conn.prepareCall(sql);
     
     //커서부르기
     stat.registerOutParameter(1, OracleTypes.CURSOR);
     
     //출력요청
     stat.executeQuery();
     
     //출력값 담기
     rs = (ResultSet)stat.getObject(1);
     
     System.out.println();
     System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     System.out.println("\t\t\t" + "<과목 목록>");
     System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     
     System.out.println("\t\t\t[번호]\t[과목명]");
     
     //출력
     while (rs.next()) {
        System.out.println("\t\t\t--------------------------------------------------------------");
        System.out.printf("\t\t\t%s\t%s\n"
              ,rs.getString("seq")
              ,rs.getString("name"));
     }

     //close
         rs.close();
         stat.close();
         conn.close();


      } catch (Exception e) {
         e.printStackTrace();
      }
 }

	private static void Roomlist() {
	 //강의실 리스트
  Connection conn = null;
  CallableStatement stat = null;
  ResultSet rs = null;
  DBUtil util = new DBUtil();      
  
  rseq.clear();
  
  try {
     //리스트 생성
     
     //프로시저 부르기
     String sql = "{ call  ProRoom(?) }";

     //open
     conn = util.open();
     stat = conn.prepareCall(sql);
     
     //커서부르기
     stat.registerOutParameter(1, OracleTypes.CURSOR);
     
     //출력요청
     stat.executeQuery();
     
     //출력값 담기
     rs = (ResultSet)stat.getObject(1);
     
     System.out.println();
     System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     System.out.println("\t\t\t" + "<강의실 목록>");
     System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
     
     System.out.println("\t\t\t[번호]\t[강의실 명]\t[정원]");
     
     //출력
     while (rs.next()) {
        System.out.println("\t\t\t--------------------------------------------------------------");
        System.out.printf("\t\t\t%s\t%s\t\t%s\n"
              ,rs.getString("seq")
              ,rs.getString("name")
        	  ,rs.getString("num"));
        rseq.add(rs.getString("seq"));
     }

     //close
         rs.close();
         stat.close();
         conn.close();


      } catch (Exception e) {
         e.printStackTrace();
      }
 }
	private static void tlist() {
		 //교사정보 리스트
	      Connection conn = null;
	      CallableStatement stat = null;
	      ResultSet rs = null;
	      DBUtil util = new DBUtil();      
	      
//	      tseq.clear();
	      
	      try {
	         //리스트 생성
	         
	         //프로시저 부르기
	         String sql = "{ call procTInfor(?) }";

	         //open
	         conn = util.open();
	         stat = conn.prepareCall(sql);
	         
	         //커서부르기
	         stat.registerOutParameter(1, OracleTypes.CURSOR);
	         
	         //출력요청
	         stat.executeQuery();
	         
	         //출력값 담기
	         rs = (ResultSet)stat.getObject(1);
	         
	         System.out.println();
	         System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	         System.out.println("\t\t\t" + "<교사 목록>");
	         System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	         
	         System.out.println("\t\t\t[번호]\t[이름]\t[아이디]");
	         
	         //출력
	         while (rs.next()) {
	            System.out.println("\t\t\t--------------------------------------------------------------");
	            System.out.printf("\t\t\t%s\t%s\t%s\n"
	                  ,rs.getString("seq")
	                  ,rs.getString("name")
	                  ,rs.getString("id"));
	            tseq.add(rs.getString("seq"));
	         }

	         //close
	         rs.close();
	         stat.close();
	         conn.close();


	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	 }
	private static void CheckInfo() {
		// 1. 전체 개설 과정 정보 확인
		ArrayList<String> list3 = new ArrayList<String>();
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;
		
		try {

			String sql = "{ call ProvwInfo(?) }";

			conn = util.open("211.63.89.59","project","java1234");
			stat = conn.prepareCall(sql);

			stat.registerOutParameter(1, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(1);
			
			System.out
					.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[No.]\t[번호]\t[과정명]\t\t\t\t\t\t[과목명]\t\t\t[교재명]\t\t[출판사명]\t[강의실명]\t[정원]");
			System.out
					.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			while (rs.next()) { list3.add(rs.getString("번호")+"\t"+ rs.getString("과정명") +"\t\t"+
					rs.getString("과목명")+"\t\t"+ rs.getString("교재명") +"\t\t"+ rs.getString("출판사명")+"\t"+ rs.getString("강의실명")+"\t"+
					rs.getString("정원"));
			
			}
			page3(list3);
		} catch (Exception e) {
			System.out.println("CheckInfo()에서 에러");
			e.printStackTrace();
		}
//		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
//		System.out.println("a. 뒤로가기");
//		System.out.println("b. 처음으로 가기");
//		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
//		System.out.print("번호를 입력해주세요 : ");
//		String CheckInfoInput = scan.nextLine();
//
//		if (CheckInfoInput.equals(toLowerCase("a"))) {
//			// 뒤로가기
//			ClassOfAdminMain();
//			
//		} else if (CheckInfoInput.equals("b")) {
//			// 처음으로 가기
//			// Menu home = new Menu();
//			   
//		   // Menu.home();
//		} else {
//			System.out.println("잘못된 입력입니다. 다시입력하세요.");
//		}

	}

}

