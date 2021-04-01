package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.PersonalDocument;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DocumentSectionResponse {

    private List<PersonalDocument> personalDocuments;
    private String errorMessage;
}
