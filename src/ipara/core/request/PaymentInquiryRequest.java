package ipara.core.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ipara.core.Constants;
import ipara.core.CoreRequest;
import ipara.core.Helper;
import ipara.core.RestHttpCaller;
import ipara.core.Settings;
import ipara.core.response.PaymentInquiryResponse;

//  Ödeme sorugulama servisi için gerekli olan servis girdi parametrelerini temsil eder.
@XmlRootElement(name = "inquiry")
public class PaymentInquiryRequest  extends CoreRequest{

	@XmlElement(name = "orderId")
	public String orderId;

	/*
	 *	Bu servise sorgulanmak istenen ödemenin mağaza sipariş numarası ve mode değeri iletilerek, ödemenin durumu ve ödemenin tutarı öğrenilebileceği servisi temsil eder.
	 *	@request Ödeme sorgulama servisi için gerekli olan girdilerin olduğu sınıfı temsil eder.
	 *	@options Kullanıcıya özel olarak belirlenen ayarları temsil eder.
	*/
	public static PaymentInquiryResponse execute(PaymentInquiryRequest request, Settings settings) throws Exception {

		settings.transactionDate = Helper.getTransactionDateString();
		settings.hashString = settings.privateKey + request.orderId + settings.mode + settings.transactionDate;
		return RestHttpCaller.getInstance().postXML(settings.baseUrl + "rest/payment/inquiry",
				Helper.getHttpHeaders(settings, Constants.ContentTypes.APPLICATION_XML_UTF8), request,
				PaymentInquiryResponse.class);

	}

}
