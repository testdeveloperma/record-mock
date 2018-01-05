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
		String jsonStr = "{\"message\":\"\",\"status\":\"\",\"success\":true,\"value\":{\"orderRestRoomViewInfo\":{\"arrCityName\":\"\",\"backRestRooms\":[],\"depCityName\":\"����\",\"goRestRooms\":[],\"restRoomPassengers\":[]},\"orderXpromotionViewModels\":[{\"changeEable\":true,\"des\":\"1���������ƣ�����������<br/>2���������ƣ���������1��<br/>3����Ч�ڣ�ָ��������ÿ���Գ˿���ݣ��������ӱ��������ĺϷ���ҵ��Ӫ�Ŀ��˷ɻ���Σ������س����˹��ڰ�ȫ�����Ĺ涨���Գ���Ч��Ʊ��Ʊ���������˿��˷ɻ��Ͳ�ʱ�����ִ��Ʊ�������յ��뿪���˿��˷ɻ��Ͳյ��ڼ������������˺������µı������Ρ�<br/>4�����շѣ�30Ԫ/��<br/>5�����ձ�����260��Ԫ<br/>6���������ƣ�����28����100����\",\"dialogText\":\"73.7%���˶������������գ�����ȫ���ϣ����˸����ģ�\",\"explainEnable\":true,\"explainText\":\"�Լ�Ʊ����ɻ��Ͳ�ʱ�����ִ�Ŀ�ĵ��뿪�ɻ��Ͳգ�ȫ�̱�������ȫ��30Ԫ/��\r\n\",\"explainTitle\":\"260��˻����Ᵽ�ϣ���73%�ͻ�����ѡ��\",\"id\":\"BXPT:7\",\"leftButton\":\"��Ҫ����\",\"legIndex\":[1,2,3],\"originalPrice\":30.0,\"promotionPrice\":30.0,\"promotionXProductType\":\"Insurance\",\"rightButton\":\"�ݲ���Ҫ\",\"selected\":true,\"subTitle\":\"����������\",\"title\":\"����������\"},{\"changeEable\":true,\"des\":\"1����Ʒ���ƣ����ں���������<br/>2���������ƣ�ÿ�����޹�1��<br/>3���������ƣ�����28����100����<br/>4�����շѣ�30Ԫ/��<br/>5����Ч�ڣ�����ɹ���ʱ��Ч<br/>6���������Σ��������˳���Ͷ�������Һ���ִ�Ŀ�ĵ�ʱ��������3Сʱ�����ϣ��⳥300Ԫ�����������˳���������ɺ󷵺����������⳥100Ԫ�������౻ȡ�����⳥100Ԫ��<br/>7���˱�˵�������չ�����Ч�����ɵ����˱���Ԥ������ƻ����ʱ��ǰ����������Ʊ/��ǩ���ڻ�Ʊ�˿����/��ǩ��ɺ��Զ��˱���<br/>8����Ʊ˵�����ṩ���ն��Ʊ�������������ն��Ʊ��������ƾ֤�����Ǳ���ƾ֤��\",\"dialogText\":\"�͹٣�������ĺ���������ƫ�ߣ����򺽿������գ�����⸶300ԪŶ��\",\"explainEnable\":true,\"explainText\":\"ÿ���޹�һ�ݡ�����3Сʱ���ϣ��⳥300Ԫ������ȡ�����������������⳥100Ԫ��30Ԫ/��\",\"explainTitle\":\"����⸶300Ԫ/�ݣ�����Լ��40.8%��������\",\"id\":\"BXPT:19\",\"leftButton\":\"���������\",\"legIndex\":0,\"originalPrice\":30.0,\"promotionPrice\":30.0,\"promotionXProductType\":\"Insurance\",\"rightButton\":\"������д\",\"selected\":true,\"subTitle\":\"����������\",\"title\":\"����������\"}]}}";
		System.out.println("������������");
		JSONObject parseJson = new JSONCrawler().parseJson(jsonStr);

		System.out.println(parseJson.toJSONString());
		System.out.println("-------------------");
		System.out.println("������������");
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

	/**
	 * ����json ����
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
	 * ����json ����
	 * 
	 * @param entry
	 */
	public void parseJsonArray(Entry<String, Object> entry) {
		String value = entry.getValue().toString();
		JSONArray parseArray = JSON.parseArray(value);
		ArrayList l = new ArrayList();
		for (Object object : parseArray) {

			if (object instanceof JSONObject) {
				System.out.println("-------------->" + object.toString());
				JSONObject parseJson = parseJson(object.toString());
				l.add(parseJson);
			} else if (object instanceof JSONArray) {
				parseJsonArray((Entry<String, Object>) object);
			} else {
				Object[] jsonArrayToArray = jsonArrayToArray(parseArray);
				System.out.println("simple:" + jsonArrayToArray.getClass().getSimpleName());
				changeData(entry, null, jsonArrayToArray, jsonArrayToArray.getClass().getSimpleName());
				
				break;
			}
		}
		if(l.size() > 0)
			entry.setValue(l);
	}

	public Object[] jsonArrayToArray(JSONArray parseArray) {
		String simpleName = parseArray.get(0).getClass().getSimpleName();

		System.out.println(simpleName);
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
