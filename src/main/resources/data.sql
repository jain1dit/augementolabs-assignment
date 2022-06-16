insert into city(id,name, country, state) values(11, 'Meerut', 'India', 'U.P');

insert into location(id, city_id, location_name) values(111, 11 , 'Civil Line');

insert into building(id, name, locations_id) values(1111, '5-star', 111);

insert into floor(id, floor_Number, building_id) values(5, 0, 1111);
insert into floor(id, floor_Number, building_id) values(6, 1, 1111);

insert into zone(id, zone_name, floor_building_id, floor_id) values(1000, 'North', 1111, 5);
insert into zone(id, zone_name, floor_building_id, floor_id) values(1001, 'South', 1111, 6);

insert into meter(id, meter_type, zone_id) values(121, 'ELECTRICITY_METER', 1000);
insert into meter(id, meter_type, zone_id) values(131, 'WATER_METER', 1000);

