-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa-project" database.
-------------------------------------------------------------------------------

// password = secret
INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('admin', '$2a$10$G1YMhG.SIKtbef.Kr172p.LcSHFTze1x.L6wY.xDhDfiRg6yr10U.', 'admin', '(admin)', 'admin@udc.es', 1),
       ('hotel', '$2a$10$G1YMhG.SIKtbef.Kr172p.LcSHFTze1x.L6wY.xDhDfiRg6yr10U.', 'hotel', '(hotel)', 'hotel@udc.es', 2),
       ('manager', '$2a$10$G1YMhG.SIKtbef.Kr172p.LcSHFTze1x.L6wY.xDhDfiRg6yr10U.', 'manager', '(manager)', 'manager@udc.es', 3);
       
       
 INSERT INTO Hotel (name, manager, address, description, phonenumber)
 VALUES ('NH Finisterre', 'Pep Guardiola', 'C/ Viñas 22', 'Hotel 5 estrellas', '981326373'),
 	('All Stars','Mike Knight','C/ Pisuerga 3','Hotel 3 Estrellas con servicio de catering','647389269'),
 	('Rock Star','Pepe Botella','C/ Mike October 3','Hotel 2 Estrellas con buena ubicacion','0948596738');
