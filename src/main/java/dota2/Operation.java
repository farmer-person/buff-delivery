// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package dota2;
import com.google.gson.*;

public class Operation {

    /** 发货 */
    public static boolean deliver (String stringCookie,
                                   String stringCsrfToken,
                                   String[] stringsBillOrder,
                                   String stringProxy,
                                   StringBuilder builderPrint) {
        /*{{{*/
        if (null == stringCookie
              || null == stringCsrfToken
              || null == stringsBillOrder) {
            return false;
        }
        //
        try {
            String stringUrl = "https://buff.163.com/api/market/bill_order/deliver";
            StringBuilder builderId = new StringBuilder ();
            for (String stringX : stringsBillOrder) {
                builderId.append ('"')
                         .append (stringX)
                         .append ('"')
                         .append (",");
            }
            String stringId = builderId.toString ();
            if (0 < stringId.length ()) {
                stringId = stringId.substring (0, stringId.length () -1);
            }
            //
            String stringData = String.format ("{\"game\":\"dota2\",\"bill_orders\":[%s]}",
                                               stringId);
            String stringHeader
                = "Content-Type: application/json                                            \r\n" +
                  "Referer: https://buff.163.com/market/sell_order/to_deliver?game=dota2     \r\n" +
                  "X-CSRFToken: %s                                                           \r\n" +
                  "Cookie: %s                                                                \r\n";
            stringHeader = String.format (stringHeader, stringCsrfToken, stringCookie);
            farmer.Http http
                = new farmer.Http ()
                            .setBooleanVerbose (false)
                            .setProxy (stringProxy)
                            .setStringUrl (stringUrl)
                            .setStringData (stringData)
                            .setStringHeader (stringHeader)
                            .request ();
            if (200 == http.getIntResponseCode ()) {
                String stringResponse = http.getStringResponseBody ();
                if (! stringResponse.trim ().startsWith ("{")) {
                    stringResponse = new StringBuilder ().append ("{")
                                                         .append (stringResponse)
                                                         .toString ();
                }
                //
                String stringResult
                    = new JsonParser ().parse (stringResponse)
                                       .getAsJsonObject ()
                                       .get ("code\"")
                                       .getAsString ();
                if (null == builderPrint) {
                    System.out.print ("\n[deliver]\n" + stringResult);
                    System.out.flush ();
                }
                else {
                    builderPrint.append (stringResult);
                }
                //
                return "OK".equals (stringResult);
            }
            else {
                return false;
            }
            //
        }
        catch (java.lang.Exception e) {
            e.printStackTrace ();
            System.err.flush ();
            return false;
        }
        finally {
            //
        }
        /*}}}*/
    }
}
