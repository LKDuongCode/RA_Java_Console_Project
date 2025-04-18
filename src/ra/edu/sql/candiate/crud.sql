use recruitment_management;
DELIMITER $$

CREATE PROCEDURE sp_find_candidate_by_id(IN p_id INT)
BEGIN
SELECT id, name, email, password, status
FROM candidate
WHERE id = p_id;
END$$

DELIMITER ;
