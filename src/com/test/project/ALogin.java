package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class ALogin {
	
	
	public static void main(String[] args) {
		
		aLogin();

	}

	static void aLogin() {
		Connection conn = null;
		CallableStatement stat = null;
		CallableStatement stat2 = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);
		
		try {
			
			
			
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t" + "<관리자 로그인>");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			System.out.println();
			System.out.print("\t\t\t아이디 : ");
			String id = scan.nextLine();
			
			System.out.print("\t\t\t비밀번호 : ");
			String pw = scan.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			
//			String id = "gahgg4833";
//			String pw = "3168928";
			
			
			String sql = "";
			
			sql = "{ call procALoginId(?,?) }";
			
			conn = util.open("211.63.89.59","project","java1234");
			stat = conn.prepareCall(sql);
			

				
			stat.setString(1, id);
			stat.registerOutParameter(2, OracleTypes.NUMBER);

			//아이디 불러오기
			stat.executeQuery();
			
			try {
				
				sql = "{ call procALoginPw(?,?,?) }";
				
				stat2 = conn.prepareCall(sql);
				
				stat2.setString(1, pw);
				stat2.registerOutParameter(2, OracleTypes.NUMBER);
				stat2.registerOutParameter(3, OracleTypes.VARCHAR);
				
				stat2.executeQuery();
				
			} catch (Exception e) {
				System.out.println("\t\t\t아이디와 비밀번호가 일치하지 않습니다.");
				aLogin();
			}
			
			if(id.equals(stat2.getString(3))) {
				
				System.out.println("\t\t\t로그인에 성공했습니다.");
//				System.out.println();
				System.out.printf("\t\t\t안녕하세요. [%s번] 관리자님\n", stat2.getString(2));
				System.out.println();
				
				
			}else {
				System.out.println("\t\t\t아이디와 비밀번호가 일치하지 않습니다.");
				aLogin();
			}
			
			
			stat.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("\t\t\t존재하지 않는 아이디 입니다. 로그인에 실패했습니다.");
			aLogin();
		}
		
		
	}

}
