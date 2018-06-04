package id.co.icg.lw.services.master;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import id.co.icg.lw.domain.master.Master;
import id.co.icg.lw.domain.master.MemberType;
import id.co.icg.lw.enums.*;
import id.co.icg.lw.services.livingWorld.LivingWorldApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MasterServiceBean implements MasterService {

    @Autowired
    private LivingWorldApiServiceImpl lwComponent;

    @Override
    public List<MemberType> getMemberTypes() {
        try {
            lwComponent.getMemberType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").enableComplexMapKeySerialization().create();

    @Override
    public List<Master> getReligions() {
        List<Master> religions = new ArrayList<>();
        try {
            Object object = lwComponent.getReligion();
            religions = gson.fromJson((String) object, new TypeToken<List<Master>>() {}.getType());
            System.out.println("list:" + religions);
        }catch(Exception ex){

        }
        return religions;
    }

    @Override
    public List<Master> getMartialStatus() {
        List<Master> martialStatus = new ArrayList<>();
        try {
            Object object = lwComponent.getMartialStatus();
            martialStatus = gson.fromJson((String) object, new TypeToken<List<Master>>() {}.getType());
            System.out.println("list:" + martialStatus);
        }catch(Exception ex){

        }
        return martialStatus;

    }

    @Override
    public List<Master> getGenders() {
        List<Master> gender = new ArrayList<>();
        try {
            Object object = lwComponent.getMartialStatus();
            gender = gson.fromJson((String) object, new TypeToken<List<Master>>() {}.getType());
            System.out.println("list:" + gender);
        }catch(Exception ex){

        }
        return gender;
    }

    @Override
    public List<Master> getNationalities() {
        List<Master> nationality = new ArrayList<>();
        try {
            Object object = lwComponent.getMartialStatus();
            nationality = gson.fromJson((String) object, new TypeToken<List<Master>>() {}.getType());
            System.out.println("list:" + nationality);
        }catch(Exception ex){

        }
        return nationality;
    }

    @Override
    public List<Master> getCities() {
        List<Master> city = new ArrayList<>();
        try {
            Object object = lwComponent.getMartialStatus();
            city = gson.fromJson((String) object, new TypeToken<List<Master>>() {}.getType());
            System.out.println("list:" + city);
        }catch(Exception ex){

        }

        return city;
    }

}
