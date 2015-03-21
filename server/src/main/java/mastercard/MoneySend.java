package mastercard;

import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by Juriy on 3/21/2015.
 */
public class MoneySend {
    private static String splitiesCardNumber = "5184680430000006";

    public static final String MASTER_CARD_URL="http://dmartin.org:8021/moneysend/v2/transfer";

    private static RestTemplate restTemplate = new RestTemplate();

    public static boolean transferMoneyTo(String recipientAccountNumber, double amount){

        while(true) {
            String requestBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<TransferRequest>" +
                    "   <LocalDate>0712</LocalDate>" +
                    "   <LocalTime>161222</LocalTime>" +
                    "   <TransactionReference>" + generateRandomTransactionReference() + "</TransactionReference>" +
                    "   <SenderName>John Doe</SenderName>" +
                    "   <SenderAddress>" +
                    "      <Line1>123 Main Street</Line1>" +
                    "      <City>Arlington</City>" +
                    "      <CountrySubdivision>VA</CountrySubdivision>" +
                    "      <PostalCode>22207</PostalCode>" +
                    "      <Country>USA</Country>" +
                    "   </SenderAddress>" +
                    "   <FundingCard>" +
                    "      <AccountNumber>"+splitiesCardNumber+"</AccountNumber>" +
                    "      <ExpiryMonth>11</ExpiryMonth>" +
                    "      <ExpiryYear>2016</ExpiryYear>" +
                    "   </FundingCard>" +
                    "   <FundingMasterCardAssignedId>123456</FundingMasterCardAssignedId>" +
                    "   <FundingAmount>" +
                    "      <Value>"+(int)amount+"</Value>" +
                    "      <Currency>840</Currency>" +
                    "   </FundingAmount>" +
                    "   <ReceiverName>Jose Lopez</ReceiverName>" +
                    "   <ReceiverAddress>" +
                    "      <Line1>Pueblo Street</Line1>" +
                    "      <Line2>PO BOX 12</Line2>" +
                    "      <City>El PASO</City>" +
                    "      <CountrySubdivision>TX</CountrySubdivision>" +
                    "      <PostalCode>79906</PostalCode>" +
                    "      <Country>USA</Country>" +
                    "   </ReceiverAddress>" +
                    "   <ReceiverPhone>1800639426</ReceiverPhone>" +
                    "   <ReceivingCard>" +
                    "      <AccountNumber>"+recipientAccountNumber+"</AccountNumber>" +
                    "   </ReceivingCard>" +
                    "   <ReceivingAmount>" +
                    "      <Value>182206</Value>" +
                    "      <Currency>484</Currency>" +
                    "   </ReceivingAmount>" +
                    "   <Channel>W</Channel>" +
                    "   <UCAFSupport>true</UCAFSupport>" +
                    "   <ICA>009674</ICA>" +
                    "   <ProcessorId>9000000442</ProcessorId>" +
                    "   <RoutingAndTransitNumber>990442082</RoutingAndTransitNumber>" +
                    "   <CardAcceptor>" +
                    "      <Name>My Local Bank</Name>" +
                    "      <City>Saint Louis</City>" +
                    "      <State>MO</State>" +
                    "      <PostalCode>63101</PostalCode>" +
                    "      <Country>USA</Country>" +
                    "   </CardAcceptor>" +
                    "  <TransactionDesc>P2P</TransactionDesc>" +
                    "  <MerchantId>123456</MerchantId>" +
                    "</TransferRequest>";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            try {
                ResponseEntity<String> response = restTemplate.exchange(MASTER_CARD_URL, HttpMethod.POST, entity, String.class);
                if(response.getStatusCode().equals(HttpStatus.OK)){
                    System.out.println("Sucessfull response: [" + response + "]");
                    return true;
                }
                System.out.println("Something strange happend ["+response+"]");
                return true;
            } catch (HttpClientErrorException e) {
                if(e.getResponseBodyAsString().contains("Duplicate Request.")){
                    System.out.println("Duplicate request. Generating new ref number");
                }else{
                    System.out.println("Something new! +\n" + e.getResponseBodyAsString());
                    return true;
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    private static String generateRandomTransactionReference(){
        StringBuffer sb = new StringBuffer("");
        Random random = new Random();
        for(int i=0;i<19;i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
