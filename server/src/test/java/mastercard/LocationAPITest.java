package mastercard;


import mastercard.LocationAPI;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

public class LocationAPITest {

    private LocationAPI testee = new LocationAPI();

    @Test
    public void test(){
        testee.getATMsByCity();
    }
}
