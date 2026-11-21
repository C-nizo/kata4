package software.ulpgc.katas;

import software.ulpgc.katas.architecture.operations.DivisorsCalculationOperation;
import software.ulpgc.katas.infrastructure.adapters.JsonDivisorsSerializer;

public class DivisorsOperationCheck {
    static void main() {
        var operation = new DivisorsCalculationOperation(new JsonDivisorsSerializer());

        String result = operation.execute(18);

        if (!result.equals("{\"number\":18,\"divisors\":[1,2,3,6,9,18]}")) {
            throw new AssertionError("Resultado incorrecto: " + result);
        }

        System.out.println("Prueba superada");
    }
}
