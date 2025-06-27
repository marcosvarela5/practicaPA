-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "paproject" database.
-- -----------------------------------------------------------------------------

INSERT INTO Province (provinceName) VALUES
  ('A Coruña'),
  ('Pontevedra');

INSERT INTO TrialType (trialTypeName) VALUES
   ('Running'),
   ('Ciclismo');

INSERT INTO Trial (trialName, trialDescription, dateTime, inscriptionPrice, maxParticipants, place, trialTypeId,
                   provinceId) VALUES
    ('Prueba 1', 'Esta es la primera prueba deportiva', '2023-07-01 17:00:00', 4.99, 500, 'Elviña', 1, 1);

INSERT INTO Trial (trialName, trialDescription, dateTime, inscriptionPrice, maxParticipants, place, trialTypeId,
                   provinceId) VALUES
    ('Prueba 2', 'Esta es la segunda prueba deportiva', '2025-01-01 18:30:00', 9.95, 100, 'Elviña', 2, 1);

INSERT INTO Trial (trialName, trialDescription, dateTime, inscriptionPrice, maxParticipants, place, trialTypeId,
                   provinceId) VALUES
    ('Prueba 3', 'Esta es la tercera prueba deportiva', '2025-02-01 20:00:00', 14.90, 300, 'Tomeza', 1, 2);

INSERT INTO Trial (trialName, trialDescription, dateTime, inscriptionPrice, maxParticipants, place, trialTypeId,
                   provinceId) VALUES
    ('Prueba 4', 'Esta es la cuarta prueba deportiva', '2025-03-01 20:00:00', 14.99, 2, 'Tomeza', 2, 2);

INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
    ('employee', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'Emp', 'Leado', 'empleado@yahoo.es', 1);

INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
    ('participant1', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'Participant', 'Uno', 'uno@yahoo.es', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
    ('participant2', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'Participant', 'Dos', 'dos@yahoo.es', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
    ('participant3', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'Participant', 'Tres', 'tres@yahoo.es', 0);

--Tests E2E:
INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
    ('testparticipant1', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'TestParticipant1', 'Uno', 'test_uno@yahoo.es', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
    ('testparticipant2', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'TestParticipant2', 'Dos', 'test_dos@yahoo.es', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role) VALUES
    ('testemployee', '$2a$10$.YPXU.Uoq3InMUD0iPo9M.ir9sMuAfo9zojaqtyp55ot/rDN.mDCO', 'TestEmp', 'Leado', 'test_empleado@yahoo.es', 1);

INSERT INTO Trial (trialName, trialDescription, dateTime, inscriptionPrice, maxParticipants, place, trialTypeId,
                   provinceId, numParticipants) VALUES
    ('Sporting Event Test', 'Esta es la primera prueba deportiva', '2030-07-01 17:00:00', 4.99, 10, 'Elviña', 1, 1, 1);

INSERT INTO Inscription (userid, trialid, number, cardNumber, numberDelivered, score, scored, dateTime) VALUES
    (6, 5, 1, '1111222233334444', 0, 0, 0, '2024-03-06 20:55:00');
