package com.aie.thrillio.entity;

import com.aie.thrillio.service.BookmarkService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class WebLinkTest {

    @Test
    public void testIsKidFriendlyEligible(){
        // Test 1: porn in url -- false
        WebLink webLink = BookmarkService.getInstance().createWebLink(2000,	"Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--porn-2.html",	"http://www.javaworld.com",	"unknown");

        boolean isKidFriendlyEligible = webLink.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible, "For porn in url - isKidFriendlyEligible() must return false" );

        // Test 2: porn in title -- false
        webLink = BookmarkService.getInstance().createWebLink(2000,	"Taming porn, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworld.com",	"unknown");

         isKidFriendlyEligible = webLink.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible, "For porn in title - isKidFriendlyEligible() must return false");

        // Test 3: adult in host -- false
        webLink = BookmarkService.getInstance().createWebLink(2000,	"Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.java.com",	"adult");

        isKidFriendlyEligible = webLink.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible, "For adult in host - isKidFriendlyEligible() must return false" );

        // Test 4: adult in url but not in host part -- true
        webLink = BookmarkService.getInstance().createWebLink(2000,	"Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--adult-2.html",	"http://www.javaworld.com",	"unknown");

        isKidFriendlyEligible = webLink.isKidFriendlyEligible();

        assertTrue(isKidFriendlyEligible, "For adult in url but not in host part - isKidFriendlyEligible() must return true");

        // Test 5: adult in title only -- true
        webLink = BookmarkService.getInstance().createWebLink(2000,	"Taming adult, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworld.com",	"unknown");

        isKidFriendlyEligible = webLink.isKidFriendlyEligible();

        assertTrue(isKidFriendlyEligible, "For adult in url - isKidFriendlyEligible() must return true");
    }
}