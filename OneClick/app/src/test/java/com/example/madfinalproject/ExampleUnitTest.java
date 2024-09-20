package com.example.madfinalproject;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void checkPasswordEquality_isCorrect(){
        String password = "123456";
        String password2= "123456";
        assertTrue(PasswordCreator.checkPasswordEquality(password,password2));
    }
    @Test
    public void checkPasswordEquality_isFalse(){
        String password = "123456";
        String password2= "abcdefg";
        assertFalse(PasswordCreator.checkPasswordEquality(password,password2));
    }
}