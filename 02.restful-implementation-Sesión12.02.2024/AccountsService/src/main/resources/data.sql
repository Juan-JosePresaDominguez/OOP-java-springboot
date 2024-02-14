INSERT INTO accounts (type, OPENING_DATE, balance, OWNER_ID) VALUES
('Cuenta Corriente', '2024-02-13T23:59:59.000Z', 100000, 1L),
('Cuenta Ahorro', '2024-01-01T23:59:59.000Z', 200000, 2L),
('Cuenta Corriente', '2023-12-25T23:59:59.000Z', 300000, 3L),
('Cuenta Ahorro', '1983-03-25T23:59:59.000Z', 400000, 4L),
('Cuenta Corriente', '2022-06-24T23:59:59.000Z', 500000, 5L);

-- COMENTARIOS:
-- + Si no le indicamos el id, lo genera automáticamente la BB.DD. H2
-- + DUDA openingDate y ownerId en BB.DD. H2 llevan un guión bajo '_'.
--   Podemos poner estos campos como opening_Date/owner_Id ó OPENING_DATE/OWNER_ID
