// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package dota2;
import com.google.gson.*;

/**
<pre>
<a target="_blank" href="https://buff.163.com/api/market/buy_order/wait_supply?appid=570&page_num=1&page_size=60">purchasing</a>
<a target="_blank" href="https://buff.163.com/market/buy_order/history?game=dota2&page_num=">buy log</a>
<a target="_blank" href="https://buff.163.com/api/market/backpack?game=dota2&page_num=1&page_size=60">buff backpack</a>
<a target="_blank" href="https://buff.163.com/account/api/user/info">user info</a>
<a target="_blank" href="https://buff.163.com/api/message/notification">message</a>
<a target="_blank" href="https://buff.163.com/api/market/steam_trade">steam trade</a>
<a target="_blank" href="https://buff.163.com/api/market/sell_order/on_sale?appid=570&mode=2%2C5&page_num=1&page_size=60">selling</a>
<a target="_blank" href="https://buff.163.com/api/market/steam_inventory?appid=570&force=0&game=dota2&page_num=1&page_size=60&state=tradable">steam inventory</a>
<a target="_blank" href="https://buff.163.com/api/asset/get_brief_asset">money</a>
<a target="_blank" href="https://buff.163.com/api/message/notification">notification</a>
<!--
<a target="_blank" href=""></a>
-->
</pre>
*/
public class User {

    /**
    等待发货
    @return 异常返回空列表
    */
    public static java.util.List <String> waitingForDelivering (String stringCookie,
                                                                String stringProxy) {
        /*{{{*/
        if (null == stringCookie) {
            return null;
        }
        //
        java.util.List <String> listStringWaitingForDelivering = new java.util.LinkedList ();
        try {
            String stringUrl = "https://buff.163.com/market/sell_order/to_deliver?game=dota2";
            farmer.Http http
                = new farmer.Http ()
                            .setBooleanVerbose (false)
                            .setBooleanRedirected (true)
                            .setProxy (stringProxy)
                            .setStringUrl (stringUrl)
                            .setStringCookie (stringCookie)
                            .request ();
            if (200 != http.getIntResponseCode ()) {
                return listStringWaitingForDelivering;
            }
            //
            String stringResponse = http.getStringResponseBody ();
            String stringRegularExpression = "sellingToDeliver\\(\\[(.*?)\\], (\\d+)\\)\\.init\\(\\);";
            java.util.regex.Matcher matcher
                = java.util.regex.Pattern.compile (stringRegularExpression)
                                         .matcher (stringResponse);
            matcher.find ();
            stringResponse = matcher.group (1);
            int intNumber = Integer.valueOf (matcher.group (2))
                                   .intValue ();
            if (0 == intNumber) {
                return listStringWaitingForDelivering;
            }
            //
            listStringWaitingForDelivering
                = java.util.Arrays.stream (stringResponse.split (","))
                                  .map (x -> x.replace ("\"", ""))
                                  .map (x -> x.trim ())
                                  .collect (java.util.stream.Collectors.toList ());
        }
        catch (java.lang.Exception e) {
            e.printStackTrace ();
            System.err.flush ();
        }
        finally {
            System.out.print ("\n[waitingForDelivering]\n");
            System.out.print (listStringWaitingForDelivering);
            System.out.flush ();
            return listStringWaitingForDelivering;
        }
        /*}}}*/
    }

    /**
    steam交易id
    @return 异常返回空列表
    */
    public static java.util.List <String> steamTradeOfferId (String stringCookie,
                                                             String stringProxy) {
        /*{{{*/
        if (null == stringCookie) {
            return null;
        }
        //
        java.util.List <String> listStringSteamTradeOfferId = new java.util.LinkedList ();
        farmer.Http http = null;
        try {
            String stringUrl = "https://buff.163.com/api/market/steam_trade";
            http
                = new farmer.Http ()
                            .setBooleanVerbose (false)
                            .setBooleanRedirected (true)
                            .setProxy (stringProxy)
                            .setStringUrl (stringUrl)
                            .setStringCookie (stringCookie)
                            .request ();
            if (200 != http.getIntResponseCode ()) {
                return listStringSteamTradeOfferId;
            }
            //
            JsonArray jsonArraySteamTrade
                = new JsonParser ().parse (http.getStringResponseBody ())
                                   .getAsJsonObject ()
                                   .getAsJsonArray ("data");
            for (int intX = 0; intX < jsonArraySteamTrade.size (); intX ++) {
                JsonElement jsonElementSteamTrade
                    = jsonArraySteamTrade.get (intX)
                                         .getAsJsonObject ()
                                         .get ("tradeofferid");
                if (jsonElementSteamTrade.isJsonNull ()) {
                    continue;
                }
                //
                listStringSteamTradeOfferId.add (jsonElementSteamTrade.getAsString ());
            }
        }
        catch (java.lang.Exception e) {
            e.printStackTrace ();
            System.err.flush ();
        }
        finally {
            System.out.print ("\n[steam trade offer id]\n" + listStringSteamTradeOfferId.toString ());
            System.out.flush ();
            return listStringSteamTradeOfferId;
        }
        /*}}}*/
    }
}
