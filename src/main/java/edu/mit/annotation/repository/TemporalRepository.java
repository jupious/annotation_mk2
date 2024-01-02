package edu.mit.annotation.repository;

import edu.mit.annotation.testdto.TemporalReceiveDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TemporalRepository {
    private static final Map<String, TemporalReceiveDTO> receiveData = new HashMap<>();
    private static Integer receiveCount = 0;

    public void saveReceive(TemporalReceiveDTO temporalReceive){
        temporalReceive.setReceiveNum(++receiveCount);
        receiveData.put(temporalReceive.getItemCode(), temporalReceive);
    }

    public TemporalReceiveDTO findByItemCode(String itemCode){
        return receiveData.get(itemCode);
    }

    public List<TemporalReceiveDTO> findAllReceiveData(){
        List<TemporalReceiveDTO> list = new ArrayList<>();
        receiveData.forEach((k,v) ->{
            list.add(v);
        });
        return list;
    }
}
