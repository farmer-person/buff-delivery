// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package steam;

public class Global {

    public static boolean booleanDebug = true;

    public static String stringDeviceId = "B6AB0C04-6225-410D-BC05-46CCC9260D9C";

    public static String stringMachine = "ios";

    public static bean.steam.information.UserInformation user
        = new bean.steam.information.UserInformation ();

    public static String stringOperationSystem = null;

    public static String stringUserHome = null;

    static {
        try {
            stringOperationSystem = System.getProperty ("os.name");
            stringUserHome = System.getProperty ("user.home");
            java.util.Properties properties = new java.util.Properties ();
            properties.load (new java.io.FileInputStream ("setting.txt"));
            user.setStringSharedSecret (properties.getProperty ("shared_secret"))
                .setStringIdentitySecret (properties.getProperty ("identity_secret"))
                .setStringSerialNumber (properties.getProperty ("serial_number"))
                .setStringSteamUser (properties.getProperty ("steam_user"))
                .setStringSteamPassword (properties.getProperty ("steam_password"));
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
    }
}
