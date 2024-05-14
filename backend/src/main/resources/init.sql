INSERT INTO client (public_id, username, password, first_name, last_name, date_of_birth, email, creation_date)
VALUES ('d4f8915a-599d-4dd3-a8c3-034f7fa946c4', 'admin', '$2a$10$Dg2aGrWs.YSIIR30g7QuQeEfpKKkyAgEiGBqjCHK4isQ6Ez65kuXG',
        'admin', 'test', '2000-01-01', 'admin@test.com', CURRENT_DATE);

INSERT INTO client_roles (client_id, roles)
SELECT id, 'ROLE_ADMIN'
FROM client
WHERE public_id = 'd4f8915a-599d-4dd3-a8c3-034f7fa946c4';

INSERT INTO client_roles (client_id, roles)
SELECT id, 'ROLE_USER'
FROM client
WHERE public_id = 'd4f8915a-599d-4dd3-a8c3-034f7fa946c4';

INSERT INTO client (public_id, username, password, first_name, last_name, date_of_birth, email, creation_date)
VALUES ('04d647ac-9f74-4c2a-b966-2ae6a6baa87a', 'user', '$2a$10$Dg2aGrWs.YSIIR30g7QuQeEfpKKkyAgEiGBqjCHK4isQ6Ez65kuXG',
        'user', 'test', '2000-01-01', 'user@test.com', CURRENT_DATE);

INSERT INTO client_roles (client_id, roles)
SELECT id, 'ROLE_USER'
FROM client
WHERE public_id = '04d647ac-9f74-4c2a-b966-2ae6a6baa87a';

SELECT *
from client
