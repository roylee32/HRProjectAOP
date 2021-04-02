package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.House;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllHousesInfoResponse {
    private List<House> houses;
    private List<Integer> sizeList;
}
