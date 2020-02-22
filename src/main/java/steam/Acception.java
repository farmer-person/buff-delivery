// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package steam;

/**
@see steam.Global
*/
public class Acception implements interfaces.steam.Acception {

    static Acception acception = null;

    public synchronized static Acception getInstance () {
        /*{{{*/
        if (null == acception) {
            acception = new Acception ();
        }
        //
        return acception;
        /*}}}*/
    }

    public void accept (java.util.List < String > listStringTradeOfferId)
                throws java.rmi.RemoteException {
        /*{{{*/
        new Thread (new AcceptionProcess (listStringTradeOfferId)).start ();
        return;
        /*}}}*/
    }
}

class AcceptionProcess implements Runnable {

    java.util.List <String> listStringTradeOfferId = null;

    public AcceptionProcess (java.util.List < String > listStringTradeOfferId) {
        /*{{{*/
        this.listStringTradeOfferId = listStringTradeOfferId;
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

    private void main () {
        /*{{{*/
        StringBuilder builderPrint
            = new StringBuilder ().append ("\naccount:")
                                  .append (steam.Global.user.getStringSteamUser ())
                                  .append (" | steam_id:")
                                  .append (steam.Global.user.getStringIdentitySecret ())
                                  .append (" | 已收到请求, 正在处理");
        System.out.print (builderPrint);
        // 收到的报价
        java.util.List <bean.steam.information.AcceptionInformation> listAcceptionInformation
            = steam.Information.acception (steam.Global.user);
        for (int intX = 0; intX < listAcceptionInformation.size (); intX ++) {
            bean.steam.information.AcceptionInformation acceptionInformation
                = listAcceptionInformation.get (intX);
            // 过滤非请求的报价
            if (! listStringTradeOfferId.contains (acceptionInformation.getStringTradeOfferId ())) {
                continue;
            }
            //
            // 接受报价
            steam.Operation.accept (steam.Global.user, acceptionInformation);
        }
        // 需要确认的报价
        java.util.List <bean.steam.information.ConfirmationInformation> listConfirmationInformation
            = steam.Information.confirmation (steam.Global.user);
        for (int intX = 0; intX < listConfirmationInformation.size (); intX ++) {
            bean.steam.information.ConfirmationInformation confirmationInformation
                = listConfirmationInformation.get (intX);
            // 过滤非请求的报价
            if (! listStringTradeOfferId.contains (confirmationInformation.getStringTradeOfferId ())) {
                continue;
            }
            //
            // 确认报价
            steam.Operation.confirm (steam.Global.user, confirmationInformation);
        }
        System.out.print ("\nok.");
        return;
        /*}}}*/
    }
}
