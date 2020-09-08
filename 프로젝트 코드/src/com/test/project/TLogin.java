package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class TLogin {
   
	//로그인한 교사 번호 
   public static ArrayList<String> tseqLogin = new ArrayList<String>();
   
   //로그인한 교사 이름 
   public static ArrayList<String> tnameLogin = new ArrayList<String>();
   
   //교사 번호 
   static int teacherNum = -1;
   
   Menu home = new Menu();
   
   
   
   
//   public static void main(String[] args) {
//      
//	   tLogin();
//	   tMenu();
////      
////      System.out.println();
////      System.out.println(tseqLogin.get(0));
//      
//      
//	   
//	   
//	   
//   }

   public static void tMenu() {
	   
	   System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
       System.out.println("\t\t\t" +"1.강의 스케줄 조회");
       System.out.println("\t\t\t" +"2.배점 및 시험정보 입력");
       System.out.println("\t\t\t" +"3.배점 출력");
       System.out.println("\t\t\t" +"4.성적 입력");
       System.out.println("\t\t\t" +"5.성적 출력");
       System.out.println("\t\t\t" +"6.출결 전체 조회");
       System.out.println("\t\t\t" +"7.출결 월별 조회");
       System.out.println("\t\t\t" +"8.출결 일별 조회");
       System.out.println("\t\t\t" +"9.중도 탈락 조회");
       System.out.println("\t\t\t" +"10.사전평가 조회");
       System.out.println("\t\t\t" +"11.과정평가 ");
       System.out.println();
       System.out.println("\t\t\t" +"a.로그아웃");
       System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
       System.out.print("\t\t\t" +"메뉴를 선택해 주세요: ");
       
       
       Scanner scan = new Scanner(System.in);
       String menu = scan.nextLine();
       
       

       
       if(menu.equals("1")) {
    	   
    	   //강의 스케줄 조회
    	   Teacher2.T_schedule();
    	   
       } else if (menu.equals("2")) {
    	   
    	   //배점 , 시험정보 입력
    	   Teacher.T_001_in(); 
   
       } else if (menu.equals("3")) {
    	   
    	   //배점 , 시험정보 출력
    	   Teacher.T_001_out();  
    	   
       } else if (menu.equals("4")) {
    	   
    	   //성적 입력
    	   Teacher.T_004();
    	   
       } else if (menu.equals("5")) {
        	   
    	   //성적 출력
    	   Teacher.T_007();	
       
       } else if (menu.equals("6")) {
        	   
    	   //출결 전체 조회
    	   Teacher.T_009();

    	   
       } else if (menu.equals("7")) {
    	   
    	   //출결 월별 조회
    	   Teacher.T_011();

	   
       } else if (menu.equals("8")) {
	   
    	   //출결 일별 조회
    	   Teacher.T_012();

   
       } else if (menu.equals("9")) {
	   
    	   //중도탈락 조회
    	   Teacher.T_010();		

   
       } else if (menu.equals("10")) {
	   
    	   //사전평가 질문 조회
    	   Teacher.T_013();

   
       }else if (menu.equals("11")) {
	   
    	   //사전평가 질문 조회
    	   Teacher2.insentiveView();

   
       } else if((menu+"").toLowerCase().equals("a")){
    	   
    	   //로그아웃
    	   Menu.home();
    	   
    	   
       } else {
    	   
    	   System.out.println();
    	   System.out.println("\t\t\t잘못 입력하였습니다.");
    	   System.out.println("\t\t\t다시 입력해 주세요.");
    	   System.out.println();
    	   tMenu();

       }
       
       
	
}
   

   
   
   

static void tLogin() {
	
      Connection conn = null;
      CallableStatement stat = null;
      CallableStatement stat2 = null;
      DBUtil util = new DBUtil();
      Scanner scan = new Scanner(System.in);
      
      try {
         tseqLogin.clear();
         
         
         System.out.println();
         System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
         System.out.println("\t\t\t" + "<교사 로그인>");
         System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
         
         System.out.println();
         System.out.print("\t\t\t아이디 : ");
         String id = scan.nextLine();
         
         System.out.print("\t\t\t비밀번호 : ");
         String pw = scan.nextLine();
         System.out.println();
         System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
         System.out.println();
         
//         String id = "gahgg4833";
//         String pw = "3168928";
         
         
         String sql = "";
         
         sql = "{ call procTtdLoginId(?,?) }";
         
         conn = util.open("211.63.89.59","project","java1234");
         stat = conn.prepareCall(sql);
         

            
         stat.setString(1, id);
         stat.registerOutParameter(2, OracleTypes.NUMBER);

         //아이디 불러오기
         stat.executeQuery();
         
         try {
            
            sql = "{ call procTtdLoginPw(?,?,?,?) }";
            
            stat2 = conn.prepareCall(sql);
            
            stat2.setString(1, pw);
            stat2.registerOutParameter(2, OracleTypes.NUMBER);
            stat2.registerOutParameter(3, OracleTypes.VARCHAR);
            stat2.registerOutParameter(4, OracleTypes.VARCHAR);
            
            stat2.executeQuery();
            
         } catch (Exception e) {
            System.out.println("\t\t\t아이디와 비밀번호가 일치하지 않습니다.");
            tLogin();
         }
         
         if(id.equals(stat2.getString(3))) {
            
            System.out.println("\t\t\t로그인에 성공했습니다.");
//            System.out.println();
            System.out.printf("\t\t\t안녕하세요. [교사] %s님\n", stat2.getString(4));
            System.out.println();
            System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
            
            
            //교사 번호 
            tseqLogin.add(stat2.getString(2));
            //교사 이름
            tnameLogin.add(stat2.getString(4));
            
            //System.out.println("로그인 후 교사번호: " + tseqLogin.get(0));
            
            teacherNum = Integer.parseInt(tseqLogin.get(0));
            //System.out.println(teacherNum);
            
            tMenu();
            
            
         }else {
            System.out.println("\t\t\t아이디와 비밀번호가 일치하지 않습니다.");
         }
         
         
         stat.close();
         conn.close();
         
         
      } catch (Exception e) {
         System.out.println("\t\t\t존재하지 않는 아이디 입니다. 로그인에 실패했습니다.");
         e.printStackTrace();
         tLogin();
      }
      
      
   }


















}