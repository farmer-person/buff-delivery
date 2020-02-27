// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package steam;

public class Client {

    public static boolean checkBlock () {
        String stringUrl = "http://soft.iceyee.cn:10002/checkBlock?id=";
        farmer.Http http
            = new farmer.Http ()
                        .setStringUrl (stringUrl + steam.Global.stringMachineId)
                        .request ();
        if (200 == http.getIntResponseCode ()
                && http.getStringResponseBody ().contains ("false")) {
            return false;
        }
        else {
            return true;
        }
        //
    }

    public static boolean checkExpired ()
                throws java.lang.Exception {
        java.util.Properties properties = new java.util.Properties ();
        String stringFile = steam.Global.stringUserHome + steam.Global.stringSeparator + ".buff_delivery";
        properties.load (new java.io.FileInputStream (stringFile));
        String stringExpired = properties.getProperty ("expired")
                                         .trim ();
        long longExpired = Long.valueOf (stringExpired)
                               .longValue ();
        return longExpired < System.currentTimeMillis ();
    }
}
