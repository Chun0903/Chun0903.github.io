package com.test.project;

import java.util.Scanner;

public class AdminMainPage {


public static void admin() {
	// 로그인 + 메인
	adminLogin();
	adminMain();
	
}

private static void adminLogin() {
	// 로그인
	ALogin Alogin = new ALogin();	//메소드 재 선언
	Alogin.aLogin();				//메소드 불러오기
	
}

public static void adminMain() {
   // 관리자 메인 페이지
   
	   Scanner scan = new Scanner(System.in);
	   
	   //System.out.printf("%s 관리자님 안녕하세요."); 관리자 로그인 페이지 구현 후, 연결하기
	   System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	   System.out.println("\t\t\t<관리자 main>");
	   System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	   System.out.println("\t\t\t1. 교사 계정 관리");
	   System.out.println("\t\t\t2. 개설과정 및 개설과목 관리");
	   System.out.println("\t\t\t3. 수강생 관리");
	   System.out.println("\t\t\t4. 시험 관리 및 성적 조회");
	   System.out.println("\t\t\t5. 출결 관리 및 출결 조회");
	   System.out.println("\t\t\t6. 인센티브 관리");
	   System.out.println();
	   System.out.println();
	   System.out.println("\t\t\ta. 로그아웃");
	   System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	   System.out.print("\t\t\t번호를 입력해주세요 : ");
	   String AdminInput = scan.nextLine();
	   
		   if (AdminInput.equals("1")) {
		      //교사 계정 관리	    
		      ATinfor atinfor = new ATinfor();
		      atinfor.A_tinfo();
			   
		   } else if (AdminInput.equals("2")) {
		      //개설과정 및 개설과목 관리
			   AdminClass.ClassOfAdminMain();
			   
		   } else if (AdminInput.equals("3")) {
		      //수강생 관리
			   ProjectAdminStudent adminStudent = new ProjectAdminStudent();
			   adminStudent.adminStudentMain();	//교육생 관리 메인메뉴 출력
			   
		   } else if (AdminInput.equals("4")) {
		      //시험관리 및 성적 조회
			   ProjectAdminScore adScore = new ProjectAdminScore();
			   adScore.adminScoreMain();	// 시험관리 및 성적 메인메뉴 출력
		   
		   } else if (AdminInput.equals("5")) {
		      //출결 관리 및 출결 조회
			  ProjectAdminAttend adAttend = new ProjectAdminAttend();
			  adAttend.adminAttendMain();	// 출결관리 및 조회 메인메뉴 출력
			  
		   } else if (AdminInput.equals("6")) {
			  //인센티브 관리
			  AInsentive ainsentive = new AInsentive();
			  ainsentive.A_insentive(); 
			   
		   }else if (AdminInput.equals("a")) {
		      //뒤로가기
			   
			   System.out.println();
			   System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			   System.out.println("\t\t\t로그아웃 하시겠습니까?");
			   System.out.println();
			   System.out.println("\t\t\t1. 네");
			   System.out.println("\t\t\t2. 아니오");
			   System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			   System.out.print("\t\t\t입력 : ");
			   String yesNo = scan.nextLine();
			   System.out.println();
			   
			   if(yesNo.equals("1")) {
				   //첫 메인 페이지로
				   Menu home = new Menu();
				   home.home();
				   
			   }else if(yesNo.equals("2")) {
				   //관리자 메뉴 페이지로
				   adminMain();
			   }
			   
			   
		   }else { 
		      System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요");
		      adminMain();
		   }

	}//main
}