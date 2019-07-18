create table if not exists admin
(
	aId int auto_increment
		primary key,
	aName varchar(30) not null,
	aPassword varchar(100) not null,
	constraint table_name_aName_uindex
		unique (aName)
);

create table if not exists consumer
(
	cId int auto_increment
		primary key,
	cName varchar(25) not null,
	cSex char(2) null,
	cAge smallint(6) null,
	cTel char(11) not null,
	cEmail varchar(40) null,
	cAddress varchar(100) null,
	cPassword varchar(100) not null,
	cBalance decimal(10,2) null,
	constraint cName
		unique (cName),
	constraint consumer_cTel_uindex
		unique (cTel)
);

create table if not exists shop
(
	sId int auto_increment
		primary key,
	sName varchar(40) not null,
	sPassword varchar(100) not null,
	sTel char(11) not null,
	sAddress varchar(100) not null,
	managerName varchar(20) null,
	managerEmail varchar(40) null,
	sPictureUrl varchar(100) null,
	score decimal(2,1) null,
	constraint sName
		unique (sName),
	constraint shop_sTel_uindex
		unique (sTel)
);

create table if not exists commentary
(
	CoID int auto_increment
		primary key,
	CoTime datetime not null,
	content varchar(1024) not null,
	stars int null,
	Cid int not null,
	SID int not null,
	constraint commentary_ibfk_1
		foreign key (Cid) references consumer (cId),
	constraint commentary_ibfk_2
		foreign key (SID) references shop (sId)
);

create index Cid
	on commentary (Cid);

create index SID
	on commentary (SID);

create table if not exists consumerreply
(
	CID int not null,
	CoID int not null,
	CoTime datetime not null,
	content varchar(1024) not null,
	primary key (CID, CoID, CoTime),
	constraint consumerreply_ibfk_1
		foreign key (CID) references consumer (cId)
			on update cascade on delete cascade,
	constraint consumerreply_ibfk_2
		foreign key (CoID) references commentary (CoID)
			on update cascade on delete cascade
);

create table if not exists contact
(
	CID int not null,
	SID int not null,
	CTime datetime not null,
	content varchar(1024) not null,
	CState varchar(10) not null,
	primary key (CID, SID, CTime),
	constraint contact_ibfk_1
		foreign key (CID) references consumer (cId),
	constraint contact_ibfk_2
		foreign key (SID) references shop (sId)
);

create index SID
	on contact (SID);

create table if not exists goods
(
	gId int auto_increment
		primary key,
	gName varchar(40) not null,
	gPrice decimal(10,2) not null,
	stock int not null,
	type varchar(20) not null,
	buyCount int default 0 not null,
	gPictureUrl varchar(1000) null,
	gPraise decimal(2,2) null,
	sId int not null,
	`desc` varchar(1024) not null,
	constraint goods_ibfk_1
		foreign key (sId) references shop (sId)
);

create index sId
	on goods (sId);

create table if not exists orderhistory
(
	oId int auto_increment
		primary key,
	oState varchar(10) not null,
	oTime datetime not null,
	oNum int not null,
	cost decimal(10,2) not null,
	CID int not null,
	GID int not null,
	constraint orderhistory_goods__fk
		foreign key (GID) references goods (gId)
			on update cascade on delete cascade,
	constraint orderhistory_ibfk_1
		foreign key (CID) references consumer (cId)
			on update cascade on delete cascade
);

create table if not exists shoppingcart
(
	CID int not null,
	GID int not null,
	num int not null,
	total decimal(10,2) null,
	primary key (CID, GID),
	constraint shoppingcart_ibfk_1
		foreign key (CID) references consumer (cId)
			on update cascade on delete cascade,
	constraint shoppingcart_ibfk_2
		foreign key (GID) references goods (gId)
			on update cascade on delete cascade
);

create table if not exists shopreply
(
	SID int not null,
	CoID int not null,
	CoTime datetime not null,
	content varchar(1024) not null,
	primary key (SID, CoID, CoTime),
	constraint shopreply_ibfk_1
		foreign key (SID) references shop (sId)
			on update cascade on delete cascade,
	constraint shopreply_ibfk_2
		foreign key (CoID) references commentary (CoID)
			on update cascade on delete cascade
);

create table if not exists sys_log
(
	ID int auto_increment
		primary key,
	USERNAME varchar(50) null,
	OPERATION varchar(50) null,
	_TIME int null,
	METHOD varchar(200) null,
	PARAMS varchar(500) null,
	IP varchar(64) null,
	CREATE_TIME datetime null
);

