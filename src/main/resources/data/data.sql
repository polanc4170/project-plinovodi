INSERT INTO holiday (local_date, name) VALUES
('2024-01-01', 'Novo leto'),
('2024-01-02', 'Novo leto'),
('2024-02-08', 'Prešernov dan'),
('2024-04-01', 'Velikonočni ponedeljek'),
('2024-05-01', 'Praznik dela'),
('2024-05-02', 'Praznik dela'),
('2024-06-25', 'Dan državnosti'),
('2024-08-15', 'Marijino vnebovzetje'),
('2024-10-31', 'Dan reformacije'),
('2024-11-01', 'Dan spomina Na mrtve'),
('2024-12-25', 'Božič'),
('2024-12-26', 'Dan samostojnosti In enotnosti');

INSERT INTO user_report (id, uuid, first_name, last_name, date_start) VALUES
(1, 1, 'Janez', 'Novak', '2024-02-05'),
(2, 2, 'Peter', 'Svete', '2024-02-12'),
(3, 3, 'Mirko', 'Petek', '2024-02-19');

INSERT INTO user_intervention (id, uuid, date_start, hour_start, hour_end, desc_short, desc_long) VALUES
(1, 1, '2024-02-06', 10, 14, 'Main gas pipe broken.', 'Main gas pipe broken with long description.'),
(2, 2, '2024-02-11',  8, 13, 'Side gas pipe broken.', 'Side gas pipe broken with long description.'),
(3, 3, '2024-02-11', 15, 16, 'Side gas pipe broken.', 'Side gas pipe broken with long description.'),
(4, 4, '2024-02-17', 12, 14, 'Side gas pipe broken.', 'Side gas pipe broken with long description.');

INSERT INTO user_interventions (report_id, intervention_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4);
