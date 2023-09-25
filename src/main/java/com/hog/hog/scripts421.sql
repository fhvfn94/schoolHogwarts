/*Возраст студента не может быть меньше 16 лет.*/
ALTER TABLE Student
ADD CONSTRAINT age_constraint CHECK (age >= 16);

/*Имена студентов должны быть уникальными и не равны нулю.*/
ALTER  TABLE Student
ADD CONSTRAINT name_unique UNIQUE (name);


/*Пара “значение названия” - “цвет факультета” должна быть уникальной.*/
ALTER TABLE Faculty
ADD CONSTRAINT UQ_Faculty_Name_Color UNIQUE (name, color);

/*При создании студента без возраста ему автоматически должно присваиваться 20 лет.*/
ALTER TABLE Student
ALTER COLUMN Age SET DEFAULT 20;
