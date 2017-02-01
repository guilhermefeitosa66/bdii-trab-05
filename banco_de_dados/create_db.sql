-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema db_trab_05
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_trab_05
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_trab_05` DEFAULT CHARACTER SET utf8 ;
USE `db_trab_05` ;

-- -----------------------------------------------------
-- Table `db_trab_05`.`tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_trab_05`.`tasks` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  `done` TINYINT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
