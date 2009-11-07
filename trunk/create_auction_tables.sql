﻿drop table Payment;
drop table Rating;
drop table Bid;
drop table Auction;
drop table Item;
drop table User;


create table User
(
	UserNo integer not null,
	UserId varchar(80) not null,
	FirstName varchar(20),
	LastName varchar(30),
	Address1 varchar(80),
	Address2 varchar(80),
	State varchar(3),
	ZipCode integer,
	Email varchar(80),
	Password varchar(12),
	primary key(UserNo)
);

create table Rating
(
	RatingNo integer not null,
	RaterId integer not null,
	RatedUserId integer not null,
	Rating integer not null,
	Comment varchar(255),
	primary key (RatingNo),
	foreign key(RaterId) references User(UserNo),
	foreign key(RatedUserId) references User(UserNo)
);

create table Item
(
	ItemNo integer not null,
	ItemName varchar (80),
	Description varchar (255),
	Condition1 integer,
	primary key(ItemNo)
);

create table Auction
(
	AuctionNo integer not null,
	ItemNo integer not null,
	SellerId integer not null,
	StartTime datetime,
	StopTime datetime,
	primary key(AuctionNo),
	foreign key(ItemNo) references Item(ItemNo),
	foreign key(SellerId) references User(UserNo)
);

create table Bid
(
	BidNo integer not null,
	BidderId integer not null,
	AuctionNo integer not null,
	BidAmt integer not null,
	primary key(BidNo),
	foreign key(AuctionNo) references Auction(AuctionNo),
	foreign key(BidderId) references User(UserNo)
);

create table Payment
(
	PaymentNo integer not null,
	BuyerNo integer not null,
	Auction integer not null,
	Amount double,
	Type varchar (20),
	CardNo varchar(20),
	ExpDate date,
	SecurityCode integer,
	primary key(PaymentNo),
	foreign key(BuyerNo) references User(UserNo),
	foreign key(Auction) references Auction(AuctionNo)
);
