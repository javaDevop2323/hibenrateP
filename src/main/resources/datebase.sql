BEGIN ;
INSERT INTO owners (dateofbirth, id, email, first_name, gender, last_name)
VALUES
    ('1985-03-15', 1, 'john.doe@example.com', 'John', 'MALE', 'Doe'),
    ('1990-07-22', 2, 'jane.smith@example.com', 'Jane', 'FEMALE', 'Smith'),
    ('1982-11-30', 3, 'alex.johnson@example.com', 'Alex', 'MALE', 'Johnson'),
    ('1978-05-10', 4, 'mary.brown@example.com', 'Mary', 'FEMALE', 'Brown'),
    ('1995-09-14', 5, 'emily.davis@example.com', 'Emily', 'FEMALE', 'Davis'),
    ('1988-12-25', 6, 'michael.wilson@example.com', 'Michael', 'MALE', 'Wilson'),
    ('1992-01-30', 7, 'lisa.miller@example.com', 'Lisa', 'FEMALE', 'Miller'),
    ('1980-04-20', 8, 'david.lee@example.com', 'David', 'MALE', 'Lee'),
    ('1998-10-05', 9, 'sarah.anderson@example.com', 'Sarah', 'FEMALE', 'Anderson'),
    ('1986-08-16', 10, 'james.martin@example.com', 'James', 'MALE', 'Martin');

INSERT INTO houses (furniture, price, rating, room, address_id, id, owner_id, description, housetype)
VALUES
    ('Furnished', 250000.00, 4.5, 3, 1, 1, 1, 'Spacious detached house with modern amenities.', 'DETACHED'),
    ('Semi-Furnished', 180000.00, 4.0, 2, 2, 2, 2, 'Cozy semi-detached house with a garden.', 'SEMI_DETACHED'),
    ('Unfurnished', 150000.00, 3.8, 2, 3, 3, 3, 'Compact terraced house in a quiet neighborhood.', 'TERRACED'),
    ('Furnished', 200000.00, 4.2, 3, 4, 4, 4, 'Beautiful bungalow with a large backyard.', 'BUNGALOW'),
    ('Fully Furnished', 300000.00, 4.9, 4, 5, 5, 5, 'Luxury apartment in the city center.', 'APARTMENT'),
    ('Furnished', 220000.00, 4.1, 3, 6, 6, 6, 'Charming cottage with scenic views.', 'COTTAGE'),
    ('Unfurnished', 275000.00, 4.6, 4, 7, 7, 7, 'Elegant villa with a swimming pool.', 'VILLA'),
    ('Furnished', 190000.00, 4.0, 3, 8, 8, 8, 'Stylish apartment with modern finishes.', 'APARTMENT'),
    ('Semi-Furnished', 160000.00, 3.9, 2, 9, 9, 9, 'Cozy cottage with rustic charm.', 'COTTAGE'),
    ('Furnished', 240000.00, 4.3, 3, 10, 10, 10, 'Spacious detached house with a home office.', 'DETACHED');
COMMIT ;
