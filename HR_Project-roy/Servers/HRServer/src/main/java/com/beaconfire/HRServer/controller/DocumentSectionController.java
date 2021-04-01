package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.response.DocumentSectionResponse;
import com.beaconfire.HRServer.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentSectionController {

    private DocumentService documentService;

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping(value = "hr/api/documentSection")
    public DocumentSectionResponse getAllPersonalDocuments(@RequestParam String eid) {
        DocumentSectionResponse response = new DocumentSectionResponse();
        response.setPersonalDocuments(documentService.getAllPersonalDocumentsForEmployee(eid));
        return response;
    }
}
