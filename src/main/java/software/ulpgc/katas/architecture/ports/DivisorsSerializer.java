package software.ulpgc.katas.architecture.ports;

import software.ulpgc.katas.architecture.domain.DivisorsResult;

public interface DivisorsSerializer {
    String serialize(DivisorsResult result);
}