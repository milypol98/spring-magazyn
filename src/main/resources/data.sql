-- ---------------------------------------------------
-- 1. TABELA user
-- ---------------------------------------------------
INSERT INTO `user` (id, phone, address, description, email, firstname, last_name, password, role) VALUES
                                                                                                      (1, 48500100200, 'ul. Przykładowa 10, 41-300 Dąbrowa Górnicza', 'Administrator systemu', 'admin@example.com', 'Jan', 'Kowalski', 'haslo123', 'admin'),
                                                                                                      (2, 48500100300, 'ul. Testowa 5, 41-300 Dąbrowa Górnicza', 'Magazynier', 'magazynier@example.com', 'Anna', 'Nowak', 'haslo456', 'user'),
                                                                                                      (3, 48500100400, 'ul. Statyczna 7, 41-300 Dąbrowa Górnicza', 'Kierowca serwisowy', 'kierowca@example.com', 'Piotr', 'Wiśniewski', 'haslo789', 'user');

-- ---------------------------------------------------
-- 2. TABELA place
-- ---------------------------------------------------
INSERT INTO `place` (id, address, description, name) VALUES
                                                         (1, 'ul. Energetyczna 1, 41-300 Dąbrowa Górnicza', 'Główna siedziba magazynu', 'Magazyn Centralny'),
                                                         (2, 'ul. Serwisowa 2, 41-300 Dąbrowa Górnicza', 'Warsztat serwisowy', 'Warsztat'),
                                                         (3, 'ul. Biurowa 3, 41-300 Dąbrowa Górnicza', 'Biuro firmy', 'Biuro');

-- ---------------------------------------------------
-- 3. TABELA product
-- ---------------------------------------------------
INSERT INTO `product` (id, price, code, description, name, quantity) VALUES
                                                                         (1, 250.00, 'P001', 'Filtr powietrza do turbin', 'Filtr Turbinowy', '50'),
                                                                         (2, 1200.00, 'P002', 'Regulator prędkości obrotowej', 'Regulator RPM', '20'),
                                                                         (3, 75.50,  'P003', 'Zestaw śrub i nakrętek (100 szt.)', 'Zestaw Śrub', '100'),
                                                                         (4, 5000.00, 'P004', 'Silnik elektryczny do pompy', 'Silnik Pompy', '5');

-- ---------------------------------------------------
-- 4. TABELA stock_position
-- ---------------------------------------------------
INSERT INTO `stock_position` (id, description, name) VALUES
                                                         (1, 'Główna półka magazynowa', 'Półka A1'),
                                                         (2, 'Druga półka magazynowa', 'Półka B2'),
                                                         (3, 'Regał z częściami elektronicznymi', 'Regał C3');

-- ---------------------------------------------------
-- 5. TABELA product_cost
-- ---------------------------------------------------
INSERT INTO `product_cost` (id, product_id, cost, date_from, date_to, description) VALUES
                                                                                       (1, 1, 200.00, '2024-01-01 00:00:00.000000', '2024-12-31 23:59:59.000000', 'Cena podstawowa 2024'),
                                                                                       (2, 2, 1100.00, '2024-01-01 00:00:00.000000', '2024-12-31 23:59:59.000000', 'Cena podstawowa 2024'),
                                                                                       (3, 3, 60.00,  '2024-01-01 00:00:00.000000', '2024-12-31 23:59:59.000000', 'Cena podstawowa 2024'),
                                                                                       (4, 4, 4500.00, '2024-01-01 00:00:00.000000', '2024-12-31 23:59:59.000000', 'Cena podstawowa 2024');

-- ---------------------------------------------------
-- 6. TABELA product_history
-- ---------------------------------------------------
INSERT INTO `product_history` (id, place_id, product_id, date_from, date_to, description) VALUES
                                                                                              (1, 1, 1, '2024-02-01 00:00:00.000000', '2024-02-15 23:59:59.000000', 'Przekazano do magazynu centralnego'),
                                                                                              (2, 2, 2, '2024-03-01 00:00:00.000000', '2024-03-10 23:59:59.000000', 'Wysłano do warsztatu serwisowego'),
                                                                                              (3, 1, 3, '2024-04-01 00:00:00.000000', '2024-04-20 23:59:59.000000', 'Dostarczono zestaw śrub do magazynu centralnego'),
                                                                                              (4, 2, 4, '2024-05-01 00:00:00.000000', '2024-05-05 23:59:59.000000', 'Przekazano silniki do warsztatu');

