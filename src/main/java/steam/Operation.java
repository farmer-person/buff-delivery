// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package steam;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;

public class Operation {

    private static PhantomJSDriver driver = null;

    /** 验证cookie有效 */
    public static boolean activateCookie (bean.steam.information.UserInformation user) {
        /*{{{*/
        if (null == user) {
            return false;
        }
        //
        farmer.Http http
            = new farmer.Http ()
                        .setBooleanVerbose (false)
                        .setStringUrl ("https://steamcommunity.com/")
                        .setStringCookie (user.getStringCookie ())
                        .request ();
        System.out.print ("\n\n");
        System.out.print (java.time.LocalDateTime.now ());
        System.out.print ("\n[账号] " + user.getStringSteamUser ());
        if (200 != http.getIntResponseCode ()
              || ! http.getStringResponseBody ().contains (user.getStringIdentitySecret ())) {
            System.out.print ("\n[检查在线状态] false");
            return false;
        }
        else {
            System.out.print ("\n[检查在线状态] true");
            return true;
        }
        //
        /*}}}*/
    }

    /** 接受报价 */
    public static boolean accept (bean.steam.information.UserInformation user,
                                  bean.steam.information.AcceptionInformation acception) {
        /*{{{*/
        if (null == user
              || null == acception) {
            return false;
        }
        //
        String stringUrl
            = String.format ("https://steamcommunity.com/tradeoffer/%s/accept",
                             acception.getStringTradeOfferId ());
        String stringHeader
            = String.format ("Referer: https://steamcommunity.com/tradeoffer/%s/",
                             acception.getStringTradeOfferId ());
        java.util.regex.Matcher matcher
            = java.util.regex.Pattern.compile ("sessionid=(.*?);")
                                     .matcher (user.getStringCookie ());
        matcher.find ();
        String stringData
            = new StringBuilder ().append ("&sessionid=")
                                  .append (matcher.group (1))
                                  .append ("&serverid=1&captcha=")
                                  .append ("&tradeofferid=")
                                  .append (acception.getStringTradeOfferId ())
                                  .append ("&partner=")
                                  .append (acception.getStringPartnerId ())
                                  .toString ();
        farmer.Http http
            = new farmer.Http ()
                        .setBooleanVerbose (false)
                        .setStringUrl (stringUrl)
                        .setStringData (stringData)
                        .setStringCookie (user.getStringCookie ())
                        .setStringHeader (stringHeader)
                        .request ();
        System.out.print ("\n[接受报价]\n" + http.getStringResponseBody ());
        return true;
        /*}}}*/
    }

    /** 手机确认报价 */
    public static boolean confirm (bean.steam.information.UserInformation user,
                                   bean.steam.information.ConfirmationInformation confirmation) {
        /*{{{*/
        if (null == user
              || null == confirmation) {
            return false;
        }
        //
        long longTime = farmer.Time.nowTimeSecond ();
        String stringKey = new steam.Encryption ()
                                    .encrypt (user.getStringSerialNumber (), longTime, "allow");
        String stringUrl
            = new StringBuilder ().append ("https://steamcommunity.com/mobileconf/ajaxop")
                                  .append ("?op=allow&tag=allow")
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
                                  .append ("&cid=")
                                  .append (confirmation.getStringConfirmationId ())
                                  .append ("&ck=")
                                  .append (confirmation.getStringConfirmationKey ())
                                  .toString ();
        farmer.Http http
            = new farmer.Http ()
                        .setBooleanVerbose (false)
                        .setStringUrl (stringUrl)
                        .setStringCookie (user.getStringCookie ())
                        .request ();
        System.out.print ("\n[确认报价]\n" + http.getStringResponseBody ());
        if (! http.getStringResponseBody ().contains ("success")
              || ! http.getStringResponseBody ().contains ("true")) {
            return false;
        }
        else {
            return true;
        }
        //
        /*}}}*/
    }

