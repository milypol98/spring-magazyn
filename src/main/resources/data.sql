
INSERT INTO place (id, address_id, latitude, longitude, description, name) VALUES
                                                                               (1, 1, 52.1234, 19.1234, 'Główne wejście na farmę wiatrową A',          'Wejście A'),
                                                                               (2, 2, 52.1250, 19.1300, 'Teren serwisowy turbiny nr 1',               'Serwis T1'),
                                                                               (3, 3, 52.1275, 19.1350, 'Stacja diagnostyczna czujników wibracji',    'Diagnostyka'),
                                                                               (4, 1, 52.1300, 19.1400, 'Plac montażowy elementów wieży',            'Montaż Wieży'),
                                                                               (5, 2, 52.1325, 19.1450, 'Magazyn części zamiennych',                  'Magazyn B'),
                                                                               (6, 3, 52.1350, 19.1500, 'Strefa testowa systemu chłodzenia gondoli',  'Testy Chłodzenia'),
                                                                               (7, 1, 52.1375, 19.1550, 'Obszar wymiany łożysk i tulei',             'Wymiana Łożysk'),
                                                                               (8, 2, 52.1400, 19.1600, 'Stacja uzupełniania smarów i olejów',       'Smary i Oleje'),
                                                                               (9, 3, 52.1425, 19.1650, 'Sekcja montażowa generatora',                'Mont. Generatora'),
                                                                               (10,1, 52.1450, 19.1700, 'Punkt kontroli oświetlenia awaryjnego',      'Kontrola Oświetlenia');

INSERT INTO cart (id, description, name) VALUES
                                             (1, 'Przygotowanie części do dostawy na farmę A',         'Koszyk Dostawy A'),
                                             (2, 'Zestaw narzędzi serwisowych dla T1',                  'Koszyk Serwis T1'),
                                             (3, 'Materiały do testów systemu chłodzenia',              'Koszyk Testów Chłodzenia'),
                                             (4, 'Części zapasowe do generatorów',                      'Koszyk Generator'),
                                             (5, 'Akcesoria do inspekcji czujników wibracji',           'Koszyk Diagnostyka');


INSERT INTO stock_position (id, min_stock, description, name) VALUES
                                                                  (7, 20, 'Zestawy narzędzi serwisowych', 'A07'),
                                                                  (22, 10, 'Zestawy do diagnostyki i pomiarów', 'C02');

INSERT INTO car (id, insured, review, year, brand, color, description, model, name, registration) VALUES
                                                                                                      (1, '2024-01-15', '2024-06-01', '2018-01-01', 'Ford',     'Biały', 'Van serwisowy do turbin',           'Transit',      'Serwis Van 1', 'WPL12345'),
                                                                                                      (2, '2024-02-20', '2024-05-20', '2019-01-01', 'Mercedes', 'Niebieski', 'Ciężarówka do transportu części',    'Sprinter',     'Transporter 2','WPL23456'),
                                                                                                      (3, '2024-03-10', '2024-07-10', '2020-01-01', 'Iveco',    'Szary',  'Pojazd do montażu wież',             'Daily',        'Wieża Truck 3','WPL34567'),
                                                                                                      (4, '2024-04-05', '2024-04-30', '2017-01-01', 'MAN',      'Czarny', 'Ciężarówka chłodnicza',              'TGL',          'Chłodnia 4',   'WPL45678'),
                                                                                                      (5, '2024-05-01', '2024-08-01', '2021-01-01', 'Volvo',    'Biały',  'Pojazd diagnostyczny',               'FL',           'Diagnostyka 5','WPL56789'),
                                                                                                      (6, '2024-06-12', '2024-09-12', '2016-01-01', 'Renault',  'Zielony','Bus serwisowy dla zespołu',         'Master',       'Bus Serwis 6', 'WPL67890'),
                                                                                                      (7, '2024-07-08', '2024-10-08', '2015-01-01', 'Peugeot',  'Biały',  'Pojazd do wymiany łopat',            'Boxer',        'Łopaty 7',     'WPL78901'),
                                                                                                      (8, '2024-08-15', '2024-11-15', '2022-01-01', 'Citroën',  'Niebieski','Bus transportowy narzędzi',         'Jumper',       'Narzędzia 8',  'WPL89012'),
                                                                                                      (9, '2024-09-20', '2025-01-20', '2014-01-01', 'Fiat',     'Szary',  'Pojazd do pomiarów wibracji',        'Ducato',       'Wibracje 9',   'WPL90123'),
                                                                                                      (10,'2024-10-30', '2025-02-28', '2023-01-01', 'Volkswagen','Biały', 'Bus do montażu paneli sterujących',  'Crafter',      'Panele 10',    'WPL01234');

