package com.mapi.bomb;

import java.awt.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.deserializer.ArrayListTypeFieldDeserializer;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;

public class JSONCrawler {

	public WeightHandler weightHandler = new WeightHandler();

	public static void main(String[] args) {
//		String jsonStr = "{\"size\":[7,66],\"width\":\"M (B)\"}";
		String jsonStr = "{\"message\":\"\",\"status\":\"\",\"success\":true,\"value\":{\"orderRestRoomViewInfo\":{\"arrCityName\":\"\",\"backRestRooms\":[],\"depCityName\":\"北京\",\"goRestRooms\":[],\"restRoomPassengers\":[]},\"orderXpromotionViewModels\":[{\"changeEable\":true,\"des\":\"1、保险名称：航空意外险<br/>2、份数限制：购买上限1份<br/>3、有效期：指被保险人每次以乘客身份，乘坐电子保单载明的合法商业运营的客运飞机班次，并遵守承运人关于安全乘坐的规定，自持有效机票检票并进入所乘客运飞机客舱时起至抵达机票载明的终点离开所乘客运飞机客舱的期间内遭受意外伤害所导致的保险责任。<br/>4、保险费：30元/份<br/>5、保险保额：最高260万元<br/>6、年龄限制：出生28天至100周岁\",\"dialogText\":\"73.7%的人都购买了意外险，购买安全保障，家人更放心！\",\"explainEnable\":true,\"explainText\":\"自检票进入飞机客舱时起，至抵达目的地离开飞机客舱，全程保障人身安全。30元/份\r\n\",\"explainTitle\":\"260万乘机意外保障，超73%客户优先选择\",\"id\":\"BXPT:7\",\"leftButton\":\"我要保障\",\"legIndex\":[1,2,3],\"originalPrice\":30.0,\"promotionPrice\":30.0,\"promotionXProductType\":\"Insurance\",\"rightButton\":\"暂不需要\",\"selected\":true,\"subTitle\":\"航空意外险\",\"title\":\"航空意外险\"},{\"changeEable\":true,\"des\":\"1、产品名称：国内航班延误保险<br/>2、份数限制：每航班限购1份<br/>3、年龄限制：出生28天至100周岁<br/>4、保险费：30元/份<br/>5、有效期：购买成功后即时生效<br/>6、保险责任：被保险人乘坐投保航班且航班抵达目的地时，若延误3小时及以上，赔偿300元；若被保险人乘坐航班起飞后返航、备降，赔偿100元；若航班被取消，赔偿100元。<br/>7、退保说明：保险购买即生效，不可单独退保。预定航班计划起飞时间前线上申请退票/改签，在机票退款完成/改签完成后自动退保。<br/>8、发票说明：提供保险定额发票以作报销。保险定额发票仅作报销凭证，不是保单凭证。\",\"dialogText\":\"客官，您购买的航班延误率偏高，购买航空延误险，最高赔付300元哦！\",\"explainEnable\":true,\"explainText\":\"每人限购一份。延误3小时以上，赔偿300元；航班取消、返航、备降、赔偿100元。30元/份\",\"explainTitle\":\"最高赔付300元/份，近期约有40.8%航班延误\",\"id\":\"BXPT:19\",\"leftButton\":\"买个延误险\",\"legIndex\":0,\"originalPrice\":30.0,\"promotionPrice\":30.0,\"promotionXProductType\":\"Insurance\",\"rightButton\":\"继续填写\",\"selected\":true,\"subTitle\":\"航空延误险\",\"title\":\"航空延误险\"}]}}";
		System.out.println("无序遍历结果：");
		JSONObject parseJson = new JSONCrawler().parseJson(jsonStr);

		System.out.println(parseJson.toJSONString());
		System.out.println("-------------------");
		System.out.println("有序遍历结果：");
		// LinkedHashMap<String, String> jsonMap = JSON.parseObject(jsonStr,
		// new TypeReference<LinkedHashMap<String, String>>() {
		// });
		// for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
		// System.out.println(entry.getKey() + ":" +
		// entry.getValue().getClass().getSimpleName());
		// }
		// JSONObject parseJson = JSON.parseObject(jsonStr);
		// Integer integer =
		// parseJson.getJSONObject("data").getInteger("needPhoneType");
		// System.out.println("integer---->" + integer);
		// Integer status = parseJson.getInteger("status");
		// System.out.println("status :" + status);
		//
		// javabean parseObject = JSON.parseObject(jsonStr, javabean.class);
		// System.out.println("parseObject status:" + parseObject.getStatus());
	}

