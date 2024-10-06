USE leave;
CREATE TABLE IF NOT EXISTS leave (
    id VARCHAR PRIMARY KEY,
    studentId INT NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    status VARCHAR,
    // Other leave details
    FOREIGN KEY (studentId) REFERENCES student(id)
);

CREATE TABLE IF NOT EXISTS student (
    id VARCHAR PRIMARY KEY,
    studentName VARCHAR NOT NULL,
    enrollmentNumber VARCHAT NOT NULL
);