INSERT INTO car_cost (id, car_id, cost, date_from, date_to, description) VALUES
                                                                             (1,  1,  500.00, '2024-01-10', '2024-01-15', 'Przegląd układu hamulcowego'),
                                                                             (2,  1,  750.00, '2024-03-05', '2024-03-10', 'Wymiana oleju i filtrów'),
                                                                             (3,  1, 1200.00, '2024-05-20', '2024-05-25', 'Serwis silnika'),
                                                                             (4,  1,  300.00, '2024-07-15', '2024-07-16', 'Kontrola układu elektrycznego'),
                                                                             (5,  1,  650.00, '2024-09-01', '2024-09-03', 'Wymiana klocków hamulcowych'),

                                                                             (6,  2,  800.00, '2024-02-12', '2024-02-15', 'Przegląd zawieszenia'),
                                                                             (7,  2,  400.00, '2024-06-18', '2024-06-19', 'Naprawa chłodnicy'),
                                                                             (8,  2,  950.00, '2024-10-05', '2024-10-08', 'Serwis klimatyzacji'),

                                                                             (9,  3,  300.00, '2024-01-22', '2024-01-23', 'Kontrola ciśnienia opon'),
                                                                             (10, 3,  780.00, '2024-04-11', '2024-04-14', 'Wymiana akumulatora'),
                                                                             (11, 3, 1020.00, '2024-08-02', '2024-08-06', 'Serwis skrzyni biegów'),

                                                                             (12, 4,  450.00, '2024-03-30', '2024-04-01', 'Przegląd układu wydechowego'),
                                                                             (13, 4,  620.00, '2024-07-20', '2024-07-22', 'Wymiana amortyzatorów'),
                                                                             (14, 4,  380.00, '2024-11-10', '2024-11-11', 'Kontrola poziomu płynów'),

                                                                             (15, 5,  900.00, '2024-02-05', '2024-02-09', 'Serwis układu chłodzenia'),
                                                                             (16, 5,  550.00, '2024-05-15', '2024-05-17', 'Wymiana pasków napędowych'),
                                                                             (17, 5, 1300.00, '2024-08-25', '2024-08-30', 'Przegląd silnika głównego'),
                                                                             (18, 5,  480.00, '2024-10-20', '2024-10-21', 'Kontrola układu hamulcowego'),

                                                                             (19, 6,  720.00, '2024-03-12', '2024-03-14', 'Wymiana oleju przekładniowego'),
                                                                             (20, 6,  360.00, '2024-06-28', '2024-06-29', 'Przegląd układu elektrycznego'),
                                                                             (21, 6,  810.00, '2024-09-15', '2024-09-18', 'Serwis klimatyzacji'),

                                                                             (22, 7,  530.00, '2024-02-20', '2024-02-22', 'Przegląd narzędzi serwisowych'),
                                                                             (23, 7,  670.00, '2024-05-30', '2024-06-01', 'Wymiana zestawów montażowych'),
                                                                             (24, 7,  290.00, '2024-09-05', '2024-09-06', 'Kontrola stanu technicznego'),

                                                                             (25, 8,  410.00, '2024-03-18', '2024-03-19', 'Serwis pompy olejowej'),
                                                                             (26, 8,  580.00, '2024-06-22', '2024-06-24', 'Wymiana filtrów oleju'),
                                                                             (27, 8,  760.00, '2024-10-12', '2024-10-15', 'Przegląd układu hydraulicznego'),

                                                                             (28, 9,  650.00, '2024-04-10', '2024-04-11', 'Przegląd zaworów hydraulicznych'),

                                                                             (29, 10, 830.00, '2024-05-08', '2024-05-10', 'Serwis systemu chłodzenia'),
                                                                             (30, 10, 470.00, '2024-09-28', '2024-09-29', 'Kontrola paneli sterujących');


-- car_product (relacje między samochodami a produktami)

