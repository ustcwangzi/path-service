# 数据库Mysql初始化
> create table test.t_path
(
path_id        int unsigned auto_increment primary key,
source_city    int unsigned not null,
destiny_city   int unsigned not null,
departure_time bigint       not null,
arrive_time    bigint       not null
);