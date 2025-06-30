
# INSERT INTO place (id, address_id, latitude, longitude, description, name) VALUES
#                                                                                (1, 1, 52.2297, 21.0122, 'Centrum miasta',   'Pałac Kultury'),
#                                                                                (2, 2, 50.0614, 19.9383, 'Rynek Główny',     'Sukiennice'),
#                                                                                (3, 3, 54.3520, 18.6466, 'Stare miasto',     'Fontanna Neptuna');

INSERT INTO place (id, address_id, latitude, longitude, description, name) VALUES
                                                                               (1, 1, 52.2297, 21.0122, 'Główna farma wiatrowa - stacja sterowania', 'Farma Wiatrowa Warszawa'),
                                                                               (2, 1, 52.2310, 21.0140, 'Turbina wiatrowa nr 1', 'Turbina 1 - Warszawa'),
                                                                               (3, 2, 50.0614, 19.9383, 'Farma wiatrowa w rejonie Krakowa', 'Farma Wiatrowa Kraków'),
                                                                               (4, 2, 50.0620, 19.9400, 'Turbina wiatrowa nr 3', 'Turbina 3 - Kraków'),
                                                                               (5, 3, 54.3520, 18.6466, 'Farma wiatrowa nad morzem', 'Farma Wiatrowa Gdańsk'),
                                                                               (6, 3, 54.3530, 18.6480, 'Turbina wiatrowa nr 5', 'Turbina 5 - Gdańsk'),
                                                                               (7, NULL, 52.2300, 21.0130, 'Strefa serwisowa farmy wiatrowej', 'Serwis Farma Warszawa'),
                                                                               (8, NULL, 50.0600, 19.9370, 'Plac techniczny farmy Kraków', 'Techniczne Centrum Kraków'),
                                                                               (9, NULL, 54.3510, 18.6450, 'Miejsce instalacji nowych turbin', 'Instalacje Gdańsk'),
                                                                               (10, NULL, 52.2280, 21.0150, 'Obszar pomiarowy farmy Warszawa', 'Pomiar Warszawa');

-- 3) cart
# INSERT INTO cart (id, description, name) VALUES
#                                                        (1,  'Koszyk na wydarzenia',      'CartA'),
#                                                        (2,  'Zakupy spożywcze',          'CartB'),
#                                                        (3,  'Wycieczka krajoznawcza',    'CartC');
INSERT INTO cart (id, description, name) VALUES
                                             (1, 'Koszyk na naprawę turbin wiatrowych',           'Koszyk Naprawczy'),
                                             (2, 'Koszyk na części zamienne do farm wiatrowych',  'Koszyk Części'),
                                             (3, 'Koszyk na narzędzia serwisowe',                  'Koszyk Serwisowy'),
                                             (4, 'Koszyk na materiały eksploatacyjne i smary',    'Koszyk Eksploatacji'),
                                             (5, 'Koszyk na inspekcje i pomiary techniczne',       'Koszyk Inspekcyjny');
-- 4) stock_position
# INSERT INTO stock_position (id, description, name) VALUES
#                                                        (1, 'Magazyn centralny',   'Central'),
#                                                        (2, 'Magazyn oraz zapasy', 'Backup'),
#                                                        (3, 'Magazyn tymczasowy',   'Temp');
INSERT INTO stock_position (id, description, name) VALUES
                                                       (1,  'Części do łopat turbiny',                'A01'),
                                                       (2,  'Silniki elektryczne do turbin',          'A02'),
                                                       (3,  'Łożyska i tuleje do turbin',              'A03'),
                                                       (4,  'Panele sterujące i czujniki',             'A04'),
                                                       (5,  'Elementy konstrukcyjne wieży',            'A05'),
                                                       (6,  'Przewody i okablowanie',                   'A06'),
                                                       (7,  'Zestawy narzędzi serwisowych',            'A07'),
                                                       (8,  'Smary i oleje do konserwacji',            'A08'),
                                                       (9,  'Elementy hydrauliczne',                     'A09'),
                                                       (10, 'Systemy chłodzenia i wentylacji',          'A10'),

                                                       (11, 'Części zapasowe generatora',               'B01'),
                                                       (12, 'Elementy mechaniczne przekładni',          'B02'),
                                                       (13, 'Czujniki wibracji i temperatury',          'B03'),
                                                       (14, 'Oświetlenie awaryjne i sygnalizacja',      'B04'),
                                                       (15, 'Zestawy montażowe i śruby',                 'B05'),
                                                       (16, 'Panele kontrolne i panele operatorskie',   'B06'),
                                                       (17, 'Elementy zabezpieczeń elektrycznych',      'B07'),
                                                       (18, 'Zawory i elementy pneumatyczne',            'B08'),
                                                       (19, 'Materiały izolacyjne',                       'B09'),
                                                       (20, 'Systemy komunikacyjne',                       'B10'),

                                                       (21, 'Kable zasilające i adaptery',                'C01'),
                                                       (22, 'Zestawy do diagnostyki i pomiarów',         'C02'),
                                                       (23, 'Elementy obudów i osłon',                    'C03'),
                                                       (24, 'Farby i środki antykorozyjne',               'C04'),
                                                       (25, 'Akumulatory i baterie awaryjne',             'C05'),
                                                       (26, 'Komponenty sterujące oprogramowaniem',       'C06'),
                                                       (27, 'Rury i przewody hydrauliczne',               'C07'),
                                                       (28, 'Elementy systemów bezpieczeństwa',           'C08'),
                                                       (29, 'Filtry powietrza i oleju',                    'C09'),
                                                       (30, 'Instrukcje obsługi i dokumentacja techniczna','C10');
