package com.beaconfire.HRServer.response;
import com.beaconfire.HRServer.domain.Contact;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class NameSectionResponse {
    private String firstName;
    private String lastName;
    private String fullName;
    private String middleName;
    private String birthday;
    private String gender;
    private String SSN;
    private String personalEmail;
    private String cellPhone;
    private String workPhone;
    private String workAuth;
    private String workAuthStartDate;
    private String workAuthEndDate;
    private String employmentStartDate;
    private String employmentEndDate;
    private String title;
    private List<Contact> contacts;
    private String avartarUrl;
}
