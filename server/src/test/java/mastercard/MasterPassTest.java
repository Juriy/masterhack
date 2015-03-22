package mastercard;

import org.junit.Test;

/**
 * Created by Juriy on 3/22/2015.
 */
public class MasterPassTest {
    MasterPass pass =new MasterPass();

    @Test
    public void test() throws Exception {
        pass.getCredentials(5);
    }
}
