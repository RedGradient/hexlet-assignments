package exercise;

import java.util.ArrayList;

class SafetyList {
    // BEGIN
    private final ArrayList<Integer> list;

    SafetyList() {
        this.list = new ArrayList<>();
    }

    public synchronized void add(int value) {
        list.add(value);
    }

    public Integer get(int index) {
        return list.get(index);
    }

    public int getSize() {
        return list.size();
    }
    // END
}
