/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palindorme.rest.adapters;

import org.junit.Test;
import org.palindrome.rest.adapters.PalindromeResource;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author andrewavetisov
 */
public class PalindromeResourceTest {

    @Test
    public void getterAndSetterTest() {
        PalindromeResource userPalindorm = new PalindromeResource();
        assertEquals("PalindromeResourceTest", 0, userPalindorm.getPlayerID());
        assertEquals("PalindromeResourceTest", null, userPalindorm.getWord());

        userPalindorm.setPlayerID(1);
        userPalindorm.setWord("wow");
        assertEquals("PalindromeResourceTest", 1, userPalindorm.getPlayerID());
        assertEquals("PalindromeResourceTest", "wow", userPalindorm.getWord());
    }
}
