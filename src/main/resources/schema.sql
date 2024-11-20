CREATE TABLE IF NOT EXISTS `address` (
    `dong_code` VARCHAR(10) NOT NULL,
    `sido_name` VARCHAR(30) NULL DEFAULT NULL,
    `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
    `dong_name` VARCHAR(30) NULL DEFAULT NULL,
    PRIMARY KEY (`dong_code`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS place_category (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_code VARCHAR(10) NULL,
    category_name VARCHAR(50) NULL
);