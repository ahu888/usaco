import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;

/*
ID: farmersrice
LANG: JAVA
TASK: concom
*/

public class concom {

	
	//static Company[] companies = new Company[10000]; //at most 100 companies and we do not use 0
	static HashSet<Company> companies = new HashSet<Company>();
	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("concom.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		
		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		//HashSet<Company> companies = new HashSet<Company>();
		
		int n = Integer.parseInt(r.readLine());
		
		for (int i = 0; i < n; i++) { //read in cases
			String input = r.readLine();
			String[] inputs = input.split(" ");
			int ownerNum = Integer.parseInt(inputs[0]);
			int ownedNum = Integer.parseInt(inputs[1]);
			int ownedShare = Integer.parseInt(inputs[2]);
			Company owner = new Company(ownerNum);
			Company owned = new Company(ownedNum);
			companies.add(owner);
			companies.add(owned);
			
			owner.addCompanyOwnership(owned, ownedShare);
		}
		
		for (int i = 1; i < companies.length; i++) {
			for (int j = 1; j < companies.length; j++) {
				companies[i].addCompany(j);
			}
		}
		for (int i = 1; i < companies.length; i++) {
			for (int j = 1; j < companies.length; j++) {
				if (companies[i].owned[j] && i != j) w.println(i + " " + j);
			}
		}
		
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}
	
	public static class Company {
		int number;
		boolean[] owned = new boolean[101];
		boolean[] added = new boolean[101];
		int[] ownedShares = new int[101]; //at most 100 companies, and we do not use [0]
		
		public Company(int n) {
			number = n;
		}
		
		public void addCompanyOwnership(Company company, int percent) {
			if (percent == 0) return;
			ownedShares[company] += percent;
			if (ownedShares[company] > 50) owned[company] = true;
			
			
		}
		
		public void addCompany(int company) {
			if (owned[company] && added[company] == false) {
				for (int i = 1; i < companies.size(); i++) {
					if (i == company) {
						for (int j = 1; j < companies.size(); j++) {
							addCompanyOwnership(j, companies[i].ownedShares[j]);
						}
						added[company] = true;
						companies[company].added[number] = true;
					}
				}
			}
		}

		public boolean equals(Object o) {
			Company c = null;
			try {
				c = (Company) o;
			} catch (Exception e) {
				return false;
			}
			if (c.number == number) return true;
			else return false;
		}
	}

}
