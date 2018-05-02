package com.mapi.bomb;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mapi.util.WeightConfigUtil;

public class WeightHandler {

	
	public  Object weightChoice(){
		
		HashMap<Object, Integer> weightmap = getWeight();
		
		
		ArrayList<HashMap<String, Comparable>> choiceList=new ArrayList<HashMap<String, Comparable>>(); //选择器列表，剔除不需要选择的选项，权重为0，不进选择列表。转变权重为上下限值。
        
        Integer minR=0,maxR=-1;    //上下限
        for(Map.Entry<Object, Integer> temp:weightmap.entrySet()){
            int weight=temp.getValue();
            if(weight==0) continue;
            minR=maxR+1;
            maxR=minR+weight-1;
            HashMap hashCh=new HashMap();
            hashCh.put("weightName", temp.getKey());
            hashCh.put("minR", minR); //下限
            hashCh.put("maxR", maxR); //上限
            choiceList.add(hashCh);
        }
        //System.out.println(JSON.toJSON(choiceList));
        Random random = new Random(); 
        int index =random.nextInt(maxR+1);  //产生大于等于0，小于maxR+1的整数
        //System.out.println(index);
        for(HashMap temp:choiceList){
            Integer mini=(Integer)temp.get("minR");
            Integer maxi=(Integer)temp.get("maxR");
            if( mini<=index && index<=maxi) {
                Object choName=temp.get("weightName");
               
                return choName;
            } 
        }
		return null;
	}

	private HashMap<Object, Integer> getWeight() {
		
		String weight = WeightConfigUtil.getConfigVal("action_weight");
		
		JSONObject parseObject = JSON.parseObject(weight);
		int del_weight = parseObject.getInteger("DEL");
		int type_weight = parseObject.getInteger("CHANGE_TYPE");
		int value_weight = parseObject.getInteger("CHANGE_VALUE");
		int empty_weight = parseObject.getInteger("EMPTY");
		
		HashMap<Object, Integer> weightMap = new HashMap<>();
		weightMap.put(ActionWeight.DEL, del_weight);
		weightMap.put(ActionWeight.CHANGE_TYPE, type_weight);
		weightMap.put(ActionWeight.CHANGE_VALUE, value_weight);
		weightMap.put(ActionWeight.EMPTY, empty_weight);
		
		return weightMap;
	}
	
	
}
