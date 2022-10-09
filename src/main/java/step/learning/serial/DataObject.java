package step.learning.serial;
import java.io.Serializable;
public class DataObject implements Serializable {
    private int privateField;
    protected float protectedField;
    public String publicField;
    private  transient String transFields; // Не сериализуется
    public DataObject(Object... args) {
        // Variadic functions - с производным кол-вом параметров
        // args - object[], not null, без аргументов - object[0]
        // !! параметры по умолчанию в джаве не поддерживаются
        privateField = args.length > 0 ? (int)args[0] : -1;
        protectedField = args.length > 1 ? (float)args[1] : -1;
        publicField = args.length > 2 ? (String)args[2] : "-";
        transFields = args.length > 3 ? (String)args[3] : "-";
    }
    @Override public String toString() {
        return String.format("{private:'%d', protected:'%f', public:'%s', trans='%s'}", privateField, protectedField, publicField, transFields);
    }
}