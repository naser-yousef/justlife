create table vehicle
(
    id   int auto_increment
        primary key,
    type varchar(20) null
);

create table driver
(
    id         bigint auto_increment
        primary key,
    name       varchar(20) null,
    mobile     varchar(20) null,
    vehicle_id int         null,
    constraint vehicle_id_fk1
        foreign key (vehicle_id) references vehicle (id)
);

create table professional
(
    id         bigint auto_increment
        primary key,
    name       varchar(20) null,
    mobile     varchar(15) not null,
    email      varchar(30) not null,
    vehicle_id int         null,
    constraint professionals_vehicles_id_fk
        foreign key (vehicle_id) references vehicle (id)
);

create table schedule
(
    id              bigint auto_increment
        primary key,
    professional_id bigint null,
    day             date   not null,
    start_hour      int    not null,
    end_hour        int    not null,
    duration        int    not null,
    appointment_id  int    null,
    constraint schedules_professionals_id_fk
        foreign key (professional_id) references professional (id)
);

create table appointment
(
    id          bigint auto_increment
        primary key,
    client_name varchar(20) null,
    schedule_id bigint      not null,
    mobile      varchar(20) null,
    location    varchar(20) null,
    status      int         null,
    created_at  datetime    null,
    constraint appointment_schedule_id_fk
        foreign key (schedule_id) references schedule (id)
);


