import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PovrceTest {
    private Povrce povrce;

    @BeforeEach
    void setUp() {
        povrce = new Povrce("Daucus carota", "Srbija", List.of(20.0, 30.0, 40.0));
    }

    @Test
    void testDajBrojKalorija() {
        assertEquals(90.0, povrce.dajBrojKalorija(), 0.001);
    }

    @Test
    void testZdravljeTrue() {
        assertTrue(povrce.zdravlje(0.6));
    }

    @Test
    void testZdravljeFalseOutOfRange() {
        assertFalse(povrce.zdravlje(0.4));
        assertFalse(povrce.zdravlje(0.8));
    }

    @Test
    void testZdravljeFalseHighCalories() {
        Povrce povrceKaloricno = new Povrce("Zelje", "Crna Gora", List.of(50.0, 60.0));
        assertFalse(povrceKaloricno.zdravlje(0.6));
    }
}
