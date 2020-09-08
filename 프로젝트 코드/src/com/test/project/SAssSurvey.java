package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class SAssSurvey {
   //로그인 학생 과정번호
   public static ArrayList<String> sLoginOcseq = new ArrayList<String>();
   
   //항목 번호 유효성 검사
   public static ArrayList<String> outNumList = new ArrayList<String>();
   
   //스캐너
   static Scanner scan = new Scanner(System.in);
   

//      String id = "tssec2724";
//      String pwd = "1580054";


   static void AssSurvayResultInput(String id, String pwd) {
      
      sLoginOcseq.clear();
      outNumList.clear();
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.println("\t\t\t<과정 평가>");
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.println();
      
      SloginOpenclassSeq(id, pwd);
      openClassSurvey();
      
      
   }
   
   private static void SloginOpenclassSeq(String id, String pwd) {
      
      Connection conn = null;   //Connection 클래스생성
      CallableStatement stat = null;   //CallableStatement 클래스생성 - 프록시저 전용 Statement
      DBUtil util = new DBUtil();
      String rs= null;
      
      try {
         conn = util.open("211.63.89.59","project","java1234");
         
         String sql = "";
         
         sql =  "{ call proStOcseq(?,?,?) }";
         
         stat = conn.prepareCall(sql);
         stat.setString(1, id);
         stat.setString(2, pwd);
         stat.registerOutParameter(3, OracleTypes.NUMBER);
         
         stat.executeQuery();
         
//         String openClassSeq = stat.getString(3);
         
         sLoginOcseq.add(stat.getString(3));
         



      } catch (Exception e) {
         // TODO: handle exception
      }
      
      
   }
   
   
   private static void openClassSurvey() {
      Connection conn = null;
      CallableStatement stat = null;
      DBUtil util = new DBUtil();
      ResultSet rs = null;
      
      String openClassSeq = sLoginOcseq.get(0);

      
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
               System.out.println("\t\t\t      " +rs2.getString("ioutnum") +"."+ rs2.getString("content"));
               
               //항목 개수 arr받기
               outNumList.add(rs2.getString("ioutnum"));
            }
            
               while(true) {
               
               System.out.println();
               System.out.print("\t\t\t입력 : ");
               String inNum = scan.nextLine();
                  
                  //항목 선택완료
                  if(outNumList.contains(inNum)) {
                     System.out.println("\t\t\t" +inNum + "번 항목 선택 완료");
                     System.out.println("\t\t\t--------------------------------------------------------------");
                     System.out.println();
                     
      
                     sql = "{ call procSurveyResultInsert(?,?,?) }";
                     
                  
                     CallableStatement stat3 = null;
                     stat3 = conn.prepareCall(sql);
                     
                     stat3.setString(1, openClassSeq);
                     stat3.setString(2, rs.getString("outnum"));
                     stat3.setString(3, inNum);
                     
                     stat3.executeUpdate();
                     
      
                     break;
                     
                  }else {
                     System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
                     
                  }

               System.out.println("\t\t\t--------------------------------------------------------------");
               }
         
         }
         
         rs.close();
         stat.close();
         
         
         conn.close();
         
         while(true) {
            
            System.out.println();
            System.out.println("\t\t\t" + "a. 뒤로가기");
            System.out.println("\t\t\t" + "b. 처음으로가기");
            System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
            System.out.print("\t\t\t입력 : ");
            
            String backHome = scan.nextLine();
            
            if(backHome.equals("a")) {
               //joo 뒤로가기 만들기
               break;
               
            } else if(backHome.equals("b")) {
               
//                  home();
               break;
               
               
            } else {
               
               System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
               
            }
         }
         
      } catch (Exception e) {
         System.out.println("AS_001()");
         e.printStackTrace();
      }
      
   }

}