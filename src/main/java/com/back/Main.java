package com.back;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.print("계산식을 입력하세요(예: 1 + 2 - 3) : ");
        int result = Calc.run(sc.nextLine());
        System.out.println(result);
    }
}
