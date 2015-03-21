package controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;


public class UserControllerTest {

    @Test
    public void testGson(){
        Gson gson = new Gson();
        Collection<String> strings = new ArrayList<>();
        strings.add("user1");
        strings.add("user2");
        System.out.println(gson.toJson(strings));

    }

}
