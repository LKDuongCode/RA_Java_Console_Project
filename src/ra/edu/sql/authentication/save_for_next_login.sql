use recruitment_management;

DELIMITER $$

CREATE PROCEDURE sp_remember_admin(IN p_email VARCHAR(255))
BEGIN
    DECLARE v_adminId INT;

SELECT id INTO v_adminId
FROM admin
WHERE email = p_email;


UPDATE remembered_admin
SET adminId = v_adminId
WHERE id = 1;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_remember_candidate(IN p_email VARCHAR(255))
BEGIN
    DECLARE v_candidateId INT;

    SELECT id INTO v_candidateId
    FROM candidate
    WHERE email = p_email;

    UPDATE remembered_candidate
    SET candidateId = v_candidateId
    WHERE id = 1;
END$$

DELIMITER ;

