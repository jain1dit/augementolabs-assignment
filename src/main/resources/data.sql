insert into city(id,name, country, state) values(11, 'Meerut', 'India', 'U.P');

insert into location(id, city_id, location_name) values(111, 11 , 'Civil Line');

insert into building(id, name, locations_id) values(1111, '5-star', 111);

insert into floor(no, flats_available, building_id) values(0, 2, 1111);
insert into floor(no, flats_available, building_id) values(1, 3, 1111);

insert into zone(id, zone_name, floor_building_id, floor_no) values(1000, 'North', 1111, 0);
insert into zone(id, zone_name, floor_building_id, floor_no) values(1001, 'South', 1111, 1);

insert into meter(id, meter_type, zone_id) values(121, 'ELECTRICITY_METER', 1000);
insert into meter(id, meter_type, zone_id) values(131, 'WATER_METER', 1000);

