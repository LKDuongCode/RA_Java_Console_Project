use recruitment_management;

DELIMITER $$

CREATE PROCEDURE insert_technology(IN p_name VARCHAR(100))
BEGIN
    INSERT INTO technology(name)
    VALUES (p_name);
END $$

DELIMITER ;


CREATE PROCEDURE update_technology(
    IN p_id INT,
    IN p_name VARCHAR(100),
    IN p_status ENUM('ACTIVE', 'DELETED')
)
BEGIN
    UPDATE technology
    SET
        name = IF(p_name IS NULL, name, p_name),
        status = IF(p_status IS NULL, status, p_status)
    WHERE id = p_id;
END;


DELIMITER $$
CREATE PROCEDURE find_technology_by_name(IN p_name VARCHAR(100))
BEGIN
    SELECT * FROM technology WHERE name = p_name;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE find_technology_by_id(IN p_id INT)
BEGIN
    SELECT * FROM technology WHERE id = p_id;
END $$

DELIMITER ;

