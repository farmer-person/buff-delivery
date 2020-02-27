// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package buff.x;

public class Deliver {

    public static void main (String[] args)
                throws java.lang.Exception {
        Class.forName ("buff.Global");
        new Thread (new XDeliver ()).start ();
        while (true) {
            Thread.sleep (1000L);
        }
    }
}

class XDeliver implements Runnable {

    public XDeliver () {
        return;
    }

    @Override
    public void run () {
        while (true) {
            try {
                main ();
            }
            catch (java.lang.Exception e) {
                e.printStackTrace ();
            }
            finally {
                //
            }
        }
    }

    private void main ()
                throws java.lang.InterruptedException {
        System.out.print ("\n\n");
        System.out.print (java.time.LocalDateTime.now ());
        Thread.sleep (1000L * 60);
        // steam确认报价
        new buff.Confirmation ()
                .run ();
        System.out.flush ();
        Thread.sleep (1000L * 60);
        // dota2开始发货
        new buff.Delivering ()
                .run ();
        System.out.flush ();
        return;
    }
}
