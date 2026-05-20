package landry.paysted.dtos;

public class ApiResponse {
    private String message;
    private Object object;

    

    public ApiResponse(String message, Object object) {
        this.message = message;
        this.object = object;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }


    
}