-- ---------------------------------------------------
-- 7. TABELA stock_position_product
-- ---------------------------------------------------
INSERT INTO `stock_position_product` (product_id, stock_position_id) VALUES
                                                                         (1, 1),  -- Filtr Turbinowy na półce A1
                                                                         (2, 1),  -- Regulator RPM na półce A1
                                                                         (3, 2),  -- Zestaw Śrub na półce B2
                                                                         (4, 3);  -- Silnik Pompy na regale C3

-- ---------------------------------------------------
-- 8. TABELA cart
-- ---------------------------------------------------
INSERT INTO `cart` (id, place_id, description, name) VALUES
                                                         (1, 1, 'Koszyk części podstawowych', 'Koszyk A'),
                                                         (2, 2, 'Koszyk części serwisowych',  'Koszyk B'),
                                                         (3, 3, 'Koszyk biurowy',              'Koszyk C');

-- ---------------------------------------------------
-- 9. TABELA cart_products
-- ---------------------------------------------------
INSERT INTO `cart_products` (cart_id, product_id) VALUES
                                                       (1, 1),  -- W koszyku A: Filtr Turbinowy
                                                       (1, 3),  -- W koszyku A: Zestaw Śrub
                                                       (2, 2),  -- W koszyku B: Regulator RPM
                                                       (2, 4),  -- W koszyku B: Silnik Pompy
                                                       (3, 3);  -- W koszyku C: Zestaw Śrub (biurowo-do dokumentacji)

-- ---------------------------------------------------
-- 10. TABELA car
-- ---------------------------------------------------
INSERT INTO `car` (id, insured, review, year, brand, color, description, model, name, registration) VALUES
                                                                                                        (1, '2025-01-10 00:00:00.000000', '2025-01-15 00:00:00.000000', '2022-06-20 00:00:00.000000',
                                                                                                         'Toyota', 'Czarny', 'Samochód serwisowy', 'Corolla', 'Serwisówka 1', 'DW12345'),
                                                                                                        (2, '2025-02-05 00:00:00.000000', '2025-02-10 00:00:00.000000', '2021-03-12 00:00:00.000000',
                                                                                                         'Ford', 'Biały', 'Bus do przewozu części', 'Transit', 'Bus Części', 'DA54321');

-- ---------------------------------------------------
-- 11. TABELA car_cost
-- ---------------------------------------------------
INSERT INTO `car_cost` (id, car_id, cost, user_id, date_from, date_to, description) VALUES
                                                                                        (1, 1, 500.00, 3, '2024-06-01 00:00:00.000000', '2024-06-30 23:59:59.000000', 'Przegląd okresowy'),
                                                                                        (2, 1, 300.00, 3, '2024-07-01 00:00:00.000000', '2024-07-15 23:59:59.000000', 'Wymiana oleju i filtrów'),
                                                                                        (3, 2, 800.00, 3, '2024-08-01 00:00:00.000000', '2024-08-10 23:59:59.000000', 'Naprawa układu hamulcowego');

-- ---------------------------------------------------
-- 12. TABELA car_history
-- ---------------------------------------------------
INSERT INTO `car_history` (id, car_id, latitude, longitude, place_id, user_id, date_from, date_to, description) VALUES
                                                                                                                    (1, 1, 50.2917, 19.0246, 1, 3, '2024-06-01 08:00:00.000000', '2024-06-01 12:00:00.000000', 'Przegląd w magazynie centralnym'),
                                                                                                                    (2, 1, 50.2890, 19.0280, 2, 3, '2024-06-01 13:00:00.000000', '2024-06-01 17:00:00.000000', 'Naprawa w warsztacie'),
                                                                                                                    (3, 2, 50.3000, 19.0400, 2, 3, '2024-08-01 09:00:00.000000', '2024-08-01 15:00:00.000000', 'Interwencja serwisowa');

-- ---------------------------------------------------
-- 13. TABELA car_product
-- ---------------------------------------------------
INSERT INTO `car_product` (car_id, product_id) VALUES
                                                   (1, 3),  -- Car 1 zabiera Zestaw Śrub
                                                   (2, 2),  -- Car 2 zabiera Regulator RPM
                                                   (2, 4);  -- Car 2 zabiera Silnik Pompy

-- ---------------------------------------------------
-- 14. TABELA worker_qualification
-- ---------------------------------------------------
INSERT INTO `worker_qualification` (id, user_id, date_from, date_to, description, qualification) VALUES
                                                                                                     (1, 1, '2023-01-01', '2025-12-31', 'Uprawnienia SEP do 1 kV', 'Elektryk SEP'),
                                                                                                     (2, 2, '2024-02-01', '2026-02-01', 'Certyfikat bezpieczeństwa', 'BHP'),
                                                                                                     (3, 3, '2023-06-01', '2025-06-01', 'Prawo jazdy kat. B', 'Kierowca');
