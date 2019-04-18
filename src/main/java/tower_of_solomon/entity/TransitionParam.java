package tower_of_solomon.entity;

import java.io.Serializable;
import java.util.HashMap;

public class TransitionParam implements Serializable {

    HashMap<String, String> stringParam;
    HashMap<String, Integer> IntParam;

    /**
     * コンストラクタ
     */
    public TransitionParam() {
        this.stringParam = new HashMap<>();
        this.IntParam = new HashMap<>();
    }

    /**
     * 値セット(文字列)
     * @param key
     * @param value
     */
    public TransitionParam put(String key, String value) {
        this.stringParam.put(key, value);
        return this;
    }

    /**
     * 値セット(数値)
     * @param key
     * @param value
     */
    public TransitionParam put(String key, int value) {
        this.IntParam.put(key, value);
        return this;
    }

    public int getInt(String key) {
        if (!this.IntParam.containsKey(key)) {
            return -1;
        }
        return this.IntParam.get(key);
    }

    public String getString(String key) {
        if (!this.stringParam.containsKey(key)) {
            return "";
        }
        return this.stringParam.get(key);
    }
}