-- 5) product
# INSERT INTO product (id, price, stock_position_id, code,    description,     name) VALUES
#                                                                                        (1, 49.99, 1, 'P100', 'Telefon komórkowy', 'Smartfon'),
#                                                                                        (2, 19.99, 2, 'P200', 'Bezprzewodowe słuchawki', 'Słuchawki'),
#                                                                                        (3,  5.49, 3, 'P300', 'Kabel USB-C', 'Kabel');
INSERT INTO product (id, price, stock_position_id, code,    description,                                               name) VALUES
                                                                                                                                 -- A01: 1 product
                                                                                                                                 (1,  2999.00, 1, 'WP001', 'Łopata turbiny wiatrowej – model L1000, materiał kompozytowy', 'Łopata L1000'),

                                                                                                                                 -- A02: 5 products
                                                                                                                                 (2,   750.00, 2, 'WP002', 'Silnik generatora prądu zmiennego – 1500 kW',           'Generator 1500 kW'),
                                                                                                                                 (3,   780.00, 2, 'WP003', 'Silnik główny turbiny – model SG-2000',              'Silnik SG-2000'),
                                                                                                                                 (4,   720.00, 2, 'WP004', 'Silnik pomocniczy do pozycjonowania łopat',         'Silnik pomocniczy'),
                                                                                                                                 (5,   790.00, 2, 'WP005', 'Silnik obrotu gondoli – 360° obrót',               'Silnik gondoli'),
                                                                                                                                 (6,   810.00, 2, 'WP006', 'Silnik chłodzenia układu elektrycznego',            'Silnik chłodzenia'),

                                                                                                                                 -- A03: 4 products
                                                                                                                                 (7,   120.00, 3, 'WP007', 'Łożysko kulkowe do wału głównego – średnica 200 mm', 'Łożysko 200 mm'),
                                                                                                                                 (8,   110.00, 3, 'WP008', 'Łożysko wałeczkowe do przekładni',                  'Łożysko przekładni'),
                                                                                                                                 (9,   130.00, 3, 'WP009', 'Tuleja dystansowa do wału napędowego',             'Tuleja dystansowa'),
                                                                                                                                 (10,  115.00, 3, 'WP010', 'Uszczelniacz wałka głównego turbiny',             'Uszczelniacz wałka'),

                                                                                                                                 -- A04: 4 products
                                                                                                                                 (11,  450.00, 4, 'WP011', 'Czujnik wibracji – model VIB-X100',               'Czujnik VIB-X100'),
                                                                                                                                 (12,  480.00, 4, 'WP012', 'Czujnik temperatury silnika – IP67',             'Czujnik temp. IP67'),
                                                                                                                                 (13,  430.00, 4, 'WP013', 'Moduł sterujący pracą turbin – PLC WindCtrl-5',  'PLC WindCtrl-5'),
                                                                                                                                 (14,  470.00, 4, 'WP014', 'Panel operatorski HMI – 7" kolorowy',            'HMI 7"'),

                                                                                                                                 -- A05: 3 products
                                                                                                                                 (15, 1250.00, 5, 'WP015', 'Sekcja dolna wieży – stal S355, wys. 10 m',       'Sekcja wieży 10 m'),
                                                                                                                                 (16, 1100.00, 5, 'WP016', 'Sekcja środkowa wieży – stal S355, wys. 8 m',    'Sekcja wieży 8 m'),
                                                                                                                                 (17,  950.00, 5, 'WP017', 'Sekcja górna wieży – stal S355, wys. 5 m',      'Sekcja wieży 5 m'),

                                                                                                                                 -- A06: 3 products
                                                                                                                                 (18,   45.00, 6, 'WP018', 'Przewód zasilający – 5 m, 600 V',               'Przewód 5 m'),
                                                                                                                                 (19,   55.00, 6, 'WP019', 'Kabel sygnałowy – 10 m, ekranowany',            'Kabel 10 m'),
                                                                                                                                 (20,   50.00, 6, 'WP020', 'Przewód hamulcowy hydrauliczny – 2 m',           'Przewód hamulcowy'),

                                                                                                                                 -- A07: 3 products
                                                                                                                                 (21,  320.00, 7, 'WP021', 'Zestaw kluczy momentowych – do turbin',          'Klucze momentowe'),
                                                                                                                                 (22,  280.00, 7, 'WP022', 'Wkrętarka akumulatorowa – 18 V',                'Wkrętarka 18 V'),
                                                                                                                                 (23,  300.00, 7, 'WP023', 'Zestaw nasadek do śrub M20–M30',                'Nasadki M20–M30'),

                                                                                                                                 -- A08: 3 products
                                                                                                                                 (24,   25.00, 8, 'WP024', 'Olejek smarujący do łożysk – 1 l',               'Olej do łożysk'),
                                                                                                                                 (25,   30.00, 8, 'WP025', 'Smar grafitowy – 500 g',                        'Smar grafitowy'),
                                                                                                                                 (26,   28.00, 8, 'WP026', 'Płyn hamulcowy do układu hydraulicznego – 1 l', 'Płyn hamulcowy'),

                                                                                                                                 -- A09: 2 products
                                                                                                                                 (27,   75.00, 9, 'WP027', 'Zawór zwrotny hydrauliczny – DN25',               'Zawór DN25'),
                                                                                                                                 (28,   80.00, 9, 'WP028', 'Pompa hydrauliczna – 1,5 kW',                   'Pompa 1.5 kW'),

                                                                                                                                 -- A10: 2 products
                                                                                                                                 (29,  150.00, 10, 'WP029', 'Moduł chłodzenia cieczą – radiator i pompa',     'Chłodzenie cieczą'),
                                                                                                                                 (30,  140.00, 10, 'WP030', 'Wentylator przemysłowy – 500 mm',               'Wentylator 500 mm');

