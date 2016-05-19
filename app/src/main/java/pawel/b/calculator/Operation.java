package pawel.b.calculator;

/**
 * Created by Paweł on 19.05.2016.
 */
public enum Operation {

    NONE(""), ADD("+"), SUB("-");

    private final String key;

    private Operation(String key){
        this.key = key;
    }

    public static Operation operationFromKey(String key){
        for (Operation operation : values()) {
            if(operation.key.equals(key)){
                return operation;
            }
        }
        return NONE;

    }
}
