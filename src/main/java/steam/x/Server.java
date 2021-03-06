// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package steam.x;

public class Server {

    public static void main (String[] args)
                throws java.lang.Exception {
        Class.forName ("steam.Global");
        System.setProperty ("java.rmi.server.hostname", "localhost");
        new Thread (steam.auto.Logging.getInstance ()).start ();
        java.rmi.registry.LocateRegistry.createRegistry (7778);
        java.rmi.server.UnicastRemoteObject.exportObject (steam.Acception.getInstance (),
                                                          7778);
        java.rmi.Naming.rebind ("rmi://localhost:7778/interfaces.steam.Acception",
                                steam.Acception.getInstance ());
        while (true) {
            Thread.sleep (1000L);
        }
    }
}
