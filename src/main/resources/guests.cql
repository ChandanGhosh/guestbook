CREATE TABLE IF NOT EXISTS guests (
  id int,
  name text,
  address text,
  age int,
  PRIMARY KEY(id)
);

INSERT INTO guests (id, name, address, age) VALUES(1, 'Chandan', 'Baidyapur', 37);