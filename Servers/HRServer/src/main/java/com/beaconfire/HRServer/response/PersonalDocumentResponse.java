package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.PersonalDocument;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PersonalDocumentResponse {
    private List<PersonalDocument> personalDocuments;
}