INSERT INTO product (id, min_stock, code, description, name, position) VALUES
                                                                           (1, 10, 'P-A01-001', 'Łopata turbiny model A', 'Łopata A', 'A01'),
                                                                           (2, 10, 'P-A02-001', 'Silnik 12kW do turbiny', 'Silnik 12kW', 'A02'),
                                                                           (3, 10, 'P-A03-001', 'Łożysko kulkowe 6205', 'Łożysko 6205', 'A03'),
                                                                           (4, 10, 'P-A04-001', 'Czujnik temperatury PT100', 'Czujnik PT100', 'A04'),
                                                                           (5, 10, 'P-A05-001', 'Stalowa rama wieży', 'Rama wieży', 'A05'),
                                                                           (6, 10, 'P-A06-001', 'Kabel zasilający 3x2.5mm²', 'Kabel 2.5mm²', 'A06'),
                                                                           (8, 10, 'P-A08-001', 'Olej syntetyczny 5L', 'Olej 5L', 'A08'),
                                                                           (9, 10, 'P-A09-001', 'Zawór hydrauliczny 24V', 'Zawór 24V', 'A09'),
                                                                           (10, 10, 'P-A10-001', 'Wentylator chłodzący', 'Wentylator', 'A10'),

                                                                           (11, 10, 'P-B01-001', 'Wirnik zapasowy', 'Wirnik', 'B01'),
                                                                           (12, 10, 'P-B02-001', 'Koło zębate przekładni', 'Koło zębate', 'B02'),
                                                                           (13, 10, 'P-B03-001', 'Czujnik wibracji BOSCH', 'Czujnik BOSCH', 'B03'),
                                                                           (14, 10, 'P-B04-001', 'Lampa awaryjna LED', 'Lampa LED', 'B04'),
                                                                           (15, 10, 'P-B05-001', 'Śruba M12x80', 'Śruba M12', 'B05'),
                                                                           (16, 10, 'P-B06-001', 'Panel operatorski Siemens', 'Panel Siemens', 'B06'),
                                                                           (17, 10, 'P-B07-001', 'Bezpiecznik 32A', 'Bezpiecznik 32A', 'B07'),
                                                                           (18, 10, 'P-B08-001', 'Zawór kulowy pneumatyczny', 'Zawór kulowy', 'B08'),
                                                                           (19, 10, 'P-B09-001', 'Pianka izolacyjna', 'Pianka', 'B09'),
                                                                           (20, 10, 'P-B10-001', 'Moduł komunikacyjny LTE', 'Moduł LTE', 'B10'),

                                                                           (21, 10, 'P-C01-001', 'Adapter 12V-24V', 'Adapter 12V-24V', 'C01'),
                                                                           (23, 10, 'P-C03-001', 'Osłona silnika', 'Osłona', 'C03'),
                                                                           (24, 10, 'P-C04-001', 'Farba antykorozyjna szara', 'Farba szara', 'C04'),
                                                                           (25, 10, 'P-C05-001', 'Akumulator AGM 12V', 'Akumulator AGM', 'C05'),
                                                                           (26, 10, 'P-C06-001', 'Sterownik PLC S7-1200', 'PLC S7', 'C06'),
                                                                           (27, 10, 'P-C07-001', 'Rura stalowa gięta', 'Rura stalowa', 'C07'),
                                                                           (28, 10, 'P-C08-001', 'Czujnik otwarcia', 'Czujnik otwarcia', 'C08'),
                                                                           (29, 10, 'P-C09-001', 'Filtr oleju', 'Filtr oleju', 'C09'),
                                                                           (30, 10, 'P-C10-001', 'Instrukcja montażu', 'Instrukcja montażu', 'C10');


