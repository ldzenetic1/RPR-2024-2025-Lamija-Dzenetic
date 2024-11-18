import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MesoTest {
    private Meso meso;

    @BeforeEach
    void setUp() {
        meso = new Meso("Piletina", "Bosna", List.of(50.0, 30.0, 20.0));
    }

    @Test
    void testDajBrojKalorija() {
        assertEquals(120.0, meso.dajBrojKalorija(), 0.001);
    }

    @Test
    void testZdravljeTrue() {
        assertTrue(meso.zdravlje(0.96));
    }

    @Test
    void testZdravljeFalse() {
        assertFalse(meso.zdravlje(0.94));
    }
}
