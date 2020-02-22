// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package steam;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggingTest {

    // @org.junit.jupiter.api.Test
    public void login () {
        steam.Operation.login (steam.Global.user);
    }
}
