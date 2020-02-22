// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package farmer;

/**
时间操作 <br>
@author Farmer
@version 2019.11.19
*/
public class Time {

    /** 1毫秒, 单位: 毫秒 */
    public final static long longOneMilliSecond = 1L;

    /** 1秒, 单位: 毫秒 */
    public final static long longOneSecond = 1000L * 1;

    /** 1分钟, 单位: 毫秒 */
    public final static long longOneMinute = 1000L * 60 * 1;

    /** 1小时, 单位: 毫秒 */
    public final static long longOneHour = 1000L * 60 * 60 * 1;

    /** 1天, 单位: 毫秒 */
    public final static long longOneDay = 1000L * 60 * 60 * 24 * 1;

    /** 1周, 单位: 毫秒 */
    public final static long longOneWeek = 1000L * 60 * 60 * 24 * 7;

    /** 取现在的时间, 单位: 秒 */
    public static long nowTimeSecond () {
        return java.time.OffsetDateTime.now ()
                                       .toEpochSecond ();
    }

    /** 取现在的时间, 单位: 毫秒 */
    public static long nowTimeMilliSecond () {
        return java.time.OffsetDateTime.now ()
                                       .toInstant ()
                                       .toEpochMilli ();
    }

    /** 等待 System.currentTimeMillis() */
    public static void wait (long longStartTime,
                             long longTimeOut,
                             long longSleep)
                throws java.lang.InterruptedException {
        while (System.currentTimeMillis () - longStartTime < longTimeOut) {
            Thread.sleep (longSleep);
        }
        return;
    }
}
