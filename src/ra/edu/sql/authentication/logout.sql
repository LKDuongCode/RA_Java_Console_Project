use recruitment_management;

DELIMITER $$

CREATE PROCEDURE sp_reset_remembered_admin ()
BEGIN
    UPDATE remembered_admin
    SET adminId = NULL
    WHERE id = 1;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_reset_remembered_candidate ()
BEGIN
    UPDATE remembered_candidate
    SET candidateId = NULL
    WHERE id = 1;
END$$

DELIMITER ;