    /** 登陆 */
    public synchronized static boolean login (bean.steam.information.UserInformation user) {
        /*{{{*/
        try {
            return login1 (user);
        }
        catch (java.lang.Exception e) {
            e.printStackTrace ();
            return false;
        }
        finally {
            if (null != driver) {
                System.out.print ("\n\033[33m关闭浏览器\033[0m\n");
                driver.quit ();
                driver = null;
            }
            //
        }
        /*}}}*/
    }

    private static boolean login1 (bean.steam.information.UserInformation user)
                throws java.rmi.RemoteException,
                       java.lang.InterruptedException,
                       java.io.FileNotFoundException,
                       java.io.IOException {
        /*{{{*/
        String stringPrint
            = new StringBuilder ().append ("\n账号: ")
                                  .append (user.getStringSteamUser ())
                                  .append (" | 请求登陆")
                                  .toString ();
        System.out.print (stringPrint);
        // 启动浏览器 
        System.out.print ("\n\033[33m启动浏览器\033[0m\n");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities ();
        desiredCapabilities.setCapability ("phantomjs.page.settings.loadImages", false);
        desiredCapabilities.setCapability ("phantomjs.page.settings.javascriptEnabled", true);
        desiredCapabilities.setCapability ("phantomjs.page.settings.resourceTimeout",
                                           "15000");
        if (steam.Global.stringOperationSystem.contains ("Windows")) {
            desiredCapabilities.setCapability (PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                                               ".\\phantomjs\\bin\\phantomjs.exe");
        }
        else {
            desiredCapabilities.setCapability (PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                                               "./phantomjs/bin/phantomjs");
        }
        //
        driver = new PhantomJSDriver (desiredCapabilities);
        driver.manage ()
              .timeouts ()
              .pageLoadTimeout (30, java.util.concurrent.TimeUnit.SECONDS);
        System.out.print ("\n打开登陆页面");
        try {
            driver.get ("https://steamcommunity.com/login/home/");
        }
        catch (java.lang.Exception e) {
            System.out.println (driver.getPageSource ());
            e.printStackTrace ();
            System.out.print ("\n登陆失败");
            return false;
        }
        finally {
            //
        }
        System.out.print ("\n等待页面加载完毕");
        Thread.sleep (1000L);
        while (26 < System.currentTimeMillis () % 30L) {
            Thread.sleep (1000L);
        }
        String stringToken
            = steam.Encryption.generateSteamGuardCode (user.getStringSharedSecret (),
                                                       System.currentTimeMillis () / 1000L);
        System.out.print ("\n令牌: " + stringToken);
        System.out.print ("\n输入账号和令牌, 登陆");
        String stringJS
            = "document.querySelector('#steamAccountName').value='%s';                                  " +
              "document.querySelector('#steamPassword').value='%s';                                     " +
              "document.querySelector('#twofactorcode_entry').value='%s';                               " +
              "document.querySelector('#login_twofactorauth_buttonset_entercode .auth_button').click(); ";
        stringJS
            = String.format (stringJS,
                             user.getStringSteamUser (),
                             user.getStringSteamPassword (),
                             stringToken);
        driver.executeScript (stringJS);
        Thread.sleep (5000L);
        System.out.print ("\n读取cookie");
        StringBuilder builderCookie = new StringBuilder ();
        for (org.openqa.selenium.Cookie cookie : driver.manage ().getCookies ()) {
            builderCookie.append (cookie.getName ())
                         .append ("=")
                         .append (cookie.getValue ())
                         .append ("; ");
        }
        String stringCookie = builderCookie.toString ();
        System.out.print ("\n" + stringCookie);
        if (! stringCookie.contains ("steamLoginSecure")) {
            System.out.print ("\ncookie无效, 登陆失败");
            return false;
        }
        //
        System.out.print ("\n验证cookie是否有效");
        // 先保存旧的cookie
        String stringOldCookie = user.getStringCookie ();
        user.setStringCookie (stringCookie);
        if (activateCookie (user)) {
            return true;
        }
        else {
            // 恢复原来的cookie
            user.setStringCookie (stringOldCookie);
            return false;
        }
        //
        /*}}}*/
    }
}