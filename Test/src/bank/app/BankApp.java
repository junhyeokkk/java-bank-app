package bank.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankApp {
	private static Scanner scanner = new Scanner(System.in);
	private static List<Account> accounts = new ArrayList<>();

	public static void main(String[] args){

		boolean run = true;
		while(run) {
			System.out.println("-----------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("-----------------------------------------");
			System.out.print("선택>");

			try {
				int selectNo = Integer.parseInt(scanner.nextLine());

				if(selectNo == 1) {
					createAccount();
				} else if (selectNo == 2) {
					accountList();
				} else if (selectNo == 3) {
					deposit();
				} else if (selectNo == 4) {
					withdraw();
				} else if (selectNo == 5) {
					run = false;
				}
				} catch (NumberFormatException e) {
					System.out.println("번호형식에 맞게 선택값을 입력하세요!");
			}
		}
		System.out.println("프로그램 종료");
	}

	private static void createAccount() {
		System.out.println("----------------  계좌생성  -----------------");
		System.out.print("계좌번호: ");
		String ano = scanner.nextLine();
		System.out.print("계좌주: ");
		String owner = scanner.nextLine();
		System.out.print("초기입금액: ");
		int balance = Integer.parseInt(scanner.nextLine());

		Account account = new Account(ano, owner, balance);
		accounts.add(account);

		System.out.println("계좌가 생성되었습니다.");


	}
	private static void accountList() {

		System.out.println("----------------  계좌목록  -----------------");
		if(accounts.size() != 0) {
			for(int i=0; i<accounts.size();i++) {
				Account account = accounts.get(i);
				System.out.print(account.getAno()+" "+account.getOwner()+" "+account.getBalance());
				System.out.println();
			}
		} else {
			System.out.println("생성된 계좌가 없습니다!");
		}

	}
	private static void deposit() {
		System.out.println("----------------   예금   -----------------");
		System.out.print("계좌번호: ");
		String ano = scanner.nextLine();
		System.out.print("예금액: ");
		int deposit_balance = Integer.parseInt(scanner.nextLine());

		if(findAccount(ano) != null) {
			findAccount(ano).setBalance(findAccount(ano).getBalance()+deposit_balance);
			System.out.println("남은 잔액: " + findAccount(ano).getBalance());

			System.out.println(" 예금이 성공되었습니다.");
		} else {
			System.out.println("계좌번호가 잘못되었습니다.");
		}
	}

	private static void withdraw() {
		System.out.println("----------------   출금   -----------------");
		System.out.print("계좌번호: ");
		String ano = scanner.nextLine();
		System.out.print("출금액: ");
		int withdraw_balance = Integer.parseInt(scanner.nextLine());

		if(findAccount(ano).getBalance() > withdraw_balance && findAccount(ano) != null) {
			findAccount(ano).setBalance(findAccount(ano).getBalance()-withdraw_balance);
			System.out.println("남은 잔액: " + findAccount(ano).getBalance());
			System.out.println(" 출금이 성공되었습니다.");
		} else if (findAccount(ano).getBalance() < withdraw_balance){
			System.out.println("출금액이 계좌 잔액보다 많습니다!");
		} else if (findAccount(ano) != null) {
			System.out.println("계좌번호가 잘못되었습니다.");
		}
	}

	private static Account findAccount(String ano) {
		Account account = null;
		for(int i=0; i<accounts.size();i++) {

			if(accounts.get(i).getAno().equals(ano)) {
				account = accounts.get(i);
			}
		}
		return account;
	}

}
