package com.aie.thrillio.controller;

import com.aie.thrillio.entity.Bookmark;
import com.aie.thrillio.entity.User;
import com.aie.thrillio.service.BookmarkService;

public class BookmarkController {
    private static BookmarkController instance = new BookmarkController();

    private BookmarkController(){}

    public static BookmarkController getInstance() {
        return instance;
    }

    public void saveUserBookmark(User user, Bookmark bookmark) {
        BookmarkService.getInstance().saveUserBookmark(user, bookmark);
    }

    public void setKidFriendlyStatus(User user, String kidFriendlyStatus, Bookmark bookmark) {
        BookmarkService.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
    }

    public void share(User user, Bookmark bookmark) {
        BookmarkService.getInstance().share(user, bookmark);
    }
}
