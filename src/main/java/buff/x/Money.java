// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package buff.x;
import com.google.gson.JsonParser;

public class Money {

    public static void main (String[] args)
                throws java.lang.Exception {
        String stringOperationSystem = System.getProperty ("os.name");
        String stringUserHome = System.getProperty ("user.home");
        String stringSeparator = System.getProperty ("file.separator");
        java.util.Properties properties = new java.util.Properties ();
        properties.load (new java.io.FileInputStream (stringUserHome + stringSeparator + ".buff_delivery"));
        String stringMachineId = properties.getProperty ("id")
                                           .trim ();
        String stringExpired = properties.getProperty ("expired")
                                         .trim ();
        long longExpired = Long.valueOf (stringExpired)
                               .longValue ();
        String stringTime
            = java.time.LocalDateTime.ofEpochSecond (longExpired / 1000L,
                                                     0,
                                                     java.time.ZoneOffset.of ("+8"))
                                     .toString ();
        System.out.print ("\n到期时间: ");
        System.out.print (stringTime);
        System.out.print ("\n");
        String stringLine
            = new java.io.BufferedReader (new java.io.InputStreamReader (System.in))
                         .readLine ()
                         .trim ();
        if (10 != stringLine.length ()) {
            System.exit (0);
        }
        //
        String stringUrl = "http://soft.iceyee.cn:10002/activate?id=%s&secret=%s&soft=buff_delivery";
        stringUrl = String.format (stringUrl, stringMachineId, stringLine);
        farmer.Http http = new farmer.Http ()
                                     .setStringUrl (stringUrl)
                                     .request ();
        if (200 != http.getIntResponseCode ()) {
            System.out.print ("\nhttp错误");
            System.in.read ();
            System.exit (1);
        }
        //
        if (http.getStringResponseBody ().contains ("true")) {
            longExpired = Math.max (longExpired, System.currentTimeMillis ());
            longExpired += 1000L * 60 * 60 * 24 * 30;
            stringTime
                = java.time.LocalDateTime.ofEpochSecond (longExpired / 1000L,
                                                         0,
                                                         java.time.ZoneOffset.of ("+8"))
                                         .toString ();
            System.out.print ("\n充值成功, 到期时间: ");
            System.out.print (stringTime);
            properties.setProperty ("expired", String.valueOf (longExpired));
            properties.store (new java.io.FileOutputStream (stringUserHome + stringSeparator + ".buff_delivery"),
                              "buff_delivery");
        }
        else {
            String stringMessage
                = new JsonParser ().parse (http.getStringResponseBody ())
                                   .getAsJsonObject ()
                                   .get ("message")
                                   .getAsString ();
            System.out.print ("\n充值失败, ");
            System.out.print (stringMessage);
        }
        //
        System.in.read ();
        return;
    }
}
