// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package steam.auto;

/** 
@see steam.Global
*/
public class Logging implements Runnable {

    static Logging logging = null;

    private Logging () {
        /*{{{*/
        return;
        /*}}}*/
    }

    public synchronized static Logging getInstance () {
        /*{{{*/
        if (null == logging) {
            logging = new Logging ();
        }
        //
        return logging;
        /*}}}*/
    }

    public void run () {
        /*{{{*/
        while (true) {
            try {
                Thread.sleep (1000L);
                main ();
                // 12小时登陆一次
                Thread.sleep (1000L * 60 * 60 * 12);
            }
            catch (java.lang.Exception e) {
                e.printStackTrace ();
            }
            finally {
                //
            }
        }
        /*}}}*/
    }

    private void main () {
        steam.Operation.login (steam.Global.user);
        return;
    }
}