INSERT INTO product (id, price, stock_position_id, code,    description,                                            name) VALUES
                                                                                                                              -- B01: 1 product
                                                                                                                              (31,  850.00, 11, 'WB001', 'Zestaw zapasowy wirnika generatora – model G-1500',          'Wirnik G-1500'),

                                                                                                                              -- B02: 5 products
                                                                                                                              (32,  320.00, 12, 'WB002', 'Zestaw kół stożkowych do przekładni głównej',               'Koła stożkowe'),
                                                                                                                              (33,  295.00, 12, 'WB003', 'Wałek napędowy do przekładni – stal chromowana',            'Wałek napędowy'),
                                                                                                                              (34,  310.00, 12, 'WB004', 'Zestaw zębatek do przekładni bocznej',                      'Zębatki boczne'),
                                                                                                                              (35,  335.00, 12, 'WB005', 'Obudowa przekładni – aluminium lotnicze',                    'Obudowa przekładni'),
                                                                                                                              (36,  305.00, 12, 'WB006', 'Uszczelki do przekładni – komplet',                          'Uszczelki przekładni'),

                                                                                                                              -- B03: 4 products
                                                                                                                              (37,  150.00, 13, 'WB007', 'Czujnik prędkości obrotowej – model RPM-5000',              'Czujnik RPM-5000'),
                                                                                                                              (38,  140.00, 13, 'WB008', 'Czujnik temperatury przekładni – IP68',                     'Czujnik temp. IP68'),
                                                                                                                              (39,  160.00, 13, 'WB009', 'Czujnik poziomu oleju w przekładni – model OL-200',         'Czujnik OL-200'),
                                                                                                                              (40,  155.00, 13, 'WB010', 'Moduł sygnałowy czujników wibracji – VIB-S4',               'Moduł VIB-S4'),

                                                                                                                              -- B04: 4 products
                                                                                                                              (41,  210.00, 14, 'WB011', 'Reflektor sygnalizacji awaryjnej – LED 24 V',              'Reflektor awaryjny'),
                                                                                                                              (42,  200.00, 14, 'WB012', 'Panel świetlny ostrzegawczy – model S-120',                 'Panel ostrzegawczy'),
                                                                                                                              (43,  225.00, 14, 'WB013', 'Syrena alarmowa – 110 dB',                                  'Syrena 110 dB'),
                                                                                                                              (44,  205.00, 14, 'WB014', 'Moduł zasilania awaryjnego do sygnalizacji',               'Zasilanie awaryjne'),

                                                                                                                              -- B05: 3 products
                                                                                                                              (45,  180.00, 15, 'WB015', 'Zestaw śrub montażowych M24 – stal nierdzewna',            'Śruby M24'),
                                                                                                                              (46,  165.00, 15, 'WB016', 'Wkręty samogwintujące do paneli',                            'Wkręty panelowe'),
                                                                                                                              (47,  175.00, 15, 'WB017', 'Nakrętki zabezpieczające – komplet',                        'Nakrętki zabezp.'),

                                                                                                                              -- B06: 3 products
                                                                                                                              (48,  500.00, 16, 'WB018', 'Panel sterowania HMI – 10" dotykowy',                      'HMI 10"'),
                                                                                                                              (49,  480.00, 16, 'WB019', 'Moduł PLC rozszerzony – 16 wejść/8 wyjść',                 'PLC 16/8'),
                                                                                                                              (50,  510.00, 16, 'WB020', 'Zestaw przewodów do panelu operatorskiego',                 'Kable HMI'),

                                                                                                                              -- B07: 3 products
                                                                                                                              (51,  95.00,  17, 'WB021', 'Bezpiecznik elektromechaniczny – 32 A',                    'Bezpiecznik 32 A'),
                                                                                                                              (52,  110.00, 17, 'WB022', 'Wyłącznik krańcowy – stal nierdzewna',                      'Wyłącznik krańcowy'),
                                                                                                                              (53,  105.00, 17, 'WB023', 'Przełącznik główny z blokadą – IP65',                      'Przełącznik IP65'),

                                                                                                                              -- B08: 3 products
                                                                                                                              (54,  75.00,  18, 'WB024', 'Zawór kulowy pneumatyczny – DN20',                         'Zawór kulowy DN20'),
                                                                                                                              (55,  80.00,  18, 'WB025', 'Zawór redukcyjny ciśnienia – 6 bar',                       'Zawór redukcyjny'),
                                                                                                                              (56,  70.00,  18, 'WB026', 'Zestaw uszczelek do zaworów pneumatycznych',               'Uszczelki zaworów'),

                                                                                                                              -- B09: 2 products
                                                                                                                              (57,  40.00,  19, 'WB027', 'Mata izolacyjna do konserwacji – 2 m²',                     'Mata izolacyjna'),
                                                                                                                              (58,  45.00,  19, 'WB028', 'Taśma izolacyjna wysokotemperaturowa – 10 m',               'Taśma izol.'),

                                                                                                                              -- B10: 2 products
                                                                                                                              (59, 130.00,  20, 'WB029', 'Moduł komunikacji radiowej – zasięg 1 km',                   'Radio 1 km'),
                                                                                                                              (60, 140.00,  20, 'WB030', 'Antena kierunkowa do systemu SCADA',                        'Antena SCADA');
