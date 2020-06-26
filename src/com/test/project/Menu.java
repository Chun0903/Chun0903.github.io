package com.test.project;

import java.util.Scanner;

public class Menu {
	
	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);		
	}
	
	public static void main(String[] args) {
		
		home();
		
	}//main

	
	
	
	 static void home() {
		boolean loop = true;
		
		
		
		
		System.out.println("\t\t\t\t\t\t"+ "  ,_\r\n" + 
				"\t\t\t\t\t\t" + "  | `\"\"---..._____\r\n" + 
				"\t\t\t\t\t\t" + "  '-...______    _````\"\"\"\"\"\"\"'`|\r\n" + 
				"\t\t\t\t\t\t" + "         \\   ```` ``\"---...__  |\r\n" + 
				"\t\t\t\t\t\t" + "         |`              |   ``!\r\n" + 
				"\t\t\t\t\t\t" + "         |               |     A\r\n" + 
				"\t\t\t\t\t\t" + "         |               /\\   /#\\\r\n" + 
				"\t\t\t\t\t\t" + "         /`--..______..-'  |  ###\r\n" + 
				"\t\t\t\t\t\t" + "        |   /  `\\    /`--. |  ###\r\n" + 
				"\t\t\t\t\t\t" + "       _|  |  .-;`-./;-.  ||  ###\r\n" + 
				"\t\t\t\t\t\t" + "      / \\  \\ /\\#|    |#/\\/ /\\ ##'\r\n" + 
				"\t\t\t\t\t\t" + "      |  `-' \\__/ _  \\__/ |  |`#\r\n" + 
				"\t\t\t\t\t\t" + "      \\_,                 /_/\r\n" + 
				"\t\t\t\t\t\t" + "         `\\              /\r\n" + 
				"\t\t\t\t\t\t" + "           '.  '.__.'  .'\r\n" + 
				"\t\t\t\t\t\t" + "    jgs      `-,____,-'\r\n" + 
				"\t\t\t\t\t\t" + "              /\"\"\"I\"\"\\\r\n" + 
				"\t\t\t\t\t\t" + "             /`---'--'\\" + "\n" + 
				"\n" +
				"\t\t\t\t"	+	"                            Z99EZ      ZZ9EZ                         \r\n" + 
				"\t\t\t\t"	+		"                           Z     Z    ZZ    Z                         \r\n" + 
				"\t\t\t\t"	+		"                           ZZEZEZZ    DZZZEZZ                         \r\n" + 
				"\t\t\t\t"	+		"                            Z 8 Z      z B Z                          \r\n" + 
				"\t\t\t\t"	+		"                         DZBZD ZZBZj Z8ZZ  Z9ZZ                       \r\n" + 
				"\t\t\t\t"	+		"                         zZ9W,W,jEZ5 ZZ5,WwwzZZ                       \r\n" + 
				"\t\t\t\t"	+		"                            ZZZZZ      EZZZZ                          \r\n" + 
				"\t\t\t\t"	+		"                           Z8   ZZ    ZZ    Z                         \r\n" + 
				"\t\t\t\t"	+		"                           Z    wZ    ZZ    Z                         \r\n" + 
				"\t\t\t\t"	+		"                           ZZZZZZz     ZZZZZZ     " + "\n" + 
				"\t\t\t\t" + "               ,D5w    ,      y jD,     y,yDZ  j        w  D     B   \r\n" + 
				"\t\t\t\t" + "      ZZZ     ZZZEZZ   DZZZ    ZZjEZZZj  ZZWBZ  ZZZ    ZZZw ZZ   ZZ   \r\n" + 
				"\t\t\t\t" + "     ZZ ZZ   ZZ        Z, Zz   ZZ    ZZ  ZW     Z ZZ  DZ Z   ZZ ZZ    \r\n" + 
				"\t\t\t\t" + "    zZ  wZy  ZD       ZZ  ZZ   ZZ    ZZ  ZZZZZ  ZD Zy Z  Z    ZZZ     \r\n" + 
				"\t\t\t\t" + "    ZZZZZZZ  ZZ      DZZZZZZZ  ZZ   9ZZ  Z      ZZ 8ZZZ  Z    yZ      \r\n" + 
				"\t\t\t\t" + "   ZZ     ZZ  zZZZZZ ZZ    jZZ ZZZZZZ    ZZZZZw ZZ  ZZ  8ZD   ZZW     ");
		
		
		
		
		
		
		
		
		
		while (loop) {
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.println("\t\t\t1. 관리자");
			System.out.println();
			System.out.println("\t\t\t2. 교사");
			System.out.println();
			System.out.println("\t\t\t3. 수강생");
			System.out.println();
			System.out.println("\t\t\t4. 종료");
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			System.out.print("\t\t\t번호를 입력하세요 : ");
			String num = scan.nextLine();
			
			System.out.println();
			
			if (num.equals("1")) {
				//관리자
				AdminMainPage admin = new AdminMainPage();
				admin.admin();
				
				
			} else if (num.equals("2")) {
				//교사
				TLogin.tLogin();
				
			} else if (num.equals("3")) {
				//수강생
				Student std = new Student();
				std.student();
				
			} else if (num.equals("4")) {
				//종료
				System.out.println("\t\t\t종료합니다.");
				System.exit(0);
			} else {
				pause(scan);
			}
			
		}//while
	}
	
private static void pause(Scanner scan) {
		
		System.out.println();
		System.out.println("\t\t\t잘못 입력했습니다.");
		System.out.println("\t\t\t계속하시려면 엔터를 입력하세요.");
		
		scan.nextLine();
		
	}//pause

}//Main