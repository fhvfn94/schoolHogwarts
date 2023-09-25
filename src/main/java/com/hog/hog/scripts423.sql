/*Получить информацию обо всех студентах из Хогвартс с их факультетами*/
SELECT Student.Name, Student.Age, Faculty.Name AS FacultyName
FROM Student
INNER JOIN Faculty ON Student.FacultyID = Faculty.FacultyID
WHERE Faculty.School = 'Хогвартс';

/*Получить имена и возраст студентов у которых есть аватарки*/
SELECT Student.Name, Student.Age
FROM Student
INNER JOIN Avatar ON Student.StudentID = Avatar.StudentID;