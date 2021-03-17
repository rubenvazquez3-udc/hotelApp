-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa-project" database.
-------------------------------------------------------------------------------

// password = secret
INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('admin', '$2a$10$G1YMhG.SIKtbef.Kr172p.LcSHFTze1x.L6wY.xDhDfiRg6yr10U.', 'admin', '(admin)', 'admin@udc.es', 1),
       ('hotel', '$2a$10$G1YMhG.SIKtbef.Kr172p.LcSHFTze1x.L6wY.xDhDfiRg6yr10U.', 'hotel', '(hotel)', 'hotel@udc.es', 2),
       ('manager', '$2a$10$G1YMhG.SIKtbef.Kr172p.LcSHFTze1x.L6wY.xDhDfiRg6yr10U.', 'manager', '(manager)', 'manager@udc.es', 3);