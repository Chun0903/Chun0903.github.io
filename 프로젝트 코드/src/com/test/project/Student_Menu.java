package com.test.project;

import java.util.Scanner;

public class Student_Menu {
	
	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);		
	}
	
	String word2;

	public void menu() {
		
		SAssSurvey survey = new SAssSurvey();
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.println("\t\t\t1. 출결 조회");
			System.out.println();
			System.out.println("\t\t\t2. 성적 조회");
			System.out.println();
			System.out.println("\t\t\t3. 사전 평가");
			System.out.println();
			System.out.println("\t\t\t4. 과정 평가");
			
			System.out.println();
			System.out.println("\t\t\ta. 로그아웃");
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.print("\t\t\t번호를 입력하세요 : ");
			String word = scan.nextLine();
			System.out.println();
			
			//출결 조회
			if (word.equals("1")) {
				Student_Attend attend = new Student_Attend();
				attend.menu();
			//성적 조회
			} else if (word.equals("2")) {
				Student_Score score = new Student_Score();
				score.Score();
				System.out.println();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\ta. 뒤로 가기");
				System.out.println("\t\t\tb. 처음으로 가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				System.out.print("\t\t\t입력하세요 : ");
				
				word2 = scan.nextLine();
				
				if (word2.equals("a")) {
					
					menu();
				} else if (word2.equals("b")) {
					
				} else {
					
					pause(scan);
				}
			//사전 평가
			} else if (word.equals("3")) {
				
				Student_PriorSurvey prior = new Student_PriorSurvey();
				prior.PriorSurveyChk();
				System.out.println();
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t\t\ta. 뒤로 가기");
				System.out.println("\t\t\tb. 처음으로 가기");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				
				System.out.print("\t\t\t입력하세요 : ");
				word2 = scan.nextLine();
				
				
				if (word2.equals("a")) {
					menu();
					
				} else if (word2.equals("b")) {
					Menu.home();
					
				} else {
					
					pause(scan);
				}
			} else if (word.equals("4")){
				//과정평가 메소드 
				survey.AssSurvayResultInput(Student_Login.id,Student_Login.pwd);
			}
			//로그아웃
			else if (word.equals("a")) {
				System.out.println("\t\t\t로그아웃 했습니다.");
				System.out.println();
				Student_Login login = new Student_Login();
				while(loop) {
					Menu home = new Menu();
					home.home();
				}
			} else {
				pause(scan);
			}//if
			
		}//while
		
	}//menu
	
	private static void pause(Scanner scan) {
		
		System.out.println();
		System.out.println("\t\t\t잘못 입력했습니다.");
		System.out.println("\t\t\t계속하실려면 엔터를 입력하세요.");
		
		scan.nextLine();
		
	}//pause
	
}//Student_Menu