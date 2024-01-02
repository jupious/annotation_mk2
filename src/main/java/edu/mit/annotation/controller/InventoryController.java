package edu.mit.annotation.controller;

import edu.mit.annotation.testdto.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/inv")
public class InventoryController {

    @GetMapping("/receiving")
    public void receiving(Model model) throws ParseException {
        List<ReceivePurchaseOrderDTO> poDtoList = new ArrayList<>();
        for(int i = 1; i <= 7; i++){
            List<ReceiveItemDTO> riDtoList = new ArrayList<>();
            List<String> iNList = new ArrayList<>();
            for(int j = i*10; j <= i*10+3; j++){
                int poIQ = j*i+j*2;
                ReceiveItemDTO riDto = ReceiveItemDTO.builder()
                        .itemCode((char)(i+65)+""+i+j)
                        .itemName("품목명"+j)
                        .poItemQuantity(poIQ)
                        .build();
                riDtoList.add(riDto);
            }
            riDtoList.forEach(x->{
                iNList.add(x.getItemName());
            });
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ReceivePurchaseOrderDTO rpoDto = ReceivePurchaseOrderDTO.builder()
                    .poCode("po-"+i)
                    .companyName("(주)협력업체"+i)
                    .poDate(sdf.parse("2023-12-"+i))
                    .poDueDate(sdf.parse("2023-12-"+(i+10)))
                    .itemNameList(iNList)
                    .receiveItemList(riDtoList)
                    .build();
            poDtoList.add(rpoDto);
        }
        model.addAttribute("data",poDtoList);
    }
    @GetMapping("/statement")
    public void statement(Model model) throws ParseException {
        List<ReceivePurchaseOrderDTO> poDtoList = new ArrayList<>();
        for(int i = 1; i <= 7; i++){
            List<String> iNList = new ArrayList<>();
            for(int j = i*10; j <= i*10+3; j++){
                iNList.add("품목명"+j);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ReceivePurchaseOrderDTO rpoDto = ReceivePurchaseOrderDTO.builder()
                    .poCode("po-"+i)
                    .companyName("(주)협력업체"+i)
                    .poDate(sdf.parse("2023-12-"+i))
                    .poDueDate(sdf.parse("2023-12-"+(i+10)))
                    .itemNameList(iNList)
                    .build();
            poDtoList.add(rpoDto);
        }
        model.addAttribute("data",poDtoList);

    }
    @GetMapping("/statement-preview/{id}")
    public String statementPreview(@PathVariable Integer id, Model model){
        CoopCompDTO ccDto = CoopCompDTO.builder()
                        .compNum("00"+id+"-11"+id+"-222"+id+"-1"+id)
                        .compName("(주)협력업체"+(id+1))
                        .address("어디시 저기동 거기로 "+id*100)
                        .tel("010-"+(id*1111)+"-4321")
                        .managerName("담당자"+id)
                        .today(new Date())
                        .build();
        List<ReceiveItemDTO> riDtoList = new ArrayList<>();
        int totalPrice = 0;
        for(int i = (id+1)*10; i <= (id+1)*10+3; i++){
            int poIQ = id*i+i*2;
            int fQ = poIQ - i;
            int price = id*100+i;
            int itemTotalPrice = fQ*price;
            ReceiveItemDTO riDto = ReceiveItemDTO.builder()
                    .itemCode((char)(id+65)+""+id+i)
                    .itemName("품목명"+i)
                    .poItemQuantity(poIQ)
                    .price(price)
                    .fairQuantity(fQ)
                    .returnQuantity(i)
                    .itemTotalPrice(itemTotalPrice)
                    .build();
            totalPrice+=itemTotalPrice;
            riDtoList.add(riDto);
        }
        model.addAttribute("itemList",riDtoList);
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("compInfo",ccDto);
        model.addAttribute("id",id+1);
        return "/inv/statement-preview";
    }
    @GetMapping("/releasing")
    public void releasing(Model model){
        List<ReleaseItemDTO> riDtoList = new ArrayList<>();
        for(int i = 1; i <=7; i++){
            ReleaseItemDTO riDto = ReleaseItemDTO.builder()
                    .itemCode((char)(i+65)+""+i)
                    .itemName("품목명"+i)
                    .invQuantity(i*384)
                    .releaseQuantity(i*23)
                    .build();
            riDtoList.add(riDto);
        }
        model.addAttribute("releaseList",riDtoList);
    }
    @GetMapping("/inv-calc")
    public void invCalc(Model model){
        List<InventoryCalcDTO> invCalcDtoList = new ArrayList<>();
        for(int i = 1; i <= 7; i++){
            int oQ = i*471;
            int iQ = i*35;
            int rQ = i*27;
            InventoryCalcDTO icDto = InventoryCalcDTO.builder()
                    .itemCode((char)(i+65)+""+i)
                    .itemName("품목명"+i)
                    .size(i*10+"x"+(i*10+i)+"x"+(i+14))
                    .material("재질"+i)
                    .originQuantity(oQ)
                    .receiveQuantity(iQ)
                    .releaseQuantity(rQ)
                    .calculatedQuantity(oQ+iQ-rQ)
                    .build();
            invCalcDtoList.add(icDto);
        }
        model.addAttribute("invCalc",invCalcDtoList);
    }
    @GetMapping("/inv-amount")
    public void invAmount(Model model){
        Random rand = new Random();
        String[] unitCate = {"관제부","압착부","용접부","장착부","절단부","정렬부","제어부","주입부","충전부"};
        Integer[] unitAmount = new Integer[unitCate.length];
        String[] assyCate = {"감지기", "고정대", "제어판", "이슈", "구동축", "모터", "실린더", "레이저", "서브모터", "펌프", "전자저울", "커넥터", "투영기"};
        Integer[] assyAmount = new Integer[assyCate.length];

        String[] partCate = {"센서", "브라켓", "릴레이", "기어", "솔레노이드벨브", "점멸램프","PLC","엔코더","베어링","케이블","트랜스미션","볼트너트","피팅류","JIG","호스"};
        Integer[] partAmount = new Integer[partCate.length];

        for(int i = 0; i < unitCate.length; i++){
            unitAmount[i] = rand.nextInt(700)+150;
        }
        for(int i = 0; i < assyCate.length; i++){
            assyAmount[i] = rand.nextInt(700)+150;
        }
        for(int i = 0; i < partCate.length; i++){
            partAmount[i] = rand.nextInt(700)+150;
        }
        model.addAttribute("unitCategories",unitCate);
        model.addAttribute("unitAmounts",unitAmount);
        model.addAttribute("assyCategories",assyCate);
        model.addAttribute("assyAmounts",assyAmount);
        model.addAttribute("partCategories",partCate);
        model.addAttribute("partAmounts",partAmount);
    }

    
}
