// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//                                                          
package farmer;

/**
http访问, 支持流式操作 <br>
<a target="_blank" 
   href="http://api.k780.com/?app=ip.local&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json">查看本机ip</a><br>
<br>
<pre>
代码示例:
farmer.Http http = new farmer .Http ();
                              .setBooleanVerbose (true)
                              .setBooleanForwarded (false)
                              .setBooleanRedirected (false)
                              .setStringUrl ("https://www.baidu.com")
                              .setStringHeader ("")
                              .setStringData ("")
                              .setStringCookie ("");
                              .request ();
</pre>
@author Farmer
@version 2019.11.19
@see #randomIp
@see #request
*/
public class Http {

    java.net.HttpURLConnection connection = null;

    boolean booleanConnected = false;

    java.net.Proxy proxy = null;

    String stringUrl = null;

    String stringCookie = null;

    String stringData = null;

    String stringHeader = null;

    int intResponseCode = 0;

    int intTimeOutMilliSecond = 10000;

    String stringResponseHeader = null;

    String stringResponseBody = null;

    byte[] bytesResponseBody = null;

    boolean booleanForwarded = true;

    boolean booleanRedirected = false;

    boolean booleanVerbose = false;

    java.util.Map <String, String> mapHeader = new java.util.LinkedHashMap ();

    static java.util.Map <String, String> mapDefaultHeader = new java.util.LinkedHashMap ();

    static {
        mapDefaultHeader.put ("accept", "*/*");
        mapDefaultHeader.put ("accept-encoding", "gzip");
        mapDefaultHeader.put ("x-requested-with", "XMLHttpRequest");
    }

    // getter and setter
    /*{{{*/
    /** 设置冗余模式, [默认] false */
    public Http setBooleanVerbose (boolean booleanVerbose) {
        this.booleanVerbose = booleanVerbose;
        return this;
    }

    /** 设置是否伪装ip, [默认] true */
    public Http setBooleanForwarded (boolean booleanForwarded) {
        this.booleanForwarded = booleanForwarded;
        return this;
    }

    /** 设置是否重定向, [默认] false*/
    public Http setBooleanRedirected (boolean booleanRedirected) {
        this.booleanRedirected = booleanRedirected;
        return this;
    }

    /** 设置请求链接, [默认] null */
    public Http setStringUrl (String stringUrl) {
        this.stringUrl = stringUrl;
        return this;
    }

    /** 设置cookie, [默认] null */
    public Http setStringCookie (String stringCookie) {
        this.stringCookie = stringCookie;
        return this;
    }

    /** 设置post提交的数据, [默认] null */
    public Http setStringData (String stringData) {
        this.stringData = stringData;
        return this;
    }

    /** 设置请求头, [默认] null */
    public Http setStringHeader (String stringHeader) {
        this.stringHeader = stringHeader;
        return this;
    }

    /** 设置请求超时, [默认] 10000毫秒 */
    public Http setIntTimeOutMilliSecond (int intTimeOutMilliSecond) {
        this.intTimeOutMilliSecond = intTimeOutMilliSecond;
        return this;
    }

