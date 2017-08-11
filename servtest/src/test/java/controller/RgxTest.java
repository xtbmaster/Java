package controller;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class RgxTest {

        public static final String FILTER_A = "^A.*$";
        public static final String FILTER_B = "^T.*$";
        public static final String FILTER_C = "^.*[hklr].*$";

        UserManager m = new UserManager();

        @Test
        public void methodShouldShowStringWithoutRegex() throws Exception {
            assertFalse(m.findUsers(FILTER_A).contains("A"));
            assertFalse(m.findUsers(FILTER_B).contains("T"));
            assertFalse(m.findUsers(FILTER_C).contains("h"));
            assertFalse(m.findUsers(FILTER_C).contains("k"));
            assertFalse(m.findUsers(FILTER_C).contains("l"));
            assertFalse(m.findUsers(FILTER_C).contains("r"));
        }
    }
