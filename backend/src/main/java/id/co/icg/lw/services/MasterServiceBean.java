package id.co.icg.lw.services;

import id.co.icg.lw.domain.master.Master;
import id.co.icg.lw.domain.master.MemberType;
import id.co.icg.lw.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MasterServiceBean implements MasterService {

    @Value("${iFabula.url}")
    private String iFabulaUrl;


    @Autowired
    private LivingWorldApiService lwComponent;

    @Override
    public List<MemberType> getMemberTypes() {
//        IFabulaService iFabulaService = livingWorldApiComponent.createService(IFabulaService.class);
//        Call<IFabulaResponse> callSync = iFabulaService.getMasterData("member_type");
//        IFabulaResponse response = null;
//        try {
//            response = callSync.execute().body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Gson gson = new Gson();
//        List<MemberType> memberTypes = gson.fromJson(response.getList(), List.class);
//        return memberTypes;
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.get(iFabulaUrl + "/master?type=member_types", MemberType.class);
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