	public static JSONCrawler getInstance(){
		return new JSONCrawler();
	}
	
	
	/**
	 * 遍历json 对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public JSONObject parseJson(String jsonStr) {
		JSONObject jsonObj = JSON.parseObject(jsonStr);
		for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
			Object value = entry.getValue();
			String simpleName = value.getClass().getSimpleName();
			if (value instanceof JSONObject) {
				JSONObject parseJson = parseJson(entry.getValue().toString());
				entry.setValue(parseJson);
			} else if (value instanceof JSONArray) {
				parseJsonArray(entry);

			} else {

				changeData(entry, value, null, simpleName);
			}
		}
		return jsonObj;
	}

	private void changeData(Map.Entry<String, Object> entry, Object value, Object[] arr, String simpleName) {
		
		if (simpleName.contains("[]") && arr != null)
			entry.setValue(DataChange.changeArrayLength(simpleName, arr));
		else{
			ActionWeight choice = (ActionWeight) weightHandler.weightChoice();
			System.out.println(choice);
			switch (choice) {
			case DEL:
				entry.setValue(null);
				break;
			case CHANGE_TYPE:
				entry.setValue(TypeChange.changeType(value));
				break;
			case CHANGE_VALUE:				
					entry.setValue(DataChange.getChanageValue(simpleName));
				break;
			case EMPTY:
				entry.setValue(DataChange.getEmpty(simpleName));
				break;
			}
		}
		
	}

	/**
	 * 遍历json 数组
	 * 
	 * @param entry
	 */
	public void parseJsonArray(Entry<String, Object> entry) {
		String value = entry.getValue().toString();
		JSONArray parseArray = JSON.parseArray(value);
		ArrayList l = new ArrayList();
		for (Object object : parseArray) {

			if (object instanceof JSONObject) {
				JSONObject parseJson = parseJson(object.toString());
				l.add(parseJson);
			} else if (object instanceof JSONArray) {
				parseJsonArray((Entry<String, Object>) object);
			} else {
				Object[] jsonArrayToArray = jsonArrayToArray(parseArray);
				changeData(entry, null, jsonArrayToArray, jsonArrayToArray.getClass().getSimpleName());
				
				break;
			}
		}
		if(l.size() > 0)
			entry.setValue(l);
	}

	public Object[] jsonArrayToArray(JSONArray parseArray) {
		String simpleName = parseArray.get(0).getClass().getSimpleName();

		switch (simpleName) {
		case "BigDecimal":
			BigDecimal[] bd = new BigDecimal[parseArray.size()];
			return DataChange.initArray(bd, parseArray.toArray());

		case "Integer":
			Integer[] i = new Integer[parseArray.size()];
			return DataChange.initArray(i, parseArray.toArray());

		case "Double":
			Double[] d = new Double[parseArray.size()];
			return DataChange.initArray(d, parseArray.toArray());
		case "Float":
			Float[] f = new Float[parseArray.size()];
			return DataChange.initArray(f, parseArray.toArray());
		case "Long":
			Long[] l = new Long[parseArray.size()];
			return DataChange.initArray(l, parseArray.toArray());
		case "Short":
			Short[] s = new Short[parseArray.size()];
			return DataChange.initArray(s, parseArray.toArray());
		case "Byte":
			Byte[] by = new Byte[parseArray.size()];
			return DataChange.initArray(by, parseArray.toArray());
		case "Character":
			Character[] c = new Character[parseArray.size()];
			return DataChange.initArray(c, parseArray.toArray());
		case "Boolean":
			Boolean[] b = new Boolean[parseArray.size()];
			return DataChange.initArray(b, parseArray.toArray());
		case "String":
			String[] str = new String[parseArray.size()];
			return DataChange.initArray(str, parseArray.toArray());

		default:
			return null;

		}
	}

}
