package com.aie.thrillio.service;

import com.aie.thrillio.constants.BookGenre;
import com.aie.thrillio.constants.KidFriendlyStatus;
import com.aie.thrillio.constants.MovieGenre;
import com.aie.thrillio.dao.BookmarksDao;
import com.aie.thrillio.entity.*;

public class BookmarkService {
    private static BookmarkService instance = new BookmarkService();
    private static BookmarksDao dao = new BookmarksDao();

    private BookmarkService() {
    }

    public static BookmarkService getInstance() {
        return instance;
    }

    public Movie createMovie(long id, String title, int releaseYear, String[] cast, String[] directors, MovieGenre genre, double imdbRating) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setReleaseYear(releaseYear);
        movie.setCast(cast);
        movie.setDirectors(directors);
        movie.setGenre(genre);
        movie.setImdbRating(imdbRating);

        return movie;
    }

    public Book createBook(long id, String title, int publicationYear, String publisher, String[] authors, BookGenre genre, Double amazonRating) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setPublicationYear(publicationYear);
        book.setPublisher(publisher);
        book.setAuthors(authors);
        book.setGenre(genre);
        book.setAmazonRating(amazonRating);

        return book;
    }

    public WebLink createWebLink(long id, String title, String profileUrl, String url, String host) {
        WebLink webLink = new WebLink();
        webLink.setId(id);
        webLink.setTitle(title);
        webLink.setProfileUrl(profileUrl);
        webLink.setUrl(url);
        webLink.setHost(host);

        return webLink;
    }

    public Bookmark[][] getBookmarks(){
        return dao.getBookmarks();
    }

    public void saveUserBookmark(User user, Bookmark bookmark) {
        UserBookmark userBookmark = new UserBookmark();
        userBookmark.setBookmark(bookmark);
        userBookmark.setUser(user);

        dao.saveUserBookmark(userBookmark);
    }

    public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
        bookmark.setKidFriendlyStatus(kidFriendlyStatus);
        bookmark.setKidFriendlyMarkedBy(user);
        System.out.println("Kid Friendly Status " + kidFriendlyStatus + " , Marked by " + user.getEmail() + " , " + bookmark );
    }

    public void share(User user, Bookmark bookmark) {
        bookmark.setSharedBy(user);

        System.out.println("Data to be shared: ");
        if(bookmark instanceof  Book){
            System.out.println(((Book) bookmark).getItemData());
        } else if (bookmark instanceof  WebLink){
            System.out.println(((WebLink) bookmark).getItemData());
        }
    }
}
