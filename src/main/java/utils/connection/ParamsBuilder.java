package utils.connection;

import models.requests.CheckinRequest;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParamsBuilder {
    public Map<String, Object> buildCheckinParams(CheckinRequest checkinRequest){
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("gmt_offset", "1");
        params.put("timezone", "CET");
        params.put("bid", checkinRequest.getUntappdBeerId());
        params.put("foursquare_id", "59a95f46d8fe7a0d13eb409e");
        params.put("geolat", "50.0488");
        params.put("geolng", "19.9692");
        params.put("shout", checkinRequest.getComment());
        params.put("rating", checkinRequest.getRating());

        return  params;
    }
}
