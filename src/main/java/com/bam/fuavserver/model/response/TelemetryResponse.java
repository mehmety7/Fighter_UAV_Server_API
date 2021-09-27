package com.bam.fuavserver.model.response;

import com.bam.fuavserver.model.dto.ServerClock;
import com.bam.fuavserver.model.dto.LocationInfo;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryResponse {

    private ServerClock sistemSaati;

    private List<LocationInfo> konumBilgileri;

}
