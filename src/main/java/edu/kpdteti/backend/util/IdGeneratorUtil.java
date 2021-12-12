package edu.kpdteti.backend.util;

import edu.kpdteti.backend.enums.IdGeneratorEnum;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGeneratorUtil {

    public String generateId(IdGeneratorEnum generationType) {
        String uuid = UUID.randomUUID().toString();
        return generationType.getIdGeneratorEnum().concat(uuid);
    }

}
