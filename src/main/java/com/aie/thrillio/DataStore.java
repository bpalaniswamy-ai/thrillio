package com.aie.thrillio;

import com.aie.thrillio.constants.BookGenre;
import com.aie.thrillio.constants.Gender;
import com.aie.thrillio.constants.MovieGenre;
import com.aie.thrillio.entity.Bookmark;
import com.aie.thrillio.entity.User;
import com.aie.thrillio.entity.UserBookmark;
import com.aie.thrillio.service.BookmarkService;
import com.aie.thrillio.service.UserService;
import com.aie.thrillio.util.IOUtil;

import java.sql.*;

public class DataStore {
    public static final int USER_BOOKMARK_LIMIT = 5;
    public static final int BOOKMARK_COUNTS_PER_TYPE = 5;
    public static final int BOOKMARK_TYPES_COUNT = 3;
    public static final int TOTAL_USER_COUNT = 5;


    private static User[] users = new User[TOTAL_USER_COUNT];

    public static User[] getUsers() {
        return users;
    }

    private static Bookmark[][] bookmarks = new Bookmark[BOOKMARK_TYPES_COUNT][BOOKMARK_COUNTS_PER_TYPE];

    public static Bookmark[][] getBookmarks() {
        return bookmarks;
    }

    private static UserBookmark[] userBookmarks = new UserBookmark[TOTAL_USER_COUNT * USER_BOOKMARK_LIMIT];

    public static UserBookmark[] getUserBookmarks() {
        return userBookmarks;
    }

    private static int bookmarkIndex;

    public static void loadData(){

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "Admin@135");
             Statement stmt = conn.createStatement();) {
            loadUsers();
            loadWebLinks();
            loadMovies(stmt);
            loadBooks(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void loadUsers() {
        String[] data = new String[TOTAL_USER_COUNT];
        IOUtil.read(data, "C:\\Users\\Balaji\\OneDrive - AIEnterprise Inc\\Thrillio\\src\\main\\User.txt");
        int rowNum = 0;
        for(String row : data){
            String[] values = row.split("\t");

            Gender gender = Gender.MALE;
            if(values[5].equals("f")){
                gender = Gender.FEMALE;
            }else if(values[5].equals("t")){
                gender = Gender.TRANSGENDER;
            }
            users[rowNum++] = UserService.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], gender, values[6]);
        }
    }

    private static void loadWebLinks() {
        String[] data = new String[BOOKMARK_COUNTS_PER_TYPE];
        IOUtil.read(data, "C:\\Users\\Balaji\\OneDrive - AIEnterprise Inc\\Thrillio\\src\\main\\Web+Link.txt");
        int colNum = 0;
        for (String row : data) {
            String[] values = row.split("\t");
            bookmarks[0][colNum++] = BookmarkService.getInstance().createWebLink( Long.parseLong(values[0]), values[1], values[2], values[3], values[4]);
        }
    }

    private static void loadMovies(Statement stmt) throws SQLException{

        String query = "Select m.id, title, release_year, GROUP_CONCAT(DISTINCT a.name SEPARATOR ',') AS cast, GROUP_CONCAT(DISTINCT d.name SEPARATOR ',') AS directors, movie_genre_id, imdb_rating"
                + " from Movie m, Actor a, Movie_Actor ma, Director d, Movie_Director md "
                + "where m.id = ma.movie_id and ma.actor_id = a.id and "
                + "m.id = md.movie_id and md.director_id = d.id group by m.id";
        ResultSet rs = stmt.executeQuery(query);
        int colNum = 0;

        while (rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            int releaseYear = rs.getInt("release_year");
            String[] cast = rs.getString("cast").split(",");
            String[] directors = rs.getString("directors").split(",");
            int genre_id = rs.getInt("movie_genre_id");
            MovieGenre genre = MovieGenre.values()[genre_id];
            double imdbRating = rs.getDouble("imdb_rating");

            Bookmark bookmark = BookmarkService.getInstance().createMovie(id, title, releaseYear, cast, directors, genre, imdbRating/*, values[7]*/);
            bookmarks[1][colNum++] = bookmark;
        }
    }

    private static void loadBooks(Statement stmt) throws SQLException {

        String query = "Select b.id, title, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, amazon_rating, created_date"
                + " from Book b, Publisher p, Author a, Book_Author ba "
                + "where b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id";
        ResultSet rs = stmt.executeQuery(query);
        int colNum = 0;

        while (rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            int publicationYear = rs.getInt("publication_year");
            String publisher = rs.getString("name");
            String[] authors = rs.getString("authors").split(",");
            int genre_id = rs.getInt("book_genre_id");
            BookGenre genre = BookGenre.values()[genre_id];
            double amazonRating = rs.getDouble("amazon_rating");


            System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear + ", publisher: " + publisher + ", authors: " + String.join(", ", authors) + ", genre: " + genre + ", amazonRating: " + amazonRating);

            Bookmark bookmark = BookmarkService.getInstance().createBook(id, title, publicationYear, publisher, authors, genre, amazonRating/*, values[7]*/);
            bookmarks[2][colNum++] = bookmark;
        }
    }
    public static void add(UserBookmark userBookmark) {
        userBookmarks[bookmarkIndex] = userBookmark;
        bookmarkIndex++;
    }
}
