ALTER TABLE `Employee` 
ADD INDEX `houseId` (`houseId` ASC) VISIBLE,
ADD CONSTRAINT `Employee_ibfk_3`
  FOREIGN KEY (`houseId`)
  REFERENCES `House` (`hid`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;
  
  
ALTER TABLE `ApplicationWorkFlow` 
ADD INDEX `employee` (`employee` ASC) VISIBLE,
ADD CONSTRAINT `ApplicationWorkFlow_ibfk_1`
  FOREIGN KEY (`employee`)
  REFERENCES `Employee` (`eid`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;