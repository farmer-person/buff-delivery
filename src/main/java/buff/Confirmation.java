// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package buff;

public class Confirmation implements Runnable {

    public Confirmation () {
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
        }
        finally {
            //
        }
        /*}}}*/
    }

    private void main ()
                throws java.lang.Exception {
        /*{{{*/
        java.util.List <String> listStringId
            = dota2.User.steamTradeOfferId (buff.Global.stringCookie, null);
        if (0 < listStringId.size ()) {
            interfaces.steam.Acception acception
                = (interfaces.steam.Acception) java.rmi.Naming.lookup ("rmi://localhost:7778/interfaces.steam.Acception");
            acception.accept (listStringId);
        }
        //
        return;
        /*}}}*/
    }
}
