insert into city(id, country, state) values(11, 'India', 'U.P');
--insert into city(id, country, state) values(12, 'USA', 'San-Francisco');

insert into locations(id, city_id, location_name) values(111, 11 , 'Civil Line');
--insert into locations(id, city_id, location_name) values(222, 12 , 'Zoo Market');

----
insert into building(id, locations_id) values(1111, 111);
--insert into buildings(id, locations_id) values(1112, 111);
--insert into buildings(id, locations_id) values(2222, 222);
--insert into buildings(id, locations_id) values(2223, 222);

insert into floor(no, flats_available, building_id) values(0, 2, 1111);
insert into floor(no, flats_available, building_id) values(1, 3, 1111);
--insert into floor(no, flats_available, building_id) values(0, 2, 1112);
--insert into floor(no, flats_available, building_id) values(1, 2, 1112);
--insert into floor(no, flats_available, building_id) values(0, 2, 2222);
--insert into floor(no, flats_available, building_id) values(1, 2, 2222);

insert into zone(id, zone_name, floor_building_id, floor_no) values(1000, 'North', 1111, 0);
insert into zone(id, zone_name, floor_building_id, floor_no) values(1001, 'South', 1111, 1);
--insert into zone(id, zone_name, floor_building_id, floor_no) values(1003, 'North', 1112, 0);
--insert into zone(id, zone_name, floor_building_id, floor_no) values(1004, 'North', 1112, 1);
--insert into zone(id, zone_name, floor_building_id, floor_no) values(1005, 'North', 2222, 0);
--insert into zone(id, zone_name, floor_building_id, floor_no) values(1006, 'North', 2222, 1);


insert into meter(id, meter_type, zone_id) values(121, 'ELECTRICITY_METER', 1000);
insert into meter(id, meter_type, zone_id) values(131, 'WATER_METER', 1000);
--insert into meter(id, meter_type, zone_id) values(141, 'ELECTRICITY_METER', 1003);
--insert into meter(id, meter_type, zone_id) values(151, 'ELECTRICITY_METER', 1004);

