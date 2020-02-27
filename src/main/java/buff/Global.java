// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package buff;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

public class Global {

    public static String stringOperationSystem = null;

    public static String stringUserHome = null;

    public static String stringSeparator = null;

    public static String stringMachineId = null;

    public static String stringCookie = null;

    public static String stringCsrfToken = null;

    static {
        try {
            stringOperationSystem = System.getProperty ("os.name");
            stringUserHome = System.getProperty ("user.home");
            stringSeparator = System.getProperty ("file.separator");
            java.util.Properties properties = new java.util.Properties ();
            if (! new java.io.File (stringUserHome + stringSeparator + ".buff_delivery").exists ()) {
                init ();
            }
            else {
                properties.load (new java.io.FileInputStream (stringUserHome + stringSeparator + ".buff_delivery"));
                stringMachineId = properties.getProperty ("id")
                                            .trim ();
            }
            //
            if (buff.Client.checkBlock ()) {
                System.out.print ("\n黑名单");
                System.in.read ();
                System.exit (1);
            }
            else if (buff.Client.checkExpired ()) {
                System.out.print ("\n软件已到期");
                System.in.read ();
                System.exit (1);
            }
            //
            new Thread (new Checking ()).start ();
            properties.load (new java.io.FileInputStream ("setting.txt"));
            stringCookie = properties.getProperty ("cookie");
        }
        catch (java.lang.Exception e) {
            e.printStackTrace ();
            try {
                System.out.print ("\n读取配置失败");
                System.in.read ();
            }
            catch (java.lang.Exception e1) {
                e1.printStackTrace ();
            }
            finally {
                System.exit (1);
            }
        }
        finally {
            //
        }
        java.util.regex.Matcher m
            = java.util.regex.Pattern.compile ("csrf_token=(.+?);")
                                     .matcher (stringCookie);
        if (! m.find ()) {
            try {
                System.out.print ("\ncookie无效");
                System.in.read ();
            }
            catch (java.lang.Exception e) {
                e.printStackTrace ();
            }
            finally {
                System.exit (1);
            }
        }
        //
        stringCsrfToken = m.group (1);
        if (! checkCookie ()) {
            try {
                System.in.read ();
            }
            catch (java.lang.Exception e) {
                e.printStackTrace ();
            }
            finally {
                System.exit (1);
            }
        }
        //
    }

    private static boolean checkCookie () {
        farmer.Http http
            = new farmer.Http ()
                        .setStringUrl ("https://buff.163.com/account/api/user/info")
                        .setStringCookie (stringCookie)
                        .request ();
        if (200 != http.getIntResponseCode ()) {
            System.out.print ("\n无法访问buff, 请检查网络");
            return false;
        }
        //
        JsonObject jsonObject = new JsonParser ().parse (http.getStringResponseBody ())
                                                 .getAsJsonObject ();
        if (! jsonObject.has ("code")
                && ! jsonObject.has ("code\"")) {
            System.out.print ("\ncookie无效");
            return false;
        }
        //
        try {
            String stringNickName = jsonObject.getAsJsonObject ("data")
                                              .get ("nickname")
                                              .getAsString ();
            System.out.print ("\ncookie有效, 用户名:");
            System.out.print (stringNickName);
        }
        catch (java.lang.Exception e) {
            e.printStackTrace ();
            System.out.print ("\n");
            System.out.print (http.getStringResponseBody ());
        }
        finally {
            return true;
        }
    }

    private static void init () {
        java.util.Random random = new java.util.Random (System.currentTimeMillis ());
        int intCounter = 0;
        StringBuilder builderMachineId = new StringBuilder ();
        while (intCounter < 10) {
            int intA = random.nextInt (0x80);
            if ((0x30 <= intA && intA <= 0x39)
                  || (0x41 <= intA && intA <= 0x5a)
                  || (0x61 <= intA && intA <= 0x7a)) {
                builderMachineId.append ((char) intA);
                intCounter += 1;
            }
            //
        }
        stringMachineId = builderMachineId.toString ();
        long longTime = System.currentTimeMillis () + 1000L * 60 * 60 * 24 * 2;
        String stringContent
            = "id=%s                                                       \r\n" +
              "expired=%s                                                  \r\n";
        stringContent = String.format (stringContent, stringMachineId, longTime);
        farmer.Io.write (stringUserHome + stringSeparator + ".buff_delivery", stringContent);
    }
}

class Checking implements Runnable {

    public Checking () {
        return;
    }

    @Override
    public void run () {
        while (true) {
            try {
                main ();
            }
            catch (java.lang.Exception e) {
                e.printStackTrace ();
            }
            finally {
                //
            }
        }
    }

    private void main ()
                throws java.lang.Exception {
        Thread.sleep (1000L * 60 * 60);
        if (buff.Client.checkBlock ()) {
            System.out.print ("\n黑名单");
            System.in.read ();
            System.exit (1);
        }
        else if (buff.Client.checkExpired ()) {
            System.out.print ("\n软件已到期");
            System.in.read ();
            System.exit (1);
        }
        //
        return;
    }
}