INSERT INTO task (id, place_id, date_from, date_to, description, name, status) VALUES
                                                                                   (1,    1,  '2024-06-01', '2024-06-05', 'Dostawa łopat turbin na farmę A',             'Dostawa łopat',            'TO_BE_PACKED'),
                                                                                   (2,    2,  '2024-06-06', '2024-06-10', 'Transport zestawu narzędzi do serwisu T1',    'Transport narzędzi',        'IN_PROGRESS'),
                                                                                   (3,   3,  '2024-07-01', '2024-07-03', 'Diagnostyka czujników wibracji',               'Diagnostyka sensorów',      'TO_BE_UNPACKED'),
                                                                                   (4,   4,  '2024-07-04', '2024-07-07', 'Montaż paneli sterujących w wieży B',         'Montaż paneli',             'IN_PROGRESS'),
                                                                                   (5,   5,  '2024-08-01', '2024-08-05', 'Uzupełnienie magazynu części zapasowych',      'Magazyn części',            'COMPLETED'),
                                                                                   (6,   6,  '2024-08-06', '2024-08-09', 'Testy systemu chłodzenia gondoli',             'Testy chłodzenia',           'TO_BE_PACKED'),
                                                                                   (7,   7,  '2024-09-01', '2024-09-04', 'Wymiana łożysk i tulei turbin',               'Wymiana łożysk',            'IN_PROGRESS'),
                                                                                   (8,    8,  '2024-09-05', '2024-09-08', 'Dostawa smarów i olejów do stacji Smary i Oleje', 'Dostawa smarów',         'TO_BE_UNPACKED'),
                                                                                   (9,    9,  '2024-10-01', '2024-10-04', 'Instalacja zaworów hydraulicznych',            'Montaż zaworów',            'IN_PROGRESS'),
                                                                                   (10,  10,  '2024-10-05', '2024-10-08', 'Pomiary oświetlenia awaryjnego',               'Kontrola oświetlenia',      'COMPLETED'),
                                                                                   (11,  2,  '2024-11-01', '2024-11-03', 'Serwis przewodów i okablowania',               'Serwis okablowania',        'TO_BE_PACKED'),
                                                                                   (12,   3,  '2024-11-05', '2024-11-07', 'Kalibracja czujników temperatury',             'Kalibracja czujników',      'IN_PROGRESS'),
                                                                                   (13,  4,  '2024-11-10', '2024-11-12', 'Montaż sekcji gondoli',                        'Montaż gondoli',            'TO_BE_UNPACKED'),
                                                                                   (14,   5,  '2024-11-15', '2024-11-18', 'Kontrola stanu konstrukcji wieży',             'Inspekcja wieży',           'IN_PROGRESS'),
                                                                                   (15,   6,  '2024-12-01', '2024-12-04', 'Dostawa filtrów powietrza i oleju',            'Dostawa filtrów',           'TO_BE_PACKED'),
                                                                                   (16,  7,  '2024-12-06', '2024-12-09', 'Przegląd narzędzi serwisowych',                'Przegląd narzędzi',         'COMPLETED'),
                                                                                   (17,  8,  '2024-12-10', '2024-12-12', 'Odnowa oznakowania awaryjnego',               'Oznakowanie awaryjne',      'TO_BE_UNPACKED'),
                                                                                   (18,  9,  '2024-12-15', '2024-12-17', 'Testy systemu komunikacji',                    'Testy komunikacji',         'IN_PROGRESS'),
                                                                                   (19,  10,  '2025-01-05', '2025-01-08', 'Serwis systemu chłodzenia gondoli – kontynuacja', 'Serwis chłodzenia',     'TO_BE_PACKED'),
                                                                                   (20,   1,  '2025-01-10', '2025-01-13', 'Aktualizacja dokumentacji technicznej',        'Aktualizacja dokumentów',   'COMPLETED');
INSERT INTO task_car (car_id, task_id) VALUES
                                           (1,  1),
                                           (2,  2),
                                           (3,  3),
                                           (4,  4),
                                           (5,  5),
                                           (6,  6),
                                           (7,  7),
                                           (8,  8),
                                           (9,  9),
                                           (10, 10),
                                           (1,  11),
                                           (2,  12),
                                           (3,  13),
                                           (4,  14),
                                           (5,  15),
                                           (6,  16),
                                           (7,  17),
                                           (8,  18),
                                           (9,  19),
                                           (10, 20);


INSERT INTO tool (id, price, stock_position_id, code, description, name, status) VALUES
                                                                                     (1,  25.00,  7, 'TL001', 'Zestaw kluczy serwisowych',                  'Klucze serwisowe',    'STOCK'),
                                                                                     (2,  40.00,  7, 'TL002', 'Zestaw nasadek i przedłużeń',                 'Nasadki serwisowe',    'STOCK'),
                                                                                     (3,  15.00,  7, 'TL003', 'Młotek gumowy do montażu paneli',            'Młotek gumowy',        'USE'),
                                                                                     (4,  30.00,  22,'TL004', 'Tester wibracji i temperatury',             'Tester wibracji',      'STOCK'),
                                                                                     (5,  50.00,  22,'TL005', 'Kalibrator czujników temperatury',            'Kalibrator sensorów',  'REPAIR'),
                                                                                     (6,  20.00,  22, 'TL006', 'Zestaw przewodów pomiarowych',               'Przewody pomiarowe',   'USE'),
                                                                                     (7,  35.00,  7, 'TL007', 'Pistolet do smarowania i olejowania',        'Pistolet smarujący',   'STOCK'),
                                                                                     (8,  45.00,  22, 'TL008', 'Manometr do pomiaru ciśnienia oleju',        'Manometr olejowy',     'USE'),
                                                                                     (9,  60.00,  7, 'TL009', 'Multimetr cyfrowy do diagnostyki układów',   'Multimetr',            'STOCK'),
                                                                                     (10, 55.00,  7, 'TL010', 'Zestaw lutowniczy do napraw elektroniki',    'Zestaw lutowniczy',    'REPAIR');

