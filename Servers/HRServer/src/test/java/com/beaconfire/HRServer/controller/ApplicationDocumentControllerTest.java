package com.beaconfire.HRServer.controller;


import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.PersonalDocument;
import com.beaconfire.HRServer.request.ApplicationDetailDocumentsRequest;
import com.beaconfire.HRServer.response.ApplicationDetailDocumentsResponse;
import com.beaconfire.HRServer.service.ApplicationService;
import com.beaconfire.HRServer.service.S3Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationDocumentControllerTest {
    private S3Service mockS3Service;
    private ApplicationDocumentController mockApplicationDocumentController;
    private ApplicationDetailDocumentsRequest applicationDetailDocumentsRequest;
    private ApplicationService mockApplicationService;
    private Employee employee;



    @BeforeEach
    public void setup(){
        mockS3Service = mock(S3Service.class);
        mockApplicationDocumentController = new ApplicationDocumentController();
        mockApplicationDocumentController.setS3Service(mockS3Service);
        when(mockS3Service.getSignedUrlByPath("34_03-27-2021_22:31:08_blank.pdf")).thenReturn(new String());
    }

    @Test
    public void testGetSignedUrl(){
        String path = "34_03-27-2021_22:31:08_blank.pdf";
        applicationDetailDocumentsRequest = new ApplicationDetailDocumentsRequest();
        applicationDetailDocumentsRequest.setPath(path);
        ApplicationDetailDocumentsResponse documentsResponse = mockApplicationDocumentController.getApplicationDocumentsDetails(applicationDetailDocumentsRequest);
        assertNotNull(documentsResponse);
    }

    @BeforeEach
    public void setup2(){
        mockApplicationService = mock(ApplicationService.class);
        mockApplicationDocumentController = new ApplicationDocumentController();
        mockApplicationDocumentController.setApplicationService(mockApplicationService);
        when(mockApplicationService.getEmployeeByID(1)).thenReturn(new Employee());
        when(mockApplicationService.getPersonalDocuments(employee)).thenReturn(new List<PersonalDocument>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<PersonalDocument> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(PersonalDocument personalDocument) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends PersonalDocument> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends PersonalDocument> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public PersonalDocument get(int index) {
                return null;
            }

            @Override
            public PersonalDocument set(int index, PersonalDocument element) {
                return null;
            }

            @Override
            public void add(int index, PersonalDocument element) {

            }

            @Override
            public PersonalDocument remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<PersonalDocument> listIterator() {
                return null;
            }

            @Override
            public ListIterator<PersonalDocument> listIterator(int index) {
                return null;
            }

            @Override
            public List<PersonalDocument> subList(int fromIndex, int toIndex) {
                return null;
            }
        });
    }

    @Test
    public void testGetApplicationDocumentDetails(){
        ApplicationDetailDocumentsResponse documentsResponse = mockApplicationDocumentController.getApplicationDocumentDetails(1);
        assertNotNull(documentsResponse);
    }


}
