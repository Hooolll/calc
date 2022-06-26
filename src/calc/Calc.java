package calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        Calculations.out();
    }
}

class RomansNumbers {
    HashMap<Integer, String> numbers = new HashMap<>();

    protected RomansNumbers() {
        numbers.put(1, "I");
        numbers.put(2, "II");
        numbers.put(3, "III");
        numbers.put(4, "IV");
        numbers.put(5, "V");
        numbers.put(6, "VI");
        numbers.put(7, "VII");
        numbers.put(8, "VIII");
        numbers.put(9, "IX");
        numbers.put(10, "X");
        numbers.put(20, "XX");
        numbers.put(30, "XXX");
        numbers.put(40, "XL");
        numbers.put(50, "L");
        numbers.put(60, "LX");
        numbers.put(70, "LXX");
        numbers.put(80, "LXXX");
        numbers.put(90, "XC");
        numbers.put(100, "C");
    }

    public static String convert(int i) {
        RomansNumbers romanN = new RomansNumbers();

        if (i < 11) {
            return romanN.numbers.get(i);
        } else if (i % 10 == 0) {
            return romanN.numbers.get(i);
        } else {
            return (romanN.numbers.get(i / 10 * 10) + romanN.numbers.get(i % 10));
        }
    }

    public static int convert(String str) {
        RomansNumbers romanN = new RomansNumbers();
        int resultat = 0;

        for (Map.Entry<Integer, String> enter :
                romanN.numbers.entrySet()) {
            if (enter.getValue().equals(str)) {
                resultat = enter.getKey();
            }
        }
        return resultat;
    }

    public static boolean etoRoman(String str) {
        RomansNumbers romanN = new RomansNumbers();
        return romanN.numbers.containsValue(str);
    }
}

class Numbers {
    int a;
    int b;
    boolean arab;


    int getA(){
        return a;
    }
    int getB(){
        return b;
    }
    boolean etoArab(){
        return arab;
    }

    public static Numbers numsOpred(String str) {
        Numbers resultat = new Numbers();
        String [] numbers = str.replaceAll("\\s", "").split("[+-/*]");
        if (numbers.length > 2 || numbers.length <= 1) {
            System.out.println("Incorrect input");
            System.exit(0);
        }
        try {
            resultat.a = Integer.parseInt(numbers[0]);
            resultat.b = Integer.parseInt(numbers[1]);
            resultat.arab = true;
        }
        catch (NumberFormatException e) {
            if (RomansNumbers.etoRoman(numbers[0]) && RomansNumbers.etoRoman(numbers[1])) {
                resultat.a = RomansNumbers.convert(numbers[0]);
                resultat.b = RomansNumbers.convert(numbers[1]);
                resultat.arab = false;
            }
            else if (RomansNumbers.etoRoman(numbers[0]) ^ RomansNumbers.etoRoman(numbers[1])) {
                System.out.println("Different number formats");
                System.exit(0);
            }
            else {
                System.out.println("Insuited data input");
                System.exit(0);
            }
        }
        if (resultat.a > 10 || resultat.a < 1 || resultat.b > 10 || resultat.b < 1) {
            System.out.println("Too big or small numbers");
            System.exit(0);
        }
        return (resultat);
    }

}

class Calculations {
    private static String podschet(String str) {
        Numbers numbers = Numbers.numsOpred(str);
        int resultat = 0;


        if (str.contains("+")) {
            resultat = numbers.getA() + numbers.getB();
        } else if (str.contains("-")) {
            resultat = numbers.getA() - numbers.getB();
        } else if (str.contains("/")) {
            resultat = numbers.getA() / numbers.getB();
        } else if (str.contains("*")) {
            resultat = numbers.getA() * numbers.getB();
        } else {
            System.out.println("There are no operators");
            System.exit(0);
        }
        if (resultat > 0) {
            return numbers.etoArab() ? (resultat + "") : RomansNumbers.convert(resultat);
        }
        else {
            System.out.println("Negative result");
            System.exit(0);
        }
        return str;
    }

    public static void out() {
        Scanner in = new Scanner(System.in);
        System.out.println("Console calculator");
        System.out.println("Data is entered in the format correctly: a + b, a - b, a / b, a * b");
        System.out.println("If u wanna exit input 'exit'");
        System.out.println("Enter example - ");
        while (true) {
            String str = in.nextLine();
            if (str.equals("exit")) {
                System.exit(0);
                return;
            }
            System.out.println("Result = " + Calculations.podschet(str));
            System.out.println("Enter next example - ...");
        }
    }
}
