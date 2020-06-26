package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class TassView {
   //스캐너
   static Scanner scan = new Scanner(System.in);
   
   static ArrayList<String> tOcseq = new ArrayList<String>();
   
//   public static void main(String[] args) {
//      TLogin Tlogin = new TLogin();   //메소드 재 선언
//      Tlogin.tLogin();
//      TAssView();
//   }

   public static void TAssView() {

   
   Connection conn = null;
   CallableStatement stat = null;
   DBUtil util = new DBUtil();
   ResultSet rs = null;
   
   
   try {
      System.out.println();
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      System.out.println("\t\t\t<교사평가 조회>");
      System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
      
      
      conn = util.open("211.63.89.59","project","java1234");

      //교사번호 가져오기
      String teacherSeq = TLogin.tseqLogin.get(0);
      
      
      String sql = "";
      
      //과정번호 가져오기
      sql = "select seq from tblOpenClass where teacherSeq =" + teacherSeq;
      
      Statement stat4 = null;
      ResultSet rs4 = null;
      
      stat4 = conn.createStatement();
      
      rs4 = stat4.executeQuery(sql);
      
      while(rs4.next()) {
      tOcseq.add(rs4.getString("seq"));
      }
      
      //과목맡은것 중에서 마지막꺼 가져오기
      String openClassSeq = tOcseq.get(tOcseq.size() - 1);
      
//      String openClassSeq = "1";
      
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
         
         sql = "{ call procAssTView(?,?,?) }";
         CallableStatement stat3 = null;
         ResultSet rs3 = null;
         stat3 = conn.prepareCall(sql);
         
         stat3.setString(1, openClassSeq);
         stat3.setString(2, rs.getString("outnum"));
         stat3.registerOutParameter(3, OracleTypes.CURSOR);
         
         stat3.executeQuery();
         
         rs3 = (ResultSet)stat3.getObject(3);
         
         try {
            
         
         
         while (rs2.next() && rs3.next()) {
            System.out.printf("\t\t\t   %s. %-20s"
                     ,rs2.getString("ioutNum")
                     ,rs2.getString("content"));
            System.out.printf("\t\t\t%8s명\n"
                     ,rs3.getString("count"));
            
            

         }
         
         } catch (Exception e) {
            System.out.println("\t\t\t 이 후 질문에 대한 설문지 작성을 마친 학생이 없습니다.");
            goBack();
         }
                  
         System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
         
      }
      
      goBack();
      
   } catch (Exception e) {
      System.out.println("AS_001()");
      e.printStackTrace();
   }
   
}

   private static void goBack() {
      while(true) {

         System.out.println();
         
         System.out.println("\t\t\t" + "a. 뒤로가기");
         System.out.println("\t\t\t" + "b. 처음으로가기");
         System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
         System.out.print("\t\t\t입력 : ");
            
         String backHome = scan.nextLine();
            
         if(backHome.equals("a")) {
            //joo 뒤로가기 만들기
        	 Teacher2 teacher2 = new Teacher2();
        	 teacher2.insentiveView();
        	 
            break;

         } else if(backHome.equals("b")) {
               
            //home();
            break;
               
         } else {
               
            System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
      
         }
      
      }
   }
   

}