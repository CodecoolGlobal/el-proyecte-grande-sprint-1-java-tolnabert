-- CLIENT
INSERT INTO client (public_id, username, password, first_name, last_name, date_of_birth, email, creation_date)
VALUES ('d4f8915a-599d-4dd3-a8c3-034f7fa946c4', 'admin', '$2a$10$Dg2aGrWs.YSIIR30g7QuQeEfpKKkyAgEiGBqjCHK4isQ6Ez65kuXG',
        'admin', 'test', '2000-01-01', 'admin@test.com', CURRENT_DATE);
-- ADMIN ROLE
INSERT INTO client_roles (client_id, roles)
SELECT id, 'ROLE_ADMIN'
FROM client
WHERE public_id = 'd4f8915a-599d-4dd3-a8c3-034f7fa946c4';
-- USER ROLE
INSERT INTO client_roles (client_id, roles)
SELECT id, 'ROLE_USER'
FROM client
WHERE public_id = 'd4f8915a-599d-4dd3-a8c3-034f7fa946c4';

--CLIENT
INSERT INTO client (public_id, username, password, first_name, last_name, date_of_birth, email, creation_date)
VALUES ('04d647ac-9f74-4c2a-b966-2ae6a6baa87a', 'user', '$2a$10$Dg2aGrWs.YSIIR30g7QuQeEfpKKkyAgEiGBqjCHK4isQ6Ez65kuXG',
        'user', 'test', '2000-01-01', 'user@test.com', CURRENT_DATE);
--USER ROLE
INSERT INTO client_roles (client_id, roles)
SELECT id, 'ROLE_USER'
FROM client
WHERE public_id = '04d647ac-9f74-4c2a-b966-2ae6a6baa87a';

SELECT *
from client;

--Recipe
INSERT INTO recipe (public_id, name, description, portions, image, created_at, created_by_id)
VALUES ('1494d916-8dfe-4873-91a9-caac1b014edc', 'Chilisbab', 'Nagyon finom, Norbi kedvence', '0', 'https://kep.index.hu/1/0/2909/29098/290985/29098593_4d6b80f6c4fa227fa2408f9bf182f4ba_wm.jpg', CURRENT_DATE, 1);
INSERT INTO recipe (public_id, name, description, portions, image, created_at, created_by_id)
VALUES ('2494d916-8dfe-4873-91a9-caac1b014edc', 'Chilisbab', 'Nagyon finom, Norbi kedvence', '0', 'https://kep.index.hu/1/0/2909/29098/290985/29098593_4d6b80f6c4fa227fa2408f9bf182f4ba_wm.jpg', CURRENT_DATE, 1);
INSERT INTO recipe (public_id, name, description, portions, image, created_at, created_by_id)
VALUES ('3494d916-8dfe-4873-91a9-caac1b014edc', 'Chilisbab', 'Nagyon finom, Norbi kedvence', '0', 'https://kep.index.hu/1/0/2909/29098/290985/29098593_4d6b80f6c4fa227fa2408f9bf182f4ba_wm.jpg', CURRENT_DATE, 1);

