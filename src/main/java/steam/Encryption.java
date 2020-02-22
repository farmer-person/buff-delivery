// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package steam;

public class Encryption {

    /**
    @param stringKey 密钥
    @param longTime 时间
    @param stringTag 标签
    @return 非法输入返回null, 异常返回null
    */
    public static String encrypt (String stringKey,
                                  long longTime,
                                  String stringTag) {
        /*{{{*/
        if (null == stringKey
              || 0 == stringKey.length ()
              || null == stringTag
              || 0 == stringTag.length ()) {
            return null;
        }
        //
        try {
            // 明文是 Time + Tag
            byte[] bytesSecret = java.util.Base64.getDecoder ()
                                                 .decode (stringKey);
            int intLength = Math.min (32, stringTag.length ()) + 8;
            byte[] bytesMain = new byte[intLength];
            for (int intX = 7; 0 <= intX; intX --) {
                bytesMain[intX] = (byte) longTime;
                // 右移1个字节, 共8次, long有8个字节
                longTime>>>= 8;
            }
            // arraycopy (Object src, int srcPos, Object dest, int destPos, int length);
            // 将Tag追加到Time后面
            System.arraycopy (stringTag.getBytes (), 0, bytesMain, 8, intLength -8);
            javax.crypto.spec.SecretKeySpec secretKeySpec
                = new javax.crypto.spec.SecretKeySpec (bytesSecret, "HmacSHA1");
            javax.crypto.Mac macInstance = javax.crypto.Mac.getInstance ("HmacSHA1");
            macInstance.init (secretKeySpec);
            return java.util.Base64.getEncoder ()
                                   .encodeToString (macInstance.doFinal (bytesMain));
        }
        catch (java.lang.Exception e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/steam.error.log", e);
            return null;
        }
        finally {
            //
        }
        /*}}}*/
    }

    static byte[] s_rgchSteamguardCodeChars
        = new byte[] {
        50, 51, 52, 53, 54, 55, 56, 57, 66, 67, 68, 70, 71, 72, 74, 75, 77, 78, 80, 81, 82, 84, 86, 87, 88, 89
    };

    /**
    @param stringKey 密钥
    @param longTime 时间
    @return 非法输入返回null, 异常返回null
    */
    public static String generateSteamGuardCode (String stringKey,
                                                 long longTime) {
        /*{{{*/
        if (null == stringKey
              || 0 == stringKey.length ()) {
            return null;
        }
        //
        try {
            byte[] bytesSecret = java.util.Base64.getDecoder ()
                                                 .decode (stringKey);
            longTime /= 30L;
            byte[] bytesMain = new byte[8];
            for (int intX = 7; 0 <= intX; intX --) {
                bytesMain[intX] = (byte) longTime;
                // 右移1个字节, 共8次, long有8个字节
                longTime>>>= 8;
            }
            javax.crypto.spec.SecretKeySpec secretKeySpec
                = new javax.crypto.spec.SecretKeySpec (bytesSecret, "HmacSHA1");
            javax.crypto.Mac macInstance = javax.crypto.Mac.getInstance ("HmacSHA1");
            macInstance.init (secretKeySpec);
            byte[] bytesResult = macInstance.doFinal (bytesMain);
            int n1 = bytesResult[19] & 0xF;
            int n2
                = (bytesResult[n1] & 0x7F) << 24 | (bytesResult[n1 + 1] & 0xFF) << 16 | (bytesResult[n1 + 2] & 0xFF) << 8 | (bytesResult[n1 + 3] & 0xFF);
            bytesResult = new byte[5];
            for (int intX = 0; intX < 5; ++ intX) {
                bytesResult[intX] = s_rgchSteamguardCodeChars[n2 % s_rgchSteamguardCodeChars.length];
                n2 /= s_rgchSteamguardCodeChars.length;
            }
            return new String (bytesResult);
        }
        catch (java.lang.Exception e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/steam.error.log", e);
            return null;
        }
        finally {
            //
        }
        /*}}}*/
    }
}
