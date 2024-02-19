INSERT INTO `accounts` (`type`, `opening_date`, `balance`, `owner_id`) VALUES
('Cuenta Corriente', '2024-02-13', 100000, 1),
('Cuenta Ahorro', '2024-01-01', 200000, 2),
('Cuenta Corriente', '2023-12-25', 300000, 3),
('Cuenta Ahorro', '2021-04-07', 400000, 4),
('Cuenta Corriente', '2022-06-24', 500000, 5);

-- NOTAS:
-- + Si no le indicamos el id, lo genera automáticamente la BB.DD. MySQL en función del @Id de la entidad.
-- + Hibernate transforma los nombres de entidades y propiedades a "snake_case" SIEMPRE.
--   Ejemplo: 'openingDate' se representará en las tablas como 'opening_date'