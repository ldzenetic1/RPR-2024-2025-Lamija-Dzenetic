import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdavacTest {
    private Prodavac prodavacValid;
    private Prodavac prodavacInvalid;

    @BeforeEach
    void setUp() {
        prodavacValid = new Prodavac("Marko", "Marković", 1, "12301");
        prodavacInvalid = new Prodavac("Ana", "Anić", 2, "12345");
    }

    @Test
    void testZdravljeTrue() {
        assertTrue(prodavacValid.zdravlje(0.0));
    }

    @Test
    void testZdravljeFalse() {
        assertFalse(prodavacInvalid.zdravlje(0.0));
    }
}
