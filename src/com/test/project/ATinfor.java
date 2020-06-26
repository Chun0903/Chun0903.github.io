package com.test.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.project.DBUtil;

import oracle.jdbc.OracleTypes;

public class ATinfor {
	
	//스캐너
	static Scanner scan = new Scanner(System.in);
	
	//교사 번호 리스트
	static ArrayList<String> tseq = new ArrayList<String>();
	//강의가능과목 리스트
	static ArrayList<String> tPname = new ArrayList<String>();
	//과목 리스트
	static ArrayList<String> sname = new ArrayList<String>();
	
	static Menu home = new Menu();
	


	
	public static void A_tinfo() {

		System.out.println();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t" + "<교사 계정관리>");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		
		//교사 정보 리스트
		tlist();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t\t\t" + "1.강의 가능 목록");
		System.out.println("\t\t\t" + "2.교사 정보 등록");
		System.out.println("\t\t\t" + "3.교사 정보 수정");
		System.out.println("\t\t\t" + "4.교사 정보 삭제");
		System.out.println("\t\t\t" + "5.정보 상세보기");
		System.out.println("\n");
		System.out.println("\t\t\t" + "a. 뒤로가기");
		System.out.println("\t\t\t" + "b. 처음으로가기");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t입력 : ");
		String teacherSeq = scan.nextLine();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");	
		
		if(teacherSeq.equals("1")) {
			//강의 가능 목록 보기	
			T_possible();

		} else if(teacherSeq.equals("2")) {
			//정보 등록
			InforInsert();
			
		} else if(teacherSeq.equals("3")) {
			//정보 수정
			InfoUpdateT();
			
		} else if(teacherSeq.equals("4")) {
			//정보 삭제
			InforDelete();
			
		} else if(teacherSeq.equals("5")) {
			//정보 상세보기
			T_seukejul();
			
		} else if(teacherSeq.equals("a")) {
			//뒤로 가기
			//joo_뒤로가기 추가하기
			AdminMainPage adminMainPage = new AdminMainPage();
			adminMainPage.adminMain();

		} else if(teacherSeq.equals("b")) {
			//처음으로 가기
			home.home();
			
		} else {
			System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			A_tinfo();
		}
		
	}//정보메인
	
	private static void T_seukejul() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		
		
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println("\t\t\tb. 처음으로 가기");
		System.out.println("\n");
		System.out.print("\t\t\t상세 정보를 볼 교사의 번호를 입력해주세요 : ");
		String teacherSeq = scan.nextLine();

		if(tseq.contains(teacherSeq)) {
			
			try {
				System.out.println();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t<정보 상세보기>"+ "\n");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");				
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
					System.out.printf("\t\t\t[과정번호] : %s\n\t\t\t[과정이름] : %s\n\t\t\t[과정기간] : %s ~ %s\n\t\t\t[ 강의실 ] : %s\n"
							,rs0.getString("cseq")
							,rs0.getString("cname")
							,rs0.getString("ocStartdate")
							,rs0.getString("ocEnddate")
							,rs0.getString("rname"));
				}
		
				rs0.close();
				stat0.close();
				System.out.println("\t\t\t" + "------------------------------------------------------------------------------------------------------------------------------");
		
				//과목
				System.out.println("\t\t\t[과목번호]\t[과목명]\t\t\t[과목기간(시작 - 끝)]\t\t\t[교재명]\t\t\t\t\t\t\t[진행여부]");
					
				sql = "{ call procTCTInfo(?,?) }";
				stat = conn.prepareCall(sql);
					
				stat.setString(1, teacherSeq);
				stat.registerOutParameter(2, OracleTypes.CURSOR);
					
				stat.executeQuery();
					
				rs = (ResultSet)stat.getObject(2);
					
					while (rs.next()) {
						System.out.printf("\t\t\t%s\t%s\t%s - %s\t\t%s\t%s\n"
								,rs.getString("sseq")
								,rs.getString("sname")
								,rs.getString("osStartdate")
								,rs.getString("osEnddate")
								,rs.getString("bname")
								,rs.getString("subjecProgress"));
					}
		
