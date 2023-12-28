package edu.mit.annotation.service;

import edu.mit.annotation.dto.ItemDTO;
import edu.mit.annotation.dto.PurchaseOrderDTO;
import edu.mit.annotation.dto.PurchaseOrderDetailDTO;
import edu.mit.annotation.dto.ReceiveDetailDTO;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Override
    public List<PurchaseOrderDTO> getPurchaseOrderList() {
        List<PurchaseOrderDTO> list = new ArrayList<>();

        for(int i = 1; i <= 10; i++){
            List<PurchaseOrderDetailDTO> detailList = new ArrayList<>();
            for(int j = i*10; j <= (i*10)+3; j++){
                PurchaseOrderDetailDTO pod = PurchaseOrderDetailDTO.builder()
                        .poCode("po-"+i)
                        .prcCode("j-"+j)
                        .orderQuantity(j*100+j*12+j)
                        .etc("비고"+j).build();
                detailList.add(pod);
            }
            PurchaseOrderDTO dto = PurchaseOrderDTO.builder()
                    .poCode("po-"+i)
                    .businessNumber(i+"-"+i*10+"-"+i*100)
                    .poDate(new Date())
                    .poDueDate(new Date())
                    .isCompleted(false)
                    .purchaseOrderDetailList(detailList).build();
            list.add(dto);
        }
        return list;
    }

    @Override
    public List<ItemDTO> getPurchaseOrderItemList(List<String> prcCodeList) {
        List<ItemDTO> list = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            ItemDTO items = ItemDTO.builder()
                    .itemCode("abc"+i)
                    .itemName("품목명"+i)
                    .build();
            list.add(items);
        }
        return list;
    }

    @Override
    public PurchaseOrderDTO getPurchaseOrder(String poCode) {
        return null;
    }

    @Override
    public boolean saveReceivedItem(List<ReceiveDetailDTO> receiveDetailList) {
        return false;
    }

    @Override
    public boolean completePurchaseOrder(String poCode) {
        return false;
    }
}
