import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;



/*
ID: farmersrice
LANG: JAVA
TASK: friday
PROG: friday
*/
public class friday {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader r = new BufferedReader(new FileReader("friday.in"));
		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		//PrintWriter w = new PrintWriter(System.out);
		
		int year = 1900;
		int day = 1;
		int weekday = 2; //0 for saturday, etc... 2 for monday which 1900 starts on
		int[] count13 = new int[7]; //0 for Saturday 1 for sunday etc...
		int month = 0; //0 for Jan, 1 for feb, etc...
		int years = Integer.parseInt(r.readLine());
		
		for (int i = 0; i < years; i++) {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) { //then leap year
				for (int j = 1; j <= 12; j++) { //month loop
					if (j == 4 || j == 6 || j == 9 || j == 11) { //30 day months
						for (int k = 0; k < 30; k++) { //day loop
							
							if (k == 13) {
								count13[weekday]++;
							}
							weekday = addWeekday(weekday);
						}
					} else if (j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10 || j == 12) { //31 day months
						for (int k = 0; k < 31; k++) { //day loop
							
							if (k == 13) {
								count13[weekday]++;
							}
							weekday = addWeekday(weekday);
						}
					} else { //february
						for (int k = 0; k < 29; k++) { //day loop
							
							if (k == 13) {
								count13[weekday]++;
							}
							weekday = addWeekday(weekday);
						}
					}
				}
			} else { //then regular year
				for (int j = 1; j <= 12; j++) { //month loop
					if (j == 4 || j == 6 || j == 9 || j == 11) { //30 day months
						for (int k = 0; k < 30; k++) { //day loop
							
							if (k == 13) {
								count13[weekday]++;
							}
							weekday = addWeekday(weekday);
						}
					} else if (j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10 || j == 12) { //31 day months
						for (int k = 0; k < 31; k++) { //day loop
							
							if (k == 13) {
								count13[weekday]++;
							}
							weekday = addWeekday(weekday);
						}
					} else { //february
						for (int k = 0; k < 28; k++) { //day loop
							
							if (k == 13) {
								count13[weekday]++;
							}
							weekday = addWeekday(weekday);
						}
					}
				}
			}
			
			
			year++;
		}
		
		for (int i = 1; i < count13.length; i++) {
				w.print(count13[i] + " ");
		}
		w.print(count13[0] + "\n");
		w.flush();

	}
	
	public static int addWeekday(int weekday) {
		if (weekday >= 6) {
			return 0;
		} else {
			return (weekday + 1);
		}
	}

}
