package sample.crypting.compilePck;

import org.junit.Test;

import static org.junit.Assert.*;
import static sample.crypting.compilePck.OctalCrypting.*;

public class OctalCryptingTest {

    private static final String
            A_STRING = "cheese", A_OCTAL = "143150145145163145",
            B_STRING = "water", B_OCTAL = "167141164145162",
            C_STRING = "sugar", C_OCTAL = "163165147141162",
            D_STRING = "peperoni", D_OCTAL = "160145160145162157156151",
            E_STRING = "fish", E_OCTAL = "146151163150";

    @Test
    public void octalDCryptTest () {
    assertEquals(A_STRING, octalDCrypt(A_OCTAL));
    assertEquals(B_STRING, octalDCrypt(B_OCTAL));
    assertEquals(C_STRING, octalDCrypt(C_OCTAL));
    assertEquals(D_STRING, octalDCrypt(D_OCTAL));
    assertEquals(E_STRING, octalDCrypt(E_OCTAL));
    }

    @Test
    public void octalNCryptTest () {
        assertEquals(A_OCTAL, octalNCrypt(A_STRING));
        assertEquals(B_OCTAL, octalNCrypt(B_STRING));
        assertEquals(C_OCTAL, octalNCrypt(C_STRING));
        assertEquals(D_OCTAL, octalNCrypt(D_STRING));
        assertEquals(E_OCTAL, octalNCrypt(E_STRING));
    }
}