INSERT INTO product (id, price, stock_position_id, code,    description,                                              name) VALUES
                                                                                                                                -- C01: 1 product
                                                                                                                                (61,  380.00, 21, 'WC001', 'Zestaw do diagnostyki kabli – tester ciągłości i izolacji',        'Tester kabli'),

                                                                                                                                -- C02: 5 products
                                                                                                                                (62,  520.00, 22, 'WC002', 'Przenośny analizator drgań – model VibCheck-200',                  'VibCheck-200'),
                                                                                                                                (63,  540.00, 22, 'WC003', 'Termowizor przenośny – rozdzielczość 320×240',                       'Termowizor 320×240'),
                                                                                                                                (64,  500.00, 22, 'WC004', 'Multimetr cyfrowy – pomiar napięcia, prądu, rezystancji',            'Multimetr cyfrowy'),
                                                                                                                                (65,  510.00, 22, 'WC005', 'Analizator jakości energii – PowerQual-3',                          'PowerQual-3'),
                                                                                                                                (66,  495.00, 22, 'WC006', 'Rejestrator danych pomiarowych – 4 kanały',                          'Rejestrator 4-kanał.'),

                                                                                                                                -- C03: 4 products
                                                                                                                                (67,  260.00, 23, 'WC007', 'Obudowa zabezpieczająca panel sterowania – stal nierdzewna',        'Obudowa panelu'),
                                                                                                                                (68,  245.00, 23, 'WC008', 'Osłona przeciwpyłowa dla modułów elektronicznych',                  'Osłona przeciwpyłowa'),
                                                                                                                                (69,  275.00, 23, 'WC009', 'Korpus modułu pomiarowego – aluminium',                              'Korpus modułu'),
                                                                                                                                (70,  250.00, 23, 'WC010', 'Obudowa czujnika wibracji – IP68',                                   'Obudowa czujnika'),

                                                                                                                                -- C04: 4 products
                                                                                                                                (71,   35.00, 24, 'WC011', 'Farba epoksydowa antykorozyjna – 1 l',                               'Farba epoksydowa'),
                                                                                                                                (72,   40.00, 24, 'WC012', 'Podkład gruntujący do metalu – 1 l',                                 'Podkład gruntujący'),
                                                                                                                                (73,   38.00, 24, 'WC013', 'Neutralizator rdzy – spray 500 ml',                                  'Neutralizator rdzy'),
                                                                                                                                (74,   37.00, 24, 'WC014', 'Lakier wykończeniowy matowy – 750 ml',                              'Lakier matowy'),

                                                                                                                                -- C05: 3 products
                                                                                                                                (75,  120.00, 25, 'WC015', 'Akumulator litowo-jonowy awaryjny – 12 V 7 Ah',                     'Akumulator 12 V'),
                                                                                                                                (76,  135.00, 25, 'WC016', 'Zestaw baterii UPS – 48 V 5 Ah',                                     'Baterie UPS'),
                                                                                                                                (77,  115.00, 25, 'WC017', 'Moduł zasilania awaryjnego – przetwornica 24 V/12 V',               'Przetwornica'),

                                                                                                                                -- C06: 3 products
                                                                                                                                (78,  650.00, 26, 'WC018', 'Licencja oprogramowania SCADA – roczna',                             'SCADA 1Y'),
                                                                                                                                (79,  680.00, 26, 'WC019', 'Moduł komunikacji Ethernet – sterownik EtherNet/IP',               'Moduł Ethernet/IP'),
                                                                                                                                (80,  630.00, 26, 'WC020', 'Aktualizacja firmware turbin – pakiet C06',                         'Firmware C06'),

                                                                                                                                -- C07: 3 products
                                                                                                                                (81,  225.00, 27, 'WC021', 'Rura hydrauliczna wysokociśnieniowa – DN32, 2 m',                    'Rura DN32'),
                                                                                                                                (82,  240.00, 27, 'WC022', 'Złączka hydrauliczna – stal nierdzewna DN25',                        'Złączka DN25'),
                                                                                                                                (83,  210.00, 27, 'WC023', 'Elastyczny przewód hydrauliczny – 1,5 m',                             'Przewód 1.5 m'),

                                                                                                                                -- C08: 3 products
                                                                                                                                (84,  330.00, 28, 'WC024', 'System zatrzymania awaryjnego – E-Stop panel',                        'E-Stop panel'),
                                                                                                                                (85,  315.00, 28, 'WC025', 'Taśma ostrzegawcza i bariery – zestaw',                              'Zestaw barier'),
                                                                                                                                (86,  345.00, 28, 'WC026', 'Zestaw czujników przeciążeniowych – model OLDS-1',                   'Czujniki OLDS-1'),

                                                                                                                                -- C09: 2 products
                                                                                                                                (87,   55.00, 29, 'WC027', 'Filtr powietrza HEPA do wentylatora',                                  'Filtr HEPA'),
                                                                                                                                (88,   60.00, 29, 'WC028', 'Filtr oleju hydraulicznego – model HF-300',                           'Filtr HF-300'),

                                                                                                                                -- C10: 2 products
                                                                                                                                (89,  180.00, 30, 'WC029', 'Zestaw instrukcji obsługi turbin – wersja PL/EN',                    'Instrukcja PL/EN'),
                                                                                                                                (90,  170.00, 30, 'WC030', 'Dokumentacja techniczna – schematy elektryczne i hydrauliczne',      'Dokumentacja tech.');


# INSERT INTO product_cost (id, cost, product_id, date_from,             date_to,               description) VALUES
#                                                                                                                (1, 45.00, 1, '2024-01-01 00:00:00', '2024-06-30 23:59:59', 'Promocja zimowa'),
#                                                                                                                (2, 17.50, 2, '2024-02-01 00:00:00', '2024-07-31 23:59:59', 'Rabat wiosenny'),
#                                                                                                                (3,  4.99, 3, '2024-03-15 00:00:00', '2024-09-15 23:59:59', 'Oferta specjalna');

