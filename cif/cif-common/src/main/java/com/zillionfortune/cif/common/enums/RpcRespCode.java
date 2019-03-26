package com.zillionfortune.cif.common.enums;

/**
 * 
 * @author zhengrunlong
 * @date 2016/11/10
 */
public enum RpcRespCode {
	
	SUCCESS(200,"成功"),
	FAIL(400,"失败");
	
	private int value;
	private String desc;

    RpcRespCode(int value,String desc){
        this.value=value;
        this.desc=desc;
    }

    public int value(){
        return value;
    }

    public String desc(){
        return desc;
    }
    
    public static String getDesc(int value){
    	for(RpcRespCode v : RpcRespCode.values()){
    		if(v.value == value){
    			return v.desc();
    		}
    	}
    	return null;
    }
    
}
