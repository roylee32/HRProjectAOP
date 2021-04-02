package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.House;
import com.beaconfire.HRServer.domain.VisaStatus;
import com.beaconfire.HRServer.response.HrEmployeeProfileResponse;
import com.beaconfire.HRServer.service.EmployeeService;
import com.beaconfire.HRServer.service.VisaStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HrEmployeeProfileControllerTest {

    private EmployeeService mockEmployeeService;
    private VisaStatusService mockVisaStatusService;
    private HrEmployeeProfileController mockHrEmployeeProfileController;

    @BeforeEach
    public void setup(){
        mockEmployeeService = mock(EmployeeService.class);
        mockVisaStatusService = mock(VisaStatusService.class);
        mockHrEmployeeProfileController = new HrEmployeeProfileController();
        mockHrEmployeeProfileController.setEmployeeService(mockEmployeeService);
        mockHrEmployeeProfileController.setVisaStatusService(mockVisaStatusService);
        when(mockEmployeeService.getAllEmployees()).thenReturn(new List<Employee>() {
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
            public Iterator<Employee> iterator() {
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
            public boolean add(Employee employee) {
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
            public boolean addAll(Collection<? extends Employee> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Employee> c) {
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
            public Employee get(int index) {
                return null;
            }

            @Override
            public Employee set(int index, Employee element) {
                return null;
            }

            @Override
            public void add(int index, Employee element) {

            }

            @Override
            public Employee remove(int index) {
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
            public ListIterator<Employee> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Employee> listIterator(int index) {
                return null;
            }

            @Override
            public List<Employee> subList(int fromIndex, int toIndex) {
                return null;
            }
        });
        when(mockVisaStatusService.getAllVisaStatuses()).thenReturn(new List<VisaStatus>() {
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
            public Iterator<VisaStatus> iterator() {
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
            public boolean add(VisaStatus visaStatus) {
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
            public boolean addAll(Collection<? extends VisaStatus> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends VisaStatus> c) {
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
            public VisaStatus get(int index) {
                return null;
            }

            @Override
            public VisaStatus set(int index, VisaStatus element) {
                return null;
            }

            @Override
            public void add(int index, VisaStatus element) {

            }

            @Override
            public VisaStatus remove(int index) {
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
            public ListIterator<VisaStatus> listIterator() {
                return null;
            }

            @Override
            public ListIterator<VisaStatus> listIterator(int index) {
                return null;
            }

            @Override
            public List<VisaStatus> subList(int fromIndex, int toIndex) {
                return null;
            }
        });
    }

    @Test
    public void testGetAllEmployees(){
        HrEmployeeProfileResponse response = mockHrEmployeeProfileController.getAllEmployees();
        assertNotNull(response);
    }
}
