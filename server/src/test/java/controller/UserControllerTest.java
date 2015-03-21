package controller;

import com.google.gson.Gson;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;


public class UserControllerTest {

    @Test
    public void testGson(){
        Gson gson = new Gson();
        Collection<User> users = new ArrayList<>();
        User user = new User();
        user.setFirstName("firstName");
        user.setSecondName("surname");
        user.setUserId("userId");
        user.setPictureUrl("picUrl");
        users.add(user);
        System.out.println(gson.toJson(users));

    }

}
