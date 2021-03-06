INSERT INTO roles(name) VALUES
    ('ROLE_USER'),
    ('ROLE_MODERATOR'),
    ('ROLE_ADMIN');

INSERT INTO type_orgs VALUES
    (1, 'Общее'),
    (2, 'Здравохранение'),
    (3, 'Образование'),
    (4, 'Социальное');

INSERT INTO organizations VALUES
    (1, 'КЦСОН Городовиковского района', 'Бюджетное учреждение Республики Калмыкия «Городовиковский КЦСОН»',4),
    (2, 'КЦСОН Ики-Бурульского района', 'Бюджетное учреждение Республики Калмыкия «Ики-Бурульский КЦСОН»',4),
    (3, 'КЦСОН Кетченеровского района', 'Бюджетное учреждение Республики Калмыкия «Кетченеровский КЦСОН»',4),
    (4, 'КЦСОН Лаганского района', 'Бюджетное учреждение Республики Калмыкия «Лаганский КЦСОН»',4),
    (5, 'КЦСОН Малодербетовского района', 'Бюджетное учреждение Республики Калмыкия «Малодертовский КЦСОН»',4),
    (6, 'КЦСОН Октябрьского района', 'Бюджетное учреждение Республики Калмыкия «Октябрьский КЦСОН»',4),
    (7, 'КЦСОН Приютненского района', 'Бюджетное учреждение Республики Калмыкия «Приютненский КЦСОН»',4),
    (8, 'КЦСОН Сарпинского района', 'Бюджетное учреждение Республики Калмыкия «Сарпинский КЦСОН»',4),
    (9, 'КЦСОН Целинного района', 'Бюджетное учреждение Республики Калмыкия «Целинный КЦСОН»',4),
    (10, 'КЦСОН Черноземельского района', 'Бюджетное учреждение Республики Калмыкия «Черноземельский КЦСОН»',4),
    (11, 'КЦСОН Юстинского района', 'Бюджетное учреждение Республики Калмыкия «Юстинский КЦСОН»',4),
    (12, 'КЦСОН Яшалтинского района', 'Бюджетное учреждение Республики Калмыкия «Яшалтинский КЦСОН»',4),
    (13, 'КЦСОН Яшкульского района', 'Бюджетное учреждение Республики Калмыкия «Яшкульский КЦСОН»',4),
    (14, 'Республиканский КЦСОН', 'Бюджетное учреждение Республики Калмыкия «Республиканский комплексный Центр социального обслуживания населения»',4),
    (15, 'РЦ для детей и подростков с ОВ', 'Казенное учреждение Республики Калмыкия «Реабилитационный центр для детей и подростков с ограниченными возможностями»',4);


INSERT INTO users (user_id, email, first_name, password, patronymic, phone_number, sur_name, username, organization_id) VALUES
    (1, 'admin@minsoc.ru', 'Админов', '$2a$10$MR8DngNq7Uxhv0QsIqp61exMtTwneiwmWFR3mBgaftd0bIRYdVcf2', 'Админович','34605','Админов', 'admin',2),
    (2, 'moderator@minsoc.ru', 'Модераторов', '$2a$10$MR8DngNq7Uxhv0QsIqp61exMtTwneiwmWFR3mBgaftd0bIRYdVcf2', 'Модераторович','34605','Модераторов', 'moderator',1),
    (3, 'user@minsoc.ru', 'Юзеров', '$2a$10$MR8DngNq7Uxhv0QsIqp61exMtTwneiwmWFR3mBgaftd0bIRYdVcf2', 'Юзерович','34919','Юзеорв', 'user',1),
    (4, 'user2@minsoc.ru', 'Юзеров2', '$2a$10$MR8DngNq7Uxhv0QsIqp61exMtTwneiwmWFR3mBgaftd0bIRYdVcf2', 'Юзерович2','34919','Юзеорв2', 'user2',2);
INSERT INTO user_roles VALUES
    (1,  3),
    (2,  2),
    (3,  1),
    (4,  1);



INSERT INTO childs (child_id, active, actual_address, birth_date, first_name, patronymic, sex, registration_address, sur_name, user_id, organization_id,snils) VALUES
    (1,true, 'г. Персик ул. Персика 1', '2019-10-10', 'ПЕРСИК', 'ПЕРСИКОВИЧ', true, 'г. Персик ул. Персиковая 1', 'ПЕРСИКОВ', 1,1,'12345678900'),
    (2,true, 'г. Абрикос ул. Абрикосова 1', '2019-11-11', 'АБРИКОС', 'АБРИКОСОВИЧ', true, 'г. Абрикос ул. Абрикосовая 1', 'АБРИКОСОВ', 1,1,'12345678901'),
    (3,false, 'г. Мандарин ул. Мандариновая 1', '2019-12-12', 'МАНДАРИН', 'МАНДАРИНОВИЧ', true, 'г. Мандарин ул. Мандариновая 1', 'МАНДАРИНОВ', 2,3,'12345678902'),
    (4,true, 'г. Помидор ул. Помидоровая 1', '2017-01-12', 'ПОМИДОР', 'ПОМИДОРОВИЧ', true, 'г. Помидор ул. Помидоровая 1', 'ПОМИДОРОВ', 2,2,'12345678903'),
    (5,true, 'г. Апельсин ул. Апельсиновая 1', '2018-01-12', 'АПЕЛЬСИН', 'АПЕЛЬСИНОВИЧ', true, 'г. Апельсин ул. Апельсиновая 1', 'АПЕЛЬСИНОВ', 3,2,'12345678904');



/*INSERT INTO documents (document_id, child_id, type_document, serial) VALUES
    (1, 1,  2, '12345678900'),
    (2, 2,  2, '12345678900'),
    (3, 3,  2, '23456789000'),
    (4, 4,  2, '34567890000'),
    (5, 5,  2, '12312312333'),
    (6, 5,  1, '12312312314');*/