create or replace definer = root@localhost view commentary_view as select `takeout`.`commentary`.`CoID`    AS `id`,
       `takeout`.`consumer`.`cId`       AS `cid`,
       `takeout`.`shop`.`sId`           AS `sid`,
       `takeout`.`consumer`.`cName`     AS `cname`,
       `takeout`.`shop`.`sName`         AS `sname`,
       `takeout`.`commentary`.`CoTime`  AS `ctime`,
       `takeout`.`commentary`.`content` AS `content`,
       `takeout`.`commentary`.`stars`   AS `stars`
from `takeout`.`commentary`
         join `takeout`.`consumer`
         join `takeout`.`shop`
where ((`takeout`.`commentary`.`SID` = `takeout`.`shop`.`sId`) and
       (`takeout`.`commentary`.`Cid` = `takeout`.`consumer`.`cId`));

create or replace definer = root@localhost view consumerreply_view as select `takeout`.`consumer`.`cId`          AS `cid`,
       `takeout`.`consumerreply`.`CoID`    AS `coid`,
       `takeout`.`consumer`.`cName`        AS `cname`,
       `takeout`.`consumerreply`.`CoTime`  AS `cotime`,
       `takeout`.`consumerreply`.`content` AS `content`
from `takeout`.`consumerreply`
         join `takeout`.`consumer`
where (`takeout`.`consumerreply`.`CID` = `takeout`.`consumer`.`cId`);

create or replace definer = root@localhost view contact_view as select `takeout`.`consumer`.`cId`    AS `cid`,
       `takeout`.`shop`.`sId`        AS `sid`,
       `takeout`.`consumer`.`cName`  AS `cname`,
       `takeout`.`shop`.`sName`      AS `sname`,
       `takeout`.`contact`.`CTime`   AS `ctime`,
       `takeout`.`contact`.`CState`  AS `state`,
       `takeout`.`contact`.`content` AS `content`
from ((`takeout`.`contact` join `takeout`.`consumer`)
         join `takeout`.`shop`)
where ((`takeout`.`contact`.`SID` = `takeout`.`shop`.`sId`) and
       (`takeout`.`contact`.`CID` = `takeout`.`consumer`.`cId`));

create or replace definer = root@localhost view orderhistory_view as select `takeout`.`orderhistory`.`oId`    AS `id`,
       `takeout`.`shop`.`sId`            AS `sid`,
       `takeout`.`orderhistory`.`CID`    AS `cid`,
       `takeout`.`orderhistory`.`GID`    AS `gid`,
       `takeout`.`shop`.`sName`          AS `shopName`,
       `takeout`.`goods`.`gName`         AS `goodsName`,
       `takeout`.`orderhistory`.`oTime`  AS `buyTime`,
       `takeout`.`goods`.`gPictureUrl`   AS `picture`,
       `takeout`.`orderhistory`.`oNum`   AS `num`,
       `takeout`.`goods`.`gPrice`        AS `price`,
       `takeout`.`orderhistory`.`cost`   AS `total`,
       `takeout`.`orderhistory`.`oState` AS `state`,
       `takeout`.`consumer`.`cName`      AS `consumerName`
from `takeout`.`shop`
         join `takeout`.`orderhistory`
         join `takeout`.`goods`
         join `takeout`.`consumer`
where ((`takeout`.`shop`.`sId` = `takeout`.`goods`.`sId`) and
       (`takeout`.`goods`.`gId` = `takeout`.`orderhistory`.`GID`) and
       (`takeout`.`consumer`.`cId` = `takeout`.`orderhistory`.`CID`));

create or replace definer = root@localhost view shoppingcart_view as select `takeout`.`shoppingcart`.`CID`   AS `cid`,
       `takeout`.`goods`.`sId`          AS `sid`,
       `takeout`.`shoppingcart`.`GID`   AS `gid`,
       `takeout`.`shop`.`sName`         AS `shopName`,
       `takeout`.`goods`.`gName`        AS `goodsName`,
       `takeout`.`goods`.`gPictureUrl`  AS `picture`,
       `takeout`.`shoppingcart`.`num`   AS `num`,
       `takeout`.`goods`.`gPrice`       AS `price`,
       `takeout`.`shoppingcart`.`total` AS `total`
from ((`takeout`.`shop` join `takeout`.`shoppingcart`)
         join `takeout`.`goods`)
where ((`takeout`.`shop`.`sId` = `takeout`.`goods`.`sId`) and
       (`takeout`.`goods`.`gId` = `takeout`.`shoppingcart`.`GID`));

create or replace definer = root@localhost view shopreply_view as select `takeout`.`shop`.`sId`          AS `sid`,
       `takeout`.`shopreply`.`CoID`    AS `coid`,
       `takeout`.`shop`.`sName`        AS `sname`,
       `takeout`.`shopreply`.`CoTime`  AS `cotime`,
       `takeout`.`shopreply`.`content` AS `content`
from (`takeout`.`shopreply`
         join `takeout`.`shop`)
where (`takeout`.`shopreply`.`SID` = `takeout`.`shop`.`sId`);

