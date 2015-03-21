package mastercard;

import org.junit.Test;

/**
 * Created by Juriy on 3/21/2015.
 */
public class MoneySendTest {

    MoneySend moneySend = new MoneySend();

    @Test
    public void testMoneySend(){
        moneySend.transferMoneyTo("5184680430000014");
    }
}