INSERT INTO product_cost (id, cost, product_id, date_from,   date_to,     description) VALUES
                                                                                           -- product_id = 1 (Łopata L1000)
                                                                                           (1,  2899.00, 1, '2024-01-01', '2024-01-31', 'Naprawa uszkodzonej łopaty L1000'),
                                                                                           (2,  2799.00, 1, '2024-02-01', '2024-02-29', 'Konserwacja łopaty L1000'),
                                                                                           (3,  2699.00, 1, '2024-03-01', '2024-03-31', 'Wymiana elementów łopaty L1000'),
                                                                                           (4,  2599.00, 1, '2024-04-01', '2024-04-30', 'Przegląd okresowy łopaty L1000'),
                                                                                           (5,  2499.00, 1, '2024-05-01', '2024-05-31', 'Serwis techniczny łopaty L1000'),

                                                                                           -- product_id = 2 (Generator 1500 kW)
                                                                                           (6,  725.00,  2, '2024-01-01', '2024-01-31', 'Naprawa uzwojenia generatora'),
                                                                                           (7,  710.00,  2, '2024-02-01', '2024-02-29', 'Konserwacja generatora 1500 kW'),
                                                                                           (8,  695.00,  2, '2024-03-01', '2024-03-31', 'Wymiana szczotek w generatorze'),
                                                                                           (9,  680.00,  2, '2024-04-01', '2024-04-30', 'Przegląd mechanizmu generatora'),
                                                                                           (10, 665.00,  2, '2024-05-01', '2024-05-31', 'Serwis elektryczny generatora'),

                                                                                           -- product_id = 3 (Silnik SG-2000)
                                                                                           (11, 710.00,  3, '2024-01-01', '2024-01-31', 'Naprawa silnika SG-2000'),
                                                                                           (12, 695.00,  3, '2024-02-01', '2024-02-29', 'Konserwacja silnika pomocniczego'),
                                                                                           (13, 680.00,  3, '2024-03-01', '2024-03-31', 'Wymiana łożysk w silniku SG-2000'),
                                                                                           (14, 665.00,  3, '2024-04-01', '2024-04-30', 'Przegląd okresowy silnika'),
                                                                                           (15, 650.00,  3, '2024-05-01', '2024-05-31', 'Serwis mechaniczny silnika'),

                                                                                           -- product_id = 4 (Silnik pomocniczy)
                                                                                           (16, 695.00,  4, '2024-01-01', '2024-01-31', 'Naprawa silnika pomocniczego'),
                                                                                           (17, 680.00,  4, '2024-02-01', '2024-02-29', 'Konserwacja silnika pomocniczego'),
                                                                                           (18, 665.00,  4, '2024-03-01', '2024-03-31', 'Wymiana uszczelek silnika'),
                                                                                           (19, 650.00,  4, '2024-04-01', '2024-04-30', 'Przegląd okresowy silnika pomocniczego'),
                                                                                           (20, 635.00,  4, '2024-05-01', '2024-05-31', 'Serwis techniczny silnika'),

                                                                                           -- product_id = 5 (Silnik gondoli)
                                                                                           (21, 770.00,  5, '2024-01-01', '2024-01-31', 'Naprawa obrotu gondoli'),
                                                                                           (22, 755.00,  5, '2024-02-01', '2024-02-29', 'Konserwacja mechanizmu gondoli'),
                                                                                           (23, 740.00,  5, '2024-03-01', '2024-03-31', 'Wymiana łożysk gondoli'),
                                                                                           (24, 725.00,  5, '2024-04-01', '2024-04-30', 'Przegląd okresowy gondoli'),
                                                                                           (25, 710.00,  5, '2024-05-01', '2024-05-31', 'Serwis elektryczny gondoli'),

                                                                                           -- product_id = 6 (Silnik chłodzenia)
                                                                                           (26, 785.00,  6, '2024-01-01', '2024-01-31', 'Naprawa układu chłodzenia'),
                                                                                           (27, 770.00,  6, '2024-02-01', '2024-02-29', 'Konserwacja pompy chłodzenia'),
                                                                                           (28, 755.00,  6, '2024-03-01', '2024-03-31', 'Wymiana radiatora chłodzenia'),
                                                                                           (29, 740.00,  6, '2024-04-01', '2024-04-30', 'Przegląd okresowy układu chłodzenia'),
                                                                                           (30, 725.00,  6, '2024-05-01', '2024-05-31', 'Serwis techniczny chłodzenia');

-- 8) cart_stock_positions
# INSERT INTO cart_stock_positions (cart_id, stock_positions_id) VALUES
#                                                                    (1, 1),
#                                                                    (2, 2),
#                                                                    (3, 3);
INSERT INTO cart_stock_positions (cart_id, stock_positions_id) VALUES
                                                                   -- Cart 1: positions 1–10
                                                                   (1,  1), (1,  2), (1,  3), (1,  4), (1,  5),
                                                                   (1,  6), (1,  7), (1,  8), (1,  9), (1, 10),

                                                                   -- Cart 2: positions 11–20
                                                                   (2, 11), (2, 12), (2, 13), (2, 14), (2, 15),
                                                                   (2, 16), (2, 17), (2, 18), (2, 19), (2, 20),

                                                                   -- Cart 3: positions 21–30
                                                                   (3, 21), (3, 22), (3, 23), (3, 24), (3, 25),
                                                                   (3, 26), (3, 27), (3, 28), (3, 29), (3, 30),

                                                                   -- Cart 4: positions 1–10
                                                                   (4,  1), (4,  2), (4,  3), (4,  4), (4,  5),
                                                                   (4,  6), (4,  7), (4,  8), (4,  9), (4, 10),

                                                                   -- Cart 5: positions 11–20
                                                                   (5, 11), (5, 12), (5, 13), (5, 14), (5, 15),
                                                                   (5, 16), (5, 17), (5, 18), (5, 19), (5, 20);

# INSERT INTO worker_qualification (id, user_id, date_from,  date_to,    description,          qualification) VALUES
#                                                                                                                 (1, 1, '2022-01-01', '2023-12-31', 'Szkolenie BHP',        'BHP'),
#                                                                                                                 (2, 2, '2023-02-15', '2024-02-14', 'Kurs Obsługi Wózków', 'Operator wózka'),
#                                                                                                                 (3, 3, '2021-05-10', '2025-05-09', 'Certyfikat SEO',       'SEO Specialist');