    /** 设置代理, [默认] null */
    public Http setProxy (java.net.Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    /** 设置代理, [默认] null */
    public Http setProxy (String stringProxy) {
        if (null == stringProxy) {
            this.proxy = null;
            return this;
        }
        //
        Object[] objectsProxy
            = java.util.Arrays.stream (stringProxy.split (":"))
                              .map (x -> x.trim ())
                              .limit (2)
                              .toArray ();
        String stringHost = objectsProxy[0].toString ();
        String stringPort = objectsProxy[1].toString ();
        int intPort = Integer.valueOf (stringPort)
                             .intValue ();
        this.proxy
            = new java.net.Proxy (java.net.Proxy.Type.HTTP,
                                  new java.net.InetSocketAddress (stringHost, intPort));
        return this;
    }

    /** 设置代理, [默认] null */
    public Http setProxy (String stringHost,
                          int intPort) {
        this.proxy
            = new java.net.Proxy (java.net.Proxy.Type.HTTP,
                                  new java.net.InetSocketAddress (stringHost, intPort));
        return this;
    }

    /** 设置代理, [默认] null */
    public Http setProxy (String stringHost,
                          String stringPort) {
        int intPort = Integer.valueOf (stringPort)
                             .intValue ();
        this.proxy
            = new java.net.Proxy (java.net.Proxy.Type.HTTP,
                                  new java.net.InetSocketAddress (stringHost, intPort));
        return this;
    }

    /** 取代理, [默认] null */
    public java.net.Proxy getProxy () {
        return this.proxy;
    }

    /** 取冗余模式, [默认] false */
    public boolean getBooleanVerbose () {
        return this.booleanVerbose;
    }

    /** 取是否伪装ip, [默认] true */
    public boolean getBooleanForwarded () {
        return this.booleanForwarded;
    }

    /** 取是否重定向, [默认] false*/
    public boolean getBooleanRedirected () {
        return this.booleanRedirected;
    }

    /** 取请求超时, [默认] 10000 */
    public int getIntTimeOutMilliSecond () {
        return this.intTimeOutMilliSecond;
    }

    /** 取请求链接, [默认] null */
    public String getStringUrl () {
        return this.stringUrl;
    }

    /** 取cookie, [默认] null */
    public String getStringCookie () {
        return this.stringCookie;
    }

    /** 取post提交的数据, [默认] null */
    public String getStringData () {
        return this.stringData;
    }

    /** 取请求头, [默认] null */
    public String getStringHeader () {
        return this.stringHeader;
    }

    /** 取请求响应正文, [默认] null */
    public String getStringResponseBody () {
        return this.stringResponseBody;
    }

    /** 取请求响应头, 返回字符串, [默认] null */
    public String getStringResponseHeader () {
        return this.stringResponseHeader;
    }

    /** 取请求响应头, 返回字节集, [默认] null */
    public byte[] getBytesResponseBody () {
        return this.bytesResponseBody;
    }

    /** 取请求响应代码, 200表示正常 [默认] 0 */
    public int getIntResponseCode () {
        return this.intResponseCode;
    }

    /*}}}*/
    /**
    添加请求头 <br>
    @param stringHeader 请求头, 格式: <br>&nbsp;&nbsp;xxx: xxx<br>&nbsp;&nbsp;xxx: xxx
    @return 如果stringHeader为null, 或者抛出异常, 则返回false
    */
    private boolean addHeader (String stringHeader) {
        /*{{{*/
        if (null == stringHeader) {
            return false;
        }
        java.util.regex.Matcher matcher
            = java.util.regex.Pattern.compile ("(\\S+)\\s*:\\s*([^\\r\\n]+)")
                                     .matcher (stringHeader);
        while (matcher.find ()) {
            this.mapHeader.put (matcher.group (1).trim ().toLowerCase (),
                                matcher.group (2).trim ());
        }
        return true;
        /*}}}*/
    }

    /** 获取随机IPv4, xxx.xxx.xxx.xxx */
    public static String randomIp () {
        /*{{{*/
        return new java.util.Random ()
                            .ints (4, 1, 255)
                            .mapToObj (x -> String.valueOf (x))
                            .collect (java.util.stream.Collectors.joining ("."));
        /*}}}*/
    }

    /** 用GET或POST的方式发起请求, 可调用api返回response_code, response_body, response_header等等 */
    public Http request () {
        /*{{{*/
        try {
            request1 ();
        }
        catch (java.net.SocketTimeoutException e) {
            System.out.print ("\n" + e.toString ());
        }
        catch (java.io.IOException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
        }
        catch (farmer.exception.FarmerException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
        }
        finally {
            if (this.booleanConnected) {
                this.booleanConnected = false;
                this.connection.disconnect ();
                this.connection = null;
            }
            //
            return this;
        }
        /*}}}*/
    }

    private void request1 ()
                throws farmer.exception.FarmerException,
                       java.io.IOException {
        /*{{{*/
        // 检查链接是否为null
        if (null == this.stringUrl) {
            throw new farmer.exception.FarmerException (new IllegalArgumentException ());
        }
        //
        // verbose [url]
        if (this.booleanVerbose) {
            System.out.print ("\n\n[Url] ");
            System.out.print (this.stringUrl);
            System.out.print ("\n\n[Request Header]");
        }
        //
        // 添加请求头
        addHeader (this.stringHeader);
        // 添加cookie
        if (null != this.stringCookie) {
            this.mapHeader.putIfAbsent ("cookie", this.stringCookie);
        }
        //
        // 设置浏览器代理头
        if (! this.mapHeader.containsKey ("user-agent")) {
            if (this.booleanForwarded) {
                String stringUserAgent
                    = new StringBuilder ().append ("farmer/1.0 (X11; Ubuntu; Linux x86_64; rv:61.0) ")
                                          .append ("iceyee/19951120 ")
                                          .append ("ip/")
                                          .append (randomIp ())
                                          .toString ();
                this.mapHeader.put ("user-agent", stringUserAgent);
            }
            else {
                this.mapHeader.put ("user-agent",
                                    "farmer/1.0 (X11; Ubuntu; Linux x86_64; rv:61.0) iceyee/19951120 ");
            }
            //
        }
        //
        // 添加默认头
        java.util.Iterator <String> iteratorStringKey = this.mapDefaultHeader.keySet ()
                                                                             .iterator ();
        while (iteratorStringKey.hasNext ()) {
            String stringKey = iteratorStringKey.next ();
            this.mapHeader.putIfAbsent (stringKey, this.mapDefaultHeader.get (stringKey));
        }
        // 伪装ip
        if (null == this.proxy
                && this.booleanForwarded) {
            // x-forwarded-for: xxx.xxx.xxx.xxx, xxx.xxx.xxx.xxx
            String stringXForwardedFor
                = new StringBuilder ().append (randomIp ())
                                      .append (", ")
                                      .append (randomIp ())
                                      .toString ();
            this.mapHeader.put ("x-forwarded-for", stringXForwardedFor);
        }
        //
        // 生成连接对象
        if (null == this.proxy) {
            connection = (java.net.HttpURLConnection) new java.net.URL (this.stringUrl)
                                                                  .openConnection ();
        }
        else {
            connection
                = (java.net.HttpURLConnection) new java.net.URL (this.stringUrl)
                                                           .openConnection (this.proxy);
        }
        //
        // 设置超时, 是否重定向
        connection.setReadTimeout (this.intTimeOutMilliSecond);
        connection.setConnectTimeout (this.intTimeOutMilliSecond);
        connection.setInstanceFollowRedirects (this.booleanRedirected);
        // 发送请求头
        iteratorStringKey = this.mapHeader.keySet ()
                                          .iterator ();
        while (iteratorStringKey.hasNext ()) {
            String stringKey = iteratorStringKey.next ();
            connection.setRequestProperty (stringKey, this.mapHeader.get (stringKey));
            // verbose [header]
            if (this.booleanVerbose) {
                String stringPrint
                    = new StringBuilder ().append ("\n")
                                          .append (stringKey)
                                          .append (": ")
                                          .append (this.mapHeader.get (stringKey))
                                          .toString ();
                System.out.print (stringPrint);
            }
            //
        }
        // 确定POST方式
        if (null != this.stringData
                && 0 < this.stringData.length ()) {
            connection.setDoInput (true);
            connection.setDoOutput (true);
            connection.setRequestProperty ("content-length",
                                           String.valueOf (this.stringData.length ()));
            // verbose [header]
            if (this.booleanVerbose) {
                StringBuilder builderPrint = new StringBuilder ();
                builderPrint.append ("\ncontent-length: ");
                builderPrint.append (this.stringData.length ());
                System.out.print (builderPrint.toString ());
            }
            //
        }
        // 否则GET方式
        else {
            connection.setDoInput (true);
        }
        //
        // 建立连接
        connection.connect ();
        booleanConnected = true;
        // 发送请求数据
        if (null != this.stringData
                && 0 < this.stringData.length ()) {
            java.io.OutputStream outputStream = connection.getOutputStream ();
            outputStream.write (this.stringData.getBytes ());
            outputStream.close ();
            // verbose [post data]
            if (this.booleanVerbose) {
                StringBuilder builderPrint = new StringBuilder ();
                builderPrint.append ("\n\n[Post Data]\n");
                builderPrint.append (this.stringData);
                System.out.print (builderPrint.toString ());
            }
            //
        }
        //
        // 处理响应头
        java.util.Set <java.util.Map.Entry <String, java.util.List <String>>> entrySetHeaders
            = connection.getHeaderFields ()
                        .entrySet ();
        StringBuilder builderResponseHeader = new StringBuilder ();
        for (java.util.Map.Entry < String, java.util.List < String >> entryX : entrySetHeaders) {
            for (int intX = 0; intX < entryX.getValue ().size (); intX ++) {
                builderResponseHeader.append ("\n")
                                     .append (entryX.getKey ())
                                     .append (": ")
                                     .append (entryX.getValue ().get (intX));
            }
        }
        this.stringResponseHeader = builderResponseHeader.toString ();
        // verbose [response header]
        if (this.booleanVerbose) {
            System.out.print ("\n\n[Response Header]");
            System.out.print (this.stringResponseHeader);
        }
        //
        // 获取输入流
        java.io.InputStream inputStream = connection.getInputStream ();
        // 尝试获取gzip输入流
        java.util.zip.GZIPInputStream gzipInputStream = null;
        try {
            gzipInputStream = new java.util.zip.GZIPInputStream (inputStream);
        }
        catch (java.util.zip.ZipException e) {
            gzipInputStream = null;
        }
        // 读取数据写入到byteArrayOutputStream
        java.io.ByteArrayOutputStream byteArrayOutputStream = new java.io.ByteArrayOutputStream ();
        while (true) {
            // 1K
            byte[] bytesBuffer = new byte[0x3ff];
            int intReaded
                = null == gzipInputStream ?
                  inputStream.read (bytesBuffer) :
                  gzipInputStream.read (bytesBuffer);
            if (-1 == intReaded) {
                break;
            }
            //
            byteArrayOutputStream.write (bytesBuffer, 0, intReaded);
        }
        // 处理数据
        this.intResponseCode = connection.getResponseCode ();
        this.bytesResponseBody = byteArrayOutputStream.toByteArray ();
        this.stringResponseBody = new String (this.bytesResponseBody);
        // 关闭所有流
        byteArrayOutputStream.close ();
        inputStream.close ();
        if (null != gzipInputStream) {
            gzipInputStream.close ();
        }
        //
        // verbose [response body]
        if (this.booleanVerbose) {
            System.out.print ("\n\n[Response Body]\n");
            System.out.print (this.stringResponseBody);
            System.out.print ("\n------------------------------\n");
        }
        //
        // 如果是json格式的数据, 则加上{, 这两个是传输丢失的两个字节.
        if (this.stringResponseHeader.contains ("application/json")) {
            if (! this.stringResponseBody.trim ().startsWith ("{")) {
                this.stringResponseBody
                    = new StringBuilder ().append ("{")
                                          .append (this.stringResponseBody)
                                          .toString ();
            }
            //
        }
        //
        return;
        /*}}}*/
    }
}
