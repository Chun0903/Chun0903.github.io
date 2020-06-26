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

public class ProjectAdminStudent {
	static Scanner scan = new Scanner(System.in);;


	

	public void adminStudentMain() {
		// 관리자 - 교육생관리 - 메뉴 페이지

		Scanner scan = new Scanner(System.in);

		while (true) {

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t1. 교육생 정보 조회");
			System.out.println("\t\t\t2. 교육생 추가하기");
			System.out.println("\t\t\t3. 교육생 수정 or 삭제");
			System.out.println();
			System.out.println();
			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로 가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t번호를 입력해주세요 : ");
			String AdminInput = scan.nextLine();

			if (AdminInput.equals("1")) {
				// 교육생 정보 조회
				studnetAllInfo();	//교육생 전체정보
				

			} else if (AdminInput.equals("2")) {
				// 교육생 추가하기
				studentInsert();
				studentInsertComplete();

			} else if (AdminInput.equals("3")) {
				// 교육생 수정 or 삭제
				studnetAllInfoChange();
				studentSearchChange(); // 교육생 검색 - 수정 or 삭제

			} else if (AdminInput.equals("a")) {
				// 뒤로가기

				AdminMainPage admain = new AdminMainPage();
				admain.adminMain();

			} else if (AdminInput.equals("b")) {
				// 처음으로가기
				// home();
			} else {
				System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요");
			}

		}
	}

	private static void studentSelectDelete() { // 교육생 삭제

		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {

			String sql = "{ call procStudnetInfoSelectDelete(?)}";

			conn = util.open();

			stat = conn.prepareCall(sql);

			System.out.print("\t\t\t삭제할 교육생번호 : ");
			String num = scan.nextLine();

			// ***
			stat.setString(1, num); // in parameter, 주는값

			stat.executeQuery(); // select

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t삭제를 완료 했습니다.");

			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로가기");

			System.out.print("\t\t\t돌아갈화면을 입력하세요 : ");
			String selectNum = scan.nextLine();

			if (selectNum.equals("a")) {

				studnetAllInfoChange();

			} else if (selectNum.equals("b")) {

				// home(); 처음화면

			}

			stat.close();
			conn.close();

		} catch (Exception e) {

		}

	}


	private static void studentSelectChange() { // 교육생 수정

		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {

			String sql = "{ call procStudnetInfoChange(?,?,?,?,?)}";

			conn = util.open();

			stat = conn.prepareCall(sql);

			System.out.print("\t\t\t수정할 교육생번호 : ");
			String num = scan.nextLine();

			System.out.println("\t\t\t-----------------------수정할내용-----------------------");

			System.out.print("\t\t\t이름 : ");
			String name = scan.nextLine();
			System.out.print("\t\t\t전화번호(-포함) : ");
			String tel = scan.nextLine();
			System.out.print("\t\t\tid(영문+숫자 9자리) : ");
			String id = scan.nextLine();
			System.out.print("\t\t\t주민번호 뒷자리 : ");
			String ssn = scan.nextLine();

			stat.setString(1, num); // in parameter, 주는값 //교육생번호 입력

			for (int i = 0; i < name.length(); i++) { // 이름 유효성검사를 위해 문자 출력
				char c = name.charAt(i);

				if ((c >= '가' && c <= '힣')) {

					if (name.length() <= 5 && ssn.length() == 7) {
						// ***유효성 검사 후 일치시 값을 넣어준다.
						stat.setString(2, name); // in parameter, 주는값
						stat.setString(4, ssn); // in parameter, 주는값
						stat.setString(5, tel); // in parameter, 주는값

					} else {
						// 양식에 맞지 않을 경우
						System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.println("\t\t\t◆잘못입력하셨습니다. 다시입력해주세요.◆");

						studentSelectChange(); // 다시시작
					}

				} else {
					// 양식에 맞지 않을 경우
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t◆잘못입력하셨습니다. 다시입력해주세요.◆");

					studentSelectChange(); // 다시시작
				}

			}

			for (int i = 0; i < id.length(); i++) { // 아이디 유효성 검사를 위해 입력된 문자호출
				char c = id.charAt(i);

				if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= 0 && c <= 9)
						|| (id.length() >= 5 && id.length() <= 9)) {

					// 유효성 검사후 아이디 값 넣어주기
					stat.setString(3, id); // in parameter, 주는값

				} else {
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t◆잘못입력하셨습니다. 다시입력해주세요.◆");

					studentSelectChange(); // 다시시작
				}

			}