					stat.close();
					conn.close();
		
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			while(true) {

				System.out.println();
				System.out.println("\t\t\t" + "a. 뒤로가기");
				System.out.println("\t\t\t" + "b. 처음으로가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t입력 : ");
					
				String backHome = scan.nextLine();
					
				if(backHome.equals("a")) {
					//뒤로가기
					A_tinfo();
					break;

				} else if(backHome.equals("b")) {
					//처음으로 가기
					home.home();
					break;
						
					
				} else {
						
					System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
			
				}
			}

				
		}else if(teacherSeq.equals("a")) {
			//뒤로가기
			A_tinfo();
			
		}else if(teacherSeq.equals("b")) {
			//처음으로 가기 
			home.home();	
			
		}else {
			System.out.println();
			System.out.println("\t\t\t" + "존재하지 않는 교사입니다. 다시입력하세요");
			T_seukejul();
		}
		
	}
	
	
	private static void InforDelete() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();

		try {
			
			//퇴사한 교사 선택
			System.out.println();
			System.out.print("\t\t\t퇴사한 교사의 번호를 선택해 주세요 : ");
			String teacherSeq = scan.nextLine();
			
			if(tseq.contains(teacherSeq)) {
			

				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.printf("\t\t\t%s번 교사의 정보를 지우시겠습니까?\n",teacherSeq);
				System.out.println("\t\t\t1. 네");
				System.out.println("\t\t\t2. 아니오");
				System.out.println("\n");
				System.out.println("\t\t\ta. 뒤로가기");
				System.out.println("\t\t\tb. 처음으로가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t입력 : ");
				
				String yesNo = scan.nextLine();
				
					if(yesNo.equals("1")) {
					
					conn = util.open("211.63.89.59","project","java1234");
					String sql = "";
					
					sql = "{ call procTInfordelete(?) }";
					
					stat = conn.prepareCall(sql);
					
					stat.setString(1, teacherSeq);
					
					stat.executeUpdate();
					
					System.out.println();
					System.out.printf("\t\t\t>>%s번 교사 퇴사 완료", teacherSeq);
					
					stat.close();
					conn.close();
					
							System.out.println();
					
							while(true) {
								
								System.out.println("\t\t\t" + "a. 뒤로가기");
								System.out.println("\t\t\t" + "b. 처음으로가기");
								System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
								System.out.print("\t\t\t입력 : ");
								
								String backHome = scan.nextLine();
								
								if(backHome.equals("a")) {
									A_tinfo();
									break;
									
									
									
								} else if(backHome.equals("b")) {
									
									home.home();
									break;
									
								} else {
									
									System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
						
								}
							}
				
					
					}else if(yesNo.equals("2")) {
						//아니요 선택시
						InforDelete();
						
					}else if(yesNo.equals("a")) {
						//뒤로가기
						A_tinfo();
						
					}else if(yesNo.equals("b")) {
						//처음으로가기
						home.home();
						
					}else{
						System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
						InforDelete();
					}
			
			}else {
				System.out.println("\t\t\t" + "존재하지 않는 교사입니다. 다시입력하세요");
				InforDelete();
			}	
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
	}
	
	private static void InfoUpdateT() {
		System.out.println();
		
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println("\t\t\tb. 처음르로 가기");
		System.out.println();
		System.out.println();
		System.out.print("\t\t\t정보를 수정할 교사 번호 입력 : ");
		String teacherSeq = scan.nextLine();
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		
		
		if(tseq.contains(teacherSeq)) {
			
		tInfoUpdate(teacherSeq);
		
		}else if (teacherSeq.equals("a")) {
			//뒤로가기
			A_tinfo();
			
		} else if (teacherSeq.equals("b")) {
			//처음으로 가기
			home.home();
		} else {
			System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
			InfoUpdateT();
		}
		
		
		
	}
	

	private static void tInfoUpdate(String teacherSeq) {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();


		try {
			String  sql = "";
			

			for(int i=0;;i++) {
			System.out.printf("\n\t\t\t<%s번 교사의 정보를 수정합니다.>\n\n", teacherSeq);
			
			System.out.println("\t\t\t1. 이름수정 \n\t\t\t2. 비밀번호 수정 \n\t\t\t3. 전화번호 수정\n\t\t\t4. 전체수정"
					+ "\n\n\n\t\t\ta. 뒤로가기\n\t\t\tb. 처음으로 가기");

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t입력 : ");
			String updateNum = scan.nextLine();
			System.out.println();
			
			for(int j=0;;j++) {
			
				if(updateNum.equals("1")) {
					scan = new Scanner(System.in);
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t<이름 수정>");
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println();
					System.out.println("\t\t\ta. 뒤로가기");
					System.out.println("\t\t\tb. 처음으로 가기");
					System.out.println("\n");
					System.out.println("\t\t\t수정할 이름을 입력해주세요");
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.print("\t\t\t입력 : ");
					String name = scan.nextLine();

						
						if(name.equals("a")) {
							//뒤로가기
							A_tinfo();
							break;
						}else if(name.equals("b")) {
							//처음으로가기
							home.home();
							
						}else {
						
							
							//검사 통과
							sql = "{ call procTInforUpdateName(?,?) }";
							
							conn = util.open("211.63.89.59","project","java1234");
							stat = conn.prepareCall(sql);
							
							stat.setString(1, teacherSeq);
							stat.setString(2, name);
							
							stat.executeUpdate();
							
							stat.close();
							System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
							System.out.println();
							System.out.println("\t\t\t이름 수정이 완료되었습니다.");
							System.out.println();
							
							conn.close();
							stat.close();
						
							while(true) {
								System.out.println("\t\t\t" + "a. 뒤로가기");
								System.out.println("\t\t\t" + "b. 처음으로가기");
								System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
								System.out.print("\t\t\t입력 : ");
								
								String backHome = scan.nextLine();
								
								if(backHome.equals("a")) {
									
									tInfoUpdate(teacherSeq);
									break;
									
									
								} else if(backHome.equals("b")) {
									
									home.home();
									break;
									
								} else {
									
									System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
						
								}		
	
							}
				}
				

				
			} else if(updateNum.equals("2")) {
				scan = new Scanner(System.in);
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t<비밀번호 수정>");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\ta. 뒤로가기");
				System.out.println("\t\t\tb. 처음으로 가기");
				System.out.println("\n");
				System.out.println("\t\t\t수정할 비밀번호를 입력해주세요");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t입력 : ");
				String pw = scan.nextLine();
				
					if(pw.equals("a")) {
						//뒤로가기
						tInfoUpdate(teacherSeq);
						break;
					}else if(pw.equals("b")) {
						//처음으로 가기
						home.home();
						
					}else {
				
						sql = "{ call procTInforUpdatePw(?,?) }";
						conn = util.open("211.63.89.59","project","java1234");
						stat = conn.prepareCall(sql);
						
						stat.setString(1, teacherSeq);
						stat.setString(2, pw);
						
						stat.executeUpdate();
						
						conn.close();
						stat.close();
						System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.println("\t\t\t비밀번호 수정이 완료되었습니다.");
						System.out.println();
						
						while(true) {
							
							
							System.out.println("\t\t\t" + "a. 뒤로가기");
							System.out.println("\t\t\t" + "b. 처음으로가기");
							System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
							System.out.print("\t\t\t입력 : ");
							
							String backHome = scan.nextLine();
							
							if(backHome.equals("a")) {
								//뒤로가기
								tInfoUpdate(teacherSeq);
								break;
								
								
								
							} else if(backHome.equals("b")) {
								//처음으로 가기
								home.home();
								break;
								
							} else {
								
								System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
					
							}
						}
					}
						
						
			} else if(updateNum.equals("3")) {
				
				scan = new Scanner(System.in);
				scan = new Scanner(System.in);
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t<전화번호 수정>");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\ta. 뒤로가기");
				System.out.println("\t\t\tb. 처음으로 가기");
				System.out.println("\n");
				System.out.println("\t\t\t수정할 전화번호를 입력해주세요");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t입력 : ");
				String tel = scan.nextLine();
				
					if(tel.equals("a")) {
						//뒤로가기
						tInfoUpdate(teacherSeq);
						break;
						
					}else if(tel.equals("b")) {
						//처음으로 가기
						home.home();
						
					}else {
				
						sql = "{ call procTInforUpdateTel(?,?) }";
						
						conn = util.open("211.63.89.59","project","java1234");
						stat = conn.prepareCall(sql);
						
						stat.setString(1, teacherSeq);
						stat.setString(2, tel);
						
						stat.executeUpdate();
						
						conn.close();
						stat.close();
						
						System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.println();
						System.out.println("\t\t\t전화번호 수정이 완료되었습니다.");
						System.out.println();
						
							while(true) {
								System.out.println("\t\t\t" + "a. 뒤로가기");
								System.out.println("\t\t\t" + "b. 처음으로가기");
								System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
								System.out.print("\t\t\t입력 : ");
								
								String backHome = scan.nextLine();
								
								if(backHome.equals("a")) {
									//뒤로가기
									tInfoUpdate(teacherSeq);
									break;
									
									
								} else if(backHome.equals("b")) {
									//처음으로가기
									home.home();
									break;
									
								} else {
									
									System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
						
								}
	
							}
					}
					
			} else if(updateNum.equals("4")) {
				scan = new Scanner(System.in);
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t<전체 수정>");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.println("\t\t\ta. 뒤로가기");
				System.out.println("\t\t\tb. 처음으로 가기");
				System.out.println("\n");
				System.out.println("\t\t\t수정할 정보를 입력해주세요");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\t수정을 중단 하시려면 이름 란에 a를 입력해주세요.");
				System.out.print("\t\t\t이름\t: ");
				String name = scan.nextLine();
				
				if(name.equals("a")) {
					//뒤로가기
					tInfoUpdate(teacherSeq);
					break;
					
				}else if(name.equals("b")) {
					//처음으로 가기
					home.home();
					
				}else {
					System.out.println("\t\t\t아이디\t: *아이디는 수정이 불가능합니다.*");
					System.out.print("\t\t\t비밀번호\t: ");
					String pw = scan.nextLine();
					System.out.print("\t\t\t전화번호\t: ");
					String tel = scan.nextLine();
				
					sql = "{ call procTInforUpdate(?,?,?,?) }";
					
					conn = util.open("211.63.89.59","project","java1234");
					stat = conn.prepareCall(sql);
					
					stat.setString(1, teacherSeq);
					stat.setString(2, name);
					stat.setString(3, pw);
					stat.setString(4, tel);
					
					
					stat.executeUpdate();
					
					conn.close();
					stat.close();
					
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println();
					System.out.println("\t\t\t수정이 완료되었습니다.");
					System.out.println();
					
					while(true) {
						System.out.println("\t\t\t" + "a. 뒤로가기");
						System.out.println("\t\t\t" + "b. 처음으로가기");
						System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.print("\t\t\t입력 : ");
						
						String backHome = scan.nextLine();
						
						if(backHome.equals("a")) {
							//뒤로가기
							tInfoUpdate(teacherSeq);
							break;
							
							
						} else if(backHome.equals("b")) {
							//처음으로 가기
							home.home();
							break;
							
						} else {
							
							System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
				
						}
					}
		}
				
				
			} else if(updateNum.equals("a")) {

				InfoUpdateT();
				break;
				
			} else if(updateNum.equals("b")){
				//처음으로가기
				home.home();
				
			}
			else {

				System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
				A_tinfo();
			}
			
			
			
			conn.close();
				}//수정선택
			}//교사번호 입력
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	private static void InforInsert() {
		Connection conn = null;
		CallableStatement stat = null;
		DBUtil util = new DBUtil();

		try {
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t" + "<교사 정보 등록>");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.println("\t\t\t등록할 정보를 입력해 주세요");
			System.out.print("\t\t\t이름 : ");
			String teacherName = scan.nextLine();
			System.out.print("\t\t\t전화번호 : ");
			String teacherTel = scan.nextLine();
			System.out.print("\t\t\t아이디: ");
			String teacherId = scan.nextLine();
			System.out.print("\t\t\t비밀번호(주민번호 뒷자리) : ");
			String teacherPw = scan.nextLine();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			System.out.println("\t\t\t등록하시겠습니까?");
			System.out.println("\t\t\t1. 네");
			System.out.println("\t\t\t2. 아니오");
			System.out.println("\n");
			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로 가기");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t입력 : ");
			String yesNo = scan.nextLine();
			
			if(yesNo.equals("1")) {	
			
			String sql = "";
			
			//교사 등록
			sql = "{ call procTInforInsert(?,?,?,?) }";

			conn = util.open("211.63.89.59","project","java1234");
			stat = conn.prepareCall(sql);
			
			stat.setString(1, teacherName);
			stat.setString(2, teacherTel);
			stat.setString(3, teacherId);
			stat.setString(4, teacherPw);

			
			stat.executeUpdate();
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.println("\t\t\t등록 완료");
			System.out.println();
			
			stat.close();
			conn.close();
			
			while(true) {
				
				System.out.println("\t\t\t" + "a. 뒤로가기");
				System.out.println("\t\t\t" + "b. 처음으로가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t입력 : ");
				
				String backHome = scan.nextLine();
				
				if(backHome.equals("a")) {
					
					A_tinfo();
					break;
					
				} else if(backHome.equals("b")) {
					//처음으로 가기
					home.home();
					
				} else {
					System.out.println();
					System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
					System.out.println();
				}
			}
			
			}else if(yesNo.equals("2")) {
				InforInsert();
				
			}else if(yesNo.equals("a")) {
				//뒤로가기
				A_tinfo();
				
			}else if(yesNo.equals("b")) {
				//처음으로 가기
				home.home();
				
			} else {
				System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
				InforInsert();
			}
			
		} catch (Exception e) {
			System.out.println("\t\t\t등록실패");
			e.printStackTrace();
		}
		
	}
	
	private static void T_possible() {
		
		System.out.println();
		System.out.println("\t\t\t1. 강의 가능 목록 전체 조회");
		System.out.println("\t\t\t2. 가의 가능 목록 선택 조회");
		System.out.println("\t\t\t3. 강의 가능 목록 교사별 추가");
		System.out.println("\n");
		System.out.println("\t\t\ta. 뒤로가기");
		System.out.println("\t\t\tb. 처음으로 가기");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.print("\t\t\t입력 : ");
		
		String choise = scan.nextLine();
		
		if(choise.equals("1")) {
			T_possibleList();
			
		}else if(choise.equals("2")) {
			t_possiblelListChoice();
			
		}else if(choise.equals("3")) {
			t_possiblePlus();
			
		}else if(choise.equals("a")) {
			//뒤로가기
			A_tinfo();
			
		}else if(choise.equals("b")) {
			//처음으로 가기 
			home.home();
			
		}else {
			System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
			T_possible();
		}
		
		
	}
	
	private static void T_possibleList() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {
			String  sql = "";
			
			//교사 정보 출력
			sql = "{ call procTInfor(?) }";
			
			conn = util.open("211.63.89.59","project","java1234");
			stat = conn.prepareCall(sql);
			
			stat.registerOutParameter(1, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(1);
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t<교사 강의가능 목록>");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			
			while (true) {
			
			while (rs.next()) {
				System.out.println("\t\t\t-교사정보-");
				System.out.printf("\n\t\t\t[번호] : %s\n\t\t\t[이름] : %s\n\t\t\t[주민번호 뒷자리] : %s\n\t\t\t[전화번호] : %s\n"
						,rs.getString("seq")
						,rs.getString("name")
						,rs.getString("pw")
						,rs.getString("tel"));
				System.out.println("\t\t\t-------------------------------------------");
				
				//강의 가능과목 출력
				CallableStatement stat2 = null;
				ResultSet rs2 = null;
				
				sql = "{ call procTInforSubject(?,?) }";
				
				stat2 = conn.prepareCall(sql);
				
				stat2.setString(1, rs.getString("seq"));
				stat2.registerOutParameter(2, OracleTypes.CURSOR);
				
				stat2.executeQuery();
				
				rs2 = (ResultSet)stat2.getObject(2);
				
				System.out.println("\t\t\t-강의가능 목록-");
				System.out.println("\t\t\t[과목번호]\t[과목명]");
				
				while (rs2.next()) {
					System.out.printf("\t\t\t%s\t%s\n"
							,rs2.getString("sseq")
							,rs2.getString("sname"));
				}
				
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				
				rs2.close();
				stat2.close();
			}
				
				System.out.println();

				System.out.println("\t\t\t" + "a. 뒤로가기");
				System.out.println("\t\t\t" + "b. 처음으로가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t입력 : ");
				
				String possiblePlus = scan.nextLine();
				

				if(possiblePlus.equals("a")) {
					//뒤로가기
					T_possible();
					break;
					
					
					
				} else if(possiblePlus.equals("b")) {
					//처음으로 가기
					home.home();
					break;
					
				} else {
					
					System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
		
				}
			}

				
			
	
			
			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void t_possiblelListChoice() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		
		try {
			
			String sql = "";
			sql = "{ call procTInforSubject(?,?) }";
			
			//교사 번호 입력
			System.out.println();
			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로가기");
			System.out.println("\t\t\t강의 가능 목록을 조회 하고 싶은 교사의 번호를 입력하세요.");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t입력 : ");
			String teacherSeq = scan.nextLine();
			
			if(tseq.contains(teacherSeq)) {
			
				conn = util.open("211.63.89.59","project","java1234");
				stat = conn.prepareCall(sql);
				
				stat.setString(1, teacherSeq);
				stat.registerOutParameter(2, OracleTypes.CURSOR);
				
				stat.executeQuery();
				
				rs = (ResultSet)stat.getObject(2);
				
				System.out.println();


				System.out.println("\t\t\t" + "--------------------------------------------------------------");
				System.out.printf("\t\t\t- [%s번] 교사의 강의 가능과목 -\n",teacherSeq);
				System.out.println("\t\t\t" + "--------------------------------------------------------------");
				System.out.println();
				System.out.println("\t\t\t[과목 번호]\t[과목명]");
				
				while (rs.next()) {
					System.out.printf("\t\t\t%s\t%s\n"
							,rs.getString("sseq")
							,rs.getString("sname"));
					tPname.add(rs.getString("sname"));
				}
				
				conn.close();
				stat.close();
				
				// 백홈 추가
				while(true) {
					
					System.out.println();
					System.out.println("\t\t\t" + "a. 뒤로가기");
					System.out.println("\t\t\t" + "b. 처음으로가기");
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.print("\t\t\t입력 : ");
					
					String backHome = scan.nextLine();
					
					if(backHome.equals("a")) {
						//뒤로가기
						t_possiblelListChoice();
						break;
						
					} else if(backHome.equals("b")) {
						//처음으로 가기
						home.home();
						
					} else {
						System.out.println();
						System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
						System.out.println();
					}
				}
				

				
			}else if(teacherSeq.equals("a")) {
				//뒤로가기
				T_possible();
				
			}else if(teacherSeq.equals("b")) {
				//처음으로가기
				home.home();
				
			}else {
				System.out.println("\t\t\t" + "존재하지 않는 교사입니다. 다시입력하세요");
				//joo 뒤로가기 강의 가능
				t_possiblelListChoice();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	//--------------------------


	private static void t_possiblePlus() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		
		try {
			
			String sql = "";
			sql = "{ call procTInforSubject(?,?) }";
			
			//교사 번호 입력
			System.out.println();
			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로가기");
			System.out.println("\t\t\t강의 가능 목록을 추가하고 싶은 교사의 번호를 입력하세요.");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t입력 : ");
			String teacherSeq = scan.nextLine();
			
			if(tseq.contains(teacherSeq)) {
			
				conn = util.open("211.63.89.59","project","java1234");
				stat = conn.prepareCall(sql);
				
				stat.setString(1, teacherSeq);
				stat.registerOutParameter(2, OracleTypes.CURSOR);
				
				stat.executeQuery();
				
				rs = (ResultSet)stat.getObject(2);
				
				System.out.println();


				System.out.println("\t\t\t" + "--------------------------------------------------------------");
				System.out.printf("\t\t\t- [%s번] 교사의 강의 가능과목 -\n",teacherSeq);
				System.out.println("\t\t\t" + "--------------------------------------------------------------");
				System.out.println();
				System.out.println("\t\t\t[과목 번호]\t[과목명]");
				
				while (rs.next()) {
					System.out.printf("\t\t\t%s\t%s\n"
							,rs.getString("sseq")
							,rs.getString("sname"));
					tPname.add(rs.getString("sname"));
				}
				
				conn.close();
				stat.close();
				
				//전체 과목 목록
				subjectList();
				
				//추가
				subPlus(teacherSeq);
			
				
				
				
			}else if(teacherSeq.equals("a")) {
				//뒤로가기
				T_possible();
				
			}else if(teacherSeq.equals("b")) {
				//처음으로 가기
				home.home();
				
			}else {
				System.out.println("\t\t\t" + "존재하지 않는 교사입니다. 다시입력하세요");
				t_possiblePlus();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private static void subPlus(String teacherSeq) {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		
		try {
			
			String sql = "";
			sql = "{ call procTPossibleInsert(?,?) }";
			
			conn = util.open("211.63.89.59","project","java1234");
			stat = conn.prepareCall(sql);

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\ta. 뒤로가기");
			System.out.println("\t\t\tb. 처음으로가기");
			System.out.println("\n");
			System.out.println("\t\t\t추가할 과목의 이름을 입력해주세요");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("\t\t\t입력 : ");
			String name = scan.nextLine();
				
			stat = conn.prepareCall(sql);
			
			stat.setString(1, teacherSeq);
			stat.setString(2, name);
			
			if(sname.contains(name)) {
				
				if(tPname.contains(name)) {
					System.out.println();
					System.out.println("\t\t\t" + "이미 있는 과목입니다. 다시입력하세요");
					subPlus(teacherSeq);
					
				}else if(name.equals("a")) {
					//뒤로 가기
					t_possiblePlus();
					
				}else if(name.equals("b")) {
					//처음으로 가기 
					home.home();
					
				}else {
					
					sname.clear();
					tPname.clear();
					
					while(true) {
						System.out.println();

						System.out.println("\t\t\t" + "--------------------------------------------------------------");
						System.out.printf("\t\t\t%s번 교사의 강의가능목록에 %s과목을 추가하시겠습니까?\n",teacherSeq,name);
						System.out.println("\t\t\t" + "--------------------------------------------------------------");
						System.out.println("\t\t\t1. 네");
						System.out.println("\t\t\t2. 아니오");
						System.out.println("\n");
						System.out.println("\t\t\ta. 뒤로가기");
						System.out.println("\t\t\tb. 처음으로가기");
						System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.print("\t\t\t입력 : ");
						
						String yesNo = scan.nextLine();
						
						if(yesNo.equals("1")) {
							stat.executeUpdate();
							
							System.out.println();
							System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
							System.out.println("\t\t\t추가 완료");
							System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
							
							while(true) {
							
								System.out.println("\t\t\t" + "a. 뒤로가기");
								System.out.println("\t\t\t" + "b. 처음으로가기");
								System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
								System.out.print("\t\t\t입력 : ");
								
								String backHome = scan.nextLine();
								
								if(backHome.equals("a")) {
									//뒤로가기
									
									break;
									
								} else if(backHome.equals("b")) {
									//처음으로 가기
									home.home();
									
								} else {
									System.out.println();
									System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
									System.out.println();
								}
							}
							
							
						}else if(yesNo.equals("2")){
							subPlus(teacherSeq);
							
						}else if(yesNo.equals("a")){
							subPlus(teacherSeq);
							
						}else if(yesNo.equals("b")){
							home.home();
							break;
							
						}else {
							System.out.println("\t\t\t" + "잘못된 입력입니다. 다시입력하세요");
						}
					
					}
					
					
				}
				
				
			}else if(name.equals("a")) {
				t_possiblePlus();
				
			}else if(name.equals("b")) {
				home.home();
				
			}else {
				System.out.println("\t\t\t" + "존재하지 않는 과목입니다. 다시입력하세요");
				subPlus(teacherSeq);
			}
	
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	private static void subjectList() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();

		try {
			
			String sql = "";
			
			sql = "{ call procTSubAll(?) }";
			
			conn = util.open("211.63.89.59","project","java1234");
			stat = conn.prepareCall(sql);
			
			stat.registerOutParameter(1, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(1);
			
			System.out.println();


			System.out.println("\t\t\t" + "--------------------------------------------------------------");
			System.out.println("\t\t\t<과목 목록>");
			System.out.println("\t\t\t" + "--------------------------------------------------------------");
			System.out.println("\t\t\t[번호]\t[이름]");
			
			while (rs.next()) {
				System.out.printf("\t\t\t%s\t%s\n"
						,rs.getString("seq")
						,rs.getString("name"));
				sname.add(rs.getString("name"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	private static void tlist() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();		
		
		tseq.clear();
		
		try {
			//리스트 생성
			
			//프로시저 부르기
			String sql = "{ call procTInfor(?) }";

			//open
			conn = util.open("211.63.89.59","project","java1234");
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
			
			System.out.println("\t\t\t[번호]\t[이름]\t[아이디]\t\t[비밀번호]\t[전화번호]");
			
			//출력
			while (rs.next()) {
				System.out.println("\t\t\t--------------------------------------------------------------");
				System.out.printf("\t\t\t%s\t%s\t%s\t%s\t\t%s\n"
						,rs.getString("seq")
						,rs.getString("name")
						,rs.getString("id")
						,rs.getString("pw")
						,rs.getString("tel"));
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
	
}//AT
