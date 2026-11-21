package software.ulpgc.katas.infrastructure;

import io.javalin.Javalin;
import software.ulpgc.katas.architecture.operations.DivisorsCalculationOperation;
import software.ulpgc.katas.infrastructure.adapters.DivisorsEndPoint;
import software.ulpgc.katas.infrastructure.adapters.JsonDivisorsSerializer;

public class Main {
    static void main() {
        DivisorsCalculationOperation operation = new DivisorsCalculationOperation(new JsonDivisorsSerializer());
        DivisorsEndPoint endPoint = new DivisorsEndPoint(operation);

        Javalin.create(config ->
                config.routes.get("/divisors", endPoint::execute)
        ).start(8080);
    }
}
