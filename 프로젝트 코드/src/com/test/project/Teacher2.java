package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class Teacher2 {
   

	
	static Menu home = new Menu();

   public static void T_schedule() {
      Scanner scan = new Scanner(System.in);
      
      
      System.out.println();
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.println("\t\t\t" + "<강의 스케줄>");
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      
      T_001();
      
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.println("\t\t\t" + "1. 강의스케줄 정보");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t" + "a. 뒤로가기");
      System.out.println("\t\t\t" + "b. 처음으로 가기");
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.print("\t\t\t" + "입력 : ");
      String input = scan.nextLine();
      System.out.println();
      
      if (input.equals("1")) {
         
         //강의스케줄 정보
         T_schedule_info();
         
      } else if(input.equals("a")) {
         //joo_교사 처음 메소드 불러오기
    	  TLogin.tMenu();
    	  
      } else if(input.equals("b")) {
    	  //처음으로 가기
    	  Menu.home();
    	  
      } else {
         System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
         T_schedule();
      }
      
      
   }//스케줄
   
   private static void T_001() {

      Connection conn = null;
      CallableStatement stat = null;
      ResultSet rs = null;
      DBUtil util = new DBUtil();

      try {
         String sql = "{ call procTScadual(?,?) }";
         String teacherSeq = TLogin.tseqLogin.get(0);

         conn = util.open("211.63.89.59","project","java1234");
         stat = conn.prepareCall(sql);
         
         stat.setString(1, teacherSeq);
         stat.registerOutParameter(2, OracleTypes.CURSOR);
         
         stat.executeQuery();
         
         rs = (ResultSet)stat.getObject(2);
         

         System.out.println("\t\t\t" + "[진행여부]" + "\t" + "[과목명]");
         while (rs.next()) {
            System.out.printf("\t\t\t" + "%s\t%s\n"
                  ,rs.getString("subjecProgress")
                  ,rs.getString("name"));
         }

         rs.close();
         stat.close();
         conn.close();

      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }//001
   
   private static void T_schedule_info() {
      Scanner scan = new Scanner(System.in);
      System.out.println();
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.println("\t\t\t" + "<강의 스케줄 정보 조회>");
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      
      T_002();

      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      
      System.out.println("\t\t\t" + "1.수강생 목록 조회");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t" + "a. 뒤로가기");
      System.out.println("\t\t\t" + "b. 처음으로 가기");

      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.print("\t\t\t" + "입력 : ");
      String input = scan.nextLine();
      System.out.println();
      
      if (input.equals("1")) {   
         T_schedule_info_Student();
         
      } else if(input.equals("a")) {
         T_schedule();
         
      } else if(input.equals("b")) {
    	  Menu.home();
    	  
      } else {
         System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
         T_schedule_info();
      }
   }

      private static void T_schedule_info_Student() {
      Scanner scan = new Scanner(System.in);
      System.out.println();
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.println("\t\t\t" + "<수강생 목록 조회>");
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      
      T_003();

      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      
      System.out.println("\t\t\t" + "a. 뒤로가기");
      System.out.println("\t\t\t" + "b. 처음으로 가기");
      
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.print("\t\t\t" + "입력 : ");
      String input = scan.nextLine();
      System.out.println();
      
      if (input.equals("a")) {
         T_schedule_info();      
         
      } else if(input.equals("b")) {
         home.home();

      } else {
         System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
         T_schedule_info_Student();
      }
   }
      
      private static void T_003() {
         Connection conn = null;
         CallableStatement stat = null;
         ResultSet rs = null;
         DBUtil util = new DBUtil();

         try {
            conn = util.open("211.63.89.59","project","java1234");
            String sql = "";

            sql = "{ call procTSubStInfo(?,?) }";
            
            //joo_로그인 받아오기
            String teacherSeq = TLogin.tseqLogin.get(0);


            stat = conn.prepareCall(sql);
            
            stat.setString(1, teacherSeq);
            stat.registerOutParameter(2, OracleTypes.CURSOR);
            
            stat.executeQuery();
            
            rs = (ResultSet)stat.getObject(2);
            
            System.out.println("\t\t\t" + "<교육생 정보>"+ "\n");
            System.out.println("\t\t\t" + "[이름]\t[전화번호]\t\t[등록일]\t\t[수료여부]");
            while (rs.next()) {
               System.out.printf("\t\t\t" + "%s\t%s\t%s\t%s\n"
                     ,rs.getString("name")
                     ,rs.getString("tel")
                     ,rs.getString("regdate")
                     ,rs.getString("complet"));
            }

            stat.close();
            conn.close();

         } catch (Exception e) {
            e.printStackTrace();
         }
         
      }

      private static void T_002() {
         
         Connection conn = null;
         CallableStatement stat = null;
         ResultSet rs = null;
         DBUtil util = new DBUtil();
         
         
         //교사 번호 -> 로그인에서 받아오기!!!
         String teacherSeq = TLogin.tseqLogin.get(0);
         
         
         try {
      
            conn = util.open("211.63.89.59","project","java1234");
            
            String sql = "";
            
            //과정
            CallableStatement stat0 = null;
            ResultSet rs0 = null;

            sql = "{ call procTScadualInfoClass(?,?) }";
            stat0 = conn.prepareCall(sql);
            
            stat0.setString(1, teacherSeq);
            stat0.registerOutParameter(2, OracleTypes.CURSOR);
            
            stat0.executeQuery();
            
            rs0 = (ResultSet)stat0.getObject(2);
            
            while (rs0.next()) {
               System.out.printf("\t\t\t" + "[과정번호] : %s\n\t\t\t[과정이름] : %s\n\t\t\t[과정기간] : %s ~ %s\n\t\t\t[ 강의실 ] : %s\n"
                     ,rs0.getString("cseq")
                     ,rs0.getString("cname")
                     ,rs0.getString("ocStartdate")
                     ,rs0.getString("ocEnddate")
                     ,rs0.getString("rname"));
            }

            rs0.close();
            stat0.close();
            
            //인원수
            Statement stat1 = null;
            ResultSet rs1 = null;
            
            stat1 = conn.createStatement();
            
            sql = "select count(*) as cnt from vwTScadualInfo where tseq = "+teacherSeq;
            
            rs1 = stat1.executeQuery(sql);
            
            if(rs1.next()) {
               System.out.println("\t\t\t" + "[ 인원수 ] : " + rs1.getString("cnt") + "명");
               System.out.println();
            }
            
            rs1.close();
            stat1.close();
            
            
            //과목
            System.out.println("\t\t\t" + "[과목번호]\t[과목명]\t\t\t[과목기간(시작 - 끝)]\t\t\t\t[교재명]");
            
            sql = "{ call procTScadualInfo(?,?) }";
            stat = conn.prepareCall(sql);
            
            stat.setString(1, teacherSeq);
            stat.registerOutParameter(2, OracleTypes.CURSOR);
            
            stat.executeQuery();
            
            rs = (ResultSet)stat.getObject(2);
            
            while (rs.next()) {
               System.out.printf("\t\t\t" + "%s\t%-30s%10s - %s\t\t%s\n"
                     ,rs.getString("sseq")
                     ,rs.getString("sname")
                     ,rs.getString("osStartdate")
                     ,rs.getString("osEnddate")
                     ,rs.getString("bname"));
            }

            stat.close();
            conn.close();

         } catch (Exception e) {
            e.printStackTrace();
         }
         
      }

      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      static void insentiveView() {

          String teacherSeq = TLogin.tseqLogin.get(0);
          String tname = TLogin.tnameLogin.get(0);

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
             System.out.println("\t\t\t과정 평가 총첨");
             System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
             System.out.println();
             System.out.printf("\t\t\t[%s]님의 과정 평가 총첨은\n", tname);
             System.out.printf("\t\t\t[%s]점 입니다.\n",(int)(stat.getFloat(3) * 100));
             
             while(true) {

                System.out.println();
                
                System.out.println("\t\t\t1. 교사평가 조회");
                System.out.println("\n");
                System.out.println("\t\t\t" + "a. 뒤로가기");
                System.out.println("\t\t\t" + "b. 처음으로가기");
                System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
                System.out.print("\t\t\t입력 : ");
                   
                Scanner scan = new Scanner(System.in);
                String backHome = scan.nextLine();
                   
                if(backHome.equals("1")) {
                   TassView tassView = new TassView();   //메소드 재 선언
                   tassView.TAssView();   
                   insentiveView();
                }
                else if(backHome.equals("a")) {
                   //joo 뒤로가기 만들기
                   TLogin.tMenu();

                } else if(backHome.equals("b")) {
                      Menu.home();
                	
                   break;
                      
                   
                } else {
                      
                   System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
             
                }
             }
             
             

             System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
             System.out.println();
             
             stat.close();
             conn.close();
             
             
          } catch (Exception e) {
             // TODO: handle exception
          }
          
       }
   
   
   
}//T