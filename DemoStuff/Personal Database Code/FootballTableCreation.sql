
Create Table Team (
	TeamID int primary key auto_increment,
    TeamName varchar(50) not null,
    TeamLocation varchar(50) not null
);


Create Table Player (
	PlayerID int primary key auto_increment,
    PlayerName varchar(50) not null,
    Birthdate date not null,
    Position varchar(50) not null
);

Create Table Game (
	GameID int primary key,
    Week int not null, 
    Location varchar(50) not null
);


Create table Plays (
	TeamID int,
    GameID int,
    Result varchar(50) not null,
    primary key (TeamID, GameID),
    foreign key (TeamID) references Team(TeamID)
		on update cascade,
	foreign key (GameID) references Game(GameID)
		on update cascade
);

Create table PlaysFor (

	TeamID int,
    PlayerID int,
	primary key (TeamID, PlayerID),
    foreign key (TeamID) references Team(TeamID)
		on update cascade,
	foreign key (PlayerID) references Player(PlayerID)
		on update cascade
);
/*
Create table PlaysIn (
	PlayerID int,
    GameID int,
    primary key (PlayerID, GameID),
    foreign key (PlayerID) references Player(PlayerID)
		on update cascade,
	foreign key (GameID) references Game(GameID)
		on update cascade
);
*/

Create table PlayerStat (
	PlayerStatID int auto_increment,
    PlayerID int not null,
    PlayerRushYD int not null,
    PlayerRushTD int not null,
    PlayerPassYD int not null,
    PlayerPassTD int not null,
    PlayerRecYD int not null,
    PlayerRecTD int not null,
    PlayerInterceptions int not null,
    primary key (PlayerStatID),
    foreign key (PlayerID) references Player(PlayerID)
		on update cascade
);

drop table playerstat;

Create table GameStat (
	GameStatID int auto_increment,
    GameID int not null,
    HomeRushYD int not null,
    HomeRushTD int not null,
    HomePassYD int not null,
    HomePassTD int not null,
    HomeScore int not null,
    AwayRushYD int not null,
    AwayRushTD int not null,
    AwayPassYD int not null,
    AwayPassTD int not null,
    AwayScore int not null,
    primary key (GameStatID),
    foreign key (GameID) references Game(GameID)
);

Create table TeamStat (
	TeamStatID int auto_increment,
    TeamID int not null, 
    TotalPassYD int not null,
    TotalPassTD int not null,
    TotalRushYD int not null,
    TotalRushTD int not null,
    Loss int not null,
    Win int not null,
    OffensiveRating int not null,
    DefensiveRating int not null,
    primary key (TeamStatID),
    foreign key (TeamID) references Team(TeamID)
);

Create table Coach (
CoachID int primary key auto_increment,
CoachName varchar(50) not null,
Birthdate date not null
);

Create table CoachedBy (
CoachID int,
TeamID int,
primary key (CoachID,TeamID),
foreign key (CoachID) references Coach(CoachID)
	on update cascade,
foreign key (TeamID) references Team(TeamID)
	on update cascade
);