package com.bam.fuavserver.service;

import com.bam.fuavserver.model.dto.LocationInfo;
import com.bam.fuavserver.model.dto.ServerClock;
import com.bam.fuavserver.model.entity.Crash;
import com.bam.fuavserver.model.entity.CrashInfo;
import com.bam.fuavserver.model.entity.Login;
import com.bam.fuavserver.model.entity.TelemetrySender;
import com.bam.fuavserver.model.response.TelemetryResponse;
import com.bam.fuavserver.repo.CrashInfoRepository;
import com.bam.fuavserver.repo.CrashRepository;
import com.bam.fuavserver.repo.LoginRepository;
import com.bam.fuavserver.repo.TelemetrySenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final TelemetrySenderRepository telemetrySenderRepository;
    private final CrashInfoRepository crashInfoRepository;
    private final CrashRepository crashRepository;
    private final LoginRepository loginRepository;

    public TelemetryResponse getTelemetryResponse(TelemetrySender telemetrySender){
        List<TelemetrySender> telemetries = telemetrySenderRepository.findAll();
        TelemetryResponse telemetryResponse = new TelemetryResponse();
        List<LocationInfo> locationInfoList = new ArrayList<>();
        for(int i = 0; i < telemetries.size(); i++){
            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setIha_irtifa(telemetries.get(i).getIHA_irtifa());
            locationInfo.setIha_enlem(telemetries.get(i).getIHA_enlem());
            locationInfo.setIha_boylam(telemetries.get(i).getIHA_boylam());
            locationInfo.setTakim_numarasi(telemetries.get(i).getTakim_numarasi());
            locationInfo.setIha_dikilme(telemetries.get(i).getIHA_dikilme());
            locationInfo.setIha_yonelme(telemetries.get(i).getIHA_yonelme());
            locationInfo.setIha_yatis(telemetries.get(i).getIHA_yatis());
            Integer diff = telemetries.get(i).getGPSSaati().getMilisaniye() - telemetrySender.getGPSSaati().getMilisaniye();
            locationInfo.setZaman_farki(diff);
            locationInfoList.add(locationInfo);
        }
        telemetryResponse.setKonumBilgileri(locationInfoList);
        telemetryResponse.setSistemSaati(new ServerClock());
        return telemetryResponse;
    }

    public Boolean login(Login login){
        Login user = loginRepository.getLoginByKadi(login.getKadi());
        if(Objects.nonNull(user)){
            if(user.getSifre().equals(login.getSifre())){
                user.setInOut(true);
                loginRepository.save(user);
                return true;
            }
            else {
                throw new RuntimeException("Invalid password");
            }
        }
        else {
            throw new RuntimeException("Invalid username");
        }
    }

    public void exit(){

        List<Login> loginList = loginRepository.findAll();
        loginList.forEach(login -> {
            login.setInOut(false);
        });
        loginList.forEach(login -> {
            loginRepository.save(login);
        });

    }

    @Transactional
    public CrashInfo saveCrashInfo(CrashInfo crashInfo){
        Crash start = crashRepository.save(crashInfo.getKilitlenmeBaslangicZamani());
        Crash finish = crashRepository.save(crashInfo.getKilitlenmeBitisZamani());
        CrashInfo result = new CrashInfo();
        result.setKilitlenmeBaslangicZamani(start);
        result.setKilitlenmeBitisZamani(finish);
        result.setOtonom_kilitlenme(crashInfo.getOtonom_kilitlenme());
        return crashInfoRepository.save(result);

    }



}
