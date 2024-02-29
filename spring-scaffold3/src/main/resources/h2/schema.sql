CREATE TABLE users (
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(255),
      age INT
);
CREATE INDEX idx_users_id ON users (id);
/*ALTER TABLE test ADD INDEX idx_test_id (id);*/