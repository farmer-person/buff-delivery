// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package buff;
import com.google.gson.JsonParser;

public class Global {

    public static String stringOperationSystem = null;

    public static String stringUserHome = null;

    public static String stringCookie = null;

    public static String stringCsrfToken = null;

    static {
        try {
            stringOperationSystem = System.getProperty ("os.name");
            stringUserHome = System.getProperty ("user.home");
            java.util.Properties properties = new java.util.Properties ();
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
        try {
            String stringNickName
                = new JsonParser ().parse (http.getStringResponseBody ())
                                   .getAsJsonObject ()
                                   .getAsJsonObject ("data")
                                   .get ("nickname")
                                   .getAsString ();
            System.out.print ("\ncookie有效, 用户名:");
            System.out.print (stringNickName);
            return true;
        }
        catch (java.lang.Exception e) {
            e.printStackTrace ();
            System.out.print ("\ncookie无效");
            return false;
        }
        finally {
            //
        }
    }
}
