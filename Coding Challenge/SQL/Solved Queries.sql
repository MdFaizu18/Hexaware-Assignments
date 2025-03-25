-- Solve Queries

-- 1. To Retrieve the names of all artists along with the number of artworks they have in the gallery, and list them in descending order of the number of artworks. 
SELECT a.Name, COUNT(b.ArtistID) AS No_of_Artworks
FROM artists a
JOIN Artworks b ON a.ArtistID=b.ArtistID
GROUP BY a.ArtistID
ORDER BY No_of_Artworks	DESC;

-- 2. List the titles of artworks created by artists from 'Spanish' and 'Dutch' nationalities, and order them by the year in ascending order. 
SELECT artworks.*
FROM artworks
JOIN artists ON artworks.ArtistID = artists.ArtistID
WHERE artists.Nationality = 'Spanish' OR artists.Nationality = 'Dutch'
ORDER BY artworks.Year ASC;

-- 3. Find the names of all artists who have artworks in the 'Painting' category, and the number of artworks they have in this category. 
SELECT a.Name, COUNT(b.ArtistID) AS Artworks_in_Painting
FROM artists a
JOIN Artworks b ON a.ArtistID=b.ArtistID
JOIN Categories c ON b.CategoryID = c.CategoryID
WHERE c.Name = 'Painting'
GROUP BY a.Name
ORDER BY Artworks_in_Painting DESC;

-- 4. To List the names of artworks from the 'Modern Art Masterpieces' exhibition, along with their artists and categories. 
SELECT b.Title,a.Name AS artist_name,c.Name AS category_name
FROM artworks b
JOIN artists a ON b.ArtistID = a.ArtistID
JOIN categories c ON b.CategoryID = c.CategoryID
JOIN ExhibitionArtworks e ON b.ArtworkID = e.ArtworkID
WHERE e.ExhibitionID IN (
        SELECT ExhibitionID
        FROM Exhibitions
        WHERE Title = 'Modern Art Masterpieces'
);

-- 5. To Find the artists who have more than two artworks in the gallery. 
SELECT a.Name AS ArtistName
FROM Artists a
JOIN Artworks b ON a.ArtistID = b.ArtistID
GROUP BY a.ArtistID
HAVING COUNT(b.ArtworkID) > 2;


-- 6. Find the titles of artworks that were exhibited in both 'Modern Art Masterpieces' and 'Renaissance Art' exhibitions 
SELECT b.Title AS ArtworkTitle
FROM Artworks b
JOIN ExhibitionArtworks e ON b.ArtworkID = e.ArtworkID
JOIN Exhibitions ex ON e.ExhibitionID = ex.ExhibitionID
WHERE ex.Title IN ('Modern Art Masterpieces', 'Renaissance Art')
GROUP BY b.ArtworkID, b.Title
HAVING COUNT(DISTINCT ex.ExhibitionID) = 1;

-- 7. Find the total number of artworks in each category
SELECT c.Name AS Category, COUNT(a.ArtworkID) AS TotalArtworks
FROM Categories c
LEFT JOIN Artworks a ON c.CategoryID = a.CategoryID
GROUP BY c.Name;

-- 8. List artists who have more than 3 artworks in the gallery.
SELECT a.Name AS artist_name , COUNT(b.ArtworkID) AS Total_artwork
FROM artists a
JOIN artworks b ON a.ArtistID = b.ArtistID
GROUP BY a.ArtistID
HAVING COUNT(b.ArtistID) >3;

-- 9. Find the artworks created by artists from a specific nationality (e.g., Spanish). 
SELECT a.ArtistID,a.Name as Artist_Name,b.*
FROM artists a
JOIN artworks b ON a.ArtistID = b.ArtistID
WHERE a.Nationality = 'Dutch';

-- 10. List exhibitions that feature artwork by both Vincent van Gogh and Leonardo da Vinci. 
SELECT e.*
FROM exhibitions e
JOIN ExhibitionArtworks ex ON e.ExhibitionID = ex.ExhibitionID
JOIN Artworks b ON ex.ArtworkID = b.ArtworkID
JOIN artists a ON b.ArtistID = a.ArtistID
WHERE a.Name IN ('Vincent van Gogh', 'Leonardo da Vinci')
GROUP BY e.ExhibitionID
HAVING COUNT(DISTINCT a.Name) = 2;

-- 11. Find all the artworks that have not been included in any exhibition. 
SELECT a.*
FROM Artworks a
JOIN ExhibitionArtworks ex ON a.ArtworkID = ex.ArtworkID
WHERE ex.ExhibitionID IS NULL;

-- 12. List artists who have created artworks in all available categories. 
SELECT a.Name
FROM Artists a
JOIN Artworks art ON a.ArtistID = art.ArtistID
JOIN Categories c ON art.CategoryID = c.CategoryID
GROUP BY a.ArtistID, a.Name
HAVING COUNT(DISTINCT art.CategoryID) =(SELECT COUNT(*) FROM Categories);

-- 13. List the total number of artworks in each category. 
SELECT c.Name as Category, COUNT(b.ArtworkID) as Total_Artworks
FROM categories c
LEFT JOIN artworks b ON c.CategoryID = b.CategoryID
GROUP BY Category;

-- 14. Find the artists who have more than 2 artworks in the gallery.
SELECT a.Name AS artist_name , COUNT(b.ArtworkID) AS Total_artwork
FROM artists a
JOIN artworks b ON a.ArtistID = b.ArtistID
GROUP BY a.ArtistID
HAVING COUNT(b.ArtistID) >=2;

-- 15. categories with the average year of artworks they contain, only for categories with more than 1 artwork.
SELECT c.Name AS Category, AVG(a.Year) AS Avg_Year
FROM Artworks a
JOIN Categories c ON a.CategoryID = c.CategoryID
GROUP BY c.CategoryID, c.Name
HAVING COUNT(a.ArtworkID) > 1;

-- 16.  Find the artworks that were exhibited in the 'Modern Art Masterpieces' exhibition. 
SELECT a.*
FROM artworks a
JOIN ExhibitionArtworks ex on a.ArtworkID = ex.ArtworkID
JOIN Exhibitions e ON ex.ExhibitionID = e.ExhibitionID
WHERE e.Title = 'Modern Art Masterpieces';

-- 17. categories where the average year of artworks is greater than the average year of all 
SELECT c.Name AS Category, AVG(a.Year) AS Avg_Year
FROM Artworks a
JOIN Categories c ON a.CategoryID = c.CategoryID
GROUP BY c.CategoryID, c.Name
HAVING AVG(a.Year) > (SELECT AVG(Year) FROM Artworks);


-- 18. List the artworks that were not exhibited in any exhibition.
SELECT a.Title
FROM Artworks a
LEFT JOIN ExhibitionArtworks ea ON a.ArtworkID = ea.ArtworkID
WHERE ea.ExhibitionID IS NULL;

-- 19. Show artists who have artworks in the same category as "Mona Lisa." 
SELECT a.Name as Artist_name
FROM artists a
JOIN artworks b on a.ArtistID = b.ArtistID
WHERE b.CategoryID IN (
       SELECT CategoryID
       from artworks
       WHERE Title = 'Mona Lisa'
);

-- 20. List the names of artists and the number of artworks they have in the gallery.
SELECT a.Name as Artist_name , COUNT(b.ArtworkID) AS TotalCount
FROM artists a
JOIN artworks b ON a.ArtistID = b.ArtistID
GROUP by Artist_name
ORDER BY TotalCount DESC;

