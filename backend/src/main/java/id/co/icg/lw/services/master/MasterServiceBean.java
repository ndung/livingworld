package id.co.icg.lw.services.master;

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

    @Override
    public List<Master> getReligions() {
        List<Master> religions = new ArrayList<>();
        List<ReligionEnum> enumList = Arrays.asList(ReligionEnum.values());
        for (ReligionEnum e : enumList ) {
            religions.add(new Master(e.getValue(), e.name()));
        }

        return religions;
    }

    @Override
    public List<Master> getMartialStatus() {
        List<Master> martialStatus = new ArrayList<>();
        List<MartialStatusEnum> enumList = Arrays.asList(MartialStatusEnum.values());
        for (MartialStatusEnum e : enumList ) {
            martialStatus.add(new Master(e.getValue(), e.name()));
        }

        return martialStatus;

    }

    @Override
    public List<Master> getGenders() {
        List<Master> gender = new ArrayList<>();
        List<GenderEnum> enumList = Arrays.asList(GenderEnum.values());
        for (GenderEnum e : enumList ) {
            gender.add(new Master(e.getValue(), e.name()));
        }

        return gender;
    }

    @Override
    public List<Master> getNationalities() {
        List<Master> nationality = new ArrayList<>();
        List<NationalityEnum> enumList = Arrays.asList(NationalityEnum.values());
        for (NationalityEnum e : enumList ) {
            nationality.add(new Master(e.getValue(), e.name()));
        }

        return nationality;
    }

    @Override
    public List<Master> getCities() {
        List<Master> city = new ArrayList<>();
        List<CityEnum> enumList = Arrays.asList(CityEnum.values());
        for (CityEnum e : enumList ) {
            city.add(new Master(e.getValue(), e.name()));
        }

        return city;
    }

}
