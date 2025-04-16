create database recruitment_management;
use recruitment_management;

create table technology
(
    id     int primary key auto_increment,
    name   varchar(100) not null unique,
    status bit default 1
);

create table recruitment_position
(
    id            int primary key auto_increment,
    name          varchar(100) not null unique,
    description   text,
    minSalary     double       not null,
    maxSalary     double       not null,
    minExperience int      default 0,
    expiredDate   date         not null,
    updatedAt     datetime default current_timestamp,
    createdAt     datetime default current_timestamp,
    status        bit      default 1,
    check (minSalary <= maxSalary)
);

create table candidate
(
    id          int primary key auto_increment,
    name        varchar(255)                   not null,
    email       varchar(255)                   not null unique,
    password    varchar(255)                   not null,
    phone       varchar(12)                    not null unique,
    experience  int      default 0,
    gender      enum ('MALE','FEMALE','OTHER') not null,
    status      bit      default 1,
    createdAt   datetime default current_timestamp,
    updatedAt   datetime default current_timestamp,
    description text,
    dob         date                           not null
);

create table admin
(
    id       int primary key auto_increment,
    name     varchar(255) not null,
    email    varchar(255) not null unique,
    password varchar(255) not null,
    status   bit default 1
);

create table recruitmentPosition_technology
(
    recruitmentPositionId int,
    technologyId          int,
    primary key (recruitmentPositionId, technologyId),
    foreign key (recruitmentPositionId) references recruitment_position (id),
    foreign key (technologyId) references technology (id)
);

create table candidate_technology
(
    candidateId  int,
    technologyId int,
    primary key (candidateId, technologyId),
    foreign key (candidateId) references candidate (id),
    foreign key (technologyId) references technology (id)
);

create table application
(
    id                    int primary key auto_increment,
    candidateId           int          not null,
    recruitmentPositionId int          not null,
    cvUrl                 varchar(255) not null,
    progress              enum ('PENDING','HANDLING','INTERVIEWING','DONE') default 'PENDING',
    interviewDateRequest  datetime,
    interviewConfirmed    bit,
    interviewUrl          varchar(255),
    interviewDate         datetime,
    interviewResult       bit,
    resultNote            text,
    createdAt             datetime                                          default current_timestamp,
    updatedAt             datetime                                          default current_timestamp,
    status                bit                                               default 1,
    deletedAt             datetime,
    deleteReason          text,
    foreign key (candidateId) references candidate (id),
    foreign key (recruitmentPositionId) references recruitment_position (id)
);

-- bổ sung thêm ghi hoạt động của đơn ứng tuyển.
create table application_status_log
(
    id            int primary key auto_increment,
    applicationId int,
    adminId       int,
    oldProgress   enum ('PENDING','HANDLING','INTERVIEWING','DONE'),
    newProgress   enum ('PENDING','HANDLING','INTERVIEWING','DONE'),
    updatedAt     datetime default current_timestamp,
    note          text,
    foreign key (applicationId) references application (id),
    foreign key (adminId) references admin (id)
);

-- bảng đại diện cho phiên đăng nhập hiện tại.
create table remembered_user
(
    id     int primary key,
    userId int                         ,
    role   enum ('ADMIN', 'CANDIDATE') not null
);
