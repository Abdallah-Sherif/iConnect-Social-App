package com.example.iconnect.Entities;

 
/**
 *
 * @author ABDELRAHMAN
 */
import java.util.regex.Pattern;
public class Loginclass {
 
    private static final Pattern CAPITAL_LETTER_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern SMALL_LETTER_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d");
    private static final Pattern SYMBOL_PATTERN = Pattern.compile("[^\\w\\s]");
 
    public static boolean hasCapitalLetter(String string) {
        return CAPITAL_LETTER_PATTERN.matcher(string).find();
    }
 
    public static boolean hasSmallLetter(String string) {
        return SMALL_LETTER_PATTERN.matcher(string).find();
    }
 
    public static boolean hasNumber(String string) {
        return NUMBER_PATTERN.matcher(string).find();
    }
 
    public static boolean hasSymbol(String string) {
        return SYMBOL_PATTERN.matcher(string).find();
    }
 
    public static boolean hasAll(String string) {
        return hasCapitalLetter(string) && hasSmallLetter(string) && hasNumber(string) && hasSymbol(string);
    }
 
    public  boolean cheackpassword(String password)
    {
 
if (hasAll(password))
{ 
    System.out.println("password correct");
     return true;
}
else
{ 
    System.out.println("your password need to contine capital letter ,small letter ,number and symbol  ");
   return false;
    }
    }
}
 