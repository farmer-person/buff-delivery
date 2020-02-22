// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package bean.steam.information;

/**                                                                 
@see #stringCookie
@see #stringSteamUser
@see #stringSharedSecret
@see #stringSerialNumber
@see #stringSteamPassword
@see #stringIdentitySecret                                                               
*/
public class UserInformation {

    String stringCookie = null;

    String stringSteamUser = null;

    String stringSharedSecret = null;

    String stringSerialNumber = null;

    String stringSteamPassword = null;

    String stringIdentitySecret = null;

    public static void main (String[] args)
                throws java.lang.Exception {
        System.out.print (new UserInformation ());
        return;
    }

    public UserInformation setStringCookie (String stringCookie) {
        this.stringCookie = stringCookie;
        return this;
    }

    public UserInformation setStringSteamUser (String stringSteamUser) {
        this.stringSteamUser = stringSteamUser;
        return this;
    }

    public UserInformation setStringSharedSecret (String stringSharedSecret) {
        this.stringSharedSecret = stringSharedSecret;
        return this;
    }

    public UserInformation setStringSerialNumber (String stringSerialNumber) {
        this.stringSerialNumber = stringSerialNumber;
        return this;
    }

    public UserInformation setStringSteamPassword (String stringSteamPassword) {
        this.stringSteamPassword = stringSteamPassword;
        return this;
    }

    public UserInformation setStringIdentitySecret (String stringIdentitySecret) {
        this.stringIdentitySecret = stringIdentitySecret;
        return this;
    }

    public String getStringCookie () {
        return this.stringCookie;
    }

    public String getStringSteamUser () {
        return this.stringSteamUser;
    }

    public String getStringSharedSecret () {
        return this.stringSharedSecret;
    }

    public String getStringSerialNumber () {
        return this.stringSerialNumber;
    }

    public String getStringSteamPassword () {
        return this.stringSteamPassword;
    }

    public String getStringIdentitySecret () {
        return this.stringIdentitySecret;
    }

    public String toString () {
        return new StringBuilder ().append ("{ ")
                                   .append ("stringCookie=")
                                   .append (stringCookie)
                                   .append (", ")
                                   .append ("stringSteamUser=")
                                   .append (stringSteamUser)
                                   .append (", ")
                                   .append ("stringSharedSecret=")
                                   .append (stringSharedSecret)
                                   .append (", ")
                                   .append ("stringSerialNumber=")
                                   .append (stringSerialNumber)
                                   .append (", ")
                                   .append ("stringSteamPassword=")
                                   .append (stringSteamPassword)
                                   .append (", ")
                                   .append ("stringIdentitySecret=")
                                   .append (stringIdentitySecret)
                                   .append (" }")
                                   .toString ();
    }
}
