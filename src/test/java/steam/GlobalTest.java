// *********************************************************
// *  Author: Farmer                                       *
// *  Mail: iceyee.studio@qq.com                           *
// *  Git: https://github.com/iceyee                       *
// *********************************************************
//
package steam;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GlobalTest {

    @org.junit.jupiter.api.Test
    public void property () {
        System.out.print ("\n");
        System.out.print (steam.Global.stringOperationSystem);
        System.out.print ("\n");
        System.out.print (steam.Global.stringUserHome);
        System.out.print ("\n");
        System.out.print (steam.Global.user);
        System.out.print ("\n");
        System.out.print (steam.Global.stringMachineId);
        return;
    }
}