INSERT INTO worker_qualification (id, user_id, date_from,   date_to,     description,                                   qualification) VALUES
                                                                                                                                           -- Worker 1 qualifications
                                                                                                                                           ( 1, 1, '2023-01-10', '2025-01-09', 'Szkolenie z dostępu linowego do gondoli turbiny',           'Dostęp linowy'),
                                                                                                                                           ( 2, 1, '2022-06-01', '2024-05-31', 'Kurs pierwszej pomocy – scenariusze w warunkach offshore', 'Pierwsza pomoc'),
                                                                                                                                           ( 3, 1, '2023-03-15', '2025-03-14', 'Szkolenie z obsługi i konserwacji turbin wiatrowych',      'Konserwacja turbin'),
                                                                                                                                           ( 4, 1, '2022-09-20', '2024-09-19', 'Kurs bezpieczeństwa elektrycznego na farmie wiatrowej',     'Elektryczne BHP'),
                                                                                                                                           ( 5, 1, '2023-11-05', '2025-11-04', 'Szkolenie z ratownictwa wysokościowego przy turbinach',     'Ratownictwo wysokościowe'),

                                                                                                                                           -- Worker 2 qualifications
                                                                                                                                           ( 6, 2, '2022-02-01', '2024-01-31', 'Szkolenie z dostępu linowego do gondoli turbiny',           'Dostęp linowy'),
                                                                                                                                           ( 7, 2, '2022-07-10', '2024-07-09', 'Kurs pierwszej pomocy – scenariusze w warunkach offshore', 'Pierwsza pomoc'),
                                                                                                                                           ( 8, 2, '2023-04-20', '2025-04-19', 'Szkolenie z obsługi i konserwacji turbin wiatrowych',      'Konserwacja turbin'),
                                                                                                                                           ( 9, 2, '2022-10-15', '2024-10-14', 'Kurs bezpieczeństwa elektrycznego na farmie wiatrowej',     'Elektryczne BHP'),
                                                                                                                                           (10, 2, '2023-12-01', '2025-11-30', 'Szkolenie z ratownictwa wysokościowego przy turbinach',     'Ratownictwo wysokościowe'),

                                                                                                                                           -- Worker 3 qualifications
                                                                                                                                           (11, 3, '2022-03-05', '2024-03-04', 'Szkolenie z dostępu linowego do gondoli turbiny',           'Dostęp linowy'),
                                                                                                                                           (12, 3, '2022-08-12', '2024-08-11', 'Kurs pierwszej pomocy – scenariusze w warunkach offshore', 'Pierwsza pomoc'),
                                                                                                                                           (13, 3, '2023-05-25', '2025-05-24', 'Szkolenie z obsługi i konserwacji turbin wiatrowych',      'Konserwacja turbin'),
                                                                                                                                           (14, 3, '2022-11-18', '2024-11-17', 'Kurs bezpieczeństwa elektrycznego na farmie wiatrowej',     'Elektryczne BHP'),
                                                                                                                                           (15, 3, '2023-08-30', '2025-08-29', 'Szkolenie z ratownictwa wysokościowego przy turbinach',     'Ratownictwo wysokościowe');

# -- 11) car
# INSERT INTO car (id, insured,      review,       year,       brand,   color,     description,       model,       name, registration) VALUES
#                                                                                                                                          (1, '2024-01-01', '2024-06-01','2020-01-01', 'Toyota', 'Czarny', 'Samochód służbowy','Corolla',   'CarA', 'WA12345'),
#                                                                                                                                          (2, '2024-02-01', '2024-07-01','2019-01-01', 'Ford',   'Biały',  'Auto serwisowe',   'Focus',     'CarB', 'KR67890'),
#                                                                                                                                          (3, '2024-03-01', '2024-08-01','2021-01-01', 'Honda',  'Czerwony','Pojazd demo',     'Civic',     'CarC', 'GD54321');

INSERT INTO car (id, insured,      review,       year,       brand,      color,       description,                           model,        name,    registration) VALUES
                                                                                                                                                                      (1, '2024-01-01', '2024-06-01', '2020-01-01', 'Toyota',   'Czarny',    'Samochód służbowy do serwisu turbin', 'Corolla',    'Serwisówka', 'WA12345'),
                                                                                                                                                                      (2, '2024-02-01', '2024-07-01', '2019-01-01', 'Ford',     'Biały',     'Auto serwisowe z podnośnikiem linowym', 'Focus',      'Podnośnik',  'KR67890'),
                                                                                                                                                                      (3, '2024-03-01', '2024-08-01', '2021-01-01', 'Honda',    'Czerwony',  'Pojazd demo dla szkoleń wysokościowych','Civic',      'DemoCar',     'GD54321'),
                                                                                                                                                                      (4, '2024-04-01', '2024-09-01', '2018-01-01', 'Mercedes', 'Niebieski', 'Ciężarówka z dźwigiem do montażu turbin','Actros',     'Dźwigówka',   'DW34567'),
                                                                                                                                                                      (5, '2024-05-01', '2024-10-01', '2022-01-01', 'Land Rover','Zielony',   'Pojazd terenowy do inspekcji farm',      'Defender',   'Terenówka',   'PO98765');

-- 12) car_cost
# INSERT INTO car_cost (id, car_id, cost,    date_from,   date_to,     description) VALUES
#                                                                                       (1, 1,      1200.00, '2024-01-01', '2024-12-31', 'Ubezpieczenie'),
#                                                                                       (2, 2,       800.00, '2024-02-01', '2024-08-01', 'Przegląd techniczny'),
#                                                                                       (3, 3,      1500.00, '2024-03-01', '2024-09-01', 'Serwis okresowy');

