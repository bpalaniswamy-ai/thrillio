package com.aie.thrillio;

import com.aie.thrillio.entity.Bookmark;
import com.aie.thrillio.entity.User;
import com.aie.thrillio.service.BookmarkService;
import com.aie.thrillio.service.UserService;

public class Launch {
    private static User[] users;
    private static Bookmark[][] bookmarks;

    public static void loadData() {
        System.out.println("Loading data 1 ...");
        DataStore.loadData();

        users = UserService.getInstance().getUsers();
        bookmarks = BookmarkService.getInstance().getBookmarks();

        /*for (User user : users) {
            System.out.println(user);
        }

        for (Bookmark[] bookmark1 : bookmarks) {
            for (Bookmark bookmark2 : bookmark1) {
                System.out.println(bookmark2);
            }
        }*/
    }

    private static void start() {
        System.out.println("\n2. Bookmarking...");
        for (User user : users){
            View.browse(user, bookmarks);
        }
    }

    public static void main(String[] args) {
        loadData();
        start();
    }


}