// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package bean.steam.information;

/**
@see #stringTradeOfferId
@see #stringConfirmationId
@see #stringConfirmationKey
*/
public class ConfirmationInformation {

    String stringTradeOfferId = null;

    String stringConfirmationId = null;

    String stringConfirmationKey = null;

    public static void main (String[] args)
                throws java.lang.Exception {
        System.out.print (new ConfirmationInformation ());
        return;
    }

    public ConfirmationInformation setStringTradeOfferId (String stringTradeOfferId) {
        this.stringTradeOfferId = stringTradeOfferId;
        return this;
    }

    public ConfirmationInformation setStringConfirmationId (String stringConfirmationId) {
        this.stringConfirmationId = stringConfirmationId;
        return this;
    }

    public ConfirmationInformation setStringConfirmationKey (String stringConfirmationKey) {
        this.stringConfirmationKey = stringConfirmationKey;
        return this;
    }

    public String getStringTradeOfferId () {
        return this.stringTradeOfferId;
    }

    public String getStringConfirmationId () {
        return this.stringConfirmationId;
    }

    public String getStringConfirmationKey () {
        return this.stringConfirmationKey;
    }

    public String toString () {
        return new StringBuilder ().append ("{ ")
                                   .append ("stringTradeOfferId=")
                                   .append (stringTradeOfferId)
                                   .append (", ")
                                   .append ("stringConfirmationId=")
                                   .append (stringConfirmationId)
                                   .append (", ")
                                   .append ("stringConfirmationKey=")
                                   .append (stringConfirmationKey)
                                   .append (" }")
                                   .toString ();
    }
}
