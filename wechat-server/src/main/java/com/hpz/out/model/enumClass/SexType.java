package com.hpz.out.model.enumClass;


/**
 * Created by mao on 2015/7/10.
 */
public enum SexType {
    WOMEN(0),   //0表示女
    MAN(1);     //1表示男


    private int type;
    SexType(int va) {
        type = va;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static SexType fromValue(int v){
        for(SexType sexType:values()){
            if(sexType != null && sexType.getType()==v){
                return sexType;
            }
        }
        return null;
    }
}
