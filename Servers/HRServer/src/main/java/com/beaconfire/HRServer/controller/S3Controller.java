package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.response.AvatarUrlResponse;
import com.beaconfire.HRServer.service.DocumentService;
import com.beaconfire.HRServer.service.EmployeeService;
import com.beaconfire.HRServer.service.S3Service;
import com.beaconfire.HRServer.util.DatetimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;

@RestController
public class S3Controller {
    private S3Service s3Service;
    private DocumentService documentService;
    private EmployeeService employeeService;

    @Autowired
    public void setS3Service(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/hr/api/getDefaultAvatar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public AvatarUrlResponse getDefaultAvatar(){
        AvatarUrlResponse response = new AvatarUrlResponse();
        response.setSignedUrl(s3Service.getDefaultAvatar());
        return response;
    }

    @PostMapping("/hr/api/upload/{eid}/{type}")
    public String uploadMultipartFile(@RequestParam("file")MultipartFile file,
                                      @PathVariable String eid, @PathVariable String type){
        String title = file.getOriginalFilename();
        String keyName = eid + "_" + DatetimeUtil.getCurrentDateTimeExtension() + "_" + title;
        s3Service.uploadFile(keyName, file);
        Integer documentID = documentService.uploadPersonalDocumentForEmployee(eid, keyName, title, null);
        if ("avatar".equals(type)){
            employeeService.setAvatar(eid, keyName);
        }
        return "Upload Successfully -> KeyName = " + keyName;
    }

    @GetMapping("hr/api/download/{keyname}")
    public ResponseEntity downloadFile(@PathVariable String keyname){
        ByteArrayOutputStream downloadInputStream = s3Service.downloadFile(keyname);
        return ResponseEntity.ok()
                .contentType(contentType(keyname))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; file=\"" + keyname + "\"")
                .body(downloadInputStream.toByteArray());
    }

    private MediaType contentType(String keyname) {
        String[] arr = keyname.split("\\.");
        String type = arr[arr.length-1];
        switch(type) {
            case "txt": return MediaType.TEXT_PLAIN;
            case "png": return MediaType.IMAGE_PNG;
            case "jpg": return MediaType.IMAGE_JPEG;
            default: return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
