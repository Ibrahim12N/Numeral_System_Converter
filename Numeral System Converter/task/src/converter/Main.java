package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        String sourceRadix=scanner.nextLine();
        int sourceRadixVal;
        if(sourceRadix.matches("\\d+")) {
            sourceRadixVal=Integer.valueOf(sourceRadix);
            if(sourceRadixVal>36||sourceRadixVal<1){
                System.out.println("Error the range must be between 1 and 36");
            return;}}
        else{
        System.out.println("Error you must enter a number");
        return;}

        String intPart="";
        String fracPart="";
        String number=scanner.nextLine();
        String [] parts=number.split("\\.");
        intPart=parts[0];
        if(parts.length>1){
        fracPart=parts[1];}
        if(checkInputRange(intPart, sourceRadixVal)){
            return;
        } else if(checkInputRange(fracPart, sourceRadixVal)){
            return;
        }

        String targetRadix=scanner.nextLine();
        int targetRadixVal;
        if(targetRadix.matches("\\d+")) {
            targetRadixVal=Integer.valueOf(targetRadix);
            if(targetRadixVal>36||targetRadixVal<1){
                System.out.println("Error the range must be between 1 and 36");
                    return;}}
        else{ System.out.println("Error You must enter a number");
        return;}

        if(!number.contains(".")){
            System.out.println(convertIntPart(number, sourceRadixVal,targetRadixVal));
            return; }

        String intResult=convertIntPart(intPart, sourceRadixVal,targetRadixVal);
        String fracResult=convertFracPart(fracPart, sourceRadixVal,targetRadixVal);
        System.out.println(intResult+"."+fracResult);
    }

    private static boolean checkInputRange(String Part, int Radix) {
        boolean res=false;
        for(int i=0;i<Part.length();i++){
            int c= Character.getNumericValue(Part.charAt(i));
            if(c==1&&Radix==1){
                break; }
            if(c>=Radix){
            System.out.println("C: "+c+" inputRadix"+Radix);
                res=true;
                System.out.println("Error entered number out of source radix range");
            }}
        return res;
    }

    private static String convertFracPart(String fracPart, int sourceRadix, int targetRadix) {

        double fractWithoutLetters;
        double fractBeforeConvert=0;
        for(int i=0;i<fracPart.length();i++){
            int c= Character.getNumericValue(fracPart.charAt(i));
            fractWithoutLetters=c*(1/Math.pow(sourceRadix,i+1));
            fractBeforeConvert+=fractWithoutLetters;
    }

        StringBuilder fractionNumbersAfter=new StringBuilder();
        double fracAfterVal=fractBeforeConvert*targetRadix;
        int frac;
        for(int i=0;i<5;i++){
            frac= (int) (fracAfterVal/1);
            char c= Character.forDigit(frac,targetRadix);
            fractionNumbersAfter.append(c);
            fracAfterVal=fracAfterVal-frac;
            fracAfterVal=fracAfterVal*targetRadix;
        }
return fractionNumbersAfter.toString();
    }

    private static String convertIntPart(String intPart, int sourceRadix, int targetRadix) {
        int decimalVal;
        if(sourceRadix!=1){
        decimalVal=Integer.parseInt(intPart,sourceRadix);}
        else{
            decimalVal=intPart.length();}

        String intResult;
        StringBuilder intResultBuild=new StringBuilder();
        if(targetRadix!=1){
        intResult=Integer.toString(decimalVal,targetRadix);}
        else {
            for(int i=0;i<decimalVal;i++)
                intResultBuild.append(1);
            intResult=intResultBuild.toString();}
        return intResult;
    }
}
