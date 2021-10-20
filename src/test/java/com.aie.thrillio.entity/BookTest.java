package com.aie.thrillio.entity;


import com.aie.thrillio.constants.BookGenre;
import com.aie.thrillio.service.BookmarkService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class BookTest {
    @Test
    public void testIsKidFriendlyEligible(){

        //test 1 for philosophy in genre must return false
        Book book = BookmarkService.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications", new String[]{"Henry David Thoreau"}, BookGenre.PHILOSOPHY, 4.3);

        boolean isKidFriendlyEligible = book.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible, "for Philosophy in genre - isKidFriendlyEligible must return false");


        book = BookmarkService.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications", new String[]{"Henry David Thoreau"}, BookGenre.SELF_HELP, 4.3);

        isKidFriendlyEligible = book.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible, "for Philosophy in genre - isKidFriendlyEligible must return false");
    }
}
