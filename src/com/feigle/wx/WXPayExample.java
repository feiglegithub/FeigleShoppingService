package com.feigle.wx;

import com.feigle.wx.WXPay;
import com.feigle.wx.WXPayUtil;

import java.util.HashMap;
import java.util.Map;

public class WXPayExample {

    public static void main(String[] args) throws Exception {

        String notifyData = "...."; // ֧�����֪ͨ��xml��ʽ����

        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);  // ת����map

        if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
            // ǩ����ȷ
            // ���д���
            // ע����������������Ѿ��˿���յ���֧������ɹ���֪ͨ����Ӧ���̻��ඩ��״̬���˿�ĳ�֧���ɹ�
        }
        else {
            // ǩ���������������û��sign�ֶΣ�Ҳ��Ϊ��ǩ������
        }
    }

    
    public static void unifiedOrder() throws Exception {
    	MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "��Ѷ��ֵ����-QQ��Ա��ֵ");
        data.put("out_trade_no", "2016090910595900000012");
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("trade_type", "NATIVE");  // �˴�ָ��Ϊɨ��֧��
        data.put("product_id", "12");

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
