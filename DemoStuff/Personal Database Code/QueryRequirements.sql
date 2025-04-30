##### Queries For Project 
# Group 1 aggregate functions, LIKE, GROUP BY, ORDER BY, LIMIT
# This Query selects the top 5 quarterbacks in the NFC North and orders them by total touchdowns 

SELECT P.PlayerName, P.Position, SUM(PS.PlayerRushTD + PS.PlayerPassTD + PS.PlayerRecTD) AS TotalTD
FROM Player as P
JOIN PlayerStat as PS ON P.PlayerID = PS.PlayerID
WHERE P.Position = 'QB'
GROUP BY P.PlayerID
ORDER BY TotalTD DESC
LIMIT 5;

## Group 2 HAVING, OFFSET, outer join, joining four or more tables
# This query selects 15 players in the nfl who have won the least amount of games this season
# because this database only takes into consideration the NFC North the output is the all of the 
# players on the chicago bears

SELECT T.TeamName, P.PlayerName, COUNT(PL.GameID) AS GamesWon
FROM Team T
JOIN Plays PL ON T.TeamID = PL.TeamID
JOIN PlaysFor PF ON T.TeamID = PF.TeamID
JOIN Player P ON PF.PlayerID = P.PlayerID
WHERE PL.Result = 'W'
GROUP BY T.TeamID, P.PlayerID
HAVING COUNT(PL.GameID) > 3
ORDER BY GamesWon ASC
LIMIT 15 
OFFSET 0;

# Group 3 subqueries, IN, set operators
# This query obtains all players in the NFC North who have rushed for over 500 yards and have over 5 touchdowns and orders them
# in descending order by touchdowns
SELECT P.PlayerName, PS.PlayerRushYD, PS.PlayerRushTD
FROM Player P
JOIN PlayerStat PS ON P.PlayerID = PS.PlayerID
WHERE P.PlayerID IN (
        SELECT PF.PlayerID
        FROM PlaysFor PF
        JOIN PlayerStat PS2 ON PF.PlayerID = PS2.PlayerID
        WHERE PS2.PlayerRushYD > 500 
        AND PS2.PlayerRushTD >= 5
    )
ORDER BY PS.PlayerRushTD desc;