INSERT INTO car_cost (id, car_id, cost,    date_from,   date_to,     description) VALUES
                                                                                      -- Car 1 (Serwisówka)
                                                                                      ( 1, 1, 1200.00, '2024-01-01', '2024-12-31', 'Ubezpieczenie pojazdu'),
                                                                                      ( 2, 1,  300.00, '2024-02-01', '2024-02-28', 'Wymiana oleju silnikowego'),
                                                                                      ( 3, 1,  450.00, '2024-03-01', '2024-03-31', 'Wymiana opon zimowych'),
                                                                                      ( 4, 1,  500.00, '2024-04-01', '2024-04-30', 'Przegląd układu hamulcowego'),
                                                                                      ( 5, 1,  200.00, '2024-05-01', '2024-05-31', 'Mycie i konserwacja nadwozia'),

                                                                                      -- Car 2 (Podnośnik)
                                                                                      ( 6, 2,  900.00, '2024-01-01', '2024-12-31', 'Ubezpieczenie pojazdu'),
                                                                                      ( 7, 2,  400.00, '2024-02-15', '2024-02-28', 'Wymiana filtra paliwa'),
                                                                                      ( 8, 2,  350.00, '2024-03-10', '2024-03-31', 'Regulacja zawieszenia'),
                                                                                      ( 9, 2,  380.00, '2024-04-05', '2024-04-30', 'Wymiana klocków hamulcowych'),
                                                                                      (10, 2,  220.00, '2024-05-01', '2024-05-31', 'Czyszczenie układu klimatyzacji'),

                                                                                      -- Car 3 (DemoCar)
                                                                                      (11, 3, 1100.00, '2024-01-01', '2024-12-31', 'Ubezpieczenie pojazdu'),
                                                                                      (12, 3,  320.00, '2024-02-01', '2024-02-28', 'Wymiana oleju silnikowego'),
                                                                                      (13, 3,  290.00, '2024-03-01', '2024-03-31', 'Przegląd układu kierowniczego'),
                                                                                      (14, 3,  410.00, '2024-04-01', '2024-04-30', 'Wymiana filtra powietrza'),
                                                                                      (15, 3,  180.00, '2024-05-01', '2024-05-31', 'Detailing i mycie wnętrza'),

                                                                                      -- Car 4 (Dźwigówka)
                                                                                      (16, 4, 1300.00, '2024-01-01', '2024-12-31', 'Ubezpieczenie pojazdu'),
                                                                                      (17, 4,  500.00, '2024-02-01', '2024-02-28', 'Przegląd techniczny dźwigu'),
                                                                                      (18, 4,  600.00, '2024-03-01', '2024-03-31', 'Serwis układu hydraulicznego'),
                                                                                      (19, 4,  450.00, '2024-04-01', '2024-04-30', 'Wymiana oleju hydraulicznego'),
                                                                                      (20, 4,  250.00, '2024-05-01', '2024-05-31', 'Inspekcja i konserwacja dźwigu'),

                                                                                      -- Car 5 (Terenówka)
                                                                                      (21, 5, 1250.00, '2024-01-01', '2024-12-31', 'Ubezpieczenie pojazdu'),
                                                                                      (22, 5,  350.00, '2024-02-01', '2024-02-28', 'Wymiana opon terenowych'),
                                                                                      (23, 5,  420.00, '2024-03-01', '2024-03-31', 'Przegląd zawieszenia terenowego'),
                                                                                      (24, 5,  480.00, '2024-04-01', '2024-04-30', 'Wymiana amortyzatorów'),
                                                                                      (25, 5,  300.00, '2024-05-01', '2024-05-31', 'Mycie i konserwacja podwozia');

-- 13) task
# INSERT INTO task (id, cart_id, place_id, date_from,   date_to,     description,    name) VALUES
#                                                                                              (1, 1,       1,        '2024-06-01','2024-06-05','Dostawa sprzętu','TaskA'),
#                                                                                              (2, 2,       2,        '2024-07-10','2024-07-12','Kontrola magazynu','TaskB'),
#                                                                                              (3, 3,       3,        '2024-08-20','2024-08-22','Prezentacja demo','TaskC');

INSERT INTO task (id, cart_id, place_id, date_from,   date_to,     description,                         name) VALUES
                                                                                                                  (1, 1, 1, '2024-06-01', '2024-06-05', 'Dostawa łopat turbin na farmę',         'Dostawa łopat'),
                                                                                                                  (2, 1, 2, '2024-06-06', '2024-06-10', 'Montaż turbiny nr 1',                    'Montaż turbiny 1'),
                                                                                                                  (3, 2, 3, '2024-07-01', '2024-07-03', 'Konserwacja generatora 1500 kW',         'Serwis generatora'),
                                                                                                                  (4, 2, 4, '2024-07-04', '2024-07-07', 'Inspekcja czujników wibracji',            'Inspekcja sensorów'),
                                                                                                                  (5, 3, 5, '2024-08-01', '2024-08-05', 'Wymiana sekcji wieży – część dolna',       'Wymiana sekcji dolnej'),
                                                                                                                  (6, 3, 6, '2024-08-06', '2024-08-09', 'Przegląd przewodów i okablowania',         'Przegląd okablowania'),
                                                                                                                  (7, 4, 7, '2024-09-01', '2024-09-04', 'Serwis narzędzi i zestawów montażowych',  'Konserwacja narzędzi'),
                                                                                                                  (8, 4, 8, '2024-09-05', '2024-09-08', 'Dostawa smarów i olejów',                  'Dostawa smarów'),
                                                                                                                  (9, 5, 9, '2024-10-01', '2024-10-04', 'Instalacja zaworów hydraulicznych',       'Montaż zaworów'),
                                                                                                                  (10,5, 10,'2024-10-05', '2024-10-08', 'Testy systemu chłodzenia gondoli',         'Testy chłodzenia');

-- 14) task_car
# INSERT INTO task_car (car_id, task_id) VALUES
#                                            (1, 1),
#                                            (2, 2),
#                                            (3, 3);
INSERT INTO task_car (car_id, task_id) VALUES
                                           (1,  1),
                                           (2,  2),
                                           (3,  3),
                                           (4,  4),
                                           (5,  5),
                                           (1,  6),
                                           (2,  7),
                                           (3,  8),
                                           (4,  9),
                                           (5, 10);