INSERT INTO task_user (task_id, user_id) VALUES
                                             (1,  1),
                                             (2,  2),
                                             (3,  3),
                                             (4,  1),
                                             (5,  2),
                                             (6,  3),
                                             (7,  1),
                                             (8,  2),
                                             (9,  3),
                                             (10, 1),
                                             (11, 2),
                                             (12, 3),
                                             (13, 1),
                                             (14, 2),
                                             (15, 3),
                                             (16, 1),
                                             (17, 2),
                                             (18, 3),
                                             (19, 1),
                                             (20, 2);
INSERT INTO tool_cost (id, cost, tool_id, date_from, date_to, description) VALUES
                                                                               (1,  10.00, 1, '2024-06-01', '2024-06-03', 'Przegląd i czyszczenie kluczy serwisowych'),
                                                                               (2,  12.50, 2, '2024-06-05', '2024-06-06', 'Uzupełnienie brakujących nasadek'),
                                                                               (3,   8.00, 3, '2024-06-10', '2024-06-11', 'Wymiana rękojeści młotka gumowego'),
                                                                               (4,  20.00, 4, '2024-06-15', '2024-06-16', 'Kalibracja testera wibracji'),
                                                                               (5,  18.00, 5, '2024-06-20', '2024-06-21', 'Naprawa modułu kalibratora'),
                                                                               (6,   6.00, 6, '2024-06-25', '2024-06-25', 'Zakup nowych końcówek pomiarowych'),
                                                                               (7,  15.00, 7, '2024-07-01', '2024-07-02', 'Konserwacja pistoletu smarującego'),
                                                                               (8,  17.50, 8, '2024-07-05', '2024-07-06', 'Kalibracja manometru olejowego'),
                                                                               (9,  22.00, 9, '2024-07-10', '2024-07-12', 'Wymiana sondy w multimetrze'),
                                                                               (10, 30.00,10,'2024-07-15', '2024-07-16', 'Naprawa grotu lutownicy');
INSERT INTO worker_qualification (id, user_id, date_from, date_to, description, qualification) VALUES
                                                                                                   (1,  1, '2023-01-01', '2023-12-31', 'Uprawnienia do pracy na wysokości',               'Praca na wysokości'),
                                                                                                   (2,  2, '2022-05-01', '2024-05-01', 'Szkolenie z zakresu elektryki przemysłowej',       'Elektryk przemysłowy'),
                                                                                                   (3,  3, '2023-03-15', '2025-03-15', 'Uprawnienia SEP do 1kV',                           'SEP 1kV'),
                                                                                                   (4,  1, '2024-01-10', '2026-01-10', 'Obsługa dźwigów i podnośników',                   'Operator dźwigu'),
                                                                                                   (5,  2, '2023-07-01', '2025-07-01', 'Szkolenie z zakresu systemów hydraulicznych',      'Technik hydrauliki'),
                                                                                                   (6,  3, '2024-02-01', '2026-02-01', 'Certyfikat konserwatora turbin wiatrowych',        'Konserwator turbin'),
                                                                                                   (7,  1, '2022-09-15', '2024-09-15', 'Szkolenie BHP dla pracowników technicznych',       'BHP techniczny'),
                                                                                                   (8,  2, '2023-11-01', '2025-11-01', 'Szkolenie z obsługi narzędzi diagnostycznych',     'Diagnostyk'),
                                                                                                   (9,  3, '2024-04-01', '2026-04-01', 'Kurs lutowania i napraw elektroniki przemysłowej', 'Technik lutowania'),
                                                                                                   (10, 1, '2023-08-20', '2025-08-20', 'Szkolenie z obsługi platform roboczych',           'Operator platformy');

INSERT INTO task_raport (date, date_come_in, description, hour, task_id) VALUES
-- TASK 1
('2025-07-01 08:00:00', '2025-07-01 07:50:00', 'Opis zadania 1 - raport 1', 2, 1),
('2025-07-02 09:00:00', '2025-07-02 08:45:00', 'Opis zadania 1 - raport 2', 3, 1),
('2025-07-03 10:00:00', '2025-07-03 09:40:00', 'Opis zadania 1 - raport 3', 4, 1),
('2025-07-04 11:00:00', '2025-07-04 10:55:00', 'Opis zadania 1 - raport 4', 1, 1),
('2025-07-05 12:00:00', '2025-07-05 11:30:00', 'Opis zadania 1 - raport 5', 2, 1),

