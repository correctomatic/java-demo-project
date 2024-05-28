package empleados;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.DisplayName;

public class EmpleadoBRTest {

    public static Stream<Arguments> valores() {
        return Stream.of(
            Arguments.of(TipoEmpleado.vendedor, 2000, 8, 1360.0f),
            Arguments.of(TipoEmpleado.vendedor, 1500, 3, 1260.0f),
            Arguments.of(TipoEmpleado.vendedor, 1499.99f, 0, 1100.0f),
            Arguments.of(TipoEmpleado.encargado, 1250, 8, 1760.0f),
            Arguments.of(TipoEmpleado.encargado, 1000, 0, 1600.0f),
            Arguments.of(TipoEmpleado.encargado, 999.9f, 3, 1560.0f),
            Arguments.of(TipoEmpleado.encargado, 500, 0, 1500.0f),
            Arguments.of(TipoEmpleado.encargado, 0, 8, 1660.0f)
        );
    }

    // Workaround porque @DisplayName no está funcionando en el XML para @ParameterizedTest
    @ParameterizedTest(name = "El programa no calcula bien el salario bruto")
    @MethodSource("valores")
    @DisplayName("El programa no calcula bien el salario bruto")
    public void CalculaSalarioBruto(TipoEmpleado tipo, float ventaMes, float horasExtra, float expResult) throws BRException {
        EmpleadoBR instance = new EmpleadoBR();
        float result = instance.calculaSalarioBruto(tipo, ventaMes, horasExtra);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    @DisplayName("El cálculo con un salario nulo debe lanzar una excepción")
    public void CalculaSalarioNulo() {
        EmpleadoBR instance = new EmpleadoBR();
        assertThrows(BRException.class, () -> {
            instance.calculaSalarioBruto(null, 1500, 8);
        });
    }
}