-- 15) task_product
# INSERT INTO task_product (product_id, task_id) VALUES
#                                                    (1, 1),
#                                                    (2, 2),
#                                                    (3, 3);
INSERT INTO task_product (product_id, task_id) VALUES
                                                   -- Task 1: products 1–20
                                                   (1, 1),  (2, 1),  (3, 1),  (4, 1),  (5, 1),
                                                   (6, 1),  (7, 1),  (8, 1),  (9, 1),  (10, 1),
                                                   (11, 1), (12, 1), (13, 1), (14, 1), (15, 1),
                                                   (16, 1), (17, 1), (18, 1), (19, 1), (20, 1),

                                                   -- Task 2: products 21–40
                                                   (21, 2), (22, 2), (23, 2), (24, 2), (25, 2),
                                                   (26, 2), (27, 2), (28, 2), (29, 2), (30, 2),
                                                   (31, 2), (32, 2), (33, 2), (34, 2), (35, 2),
                                                   (36, 2), (37, 2), (38, 2), (39, 2), (40, 2),

                                                   -- Task 3: products 41–60
                                                   (41, 3), (42, 3), (43, 3), (44, 3), (45, 3),
                                                   (46, 3), (47, 3), (48, 3), (49, 3), (50, 3),
                                                   (51, 3), (52, 3), (53, 3), (54, 3), (55, 3),
                                                   (56, 3), (57, 3), (58, 3), (59, 3), (60, 3),

                                                   -- Task 4: products 61–80
                                                   (61, 4), (62, 4), (63, 4), (64, 4), (65, 4),
                                                   (66, 4), (67, 4), (68, 4), (69, 4), (70, 4),
                                                   (71, 4), (72, 4), (73, 4), (74, 4), (75, 4),
                                                   (76, 4), (77, 4), (78, 4), (79, 4), (80, 4),

                                                   -- Task 5: products 81–90 and 1–10
                                                   (81, 5), (82, 5), (83, 5), (84, 5), (85, 5),
                                                   (86, 5), (87, 5), (88, 5), (89, 5), (90, 5),
                                                   (1,  5), (2,  5), (3,  5), (4,  5), (5,  5),
                                                   (6,  5), (7,  5), (8,  5), (9,  5), (10, 5),

                                                   -- Task 6: products 11–30
                                                   (11, 6), (12, 6), (13, 6), (14, 6), (15, 6),
                                                   (16, 6), (17, 6), (18, 6), (19, 6), (20, 6),
                                                   (21, 6), (22, 6), (23, 6), (24, 6), (25, 6),
                                                   (26, 6), (27, 6), (28, 6), (29, 6), (30, 6),

                                                   -- Task 7: products 31–50
                                                   (31, 7), (32, 7), (33, 7), (34, 7), (35, 7),
                                                   (36, 7), (37, 7), (38, 7), (39, 7), (40, 7),
                                                   (41, 7), (42, 7), (43, 7), (44, 7), (45, 7),
                                                   (46, 7), (47, 7), (48, 7), (49, 7), (50, 7),

                                                   -- Task 8: products 51–70
                                                   (51, 8), (52, 8), (53, 8), (54, 8), (55, 8),
                                                   (56, 8), (57, 8), (58, 8), (59, 8), (60, 8),
                                                   (61, 8), (62, 8), (63, 8), (64, 8), (65, 8),
                                                   (66, 8), (67, 8), (68, 8), (69, 8), (70, 8),

                                                   -- Task 9: products 71–90
                                                   (71, 9), (72, 9), (73, 9), (74, 9), (75, 9),
                                                   (76, 9), (77, 9), (78, 9), (79, 9), (80, 9),
                                                   (81, 9), (82, 9), (83, 9), (84, 9), (85, 9),
                                                   (86, 9), (87, 9), (88, 9), (89, 9), (90, 9),

                                                   -- Task 10: products 1–20
                                                   (1, 10),  (2, 10),  (3, 10),  (4, 10),  (5, 10),
                                                   (6, 10),  (7, 10),  (8, 10),  (9, 10),  (10, 10),
                                                   (11, 10), (12, 10), (13, 10), (14, 10), (15, 10),
                                                   (16, 10), (17, 10), (18, 10), (19, 10), (20, 10);
-- 16) task_product_used
# INSERT INTO task_product_used (product_id, task_id) VALUES
#                                                         (1, 1),
#                                                         (2, 2),
#                                                         (3, 3);
INSERT INTO task_product_used (product_id, task_id) VALUES
                                                        -- Task 1 used products (subset of assigned)
                                                        (1, 1), (2, 1), (3, 1), (4, 1), (5, 1),
                                                        (6, 1), (7, 1), (8, 1), (9, 1), (10, 1),

                                                        -- Task 2 used products (subset of assigned)
                                                        (21, 2), (22, 2), (23, 2), (24, 2), (25, 2),
                                                        (26, 2), (27, 2), (28, 2), (29, 2), (30, 2),

                                                        -- Task 3 used products (subset of assigned)
                                                        (41, 3), (42, 3), (43, 3), (44, 3), (45, 3),
                                                        (46, 3), (47, 3), (48, 3), (49, 3), (50, 3);

# -- 17) task_user
# INSERT INTO task_user (task_id, user_id) VALUES
#                                              (1, 1),
#                                              (2, 2),
#                                              (3, 3);
INSERT INTO task_user (task_id, user_id) VALUES
                                             (1, 1),
                                             (2, 2),
                                             (3, 3),
                                             (4, 1),
                                             (5, 2),
                                             (6, 3),
                                             (7, 1),
                                             (8, 2),
                                             (9, 3),
                                             (10, 1);