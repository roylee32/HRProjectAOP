package com.beaconfire.HRServer.controller;


import com.beaconfire.HRServer.domain.PersonalDocument;
import com.beaconfire.HRServer.response.DocumentSectionResponse;
import com.beaconfire.HRServer.service.DocumentService;
import com.beaconfire.HRServer.service.S3Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DocumentSectionControllerTest {

    private DocumentService mockDocumentService;
    private DocumentSectionController mockDocumentSectionController;


    @BeforeEach
    public void setup(){
        mockDocumentService = mock(DocumentService.class);
        mockDocumentSectionController = new DocumentSectionController();
        mockDocumentSectionController.setDocumentService(mockDocumentService);
        when(mockDocumentService.getAllPersonalDocumentsForEmployee("1")).thenReturn(new List<PersonalDocument>() {
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
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
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
    public void testGetAllPersonalDocuments(){
        String eid = "1";
        DocumentSectionResponse documentResponse = mockDocumentSectionController.getAllPersonalDocuments(eid);
        assertNotNull(documentResponse);
    }
}
