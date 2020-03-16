-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa" database.
-------------------------------------------------------------------------------

INSERT INTO Category (name) VALUES ('Movies');
INSERT INTO Category (name) VALUES ('Music');

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('2001: A Space Odyssey [Blu-ray]', 
        '2001 is a story of evolution. Sometime in the distant past, someone\
or something nudged evolution by placing a monolith on Earth (presumably\
elsewhere throughout the universe as well)...', 11.99, 1);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Blade Runner [Blu-ray]', 
        'In the 21st century, a corporation develops human clones to be used\
as slaves in colonies outside the Earth, identified as replicants. In 2019,\
a former police officer is hired to hunt down a fugitive group of clones\
living undercover in Los Angeles...', 11.99, 1);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Alien [DVD]', 
        'In the distant future, the crew of the commercial spaceship Nostromo\
are on their way home when they pick up a distress call from a distant moon.\
The crew are under obligation to investigate and the spaceship descends on the\
moon afterwards...', 7.65, 1);
    
INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Matrix [Blu-Ray]', 
        'Thomas A. Anderson is a man living two lives. By day he is an average\
computer programmer and by night a hacker known as Neo. Neo has always\
questioned his reality, but the truth is far beyond his imagination...', 11.99, 1);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Pale rider [DVD]', 
        'Clint Eastwood is a mysterious preacher who comes to a gold mining\
camp near a small town in the mountains. The miners are in grave danger as\
a ruthless landowner decides to take their land, with the support of the\
sheriff...', 7.65, 1);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Pulp Fiction [DVD]', 
        'Series of less-and-more related but separated short stories of crime\
and comedy that each result into an unexpected ending including unexpected\
deaths, sudden twists, black comedy events and horrific conclusions of crime\
and its following consequences...', 7.65, 1);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Unforgiven [DVD]',
        'Retired Old West gunslinger William Munny reluctantly takes on one\
last job, with the help of his old partner and a young man...', 7.65, 1);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Led Zeppelin [CD]', 
        'Eponymous debut album by English rock band Led Zeppelin. It was \
released on 12 January 1969 in the United States and on 31 March in \
the United Kingdom by Atlantic Records...', 6.99, 2);
    
INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Led Zeppelin II [Vinyl]', 
        'Second album by the English rock band Led Zeppelin, released on \
22 October 1969 in the United States and on 31 October 1969 in the \
United Kingdom by Atlantic Records...', 9.78, 2);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Led Zeppelin III [CD]', 
        'Third studio album by the English rock band Led Zeppelin, \
released in October 1970. It showed a progression from \
straightforward rock towards folk and acoustic music...', 6.99, 2);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Led Zeppelin IV [Vinyl]', 
        'Untitled fourth studio album by the English rock band Led \
        Zeppelin, commonly known as Led Zeppelin IV, was released on \
        8 November 1971 by Atlantic Records...', 9.78, 2);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Appetite for Destruction [Vinyl]', 
'Debut studio album by American hard rock band Guns N'' Roses. \
It was released on July 21, 1987, by Geffen Records... ', 9.78, 2);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Use Your Illusion I [CD]', 
'Third studio album by American rock band Guns N'' Roses, released on \
the same day as its counterpart Use Your Illusion II...', 6.99, 2);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Use Your Illusion II [CD]', 
        'Fourth studio album by the American hard rock band Guns N'' Roses. \
The album was released on September 17, 1991, the same day as its \
counterpart Use Your Illusion I...', 6.99, 2);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('The Dark Side of the Moon [Vinyl]', 
        'Eighth studio album by English rock band Pink Floyd, released on \
1 March 1973 by Harvest Records. It built on ideas explored in Pink Floyd''s \
earlier recordings and performances, but without the extended instrumentals \
that characterised their earlier work...', 9.78, 2);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Wish You Were Here [Vinyl]', 
        'Ninth studio album by the English rock band Pink Floyd. It was \
released on 12 September 1975 in the United Kingdom by Harvest Records and a \
day later in the United States by Columbia Records, their first American \
release for the label...', 9.78, 2);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('Animals [CD]', 
        'Tenth studio album by English rock band Pink Floyd. It was first \
released on 23 January 1977 by Harvest Records in the United Kingdom and by \
Columbia Records in the United States...', 6.99, 2);

INSERT INTO Product(name, description, price, categoryId) 
    VALUES ('The Wall [Vinyl]', 
        'Eleventh studio album by English rock band Pink Floyd. It was \
released as a double album on 30 November 1979 in the United Kingdom by \
Harvest Records and in the United States by Columbia Records....', 9.78, 2);



