package controller;

import mastercard.MasterPass;
import model.Credentials;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Juriy on 3/22/2015.
 */
@RestController()
public class MasterPassController {

    private MasterPass masterPass = new MasterPass();

    @RequestMapping(value = "/masterpass",method = RequestMethod.GET)
    public @ResponseBody
    Credentials getMasterPassCredentials() throws Exception {
        return masterPass.getCredentials();

    }
}
