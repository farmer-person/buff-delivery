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

    public static String stringMachineId = null;

    public static bean.steam.information.UserInformation user
        = new bean.steam.information.UserInformation ();

    public static String stringOperationSystem = null;

    public static String stringUserHome = null;

    public static String stringSeparator = null;

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
            if (steam.Client.checkBlock ()) {
                System.out.print ("\n黑名单");
                System.out.flush ();
                System.in.read ();
                System.exit (1);
            }
            else if (steam.Client.checkExpired ()) {
                System.out.print ("\n软件已到期");
                System.out.flush ();
                System.in.read ();
                System.exit (1);
            }
            //
            new Thread (new Checking ()).start ();
            properties.load (new java.io.FileInputStream ("setting.txt"));
            user.setStringSharedSecret (properties.getProperty ("shared_secret").trim ())
                .setStringIdentitySecret (properties.getProperty ("identity_secret").trim ())
                .setStringSerialNumber (properties.getProperty ("serial_number").trim ())
                .setStringSteamUser (properties.getProperty ("steam_user").trim ())
                .setStringSteamPassword (properties.getProperty ("steam_password").trim ());
            new Thread (new CheckCookie ()).start ();
        }
        catch (java.lang.Exception e) {
            e.printStackTrace ();
            try {
                System.out.print ("\n读取配置失败");
                System.out.flush ();
                System.in.read ();
            }
            catch (java.lang.Exception e1) {
                e1.printStackTrace ();
                System.err.flush ();
            }
            finally {
                System.exit (1);
            }
        }
        finally {
            //
        }
    }

    private static void init () {
        java.util.Random random = new java.util.Random (System.currentTimeMillis ());
        int intCounter = 0;
        StringBuilder builderMachine = new StringBuilder ();
        while (intCounter < 10) {
            int intA = random.nextInt (0x80);
            if ((0x30 <= intA && intA <= 0x39)
                  || (0x41 <= intA && intA <= 0x5a)
                  || (0x61 <= intA && intA <= 0x7a)) {
                builderMachine.append ((char) intA);
                intCounter += 1;
            }
            //
        }
        stringMachineId = builderMachine.toString ();
        long longTime = System.currentTimeMillis () + 1000L * 60 * 60 * 24 * 2;
        String stringContent
            = "id=%s                                                       \r\n" +
              "expired=%s                                                  \r\n";
        stringContent = String.format (stringContent, stringMachineId, longTime);
        farmer.Io.write (stringUserHome + stringSeparator + ".buff_delivery", stringContent);
    }
}

class CheckCookie implements Runnable {

    public CheckCookie () {
        return;
    }

    @Override
    public void run () {
        while (true) {
            try {
                Thread.sleep (1000L * 60 * 20);
                main ();
            }
            catch (java.lang.Exception e) {
                e.printStackTrace ();
                System.err.flush ();
            }
            finally {
                //
            }
        }
    }

    private void main () {
        steam.Operation.activateCookie (steam.Global.user);
        System.out.flush ();
        return;
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
                System.err.flush ();
            }
            finally {
                //
            }
        }
    }

    private void main ()
                throws java.lang.Exception {
        Thread.sleep (1000L * 60 * 60);
        if (steam.Client.checkBlock ()) {
            System.out.print ("\n黑名单");
            System.out.flush ();
            System.in.read ();
            System.exit (1);
        }
        else if (steam.Client.checkExpired ()) {
            System.out.print ("\n软件已到期");
            System.out.flush ();
            System.in.read ();
            System.exit (1);
        }
        //
        return;
    }
}