			stat.executeQuery(); // select

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t수정을 완료 했습니다.");

			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로가기");

			System.out.print("\t\t\t돌아갈화면을 입력하세요 : ");
			String selectNum = scan.nextLine();

			if (selectNum.equals("a")) {

				studnetAllInfoChange();

			} else if (selectNum.equals("b")) {

				// home(); 처음화면

			}

			stat.close();
			conn.close();

		} catch (Exception e) {

		}

	}

	private static void studentSearchChange() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;

		try {

			String sql = "{ call procStudentSearch(?,?) }";

			System.out.print("\t\t\t교육생 이름 : ");

			String num = scan.nextLine();

			conn = util.open();
			stat = conn.prepareCall(sql);

			stat.setString(1, num);
			stat.registerOutParameter(2, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(2);

			ResultSetMetaData rsmd = rs.getMetaData();

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t학생관리 - 학생검색정보");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓");

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[교육생번호]\t[교육생이름]\t[교육생ID]\t[주민번호뒷자리]\t[연락처]\t\t[등록일]");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			// 강지영 동명이인

			while (rs.next()) {

				System.out.print("\t\t\t");
				System.out.print(rs.getString("seq") + "\t\t"); // 교육생번호
				System.out.print(rs.getString("name") + "\t\t"); // 교육생이름
				System.out.print(rs.getString("id") + "\t"); // 교육생아이디
				System.out.print(rs.getString("pwd") + "\t\t"); // 교육생주민뒷자리
				System.out.print(rs.getString("tel") + "\t"); // 교육생연락처
				System.out.println(rs.getString("regDate") + "\t");// 교육생등록일

			}
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			while (true) {

				System.out.println("\t\t\t1.교육생 정보 수정");
				System.out.println("\t\t\t2.교육생 정보 삭제");
				System.out.println("\t\t\t3.교육생 중도탈락, 수료날짜 수정");
				System.out.println();
				System.out.println("\t\t\ta.뒤로가기");
				System.out.println("\t\t\tb.처음으로가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t번호를 입력하세요 : ");
				String selectNum = scan.nextLine();

				if (selectNum.equals("1")) {

					studentSelectChange();

					break;
				} else if (selectNum.equals("2")) {

					studentSelectDelete();

					break;
				} else if (selectNum.equals("3")) {

					procCompletionOrfail();

					break;
				} else if (selectNum.equals("a")) {

					studnetAllInfoChange();

					break;
				} else if (selectNum.equals("b")) {

					// home(); 처음화면

					break;
				} else {
					System.out.println("\t\t\t◆잘못입력하셨습니다 다시입력해주세요◆");
				}

				rs.close();
				stat.close();
				conn.close();

			}
		} catch (Exception e) {

		}

	}

	private static void studnetAllInfoChange() { // 교육생 전체 정보보기(수강신청횟수포함) - 교육생 관리 - 수정하기

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {

			conn = util.open();
			stat = conn.createStatement();

			// 행갯수 10개로 제한
			String sql = String.format("select * from(select * from vwStudentDetailInfo) where rownum <= 10");

			rs = stat.executeQuery(sql);

			// 2. 레코드(컬럼) 정보 얻어오기
			ResultSetMetaData rsmd = rs.getMetaData();

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t학생관리 - 학생전체정보");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓");

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[교육생이름]\t[주민번호뒷자리]\t[연락처]\t\t[등록일]\t\t[수강신청횟수]");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			while (rs.next()) {

				System.out.print("\t\t\t");
				System.out.print(rs.getString("name") + "\t\t");
				System.out.print(rs.getString("pwd") + "\t\t");
				System.out.print(rs.getString("tel") + "\t");
				System.out.print(rs.getString("regDate") + "\t");
				System.out.println(rs.getString("count") + "\t");

			}
			System.out.println("\t\t\t---------------이하생략---------------");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void studentInsert() { // 교육생 추가하기

		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {

			String sql = "{ call procStudentInsert(?,?,?,?)}";

			conn = util.open();

			stat = conn.prepareCall(sql);

			System.out.print("\t\t\t[이름](최대5글자) : ");
			String name = scan.nextLine();
			System.out.print("\t\t\t[전화번호](-포함) : ");
			String tel = scan.nextLine();
			System.out.print("\t\t\t[ID](영문+숫자 5~9자리) : ");
			String id = scan.nextLine();
			System.out.print("\t\t\t[주민번호 뒷자리](7자리) : ");
			String ssn = scan.nextLine();

			for (int i = 0; i < name.length(); i++) { // 이름 유효성검사를 위해 문자 출력
				char c = name.charAt(i);

				if ((c >= '가' && c <= '힣')) {

					if (name.length() <= 5 && ssn.length() == 7) {
						// ***유효성 검사 후 일치시 값을 넣어준다.
						stat.setString(1, name); // in parameter, 주는값
						stat.setString(2, ssn); // in parameter, 주는값
						stat.setString(3, tel); // in parameter, 주는값

					} else {
						// 양식에 맞지 않을 경우
						System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.println("\t\t\t◆잘못입력하셨습니다. 다시입력해주세요.◆");

						studentInsert(); // 다시시작
					}

				} else {
					// 양식에 맞지 않을 경우
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t◆잘못입력하셨습니다. 다시입력해주세요.◆");

					studentInsert(); // 다시시작
				}

			}

			for (int i = 0; i < id.length(); i++) { // 아이디 유효성 검사를 위해 입력된 문자호출
				char c = id.charAt(i);

				if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= 0 && c <= 9)
						|| (id.length() >= 5 && id.length() <= 9)) {

					stat.setString(4, id); // in parameter, 주는값

				} else {
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t◆잘못입력하셨습니다. 다시입력해주세요.◆");

					studentInsert(); // 다시시작
				}

			}

			stat.executeQuery(); // select
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t등록을 완료 했습니다.");

			stat.close();
			conn.close();

		} catch (Exception e) {

		}

	}

	public static void studentInsertComplete() { // 교육생 추가하기 - 등록완료화면

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t등록된 정보");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		try {

			conn = util.open();
			stat = conn.createStatement();

			String sql = "select * from (select * from tblstudent order by seq desc) where rownum = 1";

			rs = stat.executeQuery(sql);

			while (rs.next()) {

				System.out.print("\t\t\t이름:");
				System.out.println(rs.getString("name"));
				System.out.print("\t\t\t연락처:");
				System.out.println(rs.getString("tel"));
				System.out.print("\t\t\t아이디:");
				System.out.println(rs.getString("id"));
				System.out.print("\t\t\t주민번호뒷자리:");
				System.out.println(rs.getString("pwd"));
				System.out.print("\t\t\t등록일:");
				System.out.println(rs.getString("regdate"));

			}
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {

		}

		System.out.println("\t\t\ta.뒤로가기");
		System.out.println("\t\t\tb.처음으로가기");

		System.out.print("\t\t\t돌아갈화면을 입력해주세요 : ");

		String back = scan.nextLine();

		if (back.equals("a")) {
			// 이전으로가기
			ProjectAdminStudent adstudent = new ProjectAdminStudent();
			adstudent.adminStudentMain();

		} else if (back.equals("b")) {
			// 처음으로 가기
			// home(); //메인메뉴

		} else {
			System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요");
		}

	}

	public static void studnetAllInfo() { // 교육생 전체 정보보기(수강신청횟수포함) - 교육생 관리 - 첫화면

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		// 페이징 배열
		ArrayList<String> list = new ArrayList<String>();

		try {

			conn = util.open();
			stat = conn.createStatement();

			// 행갯수 10개로 제한
			String sql = String.format("select * from vwstudentDetailinfo");

			rs = stat.executeQuery(sql);

			// 2. 레코드(컬럼) 정보 얻어오기
			ResultSetMetaData rsmd = rs.getMetaData();

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t학생관리 - 학생전체정보");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓");

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[No.]\t[교육생이름]\t[주민번호뒷자리]\t[연락처]\t\t[등록일]\t\t[수강신청횟수]");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		
			while (rs.next()) {

				
				list.add(rs.getString("name") + "\t\t"+
						rs.getString("pwd") + "\t\t"+
						rs.getString("tel") + "\t"+
						rs.getString("regDate") + "\t"+
						rs.getString("count") + "\t");
				

			} 
			page(list);
			
			stat.close();
			conn.close();
		}catch (Exception e) {

			e.printStackTrace();
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
	            System.out.println("\t\t\t"+"3. 교육생검색하기");
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
	            } else if(answer.equals("3")){
	            	
	            	ProjectAdminStudent adStudent = new ProjectAdminStudent();
	            	adStudent.studentSearch();
	            	
	            	break;
		        }else if(answer.equals("a")) {
	               
	               ProjectAdminStudent adStudent = new ProjectAdminStudent();
	               adStudent.adminStudentMain();
	               break;
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

	public void studentSearch() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;

		try {

			String sql = "{ call procStudentSearchSelect(?,?) }";

			System.out.print("\t\t\t검색할 교육생 이름 : ");

			String num = scan.nextLine();

			conn = util.open();
			stat = conn.prepareCall(sql);

			stat.setString(1, num);
			stat.registerOutParameter(2, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(2);
			
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t학생관리 - 학생전체정보");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓");

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[교육생번호]\t[교육생이름] \t[주민번호뒷자리]\t[연락처]\t\t[등록일]");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			// 강지영 동명이인

			while (rs.next()) {

				System.out.print("\t\t\t");
				System.out.print(rs.getString("seq") + "\t\t"); // 교육생번호
				System.out.print(rs.getString("name") + "\t\t"); // 교육생이름
				System.out.print(rs.getString("pwd") + "\t\t"); // 교육생주민뒷자리
				System.out.print(rs.getString("tel") + "\t"); // 교육생연락처
				System.out.println(rs.getString("regDate") + "\t");// 교육생등록일

			}
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			
			if(num != null) {
				

				procStudentAcademicInfo();	//교육생 상세보기
			}
			
			rs.close();
			stat.close();
			conn.close();
			
			

		} catch (Exception e) {

		}

	}

	public static void procStudentAcademicInfo() {

		// PreparedStatement(Text) = CallableStatment(Procedure)

		// 매개변수가 있는 프로시저 호출하기
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {

			String sql = "{ call procStudentAcademicInfo(?,?)}";

			System.out.print("\t\t\t 검색할 교육생 번호 입력 : ");
			String num = scan.nextLine();

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t학생관리 - 학생상세정보");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓");

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t[교육생이름]\t[과정명]\t\t\t\t[과정기간]\t\t[강의실명]\t[중도탈락]\t[수료날짜]");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			conn = util.open();
			stat = conn.prepareCall(sql);

			stat.setString(1, num);
			stat.registerOutParameter(2, OracleTypes.CURSOR);

			stat.executeUpdate();

			rs = (ResultSet) stat.getObject(2);

			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {

				System.out.print("\t\t\t");
				System.out.print(rs.getString("studentname") + "\t"); // 교육생이름
				System.out.print(rs.getString("classname") + "\t"); // 과정명
				System.out.print(rs.getString("startdate") + "~"); // 과정시작
				System.out.print(rs.getString("enddate") + "\t"); // 과정끝
				System.out.print(rs.getString("roomname") + "\t"); // 강의실명

				if (rs.getString("faildate") == null) { // 중도탈락값이 null이면 X표시
					System.out.print("X\t");
				} else {
					System.out.print(rs.getString("faildate") + "\t"); // 중도탈락
				}

				if (rs.getString("completdate") == null) { // 수료날짜값이 null이면 X표시
					System.out.print("X\t");
				} else {
					System.out.println(rs.getString("completdate") + "\t");// 수료날짜
				}

			}

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {

		}
		System.out.println("\t\t\ta.뒤로가기");
		System.out.println("\t\t\tb.처음으로가기");

		System.out.print("\t\t\t돌아갈화면을 입력해주세요 : ");

		String back = scan.nextLine();

		if (back.equals("a")) {
			// 이전으로가기
			studnetAllInfo();
			
			ProjectAdminStudent adStudent = new ProjectAdminStudent();
        	adStudent.studentSearch();
        	
			procStudentAcademicInfo();

		} else if (back.equals("b")) {
			// 처음으로 가기
			// home(); //메인메뉴

		} else {
			System.out.println("\t\t\t잘못된 입력입니다. 다시입력하세요");
		}

	}

	private static void classAttendSecond() { // 출결현황 조회 - 과정별 출결 - 두번째화면

		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t출결조회 - 과정별 출결조회");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓");

		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;

		try {

			String sql = "{ call procSelectClassStudentAttend(?,?,?,?) }";

			System.out.print("\t\t\t검색할 과정번호를 입력하세요 : ");

			String num = scan.nextLine();

			System.out.print("\t\t\t검색할 시작날짜 ex)20/04/20) : ");

			String indate = scan.nextLine();

			System.out.print("\t\t\t검색할 끝날짜 ex)20/05/25) : ");

			String outdate = scan.nextLine();

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t교육생이름 \t날짜\t\t출퇴근시간\t\t\t출결상황");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			conn = util.open();
			stat = conn.prepareCall(sql);

			stat.setString(1, num);
			stat.setString(2, indate);
			stat.setString(3, outdate);
			stat.registerOutParameter(4, OracleTypes.CURSOR);

			stat.executeQuery();

			rs = (ResultSet) stat.getObject(4);

			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {

				System.out.print("\t\t\t");
				System.out.print(rs.getString("studentname") + "\t"); // 교육생이름 출력
				System.out.print(rs.getString("attendday") + "\t"); // 교육생이름 출력
				System.out.print(rs.getString("attendindate") + "~"); // 출근시간 출력
				System.out.print(rs.getString("attendoutdate") + "\t"); // 퇴근시간 출력
				System.out.print(rs.getString("state")); // 츨결상황 출력

				System.out.println();
			}

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\ta.뒤로가기");
			System.out.println("\t\t\tb.처음으로가기");

			System.out.print("\t\t\t돌아갈화면을 입력하세요. : ");

			String back = scan.nextLine();

			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {

		}

	}

	

	public static void procCompletionOrfail() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();
		ResultSet rs = null;

		try {

			String sql = "{ call procCompletionOrfail(?,?,?) }";

			System.out.print("\t\t\t수정할 교육생 번호 : ");

			String num = scan.nextLine();

			System.out.print("\t\t\t중도탈락 날짜 ex)20/06/06 or 없을시 Enter : ");

			String faildate = scan.nextLine();

			System.out.print("\t\t\t수료 날짜  ex)20/10/10 or 없을시 Enter : ");

			String completedate = scan.nextLine();

			conn = util.open();
			stat = conn.prepareCall(sql);

			stat.setString(1, num);
			stat.setString(2, faildate);
			stat.setString(3, completedate);

			stat.executeQuery();

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t수정을 완료 했습니다.");

			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로가기");

			while (true) {
				System.out.print("\t\t\t돌아갈화면을 입력하세요 : ");
				String selectNum = scan.nextLine();

				if (selectNum.equals("a")) {

					studnetAllInfoChange();

					break;
				} else if (selectNum.equals("b")) {

					// home(); 처음화면

					break;
				} else {

					System.out.println("잘못입력 하셨습니다. 다시 입력해주세요.");

				}

				rs.close();
				stat.close();
				conn.close();

			}

		} catch (Exception e) {

		}

	}

	
}
