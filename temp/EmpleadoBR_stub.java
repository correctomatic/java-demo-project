/*********************************************************************
 *
 * Sub for the student's code
 *
 * Need this to compile the tests
 * In case a method is not implemented, the test will fail
 * with a NoSuchMethodError
 *********************************************************************/

 package empleados;

 enum TipoEmpleado { vendedor, encargado };

 class BRException extends Exception { }

public class EmpleadoBR {
  float calculaSalarioBruto(TipoEmpleado tipo, float ventasMes, float horasExtra) { return 0; }
  public float calculaSalarioNeto(float salarioBruto) throws BRException { return 0; }
}
