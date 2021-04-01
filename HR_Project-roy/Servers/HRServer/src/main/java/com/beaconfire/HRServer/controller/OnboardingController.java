package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.dao.VisaStatusDAO;
import com.beaconfire.HRServer.domain.*;
import com.beaconfire.HRServer.request.OnboardingRequest;
import com.beaconfire.HRServer.response.*;
import com.beaconfire.HRServer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OnboardingController {
    private AddressService addressService;
    private EmployeeService employeeService;
    private ContactService contactService;
    private VisaStatusService visaStatusService;
    private ApplicationService applicationService;
    private DocumentService documentService;
    private S3Service s3Service;

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setVisaStatusService(VisaStatusService visaStatusService) {
        this.visaStatusService = visaStatusService;
    }

    @Autowired
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Autowired
    public void setS3Service(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/hr/api/getDigitalDocuments")
    public DigitalDocumentResponse getDocuments(){
        DigitalDocumentResponse response = new DigitalDocumentResponse();
        response.setDigitalDocuments(documentService.getAllDigitalDocuments());
        return response;
    }

    @GetMapping("/hr/api/getPersonalDocuments/{eid}")
    public PersonalDocumentResponse getPersonalDocuments(@PathVariable String eid){
        PersonalDocumentResponse response = new PersonalDocumentResponse();
        response.setPersonalDocuments(documentService.getAllPersonalDocumentsForEmployee(eid));
        return response;
    }

    @PostMapping(value = "/hr/api/onboarding", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public OnboardingResponse onboarding(@RequestBody OnboardingRequest request){
        OnboardingResponse response = new OnboardingResponse();
        System.out.println(request.toString());
        Integer employeeId = employeeService.addNewEmployee(request.getUid(), request.getFirstName(), request.getLastName(),
                request.getMiddleName(), request.getCellPhone(), request.getAlternatePhone(), request.getCarInfo(),
                request.getSsn(), request.getBirthday(), request.getGender(), request.getDL(), request.getDLExpiration());
        Integer addressId = addressService.addNewAddress(employeeId, request.getAddressLine1(), request.getAddressLine2(), request.getCity(),
                request.getZipcode(), request.getState());
        Integer emergenceContactId = contactService.addNewEmergenceContact(employeeId, request.getEmergencyFirstName(),
                request.getEmergencyLastName(), request.getEmergencyMiddleName(), request.getEmergencyPhone(),
                request.getEmergencyEmail(), null, request.getEmergencyRelationShip());
        if ("No".equals(request.getIsCitizenOrPR())){
            Integer visaStatusId = visaStatusService.addNewVisaStatus(employeeId, request.getVisaType(), request.getVisaStartDate(), request.getVisaEndDate());
            if ("F1(CPT/OPT)".equals(request.getVisaType())){
                Integer documentApplicationId = applicationService.addNewDocumentApplication(employeeId);
            }
        }
        if ("Yes".equals(request.getHaveReference())){
            Integer referenceContactId = contactService.addNewReferenceContact(employeeId, request.getReferenceFirstName(),
                    request.getReferenceLastName(), request.getReferenceMiddleName(), request.getReferencePhone(),
                    request.getReferenceEmail(), request.getReferenceAddress(), request.getReferenceRelationShip());
        }
        Integer onboardingApplicationId = applicationService.addNewOnboardingApplication(employeeId);
        response.setEid(employeeId);
        response.setMessage("Application Submitted.");
        return response;
    }

    @PostMapping(value = "/hr/api/updateOnboardApplication/{aid}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public OnboardingResponse updateOnboardApplication(@RequestBody OnboardingRequest request, @PathVariable Integer aid){
        OnboardingResponse  response = new OnboardingResponse();
        Application application = applicationService.getApplicationById(aid);
        Employee employee = application.getEmployee();

        employeeService.updateEmployee(employee.getEid(), request.getFirstName(), request.getLastName(),
                request.getMiddleName(), request.getCellPhone(), request.getAlternatePhone(),
                request.getCarInfo(), request.getSsn(), request.getBirthday(), request.getGender(),
                request.getDL(), request.getDLExpiration());

        Integer addressId = addressService.getAddressByEmployee(employee).get(0).getAid();
        addressService.updateAddress(addressId, request.getAddressLine1(), request.getAddressLine2(),
                request.getCity(), request.getZipcode(), request.getState());

        Integer emergencyContactId = contactService.getEmergenceContactByEmployee(employee).get(0).getCid();
        contactService.updateContact(emergencyContactId, request.getEmergencyFirstName(),
                request.getEmergencyLastName(), request.getEmergencyMiddleName(),
                request.getEmergencyPhone(), request.getEmergencyEmail(),
                null, request.getEmergencyRelationShip());

        if ("No".equals(request.getIsCitizenOrPR())){
            List<VisaStatus> visaStatuses = visaStatusService.getVisaStatusByEmployee(employee);
            if (visaStatuses.isEmpty()){
                visaStatusService.addNewVisaStatus(employee.getEid(), request.getVisaType(),
                        request.getVisaStartDate(), request.getVisaEndDate());
            } else {
                Integer vid = visaStatuses.get(0).getVid();
                visaStatusService.updateVisaStatus(vid, request.getVisaType(),
                        request.getVisaStartDate(), request.getVisaEndDate());
            }
        }

        if ("Yes".equals(request.getHaveReference())){
            List<Contact> references = contactService.getReferenceContactByEmployee(employee);
            if (references.isEmpty()){
                contactService.addNewReferenceContact(employee.getEid(), request.getReferenceFirstName(),
                        request.getReferenceLastName(), request.getReferenceMiddleName(),
                        request.getReferencePhone(), request.getReferenceEmail(),
                        request.getReferenceAddress(), request.getReferenceRelationShip());
            } else {
                Integer referenceContactId = contactService.getReferenceContactByEmployee(employee).get(0).getCid();
                contactService.updateContact(referenceContactId, request.getReferenceFirstName(),
                        request.getReferenceLastName(), request.getReferenceMiddleName(),
                        request.getReferencePhone(), request.getReferenceEmail(), request.getReferenceAddress(),
                        request.getReferenceRelationShip());
            }
        }
        applicationService.updateApplication(aid);
        response.setMessage("Application Submitted.");
        return response;
    }

    @GetMapping(value = "/hr/api/getEmployeeApplicationInfo/{email}")
    public ApplicationDetailResponse getEmployeeApplicationInfo(@PathVariable String email){
        System.out.println(email);
        ApplicationDetailResponse response = new ApplicationDetailResponse();
        Application application = applicationService.getOnboardingApplication(email);
        System.out.println(application.getApplicationType());
        response.setApplication(application);
        if (application == null){
            return response;
        }
        Employee employee = application.getEmployee();
        response.setAddresses(addressService.getAddressByEmployee(employee));
        response.setVisaStatuses(visaStatusService.getVisaStatusByEmployee(employee));
        response.setReferenceContact(contactService.getReferenceContactByEmployee(employee));
        response.setEmergenceContact(contactService.getEmergenceContactByEmployee(employee));
        response.setAvatarURL(s3Service.getSignedUrlByPath(employee.getAvartar()));
        return response;
    }

}
