use recruitment_management;

DELIMITER $$

CREATE PROCEDURE sp_get_admin_by_email (
    IN p_email VARCHAR(255)
)
BEGIN
    SELECT id, name, email, password, status
    FROM admin
    WHERE email = p_email;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_get_candidate_by_email (
    IN p_email VARCHAR(255)
)
BEGIN
    SELECT id, name, email, password, status
    FROM candidate
    WHERE email = p_email;
END$$

DELIMITER ;


