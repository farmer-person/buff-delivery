/*
 *******************************************
 *    Author: Farmer                       *
 *    Mail:   iceyee.studio@qq.com         *
 *    Git:    https://github.com/iceyee    *
 *******************************************
 */
/**/
package interfaces.steam;

public interface Acception extends java.rmi.Remote {

    public void accept (java.util.List < String > listStringTradeOfferId)
                throws java.rmi.RemoteException;
}
