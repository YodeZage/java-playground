-- MySQL Script generated by MySQL Workbench
-- Thu Feb 17 00:01:05 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema java_playground
-- -----------------------------------------------------
-- This database is for java playground practice use.

-- -----------------------------------------------------
-- Schema java_playground
--
-- This database is for java playground practice use.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `java_playground` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `java_playground` ;

-- -----------------------------------------------------
-- Table `java_playground`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java_playground`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `create_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `effect_start_date` DATETIME NOT NULL,
  `effect_end_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java_playground`.`class`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java_playground`.`class` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `class_name` VARCHAR(255) NOT NULL,
  `credits` TINYINT NOT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java_playground`.`student_class_link`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java_playground`.`student_class_link` (
  `student_id` INT NOT NULL,
  `class_id` INT NOT NULL,
  INDEX `fk_student_class_link_student_idx` (`student_id` ASC) VISIBLE,
  INDEX `fk_student_class_link_class1_idx` (`class_id` ASC) VISIBLE,
  PRIMARY KEY (`student_id`, `class_id`),
  CONSTRAINT `fk_student_class_link_student`
    FOREIGN KEY (`student_id`)
    REFERENCES `java_playground`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_class_link_class1`
    FOREIGN KEY (`class_id`)
    REFERENCES `java_playground`.`class` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;