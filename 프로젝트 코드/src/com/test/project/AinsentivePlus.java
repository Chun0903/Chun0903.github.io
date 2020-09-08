package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.project.DBUtil;

public class AinsentivePlus {
	//스캐너
	static Scanner scan = new Scanner(System.in);
	//항목 리스트
	static ArrayList<String> outNumList = new ArrayList<String>();
	//개설과정 번호 리스트
	static ArrayList<String> ocseq = new ArrayList<String>();
	
//	public static void main(String[] args) {
//	
//		assPlus();
//		
//	}

	public static void assPlus() {

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
//		ResultSet rs = null;
		
		try {
			conn = util.open();
			
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t<교사평가 설문지 추가>");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			System.out.println();
			

			//과정목록 가져오기
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t" + "<개설 과정 목록>");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			openClassList();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		
			

			//과목번호 선택하기
			System.out.print("\t\t\t과목 번호 입력\t: ");
			String openClassSeq = scan.nextLine();
			//질문번호 선택하기
			System.out.print("\t\t\t질문번호 입력\t: ");
			String arOutNum = scan.nextLine();;
			//질문
			System.out.print("\t\t\t질문 내용 입력\t: ");
			String questionAS = scan.nextLine();;
			//항목 개수 정하기
			System.out.print("\t\t\t항목 개수 입력\t: ");
			String maxAiOutNum = scan.nextLine(); 

			
			//질문 추가하기
			String sql = "";
		
			sql =  "{ call procAssPlus(?,?,?) }";
			
			stat = conn.prepareCall(sql);

			stat.setString(1, openClassSeq);
			stat.setString(2, arOutNum);
			stat.setString(3, questionAS);
			
			stat.executeUpdate();

			
			stat.close();
			
			outNumList.clear();
			//항목
			for (int i =1; i <= Integer.parseInt(maxAiOutNum) ;i++) {
//				int aiOutNum = i;
				//항목 내용 매우나쁨, 나쁨, 좋음, 매우좋음 등등
				
				System.out.printf("\t\t\t%d번째 항목내용 입력\t: " , i);
				String content = scan.nextLine(); 
				outNumList.add(content);
				
			
			}//for 끝
			
			while(true) {
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t위 내용을 등록하시겠습니까?");
				
				System.out.println("\t\t\t1. 네");
				System.out.println("\t\t\t2. 아니오");
				System.out.println("\n");
				System.out.println("\t\t\ta. 뒤로가기");
				System.out.println("\t\t\tb. 처음으로가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t입력 : ");
				
				String yesNo = scan.nextLine();
				
				if(yesNo.equals("1")) {
					
					for (int i =1; i < outNumList.size() ;i++) {
						
						sql =  "{ call procAiPlus(?,?) }";
						
						stat = conn.prepareCall(sql);
						
						stat.setInt(1, i);
						stat.setString(2, outNumList.get(i));
						
						stat.executeUpdate();
					
					}
					System.out.println();
					System.out.println("\t\t\t등록 완료");
					System.out.println();
					
					while(true) {

						System.out.println("\t\t\t" + "a. 뒤로가기");
						System.out.println("\t\t\t" + "b. 처음으로가기");
						System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.print("\t\t\t입력 : ");
							
						String backHome = scan.nextLine();
							
						if(backHome.equals("a")) {
							//joo 뒤로가기 만들기
							  AInsentive ainsentive = new AInsentive();
							  ainsentive.A_insentive(); 
							

						} else if(backHome.equals("b")) {
								
							//home();
							break;
								
						} else {
								
							System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
					
						}
					}
					
					
				}else if(yesNo.equals("2")){
					assPlus();
				}else if(yesNo.equals("a")) {
					//joo 뒤로가기 입력
					  AInsentive ainsentive = new AInsentive();
					  ainsentive.A_insentive(); 
					break;
				}else if(yesNo.equals("b")) {
//					home();
					AdminMainPage admin = new AdminMainPage();
					admin.adminMain();
					
					break;
				}else {
					
				}
			}
			
			stat.close();
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("실패");
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
	
}
