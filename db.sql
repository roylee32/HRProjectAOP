create table `User`(
	uid int primary key not null auto_increment,
    username varchar(45) not null,
    email text not null,
    pword text not null,
    createDate varchar(20) not null,
    modificationDate varchar(20) not null
);

create table Employee(
	eid int primary key not null auto_increment,
    uid int not null,
    firstName varchar(45) not null,
    lastName varchar(45) not null,
    middleName varchar(45) not null,
    email text not null,
    cellPhone varchar(10) not null,
    alternatePhone varchar(10) not null,
    gender varchar(6),
    SSN varchar(10) not null,
    DOB varchar(10) not null,
    title varchar(45) not null,
    managerId int not null,
    startDate varchar(10) not null,
    endDate varchar(10),
    avartar text not null,
    car text,
    DLNumber text,
    DLExpiration varchar(10),
    houseId int not null,
    foreign key (uid) references User(uid),
    foreign key (managerId) references Employee(eid)
);

create table `Role`(
	rid int primary key not null auto_increment,
    roleName text not null,
    `description` text not null,
    createDate varchar(10) not null,
    lastModificationDate varchar(10) not null,
    lastModificationUser int,
    foreign key (lastModificationUser) references User(uid)
);

create table UserRole(
	urid int primary key not null auto_increment,
    `user` int not null,
    `role` int not null,
    activeFlag boolean not null,
    createDate varchar(10) not null,
    lastModificationDate varchar(10),
    lastModificationUser int,
    foreign key (`user`) references User(uid),
    foreign key (`role`) references Role(rid),
    foreign key (lastModificationUser) references User(uid)
);

create table RegistrationToken(
	tid int primary key not null auto_increment,
    token text not null,
    validDuration int not null,
    email text not null,
    createBy int not null,
    foreign key (createBy) references User(uid)
);

create table Contact(
	cid int primary key not null auto_increment,
    employee int not null,
    relationship text not null,
    title text not null,
    isReference boolean not null,
    isEmergency boolean not null,
    isLandLord boolean not null,
    foreign key (employee) references Employee(eid)
);

create table Address(
	aid int primary key not null auto_increment,
    employee int not null,
    addressLine1 text not null,
    addressLine2 text,
    City text not null,
    Zipcode varchar(5) not null,
    StateName text not null,
    StateAbbr varchar(2) not null,
    foreign key (employee) references Employee(eid)
);

create table VisaStatus(
	vid int primary key not null auto_increment,
    employee int not null,
    VisaType text,
    `active` boolean not null,
    modificationDate varchar(10) not null,
    VisaStartDate varchar(10) not null,
    VisaEndDate varchar(10) not null,
    foreign key (employee) references Employee(eid)
);

create table PersonalDocument(
	pid int primary key not null auto_increment,
    employee int not null,
    `path` text not null,
    title text not null,
    `comment` text not null,
    createDate varchar(10) not null,
    foreign key (employee) references Employee(eid)
);

create table DigitalDocument(
	did int primary key not null auto_increment,
	`type` text not null,
    required boolean not null,
    templateLocation text not null,
    `description` text not null,
    title text not null
);

create table ApplicationWorkFlow(
	aid int primary key not null auto_increment,
    employee int not null,
    createDate varchar(10) not null,
    modificationDate varchar(10) not null,
    `status` text not null,
    comments text not null
);

create table House(
	hid int primary key not null auto_increment,
    contact int not null,
    address text not null,
    numberOfPerson int not null,
    foreign key (contact) references Contact(cid)
);

create table Facility(
	fid int primary key not null auto_increment,
    house int not null,
    `type` text not null,
	`description` text not null,
    quantity int not null,
    foreign key (house) references House(hid)
);

create table facilityReport(
	frid int primary key not null auto_increment,
    title text not null,
    employee int not null,
    reportDate varchar(20) not null,
    `description` text not null,
    `status` text not null,
    house int not null,
    foreign key (employee) references Employee(eid),
    foreign key (house) references House(hid) on delete cascade on update cascade
);

create table facilityReportDetail(
	frdid int primary key not null auto_increment,
    report int not null,
    employee int not null,
    comments text not null,
    createDate varchar(20),
    lastModificationDate varchar(20),
    foreign key (report) references facilityReport(frid),
    foreign key (employee) references Employee(eid)
);




