package software.ulpgc.katas;

import software.ulpgc.katas.architecture.operations.DivisorsCalculationOperation;
import software.ulpgc.katas.infrastructure.adapters.JsonDivisorsSerializer;

public class DivisorsOperationCheck {
    static void main() {
        var operation = new DivisorsCalculationOperation(new JsonDivisorsSerializer());

        String result = operation.execute(12);

        if (!result.equals("[1,2,3,4,6,12]")) {
            throw new AssertionError("Resultado incorrecto: " + result);
        }

        System.out.println("Prueba superada");
    }
}