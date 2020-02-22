// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package steam;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptionTest {

    // @org.junit.jupiter.api.Test
    public void acceptTest ()
                throws java.lang.Exception {
        new Thread (steam.auto.Logging.getInstance ()).start ();
        while (null == steam.Global.user.getStringCookie ()) {
            Thread.sleep (1000L);
        }
        Thread.sleep (1000L);
        java.util.List <String> a = new java.util.LinkedList ();
        a.add ("3919031812");
        steam.Acception.getInstance ()
                       .accept (a);
        Thread.sleep (10000L);
    }
}
