CREATE
    USER 'golajuma-local'@'localhost' IDENTIFIED BY 'golajuma-local';
CREATE
    USER 'golajuma-local'@'%' IDENTIFIED BY 'golajuma-local';

GRANT ALL PRIVILEGES ON *.* TO
    'golajuma-local'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO
    'golajuma-local'@'%';

CREATE
    DATABASE golajuma DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
