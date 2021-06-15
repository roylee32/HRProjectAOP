package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.ExceptionOliverLi.HouseNotFoundException;
import com.beaconfire.HRServer.dao.ContactDAO;
import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.dao.HousingDAO;
import com.beaconfire.HRServer.domain.*;
import com.beaconfire.HRServer.util.DatetimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HousingService {
    private EmployeeDAO employeeDAO;
    private HousingDAO housingDAO;
    private ContactDAO contactDAO;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setHousingDAO(HousingDAO housingDAO) {
        this.housingDAO = housingDAO;
    }

    @Autowired
    public void setContactDAO(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @Transactional
    public House getHouseInfoByEid(String eid){
        Employee employee = employeeDAO.getEmployeeById(Integer.valueOf(eid));
        return employee.getHouse();
    }

    @Transactional
    public List<Employee> getResidents(House house){
        return housingDAO.getResidents(house);
    }

    @Transactional
    public List<Facility> getFacilities(House house){
        return housingDAO.getFacilities(house);
    }

    @Transactional
    public Integer createNewFacilityReport(Integer hid, String eid, String title, String description){
        House house = housingDAO.getHouseById(hid);
        Employee employee = employeeDAO.getEmployeeById(Integer.valueOf(eid));
        return housingDAO.createNewFacilityReport(house, employee, title, DatetimeUtil.getCurrentDateTime(),
                description, "Open");
    }

    @Transactional
    public List<FacilityReport> getFacilityReportByEid(String eid){
        Employee employee = employeeDAO.getEmployeeById(Integer.valueOf(eid));
        return housingDAO.getFacilityReportByEmployee(employee);
    }

    @Transactional
    public FacilityReport getFacilityReport(String frid){
        return housingDAO.getFacilityReportById(Integer.valueOf(frid));
    }

    @Transactional
    public Integer createNewComment(String eid, String frid, String comment){
        Employee employee = employeeDAO.getEmployeeById(Integer.valueOf(eid));
        FacilityReport facilityReport = housingDAO.getFacilityReportById(Integer.valueOf(frid));
        return housingDAO.createNewComment(facilityReport, employee, comment,
                DatetimeUtil.getCurrentDateTime(), DatetimeUtil.getCurrentDateTime());
    }

    @Transactional
    public List<FacilityReportDetail> getFacilityReportDetailByFrid(String frid){
        FacilityReport report = housingDAO.getFacilityReportById(Integer.valueOf(frid));
        return housingDAO.getFacilityReportDetailByReport(report);
    }

    @Transactional
    public void updateFacilityReportDetailComment(Integer frdid, String comment){
        housingDAO.updateFacilityReportDetailComment(frdid, comment);
    }

    @Transactional
    public List<House> getAllHouses(){
        return housingDAO.getAllHouses();
    }

    @Transactional
    public Integer getResidentSize(Integer hid){
        return housingDAO.getHouseResidentSize(hid);
    }

    @Transactional
    public Integer addNewHouse(Integer contactId, String address, Integer size){
        Contact contact = contactDAO.getContactById(contactId);
        return housingDAO.addNewHouse(contact, address, size);
    }

    @Transactional
    public House getHouseInfoById(Integer hid) throws HouseNotFoundException{
        House house = housingDAO.getHouseById(hid);
        if (house == null){
            logger.error("House " + hid + " not found.");
            throw new HouseNotFoundException("House " + hid + " not found.");
        }
        return housingDAO.getHouseById(hid);
    }

    @Transactional
    public List<FacilityReport> getFacilityReportByHouse(House house){
        return housingDAO.getFacilityReportByHouse(house);
    }
}