-- TASK 2
('2025-07-01 13:00:00', '2025-07-01 12:50:00', 'Opis zadania 2 - raport 1', 2, 2),
('2025-07-02 14:00:00', '2025-07-02 13:45:00', 'Opis zadania 2 - raport 2', 3, 2),
('2025-07-03 15:00:00', '2025-07-03 14:40:00', 'Opis zadania 2 - raport 3', 4, 2),
('2025-07-04 16:00:00', '2025-07-04 15:55:00', 'Opis zadania 2 - raport 4', 1, 2),
('2025-07-05 17:00:00', '2025-07-05 16:30:00', 'Opis zadania 2 - raport 5', 2, 2),

-- TASK 3
('2025-07-01 09:30:00', '2025-07-01 09:00:00', 'Opis zadania 3 - raport 1', 2, 3),
('2025-07-02 10:30:00', '2025-07-02 10:00:00', 'Opis zadania 3 - raport 2', 2, 3),
('2025-07-03 11:30:00', '2025-07-03 11:00:00', 'Opis zadania 3 - raport 3', 2, 3),
('2025-07-04 12:30:00', '2025-07-04 12:00:00', 'Opis zadania 3 - raport 4', 2, 3),
('2025-07-05 13:30:00', '2025-07-05 13:00:00', 'Opis zadania 3 - raport 5', 2, 3),

-- TASK 4
('2025-07-01 14:15:00', '2025-07-01 14:00:00', 'Opis zadania 4 - raport 1', 1, 4),
('2025-07-02 15:15:00', '2025-07-02 15:00:00', 'Opis zadania 4 - raport 2', 2, 4),
('2025-07-03 16:15:00', '2025-07-03 16:00:00', 'Opis zadania 4 - raport 3', 3, 4),
('2025-07-04 17:15:00', '2025-07-04 17:00:00', 'Opis zadania 4 - raport 4', 4, 4),
('2025-07-05 18:15:00', '2025-07-05 18:00:00', 'Opis zadania 4 - raport 5', 1, 4),

-- TASK 5
('2025-07-01 08:45:00', '2025-07-01 08:30:00', 'Opis zadania 5 - raport 1', 2, 5),
('2025-07-02 09:45:00', '2025-07-02 09:30:00', 'Opis zadania 5 - raport 2', 3, 5),
('2025-07-03 10:45:00', '2025-07-03 10:30:00', 'Opis zadania 5 - raport 3', 4, 5),
('2025-07-04 11:45:00', '2025-07-04 11:30:00', 'Opis zadania 5 - raport 4', 1, 5),
('2025-07-05 12:45:00', '2025-07-05 12:30:00', 'Opis zadania 5 - raport 5', 2, 5);

INSERT INTO car_product (car_id, product_id) VALUES
                                                 (1, 1), (1, 2), (1, 3),
                                                 (2, 4), (2, 5), (2, 6),
                                                 (3, 28), (3, 8), (3, 9),
                                                 (4, 10), (4, 11), (4, 12),
                                                 (5, 13), (5, 14), (5, 15);

-- car_tool (relacje między samochodami a narzędziami)
INSERT INTO car_tool (car_id, tool_id) VALUES
                                           (1, 1), (1, 2),
                                           (2, 3), (2, 4),
                                           (3, 5), (3, 6),
                                           (4, 7), (4, 8),
                                           (5, 9), (5, 10);
INSERT INTO cart_item (cart_id, id, product_id, quantity, stock_position_id) VALUES
                                                                                 (1, 1, 1, 1, NULL),
                                                                                 (2, 2, 2, 2, NULL),
                                                                                 (3, 3, 3, 3, NULL),
                                                                                 (4, 4, 4, 4, NULL),
                                                                                 (5, 5, 5, 5, NULL),
                                                                                 (1, 6, 6, 6, NULL),
                                                                                 (2, 7, NULL, 7, 7),
                                                                                 (3, 8, 8, 8, NULL),
                                                                                 (4, 9, 9, 9, NULL),
                                                                                 (5, 10, 10, 10, NULL),
                                                                                 (1, 11, 11, 11, NULL),
                                                                                 (2, 12, 12, 12, NULL),
                                                                                 (3, 13, 13, 13, NULL),
                                                                                 (4, 14, 14, 14, NULL),
                                                                                 (5, 15, 15, 15, NULL),
                                                                                 (5, 16, 16, 16, NULL),
                                                                                 (4, 17, 17, 17, NULL),
                                                                                 (3, 18, 18, 18, NULL),
                                                                                 (2, 19, 19, 19, NULL),
                                                                                 (1, 20, 20, 20, NULL),
                                                                                 (1, 21, 21, 21, NULL),
                                                                                 (2, 22, NULL, 22, 22),
                                                                                 (3, 23, 23, 23, NULL),
                                                                                 (4, 24, 24, 24, NULL),
                                                                                 (5, 25, 25, 25, NULL),
                                                                                 (5, 26, 26, 26, NULL),
                                                                                 (4, 27, 27, 27, NULL),
                                                                                 (3, 28, 28, 28, NULL),
                                                                                 (2, 29, 29, 29, NULL),
                                                                                 (1, 30, 30, 30, NULL);

