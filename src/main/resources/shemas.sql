# DROP DATABASE IF EXISTS `user_articles`;
CREATE DATABASE `user_articles`;

USE user_articles;

DROP TABLE if exists articles;
DROP TABLE if exists users;

CREATE TABLE users
(
    id   BIGINT primary key AUTO_INCREMENT,
    name VARCHAR(64) not null,
    age  INT
);

CREATE TABLE articles
(
    id      BIGINT primary key auto_increment,
    text    TEXT not null,
    color   ENUM ('GREEN', 'BLUE', 'BLACK', 'RED'),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

SELECT *
FROM users
         INNER JOIN articles ON (users.id = articles.user_id)
WHERE color = 'green';

SELECT name
FROM users
         LEFT OUTER JOIN articles ON (users.id = articles.user_id)
WHERE color = 'green';

SELECT u.name as name, u.id as id
FROM users u
         INNER JOIN articles ON (u.id = articles.user_id)
WHERE color = 'green';

Select *
from users as u
         inner join articles as a
where u.id = a.user_id
  and a.color = 'green';

SELECT users.name, count(*)
FROM users
         join articles a on users.id = a.user_id
group by users.name
having count(a.user_id) > 1;

select users.name, count(*)
from users
         inner join articles a on users.id = a.user_id
group by users.name
having count(user_id) > 1;
