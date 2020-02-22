/*
    ***************************************
    * Author: Farmer                      *
    * Mail:   iceyee.studio@qq.com        *
    * Git:    https://github.com/iceyee   *
    ***************************************
*/
package farmer;

/**
写入日志<br>
<br>
代码示例: <pre>
farmer.Log .debug ("file", "hello world");
farmer.Log .info ("file", "hello world");
farmer.Log .warn ("file", "hello world");
farmer.Log .error ("file", "hello world");

// 结果:
2018-12-02 20:02:09  DEBUG    #  hello world
2018-12-02 20:02:09  INFO     #  hello world
2018-12-02 20:02:09  WARN     #  hello world
2018-12-02 20:02:09  ERROR    #  hello world
</pre>
@author Farmer
@version 2019.11.19
*/
public class Log {

    final static java.time.format.DateTimeFormatter dateTimeFormatter
        = java.time.format.DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss");

    /**
    @param stringFileName 目标文件名
    @param stringContent 日志内容
    @return 正常返回true, 异常返回false
    */
    public static boolean debug (String stringFileName,
                                 String stringContent) {
        /*{{{*/
        stringContent
            = new StringBuilder ().append ("\n")
                                  .append (java.time.LocalDateTime.now ().format (dateTimeFormatter))
                                  .append ("  DEBUG    #  ")
                                  .append (stringContent)
                                  .toString ();
        return farmer.Io.append (stringFileName, stringContent);
        /*}}}*/
    }

    /**
    @param stringFileName 目标文件名
    @param stringContent 日志内容
    @return 正常返回true, 异常返回false
    */
    public static boolean info (String stringFileName,
                                String stringContent) {
        /*{{{*/
        stringContent
            = new StringBuilder ().append ("\n")
                                  .append (java.time.LocalDateTime.now ().format (dateTimeFormatter))
                                  .append ("  INFO     #  ")
                                  .append (stringContent)
                                  .toString ();
        return farmer.Io.append (stringFileName, stringContent);
        /*}}}*/
    }

    /**
    @param stringFileName 目标文件名
    @param stringContent 日志内容
    @return 正常返回true, 异常返回false
    */
    public static boolean warn (String stringFileName,
                                String stringContent) {
        /*{{{*/
        stringContent
            = new StringBuilder ().append ("\n")
                                  .append (java.time.LocalDateTime.now ().format (dateTimeFormatter))
                                  .append ("  WARNING  #  ")
                                  .append (stringContent)
                                  .toString ();
        return farmer.Io.append (stringFileName, stringContent);
        /*}}}*/
    }

    /**
    @param stringFileName 目标文件名
    @param stringContent 日志内容
    @return 正常返回true, 异常返回false
    */
    public static boolean error (String stringFileName,
                                 String stringContent) {
        /*{{{*/
        stringContent
            = new StringBuilder ().append ("\n")
                                  .append (java.time.LocalDateTime.now ().format (dateTimeFormatter))
                                  .append ("  ERROR    #  ")
                                  .append (stringContent)
                                  .toString ();
        return farmer.Io.append (stringFileName, stringContent);
        /*}}}*/
    }

    /**
    @param stringFileName 目标文件名
    @param e1 {@link java.lang.Exception}异常对象
    @return 正常返回true, 异常返回false
    */
    public static boolean error (String stringFileName,
                                 Throwable e1) {
        /*{{{*/
        StringBuilder builderErrorMessage = new StringBuilder ().append ("\n\n")
                                                                .append (e1.toString ());
        StackTraceElement[] stackTraceElements = e1.getStackTrace ();
        final int intMaxRow = 20;
        for (int intX = 0; intX < stackTraceElements.length && intX < intMaxRow; intX ++) {
            builderErrorMessage.append ("\n        at ")
                               .append (stackTraceElements[intX].toString ());
        }
        if (intMaxRow < stackTraceElements.length) {
            builderErrorMessage.append ("\n        ... ")
                               .append (stackTraceElements.length - intMaxRow)
                               .append (" more");
        }
        //
        e1 = e1.getCause ();
        while (null != e1) {
            stackTraceElements = e1.getStackTrace ();
            if (1 < stackTraceElements.length) {
                builderErrorMessage.append ("\nCaused by: ")
                                   .append (e1.toString ())
                                   .append ("\n        at ")
                                   .append (stackTraceElements[0].toString ())
                                   .append ("\n        ... ")
                                   .append (stackTraceElements.length -1)
                                   .append (" more");
            }
            else {
                builderErrorMessage.append ("\nCaused by: ")
                                   .append (e1.toString ())
                                   .append ("\n        at ")
                                   .append (stackTraceElements[0].toString ());
            }
            //
            e1 = e1.getCause ();
        }
        String stringContent
            = new StringBuilder ().append ("\n")
                                  .append (java.time.LocalDateTime.now ().format (dateTimeFormatter))
                                  .append ("  ERROR    #  ")
                                  .append (builderErrorMessage.append ("\n").toString ())
                                  .toString ();
        return farmer.Io.append (stringFileName, stringContent);
        /*}}}*/
    }
}
