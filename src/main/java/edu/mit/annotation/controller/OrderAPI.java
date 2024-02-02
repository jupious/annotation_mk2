package edu.mit.annotation.controller;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import edu.mit.annotation.dto.ProdCodeWithNameDTO;
import edu.mit.annotation.entity.EmailMessage;
import edu.mit.annotation.mailfactory.MailSenderFactory;
import edu.mit.annotation.realdto.*;
import edu.mit.annotation.service.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/orderapi")
@RequiredArgsConstructor
public class OrderAPI {
    private final OrderService orderService;
    private final MemberService memberService;
    private final MailService mailService;
    private final InventoryService inventoryService;
    private final ComService comService;

    @GetMapping("/po-proc-plan")
    public ListWithPaging<ProcPlanNoPO> poProcPlan(String  startDate, String  endDate, String type, String keyword, Integer pageNum, Integer amount) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .pageNum(pageNum*amount)
                .amount(amount)
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDate))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return orderService.getProcPlanListWithNoPO(cri);
    }

    @GetMapping("/auto-search-proc-plan")
    public List<ProcPlanNoPO> autoSearchProcPlan(String  startDate, String  endDate, String type, String keyword) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDate))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return orderService.autoSearchNoPOPrcp(cri);
    }

    @PostMapping("/po-new-order/{purch_order_detail}")
    public String  poNewOrder(@RequestBody List<NewPurchOrderItem> newPurchOrderItem, @PathVariable(value = "purch_order_detail", required = false) String purch_order_detail) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        orderService.savePurchaseOrder(NewPurchaseOrderDTO.builder()
                .newPurchOrderItem(newPurchOrderItem)
                .purch_order_date(new Date())
                .purch_order_detail(purch_order_detail)
                .build());
        return "good";
    }

    @GetMapping("/po-get-published")
    public ListWithPaging<PublishedPurchaseOrderDTO> poGetPublished(String  startDate, String  endDate, String type, String keyword, Integer pageNum, Integer amount) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .pageNum(pageNum*amount)
                .amount(amount)
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDate))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return orderService.getPublishedPOList(cri);
    }

    @GetMapping("/auto-search-po")
    public List<PublishedPurchaseOrderDTO> autoSearchPo(String  startDate, String  endDate, String type, String keyword) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(endDate);
        Criteria cri = Criteria.builder()
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDate))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return orderService.autoSearchPoList(cri);
    }

    @DeleteMapping("/po-delete")
    public List<String> deletePO(@RequestParam(value = "purch_order_numbers[]") List<String> purch_order_numbers){
        System.out.println(purch_order_numbers);
        List<String> poList = new ArrayList<>();
        for (String purch_order_number: purch_order_numbers) {
            if(orderService.isPrcpClosed(purch_order_number) == 0){
                orderService.deletePurchaseOrder(purch_order_number);
            }else{
                poList.add(purch_order_number);
            }
        }
        return poList;
    }

    @PostMapping("/send")
    public String getCurrentUserName(@AuthenticationPrincipal UserDetails userDetails, MultipartFile file, String email, String subject, String message){
        String username = userDetails.getUsername();
        System.out.println(file);
        MemberDTO dto = memberService.getMemberData(username);
        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject(subject)
                .message(message)
                .multipartFile(file)
                .build();
        mailService.sendMail(emailMessage,"email",dto.getEmail(),dto.getGoogle_app_password());
        return "뭐요";
    }

    @Scheduled(cron = "0 40 15 * * *")
    public void autoMailProgCheck(){

        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        Date sDate = new Date(cal.getTimeInMillis());
        cal1.add(Calendar.DATE,3);
        Date eDate = new Date(cal1.getTimeInMillis());

        System.out.println(sDate);
        System.out.println(eDate);
        System.out.println("날짜잘 찍힘?");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<AutoProgPlanAlertDTO> ppList = orderService.autoMailPrcp(Criteria.builder().startDate(sDate).endDate(eDate).build());
        if(!ppList.isEmpty()){
            String message = "";
            for(AutoProgPlanAlertDTO dto : ppList){
                message +=  "=================================================================================="+"\n"+
                            "발주번호 : " + dto.getPurch_order_number() +"\n"+
                            "발주회사 : " + dto.getCompany_name() +" | 주소 : "+dto.getCompany_address()+"\n"+
                            "사업자번호 : " + dto.getBusiness_number() +"\n"+
                            "담당자 : " +dto.getManger() + " | 담당자 연락처 : "+dto.getManager_tel() +"\n"+
                            "검수 차수 : " + dto.getProc_check_order() +"\n"+
                            "검수 일자 : " + sdf.format(dto.getProc_check_date()) +"\n"+
                            "품목코드 : " + dto.getItem_code() +" | 품목명 : " + dto.getItem_name() +"\n"+
                            "발주 수량 : " + dto.getPurch_order_quantity() +"개\n"+
                            "==================================================================================" + "\n";
            }

            EmailMessage emailMessage = EmailMessage.builder()
                    .to("jupious@naver.com")
                    .subject(sdf.format(sDate)+" 진척검수 일정 안내메일")
                    .message(message)
                    .build();
            mailService.sendMail(emailMessage, "email", "jupious@gmail.com","ujwwpljvqbrcsrwn");
        }
    }

    @GetMapping("/search-with-mail")
    public List<CompanyInfoDTO> searchWithMail(String email){
        return orderService.getCompWithEmail("%"+email+"%");
    }

    @GetMapping("/search-prod-code")
    public List<ProdCodeWithNameDTO> searhProdCode(String product_code){
        return comService.autoPcPn("%"+product_code+"%");
    }

    @GetMapping("/save-pdf")
    public void savePdf(String htmlPath, String sendType, String business_number, String param, String etc, @AuthenticationPrincipal UserDetails userDetails, String email) throws DocumentException, IOException {
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyyMMdd");
        String nowStr = dtm.format(nowDate);
        String name = "";
        System.out.println(htmlPath + sendType + business_number + param);
        if(sendType.equals("poContent")){
            name = "발주서";
        }else if(sendType.equals("statement")){
            name = "거래명세서";
        }
        String filePath = generatePdfFromHtml(parseThymeleafTemplate(htmlPath,sendType,business_number,param, etc),
                                                                                        business_number+nowStr+name);
        String username = userDetails.getUsername();

        MemberDTO dto = memberService.getMemberData(username);
        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject(sendType.equals("poContent") ? nowStr +"발주서" : nowStr + "거래명세서")
                .message("첨부파일 참고 바랍니다.")
                .filePath(filePath)
                .build();
        mailService.sendMail(emailMessage,"email",dto.getEmail(),dto.getGoogle_app_password());
    }

    private String parseThymeleafTemplate(String htmlPath, String sendType, String business_number, String param, String etc){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        if(sendType.equals("poContent")){
            context.setVariable("company_info", orderService.getCompInfo(business_number));
            context.setVariable("po_info",orderService.getPOinfo(param));
            context.setVariable("po_items",orderService.getPublishedPOItems(param));
        }else if(sendType.equals("statement")){
            LocalDate nowDate = LocalDate.now();
            DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            StatementPreviewDTO dto = inventoryService.getStatement("'"+param+"'", business_number);
            int totalPrice = 0;
            for (StatementItemDTO d: dto.getItemList()) {
                d.setProd_price((d.getItem_price() * d.getReceive_quantity()));
                totalPrice += d.getProd_price();
            }
            context.setVariable("today",dtm.format(nowDate));
            context.setVariable("data",dto);
            context.setVariable("totalPrice",totalPrice);
            context.setVariable("etc", etc);
        }

        return templateEngine.process(htmlPath, context);
    }

    public String generatePdfFromHtml(String html, String name) throws IOException, DocumentException {
        UUID uuid = UUID.randomUUID();
        String outputFolder = System.getProperty("user.home") + File.separator +"pdfs"+File.separator +uuid+name+".pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont(
                new ClassPathResource("/static/fonts/D2Coding.ttc")
                        .getURL()
                        .toString(),
                BaseFont.IDENTITY_H,
                BaseFont.EMBEDDED

        );
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
        return outputFolder;
    }





    private String  endDateValidate(String endDate){
        Integer date = Integer.valueOf(endDate.split("-")[2]);
        Integer month = Integer.valueOf(endDate.split("-")[1]);
        Integer year = Integer.valueOf(endDate.split("-")[0]);
        if(month < 8){
            if(month%2 == 1){
                if(date == 31){
                    month+=1;
                    date = 1;
                }else{
                    date += 1;
                }
            }else{
                if(month == 2){
                    if(date==28 || date==29){
                        month += 1;
                        date = 1;
                    }else{
                        date+=1;
                    }
                }else{
                    if(date ==30){
                        month+=1;
                        date = 1;
                    }else{
                        date+=1;
                    }
                }
            }
        }else{
            if(month%2==0){
                if(date==31){
                    date = 1;
                    if(month == 12){
                        year+=1;
                        month = 1;
                    }
                    else{
                        month+=1;
                    }
                }else{
                    date+=1;
                }
            }else{
                if(date==30){
                    month+=1;
                    date = 1;
                }else{
                    date+=1;
                }
            }
        }
        return year+"-"+ (month < 10 ? "0"+month : month) +"-"+ (date < 10? "0"+date : date);
    }
}
