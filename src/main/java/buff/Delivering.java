// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package buff;

public class Delivering implements Runnable {

    public Delivering () {
        /*{{{*/
        return;
        /*}}}*/
    }

    @Override
    public void run () {
        /*{{{*/
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
        /*}}}*/
    }

    private void main () {
        /*{{{*/
        java.util.List <String> listStringId
            = dota2.User.waitingForDelivering (buff.Global.stringCookie, null);
        if (0 == listStringId.size ()) {
            return;
        }
        //
        // 发货
        dota2.Operation.deliver (buff.Global.stringCookie,
                                 buff.Global.stringCsrfToken,
                                 listStringId.toArray (new String[0]),
                                 null,
                                 null);
        /*}}}*/
    }
}
