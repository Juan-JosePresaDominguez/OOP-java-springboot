INSERT INTO `accounts` (`type`, `opening_date`, `balance`, `owner_id`) VALUES
('Cuenta Corriente', '2024-02-13', 100000, 1),
('Cuenta Ahorro', '2024-01-01', 200000, 2),
('Cuenta Corriente', '2023-12-25', 300000, 3),
('Cuenta Ahorro', '1983-03-25', 400000, 4),
('Cuenta Corriente', '2022-06-24', 500000, 5);

-- COMENTARIOS:
-- + Si no le indicamos el id, lo genera automáticamente la BB.DD. MySQL
-- + DUDA openingDate y ownerId en BB.DD. MySQL llevan un guion bajo '_'.
--   Podemos poner estos campos como opening_date/owner_id
