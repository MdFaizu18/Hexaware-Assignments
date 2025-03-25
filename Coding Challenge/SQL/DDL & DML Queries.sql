CREATE DATABASE VirtualArtGallery;
USE VirtualArtGallery;

-- To Create Tables 
CREATE TABLE Artists ( 
      ArtistID INT PRIMARY KEY, 
      Name VARCHAR(255) NOT NULL, 
      Biography TEXT, 
      Nationality VARCHAR(100)
);

CREATE TABLE Categories ( 
      CategoryID INT PRIMARY KEY, 
      Name VARCHAR(100) NOT NULL
);

CREATE TABLE Artworks ( 
      ArtworkID INT PRIMARY KEY, 
      Title VARCHAR(255) NOT NULL, 
      ArtistID INT, 
      CategoryID INT, 
      Year INT, 
      Description TEXT, 
      ImageURL VARCHAR(255), 
      FOREIGN KEY (ArtistID) REFERENCES Artists (ArtistID), 
      FOREIGN KEY (CategoryID) REFERENCES Categories (CategoryID)
);

CREATE TABLE Exhibitions ( 
      ExhibitionID INT PRIMARY KEY, 
      Title VARCHAR(255) NOT NULL, 
      StartDate DATE, 
      EndDate DATE, 
      Description TEXT
); 

CREATE TABLE ExhibitionArtworks ( 
      ExhibitionID INT, 
      ArtworkID INT, 
      PRIMARY KEY (ExhibitionID, ArtworkID), 
      FOREIGN KEY (ExhibitionID) REFERENCES Exhibitions (ExhibitionID), 
      FOREIGN KEY (ArtworkID) REFERENCES Artworks (ArtworkID)
); 

SHOW tables;

-- To insert sample data to tables 
INSERT INTO Artists (ArtistID, Name, Biography, Nationality) VALUES 
(1, 'Pablo Picasso', 'Renowned Spanish painter and sculptor.', 'Spanish'), 
(2, 'Vincent van Gogh', 'Dutch post-impressionist painter.', 'Dutch'), 
(3, 'Leonardo da Vinci', 'Italian polymath of the Renaissance.', 'Italian'),
(4, 'Claude Monet', 'French impressionist painter.', 'French'), 
(5, 'Salvador Dalí', 'Spanish surrealist artist.', 'Spanish'),
(6, 'Frida Kahlo', 'Mexican painter known for self-portraits.', 'Mexican'),
(7, 'Andy Warhol', 'American pop art pioneer.', 'American');

INSERT INTO Categories (CategoryID, Name) VALUES 
(1, 'Painting'), 
(2, 'Sculpture'), 
(3, 'Photography'),
(4, 'Surrealism'), 
(5, 'Impressionism'), 
(6, 'Abstract');

INSERT INTO Artworks (ArtworkID, Title, ArtistID, CategoryID, Year, Description, ImageURL) VALUES 
(1, 'Starry Night', 2, 1, 1889, 'A famous painting by Vincent van Gogh.', 'starry_night.jpg'), 
(2, 'Mona Lisa', 3, 1, 1503, 'The iconic portrait by Leonardo da Vinci.', 'mona_lisa.jpg'), 
(3, 'Guernica', 1, 1, 1937, 'Pablo Picasso\'s powerful anti-war mural.', 'guernica.jpg'),
(4, 'The Persistence of Memory', 5, 4, 1931, 'Famous surrealist painting by Salvador Dalí.', 'persistence_memory.jpg'),
(5, 'Water Lilies', 4, 5, 1919, 'Series of impressionist paintings by Claude Monet.', 'water_lilies.jpg'),
(6, 'The Two Fridas', 6, 6, 1939, 'A self-portrait by Frida Kahlo.', 'the_two_fridas.jpg'),
(7, 'Campbell’s Soup Cans', 7, 6, 1962, 'Pop art series by Andy Warhol.', 'campbells_soup.jpg'),
(8, 'Self-Portrait with Thorn Necklace', 6, 6, 1940, 'Famous self-portrait by Frida Kahlo.', 'thorn_necklace.jpg'); 

INSERT INTO Exhibitions (ExhibitionID, Title, StartDate, EndDate, Description) VALUES 
(1, 'Modern Art Masterpieces', '2023-01-01', '2023-03-01', 'A collection of modern art masterpieces.'), 
(2, 'Renaissance Art', '2023-04-01', '2023-06-01', 'A showcase of Renaissance art treasures.'),
(3, 'Surrealism and Beyond', '2023-07-01', '2023-09-01', 'Exploring the surrealist movement.'), 
(4, 'Impressionist Wonders', '2023-10-01', '2023-12-01', 'Featuring the best of Impressionism.');

INSERT INTO ExhibitionArtworks (ExhibitionID, ArtworkID) VALUES 
(1, 1), 
(1, 2), 
(1, 3), 
(2, 2),
(3, 4), 
(3, 6), 
(4, 5);  

-- To see every table with data
SELECT * FROM Artists;
SELECT * FROM Categories;
SELECT * FROM Artworks;
SELECT * FROM Exhibitions;
SELECT * FROM ExhibitionArtworks;

DROP TABLE Artists;
DROP TABLE Categories;
DROP TABLE Artworks;
DROP TABLE Exhibitions;
DROP TABLE ExhibitionArtworks;