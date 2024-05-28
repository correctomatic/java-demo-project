package empleados;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;

public class EmpleadoNetoTest {

    public static Stream<Object[]> valores() {
        return Stream.of(
                new Object[] { 2000, 1640 },
                new Object[] { 1500, 1230 },
                new Object[] { 1499.99f, 1259.9916f },
                new Object[] { 1250, 1050 },
                new Object[] { 1000, 840 },
                new Object[] { 999.9f, 999.9f },
                new Object[] { 500, 500 },
                new Object[] { 0, 0 });
    }

    // Workaround porque @DisplayName no está funcionando en el XML para
    // @ParameterizedTest
    @ParameterizedTest(name = "El programa no calcula bien el salario neto")
    @MethodSource("valores")
    @DisplayName("El programa no calcula bien el salario neto")
    public void CalculaSalarioNE(float entrada, float salida) throws BRException {
        EmpleadoBR instance = new EmpleadoBR();
        float result = instance.calculaSalarioNeto(entrada);
        assertEquals(salida, result, 0.01);
    }
}