INSERT INTO product_event (
    id, product_id, quantity, timestamp, comment, location_code,
    location_type, type, unit_price
) VALUES
      (1, 1, 5, '2025-07-01 08:00:00', 'Pierwsza dostawa', 1, 'WAREHOUSE', 'DELIVERY', 120.50),
      (2, 2, 3, '2025-07-01 09:00:00', 'Zwrot fabryczny', 2, 'WAREHOUSE', 'RETURN', 45.00),
      (3, 3, 10, '2025-07-01 10:00:00', 'Transfer do serwisu', 3, 'CAR', 'TRANSFER', 15.75),
      (4, 4, 2, '2025-07-01 11:00:00', 'Zużycie w produkcji', 4, 'JOB', 'CONSUMPTION', 89.90),
      (5, 5, 7, '2025-07-01 12:00:00', 'Nowa rama wieży', 5, 'WAREHOUSE', 'DELIVERY', 250.00),
      (6, 1, 12, '2025-07-02 08:30:00', 'Kable zainstalowane', 6, 'JOB', 'CONSUMPTION', 110.00),
      (7, 2, 20, '2025-07-02 09:30:00', 'Narzędzia serwisowe', 7, 'WAREHOUSE', 'DELIVERY', 55.25),
      (8, 3, 6, '2025-07-02 10:30:00', 'Uzupełnienie smarów', 8, 'WAREHOUSE', 'DELIVERY', 12.40),
      (9, 4, 4, '2025-07-02 11:30:00', 'Hydraulika sprawdzona', 9, 'CAR', 'TRANSFER', 95.60),
      (10, 5, 8, '2025-07-02 12:30:00', 'Wentylator serwisowany', 10, 'WAREHOUSE', 'RETURN', 180.00),

      (11, 1, 5, '2025-07-03 08:15:00', 'Wirnik dostarczony', 11, 'WAREHOUSE', 'DELIVERY', 130.75),
      (12, 2, 3, '2025-07-03 09:15:00', 'Koło zębate zwrócone', 12, 'WAREHOUSE', 'RETURN', 49.90),
      (13, 3, 15, '2025-07-03 10:15:00', 'Czujnik wysłany', 13, 'WAREHOUSE', 'DELIVERY', 18.00),
      (14, 4, 2, '2025-07-03 11:15:00', 'Lampa zamontowana', 14, 'JOB', 'CONSUMPTION', 65.00),
      (15, 5, 10, '2025-07-03 12:15:00', 'Śruby M12 użyte', 15, 'WAREHOUSE', 'DELIVERY', 3.25),
      (16, 1, 6, '2025-07-04 08:05:00', 'Panel zwrócony', 16, 'WAREHOUSE', 'RETURN', 150.00),
      (17, 2, 8, '2025-07-04 09:05:00', 'Bezpieczniki nowe', 17, 'WAREHOUSE', 'DELIVERY', 7.50),
      (18, 3, 12, '2025-07-04 10:05:00', 'Zawory zamontowane', 18, 'JOB', 'CONSUMPTION', 22.40),
      (19, 4, 7, '2025-07-04 11:05:00', 'Pianka zużyta', 19, 'WAREHOUSE', 'DELIVERY', 14.90),
      (20, 5, 10, '2025-07-04 12:05:00', 'Moduł testowy', 20, 'CAR', 'TRANSFER', 220.00),

      (21, 1, 5, '2025-07-05 08:45:00', 'Adapter dostarczony', 21, 'WAREHOUSE', 'DELIVERY', 99.99),
      (22, 2, 10, '2025-07-05 09:45:00', 'Zestaw diagnostyczny', 22, 'WAREHOUSE', 'DELIVERY', 60.00),
      (23, 3, 6, '2025-07-05 10:45:00', 'Osłona zamontowana', 23, 'WAREHOUSE', 'DELIVERY', 13.90),
      (24, 4, 10, '2025-07-05 11:45:00', 'Farba użyta', 24, 'WAREHOUSE', 'RETURN', 9.50),
      (25, 5, 8, '2025-07-05 12:45:00', 'Akumulator zamontowany', 25, 'CAR', 'TRANSFER', 210.00),
      (26, 1, 12, '2025-07-06 08:10:00', 'PLC zainstalowany', 26, 'JOB', 'CONSUMPTION', 185.00),
      (27, 2, 10, '2025-07-06 09:10:00', 'Rury dostarczone', 27, 'WAREHOUSE', 'DELIVERY', 42.75),
      (28, 3, 7, '2025-07-06 10:10:00', 'Czujnik montowany', 28, 'WAREHOUSE', 'DELIVERY', 16.90),
      (29, 4, 9, '2025-07-06 11:10:00', 'Filtr wymieniony', 29, 'JOB', 'CONSUMPTION', 27.30),
      (30, 5, 5, '2025-07-06 12:10:00', 'Instrukcje przekazane', 30, 'WAREHOUSE', 'DELIVERY', 5.00),

      (31, 1, 4, '2025-07-07 08:20:00', 'Dodatkowa dostawa', 1, 'WAREHOUSE', 'DELIVERY', 125.00),
      (32, 2, 2, '2025-07-07 09:20:00', 'Zwrot części', 2, 'WAREHOUSE', 'RETURN', 39.00),
      (33, 3, 6, '2025-07-07 10:20:00', 'Transfer łożysk', 3, 'CAR', 'TRANSFER', 14.80),
      (34, 4, 1, '2025-07-07 11:20:00', 'Zużycie czujników', 4, 'JOB', 'CONSUMPTION', 58.00),
      (35, 5, 9, '2025-07-07 12:20:00', 'Rama zwrócona', 5, 'WAREHOUSE', 'RETURN', 199.90),
      (36, 1, 11, '2025-07-08 08:35:00', 'Kable użyte', 6, 'JOB', 'CONSUMPTION', 115.00),
      (37, 2, 15, '2025-07-08 09:35:00', 'Narzędzia u klienta', 7, 'WAREHOUSE', 'DELIVERY', 51.75),
      (38, 3, 4, '2025-07-08 10:35:00', 'Oleje uzupełnione', 8, 'WAREHOUSE', 'DELIVERY', 11.30),
      (39, 4, 5, '2025-07-08 11:35:00', 'Hydraulika sprawdzona', 9, 'CAR', 'TRANSFER', 93.25),
      (40, 5, 7, '2025-07-08 12:35:00', 'Wentylator zwrócony', 10, 'WAREHOUSE', 'RETURN', 175.00),

      (41, 1, 8, '2025-07-09 08:50:00', 'Dostawa wirników', 11, 'WAREHOUSE', 'DELIVERY', 129.50),
      (42, 2, 3, '2025-07-09 09:50:00', 'Zwrot kół zębatych', 12, 'WAREHOUSE', 'RETURN', 44.00),
      (43, 3, 12, '2025-07-09 10:50:00', 'Czujniki wibracji zainstal.', 13, 'WAREHOUSE', 'DELIVERY', 17.80),
      (44, 4, 2, '2025-07-09 11:50:00', 'Instalacja lamp', 14, 'JOB', 'CONSUMPTION', 67.00),
      (45, 5, 10, '2025-07-09 12:50:00', 'Śruby M12 montowane', 15, 'WAREHOUSE', 'DELIVERY', 3.00),
      (46, 1, 6, '2025-07-10 08:00:00', 'Panel Siemens dostarczony', 16, 'WAREHOUSE', 'RETURN', 160.00),
      (47, 2, 9, '2025-07-10 09:00:00', 'Bezpieczniki uzupełnione', 17, 'WAREHOUSE', 'DELIVERY', 7.90),
      (48, 3, 11, '2025-07-10 10:00:00', 'Zawory pneumatyczne', 18, 'JOB', 'CONSUMPTION', 20.50),
      (49, 4, 7, '2025-07-10 11:00:00', 'Materiały izolacyjne', 19, 'WAREHOUSE', 'DELIVERY', 13.75),
      (50, 5, 10, '2025-07-10 12:00:00', 'Moduł komunikacyjny', 20, 'CAR', 'TRANSFER', 230.00);
