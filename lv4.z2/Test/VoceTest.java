import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VoceTest {
    private Voce voce;

    @BeforeEach
    void setUp() {
        voce = new Voce("Malus domestica", "Hrvatska", List.of(10.0, 15.0, 20.0));
    }

    @Test
    void testDajBrojKalorija() {
        assertEquals(45.0, voce.dajBrojKalorija(), 0.001);
    }

    @Test
    void testZdravljeTrue() {
        assertTrue(voce.zdravlje(0.8));
    }

    @Test
    void testZdravljeFalse() {
        assertFalse(voce.zdravlje(0.7));
    }
}
