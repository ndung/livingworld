package id.co.icg.lw.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncryptUtil {

    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static String MD5(String string) {
        StringBuilder hexString = new StringBuilder();
        String md5 = "";
        try {
            MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
            mdAlgorithm.update(string.getBytes());
            byte[] digest = mdAlgorithm.digest();
            for (int i = 0; i < digest.length; i++) {
                string = Integer.toHexString(0xFF & digest[i]);
                if (string.length() < 2) {
                    string = "0" + string;
                }
                hexString.append(string);
            }
            md5 = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            md5 = string;
        }
        return md5;
    }

    public static String generatePassword(int minLength, int maxLength, int minLCaseCount, int minUCaseCount, int minNumCount)
    {
        char[] randomString;

        String LCaseChars = "abcdefgijkmnopqrstwxyz";
        String UCaseChars = "ABCDEFGHJKLMNPQRSTWXYZ";
        String NumericChars = "1234567890";

        Map<String,Integer> charGroupsUsed = new HashMap();
        charGroupsUsed.put("lcase", minLCaseCount);
        charGroupsUsed.put("ucase", minUCaseCount);
        charGroupsUsed.put("num", minNumCount);

        byte[] randomBytes = new byte[4];

        new Random().nextBytes(randomBytes);

        int seed = (randomBytes[0] & 0x7f) << 24 |
                    randomBytes[1] << 16 |
                    randomBytes[2] << 8 |
                    randomBytes[3];

        Random random = new Random(seed);

        int randomIndex = -1;
        if (minLength < maxLength)
        {
            randomIndex = random.nextInt((maxLength-minLength)+1)+minLength;
            randomString = new char[randomIndex];
        }
        else
        {
            randomString = new char[minLength];
        }

        int requiredCharactersLeft = minLCaseCount + minUCaseCount + minNumCount;

        for (int i = 0; i < randomString.length; i++)
        {
            String selectableChars = "";
            if (requiredCharactersLeft < randomString.length - i)
            {
                selectableChars = LCaseChars + UCaseChars + NumericChars;
            }else
            {
                for(Map.Entry<String, Integer> charGroup : charGroupsUsed.entrySet())
                {
                    if ((int)charGroup.getValue() > 0)
                    {
                        if("lcase".equals(charGroup.getKey()) ){
                            selectableChars += LCaseChars;
                        }
                        else if("ucase".equals(charGroup.getKey())){
                            selectableChars += UCaseChars;
                        }
                        else if("num".equals(charGroup.getKey())){
                            selectableChars += NumericChars;
                        }
                    }
                }
            }

            randomIndex = random.nextInt((selectableChars.length())-1);
            char nextChar = selectableChars.charAt(randomIndex);

            randomString[i] = nextChar;

            if (LCaseChars.indexOf(nextChar) > -1)
            {
                charGroupsUsed.put("lcase",charGroupsUsed.get("lcase") - 1);
                if (charGroupsUsed.get("lcase") >= 0)
                {
                    requiredCharactersLeft--;
                }
            }
            else if (UCaseChars.indexOf(nextChar) > -1)
            {
                charGroupsUsed.put("ucase",charGroupsUsed.get("ucase") - 1);
                if (charGroupsUsed.get("ucase") >= 0)
                {
                    requiredCharactersLeft--;
                }
            }
            else if (NumericChars.indexOf(nextChar) > -1)
            {
                charGroupsUsed.put("num", charGroupsUsed.get("num") - 1);
                if (charGroupsUsed.get("num") >= 0)
                {
                    requiredCharactersLeft--;
                }
            }
        }
        return new String(randomString);
    }
    
    public static boolean validatePassword(String password, boolean isDigit, boolean isUcase, boolean isLcase, int minLength, int maxLength){
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = "";

        if(isDigit) PASSWORD_PATTERN += "(?=.*\\d)";
        if(isUcase) PASSWORD_PATTERN += "(?=.*[A-Z])";
        if(isLcase) PASSWORD_PATTERN += "(?=.*[a-z])";
        PASSWORD_PATTERN += ".{" + minLength + "," + maxLength + "}";
        PASSWORD_PATTERN = "(" + PASSWORD_PATTERN + ")";
        
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
