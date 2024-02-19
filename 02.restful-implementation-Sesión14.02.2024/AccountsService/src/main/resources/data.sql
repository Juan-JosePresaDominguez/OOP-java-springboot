INSERT INTO accounts (type, OPENING_DATE, balance, OWNER_ID) VALUES
('Cuenta Corriente', '2024-02-13', 100000, 1L),
('Cuenta Ahorro', '2024-01-01', 200000, 2L),
('Cuenta Corriente', '2023-12-25', 300000, 3L),
('Cuenta Ahorro', '1983-03-25', 400000, 4L),
('Cuenta Corriente', '2022-06-24', 500000, 5L);

-- NOTAS:
-- + Si no le indicamos el id, lo genera automáticamente la BB.DD. H2 en función del @Id de la entidad.
-- + Hibernate transforma los nombres de entidades y propiedades a "snake_case" SIEMPRE.
--   Ejemplo: 'openingDate' se representará en las tablas como 'opening_date'

-- + DUDA openingDate y ownerId en BB.DD. H2 llevan un guion bajo '_'.
--   Podemos poner estos campos como opening_Date/owner_Id ó OPENING_DATE/OWNER_ID
