SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema EasyGoDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema EasyGoDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `EasyGoDB` DEFAULT CHARACTER SET utf8 ;
USE `EasyGoDB` ;

DROP TABLE `users`;
DROP TABLE `gomaps`;
DROP TABLE `points`;

-- -----------------------------------------------------
-- Table `EasyGoDB`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EasyGoDB`.`users` (
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `avatar` VARCHAR(127),
  PRIMARY KEY (`login`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `EasyGoDB`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EasyGoDB`.`users` (
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `avatar` VARCHAR(127),
  PRIMARY KEY (`login`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EasyGoDB`.`gomaps`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EasyGoDB`.`gomaps` (
  `map_id` int not null primary key auto_increment,
  `owner_login` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `tags` VARCHAR(45) NOT NULL,
  `map_attributes` VARCHAR(45) NOT NULL,
  `is_private` TINYINT(1) NULL,
  CONSTRAINT `owner_login`
    FOREIGN KEY (`owner_login`)
    REFERENCES `EasyGoDB`.`users` (`login`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EasyGoDB`.`points`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EasyGoDB`.`points` (
  `point_id` int not null primary key auto_increment,
  `x` FLOAT NOT NULL,
  `y` FLOAT NOT NULL,
  `name` VARCHAR(60) NOT NULL,
  `map_id` int NOT NULL,
  `attribute_values` VARCHAR(255) NULL,
  CONSTRAINT `id`
    FOREIGN KEY (`map_id`)
    REFERENCES `EasyGoDB`.`gomaps` (`map_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `EasyGoDB`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `EasyGoDB`;
INSERT INTO `EasyGoDB`.`users` (`login`, `password`, `name`) VALUES ('olhaR', '0506909637', 'olha', '#');
INSERT INTO `EasyGoDB`.`users` (`login`, `password`, `name`) VALUES ('olehG', '1234', 'oleg', '#');
INSERT INTO `EasyGoDB`.`users` (`login`, `password`, `name`) VALUES ('aleksS', '1234', 'alesks', '#');
INSERT INTO `EasyGoDB`.`users` (`login`, `password`, `name`) VALUES ('AnnaN', '1234', 'anna', '#');
INSERT INTO `EasyGoDB`.`users` (`login`, `password`, `name`) VALUES ('IraP', '1234', 'ira', '#');
INSERT INTO `EasyGoDB`.`users` (`login`, `password`, `name`) VALUES ('AnastasiaF', '1234', 'anastasia', '#');
INSERT INTO `EasyGoDB`.`users` (`login`, `password`, `name`) VALUES ('IhorK', '1234', 'ihor', '#');

COMMIT;

-- -----------------------------------------------------
-- Data for table `EasyGoDB`.`gomaps`
-- -----------------------------------------------------
START TRANSACTION;
USE `EasyGoDB`;
INSERT INTO `EasyGoDB`.`gomaps` (`map_id`, `owner_login`, `name`, `tags`, `map_attributes`, `is_private`) VALUES ('0', '0', 'ATMs', 'ATMs', 'look for this ATMs', true);
INSERT INTO `EasyGoDB`.`gomaps` (`owner_login`, `name`, `tags`, `map_attributes`, `is_private`) VALUES ('0', 'Coffee', 'Cup', 'look for this coffee', true);
INSERT INTO `EasyGoDB`.`gomaps` (`owner_login`, `name`, `tags`, `map_attributes`, `is_private`) VALUES ('2', 'WaterAutomats', 'Water', 'look for this water', false);

COMMIT;


-- -----------------------------------------------------
-- Data for table `EasyGoDB`.`points`
-- -----------------------------------------------------
START TRANSACTION;
USE `EasyGoDB`;
INSERT INTO `EasyGoDB`.`points` (`point_id`, `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ('0', -24, 6, 'Privat', '0', 'ATM');
INSERT INTO `EasyGoDB`.`points` (`x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( -15, 3, 'GasBank', '0', 'ATMGasBank');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 12, 8, 'UniCredit', '0', 'ATMUniCredit');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES (6, -3, 'UniCredit', '0', 'ATMUniCredit2');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( -20, 21, 'GasBank', '0', 'ATMGasBank');
INSERT INTO `EasyGoDB`.`points` (`x`, `y`, `name`, `map_id`, `attribute_values`) VALUES (3, -1, 'Privat', '0', 'ATMPrivat');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 0, 13, 'Privat', '0', 'ATMPrivat');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( -23, 14, 'Privat', '0', 'ATMPrivat');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 28, 12, 'Caffka', '1', 'coff');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 10, 9, 'CupCoff', '1', 'cococogood');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 4, 7, 'CoffeeMat', '1', 'good capuchino');
INSERT INTO `EasyGoDB`.`points` (`x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 22, -17, 'CoffeeGo', '1', 'just try');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 23, -6, 'Tea&Coffee', '1', NULL);
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 11, 16, 'Caffka', '1', 'caaffka2');
INSERT INTO `EasyGoDB`.`points` (`x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 15, -3, 'Caffka', '1', 'caaffka3');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( -16, -11, 'StarBucks', '1', 'For your instagrsm');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 13, -9, 'StarBucks', '1', 'more photos');
INSERT INTO `EasyGoDB`.`points`( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( -4, 6, 'CoffeeMat', '1', 'coffemat2 ');
INSERT INTO `EasyGoDB`.`points` (`x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 18, 16, 'filin', '1', 'fill your morning');
INSERT INTO `EasyGoDB`.`points` (`x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( -15, 22, 'coff3', '1', 'IT caffe');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES (26, -1, 'water', '2', NULL);
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( -1, 0, 'water', '2', '73 k/l');
INSERT INTO `EasyGoDB`.`points` (`x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 15, -12, 'water1', '2', 'allways water');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( -6, 16, '2', '2', NULL);
INSERT INTO `EasyGoDB`.`points` (`x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 19, 21, 'water3', '2', '77 k/l');
INSERT INTO `EasyGoDB`.`points` (`x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( -4, 18, 'water4', '2', '71 k/l');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 27, -3, 'water5', '2', '77 k/l');
INSERT INTO `EasyGoDB`.`points` (`x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 18, 4, 'water6', '2', '70 k/l');
INSERT INTO `EasyGoDB`.`points` ( `x`, `y`, `name`, `map_id`, `attribute_values`) VALUES (20, 12, 'water7', '2', NULL);
INSERT INTO `EasyGoDB`.`points` (`x`, `y`, `name`, `map_id`, `attribute_values`) VALUES ( 17, 15, 'water8', '2', '72 k/l');

COMMIT;
