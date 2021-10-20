package com.aie.thrillio.entity;

import com.aie.thrillio.constants.MovieGenre;
import com.aie.thrillio.service.BookmarkService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MovieTest {

    @Test
    public void testIsKidFriendlyEligible(){
        //Test 1
        Movie movie = BookmarkService.getInstance().createMovie(3000, "Citizen Kane", 1941, new String[]{"Orson Welles","Joseph Cotten"}, new String[]{"Orson Welles"} , MovieGenre.HORROR,8.5);

        boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible, "for horror in genre - isKidFriendlyEligible() must return false");
        //Test 2

        movie = BookmarkService.getInstance().createMovie(3000, "Citizen Kane", 1941, new String[]{"Orson Welles","Joseph Cotten"}, new String[]{"Orson Welles"} , MovieGenre.THRILLERS ,8.5);

        isKidFriendlyEligible = movie.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible, "for thriller in genre - isKidFriendlyEligible() must return false");
    }
}
