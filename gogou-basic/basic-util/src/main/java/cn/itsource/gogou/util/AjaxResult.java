package cn.itsource.gogou.util;

public class AjaxResult {
    private Boolean success=true;
    private String message;
    private Object resultObject;
    private AjaxResult(){}
    public static AjaxResult getAjaxResult(){
        return new AjaxResult();
    }
    public Boolean getSuccess() {
        return success;
    }

    public AjaxResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public AjaxResult setResultObject(Object resultObject) {
        this.resultObject = resultObject;
        return this;
    }
}
