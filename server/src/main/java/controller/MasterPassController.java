package controller;

import com.google.gdata.data.apt.DataAnnotationProcessorFactory;
import db.Database;
import db.DatabaseFactory;
import mastercard.MasterPass;
import model.Credentials;
import model.Item;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Juriy on 3/22/2015.
 */
@RestController()
public class MasterPassController {

    private Database database = DatabaseFactory.getDatabase();

    private MasterPass masterPass = new MasterPass();


    @RequestMapping(value = "/masterpass",method = RequestMethod.POST)
    public @ResponseBody
    Credentials getMasterPassCredentials(@RequestBody Collection<Item> items) throws Exception {
        return masterPass.getCredentials(items);
    }

    @RequestMapping(value = "/masterpass/{amount}",method = RequestMethod.POST)
    public @ResponseBody
    Credentials getMasterPassCredentials(@PathVariable String amount) throws Exception {
        return masterPass.getCredentials(Double.valueOf(amount).intValue());
    }

}
