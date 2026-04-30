package com.back;

public class Calc {
    public static int run(String exp) {
        switch (isAction(exp)) {
            case "+" -> { return actionAdd(exp); }
            case "-" -> { return actionSub(exp); }
            case "*" -> { return actionMul(exp); }
        }
        return 0;
    }

    public static String isAction(String exp) {
        if(exp.contains(" + ")) return "+";
        else if(exp.contains(" - ")) return "-";
        else if(exp.contains(" * ")) return "*";
        return "";
    }

    private static int actionAdd(String exp) {
        String[] expBits = exp.trim().split(" \\+ ");
        int result = 0;
        for(String i : expBits) {
            if(i.contains(" - "))
                result += run(i);
            else
                result += Integer.parseInt(i);
        }
        return result;
    }

    private static int actionSub(String exp) {
        String[] expBits = exp.trim().split(" - ");
        int result = 0;
        for (int i = 0; i < expBits.length; i++)
            if(expBits[i].contains(" * "))
                result += run(expBits[i]) * (i == 0 ? 1 : -1);
            else
                result += Integer.parseInt(expBits[i]) * (i == 0 ? 1 : -1);
        return result;
    }

    private static int actionMul(String exp) {
        String[] expBits = exp.trim().split(" \\* ");
        int result = 1;
        for (int i = 0; i < expBits.length; i++)
            result *= Integer.parseInt(expBits[i]);
        return result;
    }
}
