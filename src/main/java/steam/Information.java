// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package steam;

public class Information {

    /**
    @param user {cookie, steam_id}
    */
    public static java.util.List <bean.steam.information.AcceptionInformation> acception (bean.steam.information.UserInformation user) {
        /*{{{*/
        if (null == user) {
            return null;
        }
        //
        java.util.List <bean.steam.information.AcceptionInformation> listAcceptionInformation
            = new java.util.LinkedList ();
        String stringUrl
            = String.format ("https://steamcommunity.com/profiles/%s/tradeoffers",
                             user.getStringIdentitySecret ());
        String stringCookie
            = user.getStringCookie ()
                  .contains ("webTradeEligibility") ?
              user.getStringCookie () :
              getWebTradeEligibility () + user.getStringCookie ();
        farmer.Http http
            = new farmer.Http ()
                        .setBooleanVerbose (false)
                        .setBooleanRedirected (true)
                        .setStringUrl (stringUrl)
                        .setStringCookie (stringCookie)
                        .request ();
        if (200 != http.getIntResponseCode ()) {
            System.out.print ("\nhttp 异常, 无法检查报价");
            return listAcceptionInformation;
        }
        //
        java.util.regex.Matcher matcher
            = java.util.regex.Pattern.compile ("<div class=\"tradeoffer\" id=\"tradeofferid_(\\d+)\">\\s*<a href=\"#\" onclick=\"ReportTradeScam\\( '(\\d+)', &quot;(.+?)&quot; \\);\"")
                                     .matcher (http.getStringResponseBody ());
        while (matcher.find ()) {
            bean.steam.information.AcceptionInformation acceptionInformation
                = new bean.steam.information.AcceptionInformation ()
                                            .setStringTradeOfferId (matcher.group (1))
                                            .setStringPartnerId (matcher.group (2))
                                            .setStringPartnerName (matcher.group (3));
            listAcceptionInformation.add (acceptionInformation);
        }
        if (http.getStringResponseBody ().contains ("please enter the code below")) {
            System.out.print ("\n需要验证码");
        }
        //
        System.out.print ("\n[收到的报价]\n" + listAcceptionInformation.toString ());
        return listAcceptionInformation;
        /*}}}*/
    }

    /**
    @param user {cookie, steam_id, encryption_key}
    */
    public static java.util.List <bean.steam.information.ConfirmationInformation> confirmation (bean.steam.information.UserInformation user) {
        /*{{{*/
        if (null == user) {
            return null;
        }
        //
        java.util.List <bean.steam.information.ConfirmationInformation> listConfirmationInformation
            = new java.util.LinkedList ();
        long longTime = farmer.Time.nowTimeSecond ();
        String stringKey = steam.Encryption.encrypt (user.getStringSerialNumber (),
                                                     longTime,
                                                     "conf");
        String stringUrl
            = new StringBuilder ().append ("https://steamcommunity.com/mobileconf/conf")
                                  .append ("?tag=conf")
                                  .append ("&p=")
                                  .append (steam.Global.stringDeviceId)
                                  .append ("&m=")
                                  .append (steam.Global.stringMachine)
                                  .append ("&a=")
                                  .append (user.getStringIdentitySecret ())
                                  .append ("&t=")
                                  .append (longTime)
                                  .append ("&k=")
                                  .append (java.net.URLEncoder.encode (stringKey))
                                  .toString ();
        farmer.Http http
            = new farmer.Http ()
                        .setBooleanVerbose (false)
                        .setBooleanRedirected (true)
                        .setStringUrl (stringUrl)
                        .setStringCookie (user.getStringCookie ())
                        .request ();
        if (200 != http.getIntResponseCode ()) {
            System.out.print ("\nhttp 异常, 请求重新登陆");
            steam.Operation.login (user);
            return listConfirmationInformation;
        }
        //
        java.util.regex.Matcher matcher
            = java.util.regex.Pattern.compile ("<div class=\"mobileconf_list_entry\" id=\"conf\\d+\" data\\-confid=\"(\\d+)\" data\\-key=\"(\\d+)\" data\\-type=\"2\" data\\-creator=\"(\\d+)\"")
                                     .matcher (http.getStringResponseBody ());
        while (matcher.find ()) {
            bean.steam.information.ConfirmationInformation confirmationInformation
                = new bean.steam.information.ConfirmationInformation ()
                                            .setStringConfirmationId (matcher.group (1))
                                            .setStringConfirmationKey (matcher.group (2))
                                            .setStringTradeOfferId (matcher.group (3));
            listConfirmationInformation.add (confirmationInformation);
        }
        System.out.print ("\n[待确认的报价]\n" + listConfirmationInformation.toString ());
        return listConfirmationInformation;
        /*}}}*/
    }

    public static String getWebTradeEligibility () {
        /*{{{*/
        long longTime = farmer.Time.nowTimeSecond () -10;
        String stringWebTradeEligibility
            = "webTradeEligibility=%7B%22allowed%22%3A1%2C%22allowed_at_time%22%3A0%2C%22steamguard_required_days%22%3A15%2C%22new_device_cooldown_days%22%3A7%2C%22time_checked%22%3A{{{TIME}}}%7D;";
        return stringWebTradeEligibility.replace ("{{{TIME}}}", String.valueOf (longTime));
        /*}}}*/
    }
}
