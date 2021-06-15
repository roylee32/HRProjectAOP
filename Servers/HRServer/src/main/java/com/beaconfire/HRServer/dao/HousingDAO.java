package com.beaconfire.HRServer.dao;

import com.beaconfire.HRServer.domain.*;
import com.beaconfire.HRServer.util.DatetimeUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class HousingDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public House getHouseById(Integer hid){
        Session session = sessionFactory.getCurrentSession();
        return session.get(House.class, hid);
    }

    public List<Employee> getResidents(House house){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from Employee where house = :house";
        Query query = session.createQuery(statement);
        query.setParameter("house", house);
        return query.getResultList();
    }

    public List<Facility> getFacilities(House house){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from Facility where house = :house";
        Query query = session.createQuery(statement);
        query.setParameter("house", house);
        return query.getResultList();
    }

    public Integer createNewFacilityReport(House house, Employee employee, String title, String reportDate,
                                           String description, String status){
        Session session = sessionFactory.getCurrentSession();
        FacilityReport facilityReport = new FacilityReport();
        facilityReport.setEmployee(employee);
        facilityReport.setTitle(title);
        facilityReport.setReportDate(reportDate);
        facilityReport.setDescription(description);
        facilityReport.setStatus(status);
        facilityReport.setHouse(house);
        return (Integer) session.save(facilityReport);
    }

    public List<FacilityReport> getFacilityReportByEmployee(Employee employee){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from FacilityReport where employee = :employee";
        Query query = session.createQuery(statement);
        query.setParameter("employee", employee);
        return query.getResultList();
    }

    public FacilityReport getFacilityReportById(Integer frid){
        Session session = sessionFactory.getCurrentSession();
        return session.get(FacilityReport.class, frid);
    }

    public Integer createNewComment(FacilityReport report, Employee employee, String comment,
                                    String createDate, String lastModificationDate){
        Session session = sessionFactory.getCurrentSession();
        FacilityReportDetail facilityReportDetail = new FacilityReportDetail();
        facilityReportDetail.setReport(report);
        facilityReportDetail.setEmployee(employee);
        facilityReportDetail.setComments(comment);
        facilityReportDetail.setCreateDate(createDate);
        facilityReportDetail.setLastModificationDate(lastModificationDate);
        return (Integer) session.save(facilityReportDetail);
    }

    public List<FacilityReportDetail> getFacilityReportDetailByReport(FacilityReport report){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from FacilityReportDetail where report = :report";
        Query query = session.createQuery(statement);
        query.setParameter("report", report);
        return query.getResultList();
    }

    public void updateFacilityReportDetailComment(Integer frdid, String comment){
        Session session = sessionFactory.getCurrentSession();
        FacilityReportDetail facilityReportDetail = session.get(FacilityReportDetail.class, frdid);
        facilityReportDetail.setComments(comment);
        facilityReportDetail.setLastModificationDate(DatetimeUtil.getCurrentDateTime());
    }

    public List<House> getAllHouses(){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from House";
        Query query = session.createQuery(statement);
        return query.getResultList();
    }

    public Integer getHouseResidentSize(Integer hid){
        Session session = sessionFactory.getCurrentSession();
        House house = session.get(House.class, hid);
        return house.getResidents().size();
    }

    public Integer addNewHouse(Contact contact, String address, Integer size){
        Session session = sessionFactory.getCurrentSession();
        House house = new House();
        house.setContact(contact);
        house.setAddress(address);
        house.setNumberOfPerson(size);
        return (Integer )session.save(house);
    }

    public List<FacilityReport> getFacilityReportByHouse(House house){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from FacilityReport where house = :house order by reportDate desc";
        Query query = session.createQuery(statement);
        query.setParameter("house", house);
        return query.getResultList();
    }


}
