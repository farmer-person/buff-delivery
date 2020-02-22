/*
 *******************************************
 *    Author: Farmer                       *
 *    Mail:   iceyee.studio@qq.com         *
 *    Git:    https://github.com/iceyee    *
 *******************************************
 */
/**/
package farmer.exception;

public class FarmerException extends java.lang.RuntimeException {

    public FarmerException () {
        super ("you got something wrong in the farmer's library.");
    }

    public FarmerException (String stringMessage) {
        super (stringMessage);
    }

    public FarmerException (String stringMessage,
                            Throwable e) {
        super (stringMessage, e);
    }

    public FarmerException (Throwable e) {
        super (e);
    }
}
