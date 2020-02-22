// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package bean.steam.information;

/**
@see #stringPartnerId
@see #stringPartnerName
@see #stringTradeOfferId
*/
public class AcceptionInformation {

    String stringPartnerId = null;

    String stringPartnerName = null;

    String stringTradeOfferId = null;

    public static void main (String[] args)
                throws java.lang.Exception {
        System.out.print (new AcceptionInformation ());
        return;
    }

    public AcceptionInformation setStringPartnerId (String stringPartnerId) {
        this.stringPartnerId = stringPartnerId;
        return this;
    }

    public AcceptionInformation setStringPartnerName (String stringPartnerName) {
        this.stringPartnerName = stringPartnerName;
        return this;
    }

    public AcceptionInformation setStringTradeOfferId (String stringTradeOfferId) {
        this.stringTradeOfferId = stringTradeOfferId;
        return this;
    }

    public String getStringPartnerId () {
        return this.stringPartnerId;
    }

    public String getStringPartnerName () {
        return this.stringPartnerName;
    }

    public String getStringTradeOfferId () {
        return this.stringTradeOfferId;
    }

    public String toString () {
        return new StringBuilder ().append ("{ ")
                                   .append ("stringPartnerId=")
                                   .append (stringPartnerId)
                                   .append (", ")
                                   .append ("stringPartnerName=")
                                   .append (stringPartnerName)
                                   .append (", ")
                                   .append ("stringTradeOfferId=")
                                   .append (stringTradeOfferId)
                                   .append (" }")
                                   .toString ();
    }
}
