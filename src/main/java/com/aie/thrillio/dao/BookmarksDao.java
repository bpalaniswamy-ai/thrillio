package com.aie.thrillio.dao;

import com.aie.thrillio.DataStore;
import com.aie.thrillio.entity.Bookmark;
import com.aie.thrillio.entity.UserBookmark;

public class BookmarksDao {
    public Bookmark[][] getBookmarks(){
       return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark) {
        DataStore.add(userBookmark);
    }
}
