package com.back;

public class Calc {
    public static int run(String exp) {
        switch (chkAction(exp)) {
            case "+" -> { return actionAdd(exp); }
            case "-" -> { return actionSub(exp); }
            case "*" -> { return actionMul(exp); }
        }
        return 0;
    }

    /* === chkAction === */
    public static String chkAction(String exp) {
        if(exp.contains(" + ")) return "+";
        else if(exp.contains(" - ")) return "-";
        else if(exp.contains(" * ")) return "*";
        return "Keep Going";
    }

    /* === Action === */
    private static int actionAdd(String exp) {
        String[] expBits = exp.trim().split(" \\+ ");
        int result = 0;
        for(String expBit : expBits) {
            result += !chkAction(expBit).equals("Keep Going") ?
                    run(expBit) : Integer.parseInt(expBit);
        }
        return result;
    }

    private static int actionSub(String exp) {
        String[] expBits = exp.trim().split(" - ");
        int result = 0;
        for (int i = 0; i < expBits.length; i++)
            result += !chkAction(expBits[i]).equals("Keep Going") ?
                    run(expBits[i]) * (i == 0 ? 1 : -1) : Integer.parseInt(expBits[i]) * (i == 0 ? 1 : -1);
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
