package com.back;

public class Calc {
    public static int run(String exp) {
        while(true) {
            // Boundary("(...)") 검사 및 선행 계산
            if(!chkBoundary(exp))  break;
            else exp = actionBoundary(exp);
        }

        // 계산식 내 연산자 검사 및 계산
        switch (chkAction(exp)) {
            case "+" -> { return actionAdd(exp); }
            case "-" -> { return actionSub(exp); }
            case "*" -> { return actionMult(exp); }
            default -> { return Integer.parseInt(exp); }
        }
    }


    /* === chkAction === */
    // 계산식 내 "(", ")" 유무 확인
    private static boolean chkBoundary(String exp) {
        return (exp.contains("(") || exp.contains(")"));
    }

    // 계산식 내 연산자 유무 확인(계산순서의 역순으로 검사: +,- -> *)
    private static String chkAction(String exp) {
        if(exp.contains(" + ")) return "+";
        else if(exp.contains(" - ")) return "-";
        else if(exp.contains(" * ")) return "*";
        return "Keep Going";
    }


    /* === Action === */
    // 계산식 내 "(", ")" 선행계산
    // 논리: ()연산은 가장 안쪽 ()부터 계산한다.
    // -> ")"의 위치(close)를 찾고, 그 위치보다 앞에 있는 "("의 위치(open)를 찾는다.
    // -> ()안의 계산식을 계산하여 결과값으로 대체한다.
    // -> 반환값: open 이전 문자열 + open~close 문자열 계산값 문자열 + close 이후 문자열
    private static String actionBoundary(String exp) {
        int open = exp.lastIndexOf("(", exp.indexOf(")"));
        int close = exp.indexOf(")");
        return exp.substring(0, open) + Integer.toString(run(exp.substring(open+1, close))) + exp.substring(close+1);
    }

    // 덧셈계산
    // 논리: 계산식 내 "+"를 기준으로 계산식 분리
    // -> 각 계산식에 대해 chkAction 검사
    // -> chkAction이 "Keep Going"이 아닌 경우(run) / "Keep Going"인 경우(Integer.parseInt) -> 결과값 반환
    private static int actionAdd(String exp) {
        String[] expBits = exp.trim().split(" \\+ ");
        int result = 0;
        for(String expBit : expBits) {
            result += !chkAction(expBit).equals("Keep Going") ?
                    run(expBit) : Integer.parseInt(expBit);
        }
        return result;
    }

    // 뺄셈계산
    // 논리: 계산식 내 "-"를 기준으로 계산식 분리
    // -> 각 계산식에 대해 chkAction 검사
    // -> chkAction이 "Keep Going"이 아닌 경우(run) / "Keep Going"인 경우(Integer.parseInt) -> 결과값 반환
    private static int actionSub(String exp) {
        String[] expBits = exp.trim().split(" - ");
        int result = 0;
        for (int i = 0; i < expBits.length; i++)
            result += !chkAction(expBits[i]).equals("Keep Going") ?
                    run(expBits[i]) * (i == 0 ? 1 : -1) : Integer.parseInt(expBits[i]) * (i == 0 ? 1 : -1);
        return result;
    }

    // 곱셈계산
    // 논리: 계산식 내 "*"를 기준으로 계산식 분리 / 계산
    // -> 결과값 반환
    // 곱셈식은 재귀 불필요(chkAction 검사 불필요) -> 어차피 앞서 +,- 계산이 모두 끝났기 때문에 곱셈만 하면 되기 때문이다.
    private static int actionMult(String exp) {
        String[] expBits = exp.trim().split(" \\* ");
        int result = 1;
        for (int i = 0; i < expBits.length; i++)
            result *= Integer.parseInt(expBits[i]);
        return result;
    }
}